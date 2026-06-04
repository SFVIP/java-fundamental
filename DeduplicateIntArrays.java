import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeduplicateIntArrays {
    public static void main(String[] args) {
        List<int[]> input = Arrays.asList(
            new int[] {1, 2},
            new int[] {1, 3, 2},
            new int[] {1, 2}
        );
        
        List<int[]> result = dedup(input);
        System.out.print("[");
        for (int[] arr : result) {
            System.out.print(Arrays.toString(arr));
        }
        System.out.println("]");
    }

    private static List<int[]> dedup(List<int[]> input) {
        List<int[]> storage = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        
        for (int[] arr : input) {
            String s = Arrays.toString(arr);
            if (seen.add(s)) {
                storage.add(arr);
            }
        }
        
        return storage;
    }
}