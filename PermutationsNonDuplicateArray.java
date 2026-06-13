import java.util.Arrays;

public class PermutationsNonDuplicateArray {
    
    // When:
    // - Permutations 
    // - All arrangements
    // - Generate every ordering
    public static void main(String[] args) {
        String[] arr = { "A", "B", "C" };
        permute(arr);
    }

    public static void permute(String[] arr) {
        backtrack(arr, 0);
    }

    private static void backtrack(String[] arr, int start) {
        // System.out.println();
        // System.out.println("arr is = "+ Arrays.toString(arr) + ", start is = "+ start);
        if (start == arr.length) {
            System.out.println("ANSWER IS =>" + Arrays.toString(arr));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            // System.out.println("i is: " + i);
            // System.out.println("swap("+Arrays.toString(arr)+", "+start+", "+i+");");
            swap(arr, start, i);
            // System.out.println("After first swap: " + Arrays.toString(arr));
            // System.out.println("backtrack("+Arrays.toString(arr)+", "+start+" + 1)");
            backtrack(arr, start + 1);
            // System.out.println("swap("+Arrays.toString(arr) + ", " + start + ", " + i + ");");
            swap(arr, start, i); // backtrack
            // System.out.println("After second swap: " + Arrays.toString(arr));
        }
    }

    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}