import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class TopKFreq {
    public static void main(String[] args) {
        int[] numsA = { 1, 1, 1, 2, 2, 3 };
        int kA = 2;
        
        List<Integer> resultA = topKFrequent(numsA, kA);    // expect [1,2]
        assert resultA.containsAll(Arrays.asList(1, 2));
        
        int[] numsB = { 4, 4, 4, 4, 5, 5, 6, 6, 6, 7 };
        int kB = 2;
        
        List<Integer> resultB = topKFrequent(numsB, kB);    // expect [4,6]
        assert resultB.containsAll(Arrays.asList(4, 6));
    }
    
    public static List<Integer> topKFrequent(int[] nums, int k) {
        // 1. assumed that the nums is always sorted, so no sorting is done here
        // 2. collect occurance using hashmap
        Map<Integer, Integer> numberCountMap = new HashMap<>();
        for (int num : nums) {
            numberCountMap.merge(num, 1, Integer::sum);
        }
        
        // BoundedPQ<Entry<Integer, Integer>> bpq = 
                // new BoundedPQ<>(k, Comparator.comparingInt(Entry::getValue));
        BoundedPQ<Entry<Integer, Integer>> bpq = new BoundedPQ<>(k, 
                (entryA, entryB) -> Integer.compare(entryA.getValue(), entryB.getValue()));
        
        for(Entry<Integer, Integer> entry : numberCountMap.entrySet()) {
            if (!bpq.isFull()) {
                bpq.offer(entry);
                continue;
            }
            
            if (entry.getValue() > bpq.peek().getValue()) {
                bpq.offer(entry);
            }
        }
        
        return bpq.stream()
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }
}

class BoundedPQ<T> extends PriorityQueue<T> {
    private int maxSize; // should put a getter for this but I lazy this time

    public BoundedPQ(int size, Comparator<T> comparator) {
        super(comparator);
        maxSize = size;
    }

    @Override
    public boolean offer(T e) {
        super.offer(e);
        if (super.size() > maxSize) {
            super.poll();
        }
        return true;
    }

    public boolean isFull() {
        return super.size() == maxSize;
    }
}