package bullscows;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Code {
    private String value;
    private String description;
    private String validCodeCharacters = "0123456789abcdefghijklmnopqrstuvwxyz";

    Code(int requiredLength, int requiredNumberOfCharacters) {
        value = generateCode(requiredLength, requiredNumberOfCharacters);
        description = generateCodeDescription(requiredLength, requiredNumberOfCharacters);
    }

    public String getValue() {
        return value;
    }

    public String getSecretDescription() {
        return description;
    }

    private String generateCodeDescription(int requiredLength, int requiredNumberOfCharacters) {
        String maskedCode = generateMaskedCode(requiredLength);
        String includedCharacters = getIncludedCharacters(requiredNumberOfCharacters);
        return String.format("%s %s.", maskedCode, includedCharacters);
    }

    private String generateMaskedCode(int requiredLength) {
        StringBuilder maskedCode = new StringBuilder(requiredLength);
        for (int i = 0; i < requiredLength; i++) {
            maskedCode.append("*");
        }
        return maskedCode.toString();
    }

    private String getIncludedCharacters(int requiredNumberOfCharacters) {
        StringBuilder includedCharacters = new StringBuilder();
        if (requiredNumberOfCharacters <= 10) {
            includedCharacters.append(String.format("(0-%d)", requiredNumberOfCharacters - 1));
        } else {
            includedCharacters.append(String.format("(0-9, a-%s)", validCodeCharacters.charAt(requiredNumberOfCharacters - 1)));
        }
        return includedCharacters.toString();
    }

    private String generateCode(int requiredCodeLength, int requiredNumberOfCharacters) {
        List<Character> charactersToUse = new ArrayList(requiredNumberOfCharacters);
        for (int i = 0; i < requiredCodeLength; i++) {
            charactersToUse.add(validCodeCharacters.charAt(i));
        }
        Collections.shuffle(charactersToUse);
        StringBuilder code = new StringBuilder(charactersToUse.size());
        for (char c : charactersToUse) {
            code.append(c);
        }
        return code.toString();
    }
}
