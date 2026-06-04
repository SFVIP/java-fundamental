import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

record User(int id, String name, String email) {}

public class JavaArrayConversion {
    
    public static void main(String[] args) {
        // 1. Arrays.asList()
        String[] strArr = {"A", "B"};
        List<String> listOfStrings = Arrays.asList(strArr);  // create fix size array, [A, B]
        // 8. Arrays.asList() Backed by Original Array
        strArr[0] = "X";    // Arrays.asList() backed by original array, [X, B]
        
        // 2. List.of()
        List<Integer> listOfIntegers = List.of(1,2,3);  // Immutable and reject null, not backed by array
        // int[] intArr = listOfIntegers.stream()
        //         .mapToInt(Integer::intValue)
        //         .toArray();
        int[] intArr = new int[listOfIntegers.size()];
        // for (int i = 0; i < listOfIntegers.size(); i++) {
        //     intArr[i] = listOfIntegers.get(i);
        // }
        int i = 0;
        for (Integer v : listOfIntegers) {
            intArr[i++] = v;
        }
        
        // 5. int[] → List<Integer>
        List<Integer> listOfIntegers2 = Arrays.stream(intArr)
                .boxed()
                .toList();
        
        // 6. List<Integer> → int[]
        List<Integer> listOfIntegers3 = IntStream.of(intArr)
                .boxed()
                .toList();
        
        // 3. toArray(new String[0])
        // 4. toArray(String[]::new)
        // String[] strArr2 = listOfStrings.toArray(new String[0]);
        String[] strArr2 = listOfStrings.toArray(String[]::new);        // cleaner and often
        Integer[] integerArr = listOfIntegers.toArray(Integer[]::new);  // slightly more efficient
        // Integer[] integerArr = listOfIntegers.toArray(new Integer[0]);
        
        // 9. int[] → List<Integer[]> using stream
        List<int[]> inputs = Arrays.asList(new int[] { 1, 2 }, new int[] { 3, 4, 5 }, new int[] { 1, 2 });
        List<Integer[]> inputsIntegers = inputs.stream()
                .map(arr -> Arrays.stream(arr).boxed().toArray(Integer[]::new))
                .toList();
        // 10. List<Integer[]> → int[] using stream
        List<int[]> inputs2 = inputsIntegers.stream()
                .map(arr -> Arrays.stream(arr).mapToInt(Integer::intValue).toArray())
                .toList();
        
        // 9. int[] → List<Integer> not using stream
        List<Integer[]> inputsIntegers3 = new ArrayList<>();
        for (int[] arr : inputs) {
            Integer[] converted = new Integer[arr.length];
            for (int j = 0; j < arr.length; j++) {
                converted[j] = arr[j];  // auto-boxing
            }
            inputsIntegers3.add(converted);
        }
        
        // 10. List<Integer[]> → List<int[]> not using stream
        List<int[]> listOfIntArrays = new ArrayList<>();
        for (Integer[] arr : inputsIntegers3) {
            int[] container = new int[arr.length];
            for(int k = 0; k < arr.length; k++) {
                container [k] = arr[k]; // auto-unboxing
            }
            listOfIntArrays.add(container);
        }
        
        Integer[] arr = {1, null, 3};
        int x = arr[1];
    }
    
    public static List<int[]> dedup(List<int[]> inputs) {
        List<int[]> result = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        
        for (int[] input : inputs) {
            String s = String.valueOf(input);
            if (seen.add(s)) {
                result.add(input);
            }
        }
        
        return result;
    }
}
// Test Case:
// List<int[]> inputs = Arrays.asList(new int[] {1,2}, new int[] {3,4,5}, new int[] {1,2});
// List<int[]> exepectedOutputs = Arrays.asList(new int[] {1,2}, new int[] {3,4,5});