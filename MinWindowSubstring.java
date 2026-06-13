import java.util.HashMap;
import java.util.Map;

public class MinWindowSubstring {
  public static void main(String[] args) {
    // keep this function call here
    System.out.print(MinWindowSubstring(new String[] { "ahffaksfajeefaajsne", "jefaa" }));
  }

  public static String MinWindowSubstring(String[] strArr) {
    String n = strArr[0];
    String k = strArr[1];

    // Store required character counts from K
    Map<Character, Integer> required = new HashMap<>();
    for (char c : k.toCharArray()) {
      required.put(c, required.getOrDefault(c, 0) + 1);
    }

    Map<Character, Integer> window = new HashMap<>();
    int left = 0;
    int matched = 0;

    int minLength = Integer.MAX_VALUE;
    int minStart = 0;

    for (int right = 0; right < n.length(); right++) {
      char rightChar = n.charAt(right);

      window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);

      // Count this character only if it is still needed
      if (required.containsKey(rightChar)
          && window.get(rightChar) <= required.get(rightChar)) {
        matched++;
      }

      // When matched == k.length(), current window contains all chars in K
      while (matched == k.length()) {
        int currentLength = right - left + 1;
        if (currentLength < minLength) {
          minLength = currentLength;
          minStart = left;
        }
        char leftChar = n.charAt(left);
        window.put(leftChar, window.get(leftChar) - 1);

        // If removing this char makes window invalid, reduce matched to break out of while loop
        if (required.containsKey(leftChar)
            && window.get(leftChar) < required.get(leftChar)) {
          matched--;
        }
        left++;
      }
    }

    return n.substring(minStart, minStart + minLength);
  }
}
