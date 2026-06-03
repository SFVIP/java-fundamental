import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNotRepeatingChar {
    public static void main(String[] args) {
        Character b = firstNonRepeatingChar("abac");
        assert (b == 'b');
        
        Character c = firstNonRepeatingChar("aabbcdde");
        assert(c == 'c');
        
        Character d = firstNonRepeatingChar("aabbccd");
        assert (d == 'd');
        
        Character firstNull = firstNonRepeatingChar("aabbcc");
        assert(firstNull == null);
        
        Character l = firstNonRepeatingChar("leetcode");
        assert (l == 'l');
        
        Character emptyStr = firstNonRepeatingChar("");
        assert (emptyStr == null);
        
        Character secondNull = firstNonRepeatingChar(null);
        assert (secondNull == null);
        System.out.println("all good");
    }
    
    public static Character firstNonRepeatingChar(String input) {
        if (input == null || input.length() == 0) {
            return null;
        }
        
        // for repeated characters appear consecutively
        /*
        if (input.charAt(0) != input.charAt(1)) {
            return input.charAt(0);
        }
        
        Character c = null;
        boolean isChangingWords = false;
        boolean isBothCharSame = false;
        for (int i = 2; i < input.length(); i++) {
            isBothCharSame = input.charAt(i - 1) == input.charAt(i);
            if (isBothCharSame) {
                isChangingWords = false;
                continue;
            }
            if (!isChangingWords) {
                isChangingWords = true;
                continue;
            } else {
                return input.charAt(i - 1);
            }
        }
        if (!isBothCharSame && isChangingWords) {
            c = input.charAt(input.length() - 1);
        }
        return c;
        */
        
        Map<Character, Integer> charCountMap = new LinkedHashMap<>();
        for (char c : input.toCharArray()) {
            charCountMap.compute(c, (k, v) -> v == null ? 1 : ++v);
        }
        
        return charCountMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .findFirst()
                .map(entry -> entry.getKey())
                .orElse(null);
    }
}
