package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int codeLength = scanner.nextInt();
        String message = "Error";
        if (codeLength <= 10) {
            Code secretCode = new Code(codeLength);
            message = String.format("The random secret number is %s.", secretCode.getValue());
        }
        System.out.println(message);
    }
}
