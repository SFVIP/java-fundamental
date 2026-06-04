import java.util.ArrayDeque;
import java.util.Map;

public class ValidateParentheses {
    private static final Map<Character, Character> pairs = Map.of(
        ')', '(',
        ']', '[',
        '}', '{',
        '>', '<',
        '/', '\\'
    );
    
    public static void main(String[] args) {
        assert isValid("")          == true;
        assert isValid("()")        == true;
        assert isValid("()[]{}")    == true;
        assert isValid("(]")        == false;
        assert isValid("([)]")      == false;
        assert isValid("{[]}")      == true;
        assert isValid("{{[]()}}")    == true;
        System.out.println("All good");
    }
    
    public static boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        ArrayDeque<Character> dq = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            Character start = pairs.get(c);
            if (dq.size() == 0 || start == null) {
                dq.add(c);
                continue;
            }
            
            if (dq.peekLast() == start) {
                dq.pollLast();
                continue;
            }
        }
        return dq.size() == 0;
    }
}
