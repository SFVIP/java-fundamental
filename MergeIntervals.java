import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public static void main(String[] args) {
        List<int[]> intervalsA = Arrays.asList(
                new int[] { 1, 3 },
                new int[] { 2, 6 },
                new int[] { 8, 10 },
                new int[] { 15, 18 });
        List<int[]> resultA = mergeIntervals(intervalsA);
        // printResult(resultA); // expect [[1,6],[8,10],[15,18]]
        List<int[]> answerA = Arrays.asList(
                new int[] { 1, 6 },
                new int[] { 8, 10 },
                new int[] { 15, 18 });
        assertResultAndAnswerSame(resultA, answerA);

        List<int[]> intervalsB = Arrays.asList(
                new int[] { 1, 4 },
                new int[] { 4, 5 });
        List<int[]> resultB = mergeIntervals(intervalsB);
        // printResult(resultB); // expect [[1,5]]
        List<int[]> answerB = Arrays.asList(
                new int[] { 1, 5 });
        assertResultAndAnswerSame(resultB, answerB);
        
        List<int[]> intervalsC = Arrays.asList(
                new int[] { 1, 4 },
                new int[] { 2, 3 },
                new int[] { 3, 8 },
                new int[] { 10, 12 },
                new int[] { 11, 15 });
        List<int[]> resultC = mergeIntervals(intervalsC);
        // printResult(resultC); // expect [[1,8],[10,15]]
        List<int[]> answerC = Arrays.asList(
                new int[] { 1, 8 },
                new int[] { 10, 15 });
        assertResultAndAnswerSame(resultC, answerC);
        
        List<int[]> intervalsD = Arrays.asList(
                new int[] { 1, 3 },
                new int[] { 1, 5 });
        List<int[]> resultD = mergeIntervals(intervalsD);
        // printResult(resultD); // expect [[1,5]]
        List<int[]> answerD = Arrays.asList(
                new int[] { 1, 5 });
        assertResultAndAnswerSame(resultD, answerD);
        System.out.println("All good");
    }

    private static void assertResultAndAnswerSame(List<int[]> resultA, List<int[]> answerA) {
        assert(resultA.size() == answerA.size());
        for (int i = 0; i < resultA.size(); ++i) {
            assert Arrays.equals(resultA.get(i), answerA.get(i));
        }
    }
    
    private static void printResult(List<int[]> results) {
        System.out.print("[");
        for (int[] result : results) {
            System.out.print(Arrays.toString(result));
        }
        System.out.println("]");
    }

    public static List<int[]> mergeIntervals(List<int[]> intervals) {
        if (intervals.size() == 0) {
            return new ArrayList<>();
        }
        if (intervals.size() == 1) {
            return intervals;
        }
        
        // BAD!!! because if first element is same it will break sort first in case the input list is not sorted
        // Map<Integer, Integer> treeMap = new TreeMap<>();
        // for (int i = 0; i < intervals.size(); ++i) {
        //     int[] interval = intervals.get(i);
        //     treeMap.put(interval[0], i);
        // }
        
        intervals.sort(Comparator.comparingInt(a -> a[0]));
        
        // a result holder
        List<int[]> result = new ArrayList<>();
        // a temp holder
        int[] holder = null;
        for (int idx = 0; idx < intervals.size(); ++idx) {
            // if holder is null, put into holder and continue
            if (holder == null) {
                holder = intervals.get(idx);
                continue;
            }
            
            // compare holder and second
            int[] second = intervals.get(idx);
            // holder will always smaller than holder,
            // now compare holder end element with second start element
            if (holder[1] >= second[0]) {
                // skip this if holder cover whole span of second
                if (holder[1] >= second[1]) {
                    continue;
                }
                // holder[1] = second[1];
                holder = new int[] { holder[0], second[1] }; // this is safer
                continue;
            }
            
            // if it have gap, put holder into result,
            // then assign second to the holder
            result.add(holder);
            holder = second;
        }
        if (holder != null) {
            result.add(holder);
        }
        
        return result;
    }
}
