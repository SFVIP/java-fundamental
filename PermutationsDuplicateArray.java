import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PermutationsDuplicateArray {
    public static void main(String[] args) {
        String[] arr = { "A", "A", "B", "C" };
        permute(arr);
    }

    public static void permute(String[] arr) {
        backtrack(arr, 0);
    }

    private static void backtrack(String[] arr, int start) {
        if (start == arr.length) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        Set<String> used = new HashSet<>();
        for (int i = start; i < arr.length; i++) {
            if (!used.add(arr[i])) {
                continue;
            }
            swap(arr, start, i);
            backtrack(arr, start + 1);
            swap(arr, start, i);
        }
    }

    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
