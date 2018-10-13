import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {
	
	/*
	 * Implement this method in Part 1
	 */
	public static List<Sentence> readFile(String filename){

		/* IMPLEMENT THIS METHOD! */
		List<Sentence> sentences = new  ArrayList<Sentence>();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();
			
			while(line!=null && !line.equals("")){				
				String regex = "(-)?[0-2]\\s[a-zA-Z\\s]+";
				if(line.matches(regex)){
					String sub = line.substring(0,1);             
					boolean neg = false;
					if(sub.equals("-")) {
						sub = line.substring(0,2);
						neg =true;
					}
					int score = Integer.parseInt(sub);
					String text ="";
					Sentence sentence=null;
					if(score == -2 || score == -1 || score == 0 ||  score == 1 ||  score == 2){
						if(neg) {
						text = line.substring(3);	
						
						} else  text = line.substring(2);
						sentence = new Sentence(score,text);
						sentences.add(sentence);
						
					}
				}
				
				line = br.readLine();		
			}
		}
		catch(FileNotFoundException e){
			return sentences;
		}
		catch(IOException e){
			return sentences;
		}
		catch(NullPointerException e){
			return sentences;
		}
		
		return sentences; // this line is here only so this code will compile if you don't modify it

	}
	
	/*
	 * Implement this method in Part 2
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {

		/* IMPLEMENT THIS METHOD! */
		Set<Word> words = null;
		List<Word> wordList = new ArrayList<Word>();
		
		if(sentences!=null && !sentences.isEmpty()){
			for(Sentence sentence: sentences){
				if(sentence!=null){
					int score = sentence.score;
					String text = sentence.text;
					String[] tokens = text.split(" ");
					for(String token: tokens){
						String regex = "^[a-zA-z]+";
						if(token.matches(regex)){
							Word word= new Word(token.toLowerCase());
							if(wordList.contains(word)){
								int index = wordList.indexOf(word);
								Word wordFromList = wordList.get(index);
								wordFromList.increaseTotal(sentence.score);
							}else{
								word.increaseTotal(sentence.score);
								wordList.add(word);
							}
							
						}
						
					}
				}
				
			}		
			
	}
		if(!wordList.isEmpty()){
			words = new HashSet<Word>(wordList);
		}
		else words = new HashSet<Word>();
		
		return words; // this line is here only so this code will compile if you don't modify it

	}
	
	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {

		/* IMPLEMENT THIS METHOD! */
		Map<String, Double> wordMap = new HashMap<String, Double>();
		if(words!=null && !words.isEmpty() ){
			for(Word word: words){
				if(word!=null){
					double score = word.calculateScore();
					wordMap.put(word.text, score);
				}
			}
		}
		return wordMap; // this line is here only so this code will compile if you don't modify it

	}
	
	/*
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {

		/* IMPLEMENT THIS METHOD! */
		double sentenceScore=0;
		int count = 0;
		if(wordScores!=null && !wordScores.isEmpty() && sentence!=null && !sentence.isEmpty()){
			String words[] = sentence.split(" ");
			double score=0;
			String regex = "^[a-zA-z]+";
			for(String word: words){
				if(word.matches(regex)){
					if(wordScores.containsKey(word.toLowerCase())){
						 score = wordScores.get(word.toLowerCase());
					}
					else score = 0;
					sentenceScore+=score;
					count++;
				}
				
			}
			
			sentenceScore = sentenceScore/count;
		}
		if (count==0) return 0;
		return sentenceScore; // this line is here only so this code will compile if you don't modify it

	}
	
	/*
	 * This method is here to help you run your program. Y
	 * You may modify it as needed.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify the name of the input file");
			System.exit(0);
		}
		String filename = args[0];
		System.out.print("Please enter a sentence: ");
		
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		List<Sentence> sentences = Analyzer.readFile(filename);		
		Set<Word> words = Analyzer.allWords(sentences);		
		Map<String, Double> wordScores = Analyzer.calculateScores(words);		
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
		
	}
}
