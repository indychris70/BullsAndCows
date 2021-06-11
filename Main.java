package bullscows;

import java.util.Scanner;

public class Main {
    private enum Messages {
        START_GAME("Hello Professor. Would you like to play a game?"),
        GAME_WON("Congratulations! You guessed the secret code."),
        TURN_NUMBER("Turn %s:"),
        ERROR("Error"),
        PROMPT_CODE_LENGTH("Input the length of the secret code:"),
        PROMPT_NUMBER_SYMBOLS("Input the number of possible symbols in the code:"),
        SECRET_READY("The secret is prepared: %s");

        String text;

        Messages(String text) {
            this.text = text;
        }

        public void print(String... strings) {
            System.out.println(String.format(text, strings));
        }
    }
    public static void main(String[] args) {
        final int MAX_CODE_LENGTH = 36;
        Scanner scanner = new Scanner(System.in);

        Messages.PROMPT_CODE_LENGTH.print();
        int codeLength = scanner.nextInt();
        if (codeLength <= MAX_CODE_LENGTH) {
            Messages.PROMPT_NUMBER_SYMBOLS.print();
            int numberOfSymbols = scanner.nextInt();
            Code secretCode = new Code(codeLength, numberOfSymbols);
            System.out.println(secretCode.getValue());
            Messages.SECRET_READY.print(secretCode.getSecretDescription());
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
