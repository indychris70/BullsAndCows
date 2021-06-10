package bullscows;

public class Game {
    private final String code;
    private int cows;
    private int bulls;
    private String result;

    Game(String code) {
        this.code = code;
    }

    public boolean isOver() {
        return bulls == code.length();
    }

    public void printResult() {
        System.out.println(result);
    }

    public void submitGuess(String guess) {
        reset();
        for (int i = 0; i < guess.length(); i++) {
            cows = isCow(i, guess.charAt(i)) ? cows + 1 : cows;
            bulls = isBull(i, guess.charAt(i)) ? bulls + 1 : bulls;
        }
        buildResult();
    }

    private void reset() {
        cows = 0;
        bulls = 0;
    }

    private boolean isCow(int index, char c) {
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == c && index != i) {
                return true;
            }
        }
        return false;
    }

    private boolean isBull(int index, char c) {
        return code.charAt(index) == c;
    }

    private void buildResult() {
        StringBuilder statement = new StringBuilder("Grade: ");
        statement.append(hasCows() ? String.format("%d cow(s)", cows) : "");
        statement.append(hasCows() && hasBulls() ? " and " : "");
        statement.append(hasBulls() ? String.format("%d bull(s)", bulls) : "");
        statement.append(!hasCows() && !hasBulls() ? "None" : "");
        statement.append(".");
        result = statement.toString(); // .append(String.format("The secret code is %s.", code)).toString();
    }

    private boolean hasCows() {
        return cows > 0;
    }

    private boolean hasBulls() {
        return bulls > 0;
    }
}
