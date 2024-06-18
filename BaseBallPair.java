import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BaseBallPair {

    public static void main(String[] args) {
        int[] answer = new int[3];
        Set<Integer> set = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        int round = 1;
        int index = 0;

        while (index < 3) {
            int num = (int) (Math.random() * 10);
            if (!set.contains(num)) {
                answer[index] = num;
                set.add(num);
                index++;
            }
        }

        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");

        while (true) {
            System.out.print(round + "번째 시도: ");
            String input = sc.nextLine();

            if (input.length() != 3 || !input.matches("[0-9]+")) {
                System.out.println("잘못된 입력입니다. 3자리 숫자를 입력하세요.");
                continue;
            }


            int[] player = new int[3];
            for (int i = 0; i < 3; i++) {
                player[i] = Integer.parseInt(String.valueOf(input.charAt(i)));
            }

            int strike = 0;
            int ball = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (player[i] == answer[j]) {
                        if (i == j) {
                            strike++;
                        } else {
                            ball++;
                        }
                    }
                }
            }

            if (strike == 3) {
                System.out.println(round + "번만에 맞히셨습니다.");
                System.out.println("게임을 종료합니다.");
                break;
            } else {
                System.out.println(ball + "B" + strike + "S");
            }

            round++;
        }

        sc.close();
    }
}