package bullscows;

public class Code {
    private String value = "";

    Code(int valueLength) {
        value = generateCode(valueLength, value);
    }

    public String getValue() {
        return value;
    }

    /**
     * Generates a hard to predict ("pseudo-random") String of digit characters (of length VALUE_LENGTH)
     * derived from the current System.nanoTime().
     *
     * VALUE_LENGTH: The required length of the code to be generated
     * INITIAL_VALUE: A partially generated code can be passed in to use as the starting point. For recursive calls.
     *
     * Process:
     *  - Generate SEED: a String representation of a call to System.nanoTime()
     *  - Iterate over SEED characters in reverse order. For each iteration:
     *    - TARGET_INDEX: The Integer value that the SEED character at the current iteration's index represents.
     *      - Possible values of TARGET_INDEX: 0 to 9
     *    - ADJUSTED_INDEX: Adjust the BASE_INDEX so the characters sampled are from the last 10 index positions
     *      - Since the System.nanoTime() digits change more frequently, this range is more variable in case of recursive calls
     *      - Possible values of ADJUSTED_INDEX: 6 to 15 (assuming SEED.LENGTH() == 16)
     *    - If the SEED character at ADJUSTED_INDEX is not already present in TEMP_VALUE, it is appended to TEMP_VALUE
     *  - If the length of TEMP_VALUE is less than VALUE_LENGTH when the loop completes,
     *                  make a recursive call passing in TEMP_VALUE as INITIAL_VALUE
     *  - If TEMP_VALUE begins with '0', make recursive call passing in substring of TEMP_VALUE omitting index 0;
     *    - TEMP_VALUE can not begin with zero
     *  - Finally, TEMP_VALUE is returned.
     */
    private String generateCode(int valueLength, String initialValue) {
        String seed = Long.toString(System.nanoTime());
        StringBuilder tempValue = new StringBuilder(initialValue);
        int baseIndex;
        int adjustedIndex;
        char valueChar;
        // generate value with as many unique characters as possible
        for (int i = seed.length() - 1; i >= 0; i--) {
            baseIndex = Integer.parseInt(String.valueOf(seed.charAt(i)));
            adjustedIndex = baseIndex + seed.length() - 10;
            valueChar = seed.charAt(adjustedIndex);
            if (tempValue.length() < valueLength && tempValue.toString().indexOf(valueChar) == -1) {
                tempValue.append(valueChar);
            }
        }
        // if initial loop did not build a value of required length, make recursive call
        while (tempValue.length() < valueLength) {
            tempValue = new StringBuilder(generateCode(valueLength, tempValue.toString()));
        }
        while (tempValue.charAt(0) == '0') {
            tempValue = new StringBuilder(generateCode(valueLength, tempValue.substring(1).toString()));
        }

        return tempValue.toString();
    }
}
