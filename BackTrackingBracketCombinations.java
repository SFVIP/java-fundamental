public class BackTrackingBracketCombinations {
    public static void main(String[] args) {
        System.out.println(BracketCombinations(2)); // 2
        System.out.println(BracketCombinations(3)); // 5
        System.out.println(BracketCombinations(4)); // 14
        System.out.println(BracketCombinations(5)); // 42
    }
    
    public static int BracketCombinations(int num) {
        int[] count = new int[1];
        backtrack(num, 0, 0, count);
        return count[0];
    }

    private static void backtrack(int num, int open, int close, int[] count) {
        if (open == num && close == num) {
            count[0]++;
            return;
        }

        // Add '(' if we still have open brackets left
        if (open < num) {
            backtrack(num, open + 1, close, count);
        }

        // Add ')' only if it will not make the string invalid
        if (close < open) {
            backtrack(num, open, close + 1, count);
        }
    }
}
