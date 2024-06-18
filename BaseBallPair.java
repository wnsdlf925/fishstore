import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BaseBallPair {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();


        int[] numArr = generateRandomNumber(random);

        System.out.println("세 자리 숫자를 맞춰보세요. (중복X).");

        int attempts = 0;
        while (true) {
            attempts++;

            int[] inputArr = getUserInput(scanner);

            int strikes = countStrikes(numArr, inputArr);
            int balls = countBalls(numArr, inputArr);

            System.out.println(strikes + " Strike(s) " + balls + " Ball(s)");

            if (strikes == 3) {
                System.out.println("정답!" + Arrays.toString(numArr) + attempts + "번 째 성공!");
                break;
            }
        }
        scanner.close();
    }

    private static int[] generateRandomNumber(Random random) {
        int[] digits = new int[3];
        boolean[] used = new boolean[10];

        for (int i = 0; i < 3; i++) {
            int rand;

            do {
                rand = random.nextInt(10);
            } while (used[rand]);

            digits[i] = rand;
            used[rand] = true;
        }

        return digits;
    }

    private static int[] getUserInput(Scanner scanner) {
        int[] inputArr = new int[3];
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print("입력 :");
            int input = scanner.nextInt();

            if (isValidGuess(input)) {
                inputArr = numberToArray(input);
                isValidInput = true;
            } else {
                System.out.println("잘못된 입력입니다. 중복없는 3자리 숫자를 입력해주세요.");
            }
        }

        return inputArr;
    }

    private static boolean isValidGuess(int guess) {
        if (guess < 100 || guess > 999) {
            return false;
        }

        int[] digits = numberToArray(guess);
        return digits[0] != digits[1] && digits[0] != digits[2] && digits[1] != digits[2];
    }

    private static int[] numberToArray(int number) {
        int[] array = new int[3];
        array[0] = number / 100;
        array[1] = (number / 10) % 10;
        array[2] = number % 10;
        return array;
    }

    private static int countStrikes(int[] numArr, int[] inputArr) {
        int strikes = 0;
        for (int i = 0; i < 3; i++) {
            if (numArr[i] == inputArr[i]) {
                strikes++;
            }
        }
        return strikes;
    }

    private static int countBalls(int[] numArr, int[] inputArr) {
        int balls = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j && numArr[i] == inputArr[j]) {
                    balls++;
                }
            }
        }
        return balls;
    }
}