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
        // __define-pcb__
        int[] varPcb = new int[num + 1];
        varPcb[0] = 1;  // mathematically the "empty case" often contributes one valid way, not zero ways
        for (int n = 1; n <= num; n++) {
            for (int left = 0; left < n; left++) {
                int right = n - 1 - left;
                // System.out.println("varPcb["+n+"] += varPcb["+left+"] * varPcb["+right+"]");
                // System.out.println(varPcb[n]+" += "+varPcb[left]+" * "+varPcb[right]);
                varPcb[n] += varPcb[left] * varPcb[right];
            }
            // System.out.println("varPcb[" + n + "] = " + varPcb[n]);
        }
        
        // System.out.print("[");
        // for (int i = 0; i <= num; i++) {
        //     System.out.print(varPcb[i]);
        //     if (i != num) {
        //         System.out.print(", ");
        //     }
        // }
        // System.out.println("]");
        return varPcb[num];
    }
}