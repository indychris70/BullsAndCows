package bullscows;

import java.util.Scanner;

public class Main {
    private enum Messages {
        START_GAME("Okay, let's start a game!"),
        GAME_WON("Congratulations! You guessed the secret code."),
        TURN_NUMBER("Turn %s:"),
        ERROR("Error");

        String text;

        Messages(String text) {
            this.text = text;
        }

        public void print(String... strings) {
            System.out.println(String.format(text, strings));
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int codeLength = scanner.nextInt();
        if (codeLength <= 10) {
            Code secretCode = new Code(codeLength);
            Grader grader = new Grader(secretCode.getValue());
            int turn = 1;

            Messages.START_GAME.print();
            do {
                Messages.TURN_NUMBER.print(Integer.toString(turn));
                grader.submitGuess(scanner.nextLine());
                grader.printResult();
                turn++;
            } while (!grader.gameOver());
            Messages.GAME_WON.print();
        } else {
            Messages.ERROR.print();
        }
    }
}
