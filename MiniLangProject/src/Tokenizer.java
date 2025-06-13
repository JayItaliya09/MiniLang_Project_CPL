import java.util.*;
import java.util.regex.*;

public class Tokenizer {
    private static final Pattern TOKEN_PATTERN = Pattern.compile(
            "\\s*(let|if|then|else|end|loop|from|to|do|print|==|=|\"[^\"]*\"|[a-zA-Z_][a-zA-Z0-9_]*|\\d+)"
    );

    private List<String> tokens;
    private int position;

    public Tokenizer(String input) {
        tokens = new ArrayList<>();
        Matcher matcher = TOKEN_PATTERN.matcher(input);
        while (matcher.find()) {
            tokens.add(matcher.group().trim());
        }
        position = 0;
    }

    public String peek() {
        if (position < tokens.size()) {
            return tokens.get(position);
        }
        return null;
    }

    public String next() {
        return position < tokens.size() ? tokens.get(position++) : null;
    }

    public boolean hasNext() {
        return position < tokens.size();
    }

    public void expect(String expected) {
        String actual = next();
        if (!expected.equals(actual)) {
            throw new RuntimeException("Expected: " + expected + " but found: " + actual);
        }
    }
}
