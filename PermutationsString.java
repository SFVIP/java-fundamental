public class PermutationsString {
    
    static int count;
    
    public static void main(String[] args) {
        permutations("1234");
        System.out.println("Answer is = " + count);
    }
    
    public static void permutations(String str) {
        permutations("", str);
    }
    
    private static void permutations(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            System.out.println(prefix);
            count++;
        }
        else {
            for (int i = 0; i < n; i++) {
                // System.out.println("prefix + str.charAt("+i+"), str.substring(0, "+i+") + str.substring("+i+"+1, "+n+")");
                // System.out.println("permutations("+prefix + str.charAt(i)+", "+str.substring(0, i) + str.substring(i+1, n)+")");
                permutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
            }
        }
    }
}
