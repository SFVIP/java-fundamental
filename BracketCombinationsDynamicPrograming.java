public class BracketCombinationsDynamicPrograming {

    // Catalan Number, APPLICATION WHEN:
    // - Valid parenthesis combinations
    // - Unique BST count
    // - Mountain ranges 
    // - Non-crossing handshakes
    public static void main(String[] args) {
        // System.out.println(BracketCombinations(2)); // 2
        // System.out.println(BracketCombinations(3)); // 5
        System.out.println(BracketCombinations(4)); // 14
        // System.out.println(BracketCombinations(5)); // 42
    }
    
    public static int BracketCombinations(int num) {
        // Catalan DP table
        int[] dp = new int[num + 1];
        dp[0] = 1;  // mathematically the "empty case" often contributes one valid way, not zero ways
        for (int n = 1; n <= num; n++) {
            for (int left = 0; left < n; left++) {
                int right = n - 1 - left;
                // System.out.println("dp["+n+"] += dp["+left+"] * dp["+right+"]");
                // System.out.println(dp[n]+" += "+dp[left]+" * "+dp[right]);
                dp[n] += dp[left] * dp[right];
            }
            // System.out.println("dp[" + n + "] = " + dp[n]);
        }
        
        // System.out.print("[");
        // for (int i = 0; i <= num; i++) {
        //     System.out.print(dp[i]);
        //     if (i != num) {
        //         System.out.print(", ");
        //     }
        // }
        // System.out.println("]");
        return dp[num];
    }
}