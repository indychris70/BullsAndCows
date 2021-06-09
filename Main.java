package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String guess = scanner.nextLine();
        Grader grader = new Grader(guess, "1234");
        grader.printResult();
    }
}
