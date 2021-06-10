package bullscows;

import java.util.Collections;
import java.util.Arrays;
import java.util.List;

public class Code {
    private String value;

    Code(int requiredLength) {
        value = generateCode(requiredLength);
    }

    public String getValue() {
        return value;
    }

    private String generateCode(int requiredLength) {
        List<Character> coll = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8','9');
        Collections.shuffle(coll);
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < requiredLength; i++) {
            code.append(coll.get(i));
        }
        return code.toString();
    }
}
