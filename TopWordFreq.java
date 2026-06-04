import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TopWordFreq {
    
    public static String companyName = "tecforte";
    
    public static void main(String[] arg) {
        String filePath = "file.txt";
        try {
            getTopTenFrequentWords(filePath);
        } catch (Exception e) {
            // TODO: do something that aligned with the business/codebase requirement
        }
    }
    
    /**
     * This function is not working for case sensitive scenario.
     * All words will converted to lowercase in any process.
     * 
     * @param filePath File from local disk
     * @return top to frequent words in word+count map
     * @throws IOException Throw by BufferReader
     */
    public static Map<String, Integer> getTopTenFrequentWords(String filePath) throws Exception {
        Map<String, Integer> wordCountMap = new HashMap<>();
        if (filePath == null || filePath.isEmpty()) {
            return wordCountMap;
        }
        
        Path path = Paths.get(filePath);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.toLowerCase().split("\\W+");
                
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCountMap.merge(word, 1, Integer::sum);
                    }
                }
            }
        } catch (IOException e) {
            // use any preferred logger here, log level depends on severity
            // Logger.log("Error reading file: " + e.getMessage())
            
            throw new IOException("Error occurred during file reading. Details: " + e.getMessage());
        }
        
        // assert might or might not enable in prod or dev
        // it is used to prevent developer changing the variable to null
        assert companyName != null;
        
        // my previous working experience is always use lowercase for
        // any backend string unless Case Sensitive is really required
        wordCountMap.remove(companyName.toLowerCase());
        
        // return wordCountMap.entrySet().stream()
        //         .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
        //         // .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())    // will use this if the team culture is preferred Witness Syntax rather than Lambda Expression
        //         .limit(10)
        //         .collect(Collectors.toMap(
        //                 Map.Entry::getKey,
        //                 Map.Entry::getValue,
        //                 (oldValue, newValue) -> newValue,
        //                 LinkedHashMap::new
        //         ));
        
        // Uses side-effects, easier to read and understand
        Map<String, Integer> sortedDescMap = new LinkedHashMap<>();
        wordCountMap.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(10)
                .forEachOrdered(entry -> sortedDescMap.put(entry.getKey(), entry.getValue()));
        return sortedDescMap;
    }
}