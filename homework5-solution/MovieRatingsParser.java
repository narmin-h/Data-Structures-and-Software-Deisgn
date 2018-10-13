/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MovieRatingsParser {

	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {
		
		/* IMPLEMENT THIS METHOD! */
		TreeMap<String, PriorityQueue<Integer>> map = new TreeMap<String, PriorityQueue<Integer>>();
		if(allUsersRatings!=null && !allUsersRatings.isEmpty()){
			for(UserMovieRating umt:allUsersRatings){
				if(umt!=null && umt.movie!=null && umt.movie!="" && umt.userRating>0){
					String movie = umt.movie.toLowerCase();
					if(!map.containsKey(movie)){
						PriorityQueue<Integer> temp = new PriorityQueue<Integer>();
						temp.add(umt.userRating);
						map.put(movie, temp);
					}else{
						PriorityQueue<Integer> heap = map.get(movie);
						heap.add(umt.userRating);
					}
				}
			}
		}
		return map; // this line is here only so this code will compile if you don't modify it
	}
}
