/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;


public class MovieRatingsProcessor {

	public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {
		
		/* IMPLEMENT THIS METHOD! */
		List<String> allMovies = new ArrayList<String>();
		if(movieRatings!=null && !movieRatings.isEmpty()){
			allMovies.addAll(movieRatings.keySet());
		}
		
		return allMovies; // this line is here only so this code will compile if you don't modify it
	}

	public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		
		/* IMPLEMENT THIS METHOD! */
		List<String> allMovies = new ArrayList<String>();
		if(movieRatings!=null && !movieRatings.isEmpty()){
			for(Map.Entry<String, PriorityQueue<Integer>> entry: movieRatings.entrySet()){
				if(entry.getValue().peek() > rating){
					allMovies.add(entry.getKey());
				}
			}
		}
		
		return allMovies; // this line is here only so this code will compile if you don't modify it
	}
	
	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		
		/* IMPLEMENT THIS METHOD! */
		TreeMap<String, Integer> removedRatings = new TreeMap<String, Integer>();
		if(movieRatings!=null && !movieRatings.isEmpty()){
			Iterator<Map.Entry<String, PriorityQueue<Integer>>> it = movieRatings.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String, PriorityQueue<Integer>> pair = it.next();
				String  movie = pair.getKey();
				Iterator<Integer> ratings = pair.getValue().iterator();
				if(true){
					int count = 0;					
					while(ratings.hasNext()){
						int rating1 =ratings.next();
						if(rating1 < rating){
							count++;
							removedRatings.put(movie, count);
							ratings.remove();				
						}
					}
					if(pair.getValue().isEmpty()){
						it.remove();
					}
					
				}
			}
		}
		return removedRatings; // this line is here only so this code will compile if you don't modify it
	}
}
