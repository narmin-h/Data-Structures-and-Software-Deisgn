

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
 * SD2x Homework #11
 * Improve the efficiency of the code below according to the guidelines in the assignment description.
 * Please be sure not to change the signature of the detectPlagiarism method!
 * However, you may modify the signatures of any of the other methods as needed.
 */

public class PlagiarismDetector2 {

	public static Map<String, Integer> detectPlagiarism(String dirName, int windowSize, int threshold) {
		if(dirName==" " || windowSize < 1) return null;
		File dirFile = new File(dirName);
		File[] listOfFiles = dirFile.listFiles();
		
		Map<String, Integer> numberOfMatches = new HashMap<>();
		Map<String, Set<String>> filesPhrases = new HashMap<>();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			File file1 = listOfFiles[i];
			Set<String> file1Phrases = createOrGetPhrases(file1, filesPhrases, windowSize);
	
			for (int j = i+1; j < listOfFiles.length; j++) { 
				File file2 = listOfFiles[j];
				
				Set<String> file2Phrases = createOrGetPhrases(file2, filesPhrases, windowSize);				
				Set<String> matches = findMatches(file1Phrases, file2Phrases);
								
				if (matches.size() > threshold) {
					String key = file1.getName() + "-" + file2.getName();
						numberOfMatches.put(key,matches.size());

				}				
			}
			
		}		
		return sortByValues(numberOfMatches);
	}
	
	public static Set<String> createOrGetPhrases(File file, Map<String, Set<String>> filesPhrases, int windowSize){
		Set<String>filePhrases;
		if(!filesPhrases.containsKey(file.getName())) {
			 filePhrases = createPhrases(file, windowSize); 
			 filesPhrases.put(file.getName(), filePhrases);
			 }
		else 
			filePhrases = filesPhrases.get(file.getName()); 
		return filePhrases;
	}

	
	/*
	 * This method reads the given file and then converts it into a Collection of Strings.
	 * It does not include punctuation and converts all words in the file to uppercase.
	 */
	protected static List<String> readFile(File file) {		
		List<String> words = new ArrayList<>();	
		try {
			Scanner in = new Scanner(file);
			while (in.hasNext()) {
				words.add(in.next().replaceAll("[^a-zA-Z]", "").toUpperCase());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return words;
	}

	
	/*
	 * This method reads a file and converts it into a Set/List of distinct phrases,
	 * each of size "window". The Strings in each phrase are whitespace-separated.
	 */
	protected static Set<String> createPhrases(File filename, int window) {		
		List<String> words = readFile(filename);
		if(words==null || words.isEmpty()) return null;
		
			Set<String> phrases = new HashSet<String>();
			String phrase ="";
			for(int i=0;i<window;i++){
				phrase += words.get(i) + " ";
			}
			for(int i = 0; i< words.size() - window;i++){
				phrases.add(phrase);
				phrase = phrase.substring(words.get(i).length()+1) + " " +words.get(i+window);
			}
			
		return phrases;		
	}
	
	/*
	 * Returns a Set of Strings that occur in both of the Set parameters.
	 * However, the comparison is case-insensitive.
	 */
	protected static Set<String> findMatches(Set<String> myPhrases, Set<String> yourPhrases) {
	
		Set<String> matches = new HashSet<String>();
			for (String mine : myPhrases) {
				if(yourPhrases.contains(mine))
					matches.add(mine);				
			}
		
		return matches;
	}
	
	/*
	 * Returns a LinkedHashMap in which the elements of the Map parameter
	 * are sorted according to the value of the Integer, in non-ascending order.
	 */
	public static  <K, V extends Comparable<? super V>> Map<String, Integer> sortByValues(Map<String, Integer> map) {
  List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
	    Collections.sort( list, new Comparator<Map.Entry<String, Integer>>() {
	        @Override
	        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2){ 
	        	return  (int) o2.getValue() - (int) o1.getValue();
	        }
	    });
	
	    Map<String, Integer> result = new LinkedHashMap<>();
	    for (Map.Entry<String, Integer> entry : list) {
	        result.put(entry.getKey(), entry.getValue());
    }
    return result;
}
	
	
	
	/*
	 * This method is here to help you measure the execution time and get the output of the program.
	 * You do not need to consider it for improving the efficiency of the detectPlagiarism method.
	 */
    public static void main(String[] args) {
    	if (args.length == 0) {
    		System.out.println("Please specify the name of the directory containing the corpus.");
    		System.exit(0);
    	}
    	String directory = "";
    	long start = System.currentTimeMillis();
    	Map<String, Integer> map = PlagiarismDetector2.detectPlagiarism(directory, 4, 5);
    	long end = System.currentTimeMillis();
    	double timeInSeconds = (end - start) / (double)1000;
    	System.out.println("Execution time (wall clock): " + timeInSeconds + " seconds");
    	Set<Map.Entry<String, Integer>> entries = map.entrySet();
    	for (Map.Entry<String, Integer> entry : entries) {
    		System.out.println(entry.getKey() + ": " + entry.getValue());
    	}
    }

}
