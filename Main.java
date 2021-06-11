package bullscows;

import java.util.Scanner;

public class Main {
    private enum Messages {
        START_GAME("Hello Professor. Would you like to play a game?"),
        GAME_WON("Congratulations! You guessed the secret code."),
        TURN_NUMBER("Turn %s:"),
        PROMPT_CODE_LENGTH("Input the length of the secret code:"),
        PROMPT_NUMBER_SYMBOLS("Input the number of possible symbols in the code:"),
        SECRET_READY("The secret is prepared: %s"),
        ERROR_INVALID_LENGTH("Error: it's not possible to generate a code with a length of %s with %s unique symbols."),
        ERROR_INVALID_NUMBER("Error: '%s' isn't a valid number."),
        ERROR_TOO_MANY_SYMBOLS("Error: maximum number of possible symbols in the code is %s (0-9, a-z)."),
        ERROR_INVALID_GUESS_LENGTH("Error: guess must be exactly %s characters long"),
        ERROR_INPUT_TOO_SMALL("Error: code parameters must be greater than zero.");

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

        Messages.PROMPT_CODE_LENGTH.print();
        String codeLengthInput = scanner.nextLine();

        if (inputIsValid(codeLengthInput)) {
            int codeLength = Integer.parseInt(codeLengthInput);

            Messages.PROMPT_NUMBER_SYMBOLS.print();
            String numberOfSymbolsInput = scanner.nextLine();

            if (inputIsValid(numberOfSymbolsInput)) {
                int numberOfSymbols = Integer.parseInt(numberOfSymbolsInput);

                if (numberOfSymbols < codeLength) {
                    Messages.ERROR_INVALID_LENGTH.print(codeLengthInput, numberOfSymbolsInput);
                } else {
                    Code secretCode = new Code(codeLength, numberOfSymbols);
                    Messages.SECRET_READY.print(secretCode.getSecretDescription());
                    Game game = new Game(secretCode.getValue());
                    int turn = 1;

                    Messages.START_GAME.print();
                    do {
                        Messages.TURN_NUMBER.print(Integer.toString(turn++));
                        String guess = scanner.nextLine();
                        if (guessIsValid(guess, codeLength)) {
                            game.submitGuess(guess);
                            game.printResult();
                        } else {
                            break;
                        }
                    } while (!game.isWon());
                    if (game.isWon()) {
                        Messages.GAME_WON.print();
                    }
                }
            }
        }
    }

    private static boolean guessIsValid(String guess, int codeLength) {
        if (guess.length() != codeLength) {
            Messages.ERROR_INVALID_GUESS_LENGTH.print(Integer.toString(codeLength));
            return false;
        }
        return true;
    }

    private static boolean inputIsValid(String input) {
        if (!stringIsValidInt(input)) {
            Messages.ERROR_INVALID_NUMBER.print(input);
            return false;
        }
        int value = Integer.parseInt(input);
        if (value < 1) {
            Messages.ERROR_INPUT_TOO_SMALL.print();
            return false;
        }
        if (value > Code.getMaxCodeLength()) {
            Messages.ERROR_TOO_MANY_SYMBOLS.print(Integer.toString(Code.getMaxCodeLength()));
            return false;
        }
        return true;
    }

    private static boolean stringIsValidInt(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
