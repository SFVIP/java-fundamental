import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagrams {
    
    public static void main(String[] args) {
        // assume we no need to cater string size and it is always lowercase
        List<String> input = List.of("eat", "tea", "tan", "ate", "nat", "bat");
        Map<String, List<String>> map = new HashMap<>();
        
        /*
        // 1. easiest way is to reorder the word by char sequence
        // then group the one with same order
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            char[] split = s.toCharArray();
            Arrays.sort(split);
            String combine = Arrays.toString(split);
            
            List<String> list;
            if (map.get(combine) != null) {
                list = map.get(combine);
            } else {
                list = new ArrayList<>();
            }
            list.add(input.get(i));
            map.put(combine, list);
        } 
        */
        
        /*
        // 2. try to put all char into the hashmap
        for (int i = 0; i < input.size(); i++) {
            int[] container = new int[26];
            char[] charArray = input.get(i).toCharArray();
            for (char c : charArray) {
                container[c - 'a']++;
            }
            
            List<String> list;
            String containerStr = Arrays.toString(container);
            if (map.get(containerStr) != null) {
                list = map.get(containerStr);
            } else {
                list = new ArrayList<>();
            }
            list.add(input.get(i));
            map.put(containerStr, list);
        }
        */
        
        // 3. more concise and using stream
        for (int i = 0; i < input.size(); i++) {
            int[] container = new int[26];
            char[] charArray = input.get(i).toCharArray();
            for (char c : charArray) {
                container[c - 'a']++;
            }

            String containerStr = Arrays.toString(container);
            map.computeIfAbsent(containerStr, key -> new ArrayList<>()).add(input.get(i));
        }
        System.out.println(map.values());
    }
    
    // 
}
