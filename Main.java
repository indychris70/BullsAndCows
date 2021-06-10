package bullscows;

import java.util.Scanner;

public class Main {
    private enum Messages {
        START_GAME("Hello Professor. Would you like to play a game?"),
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
            Game game = new Game(secretCode.getValue());
            int turn = 1;

            Messages.START_GAME.print();
            do {
                Messages.TURN_NUMBER.print(Integer.toString(turn));
                game.submitGuess(scanner.nextLine());
                game.printResult();
                turn++;
            } while (!game.isOver());
            Messages.GAME_WON.print();
        } else {
            Messages.ERROR.print();
        }
    }
}
