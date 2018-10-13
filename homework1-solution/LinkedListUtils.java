import java.util.Iterator;
import java.util.LinkedList;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {
	
	public static void insertSorted(LinkedList<Integer> list, int value) {

		/* IMPLEMENT THIS METHOD! */
		if(list==null) {
			return;
		}
		else if(list.isEmpty()){
			list.add(value);
		}
		else {
		Iterator<Integer> iterator = list.iterator();
		int tempValue = iterator.next();
		int i=0;
		while(tempValue<=value){
			i++;
			if(iterator.hasNext()){
			tempValue=iterator.next();
			}
			else break;		
		}
		list.add(i, value);		
		}

	}
	

	public static void removeMaximumValues(LinkedList<String> list, int N) {

		/* IMPLEMENT THIS METHOD! */
		if(N<0 || list==null){
			return;
		}
		for(int i=0;i<N;i++){
			Iterator<String> iterator = list.iterator();
			String max = "";
			while(iterator.hasNext()){
				String tempValue = iterator.next();
				if(tempValue.compareTo(max)>0){
					max = tempValue;
				}
			}
			while(list.contains(max)){
				list.remove(max);
			}
		}

	}
	
	public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {

		/* IMPLEMENT THIS METHOD! */
		if(one == null || two == null || one.isEmpty() || two.isEmpty() ){
			return false;
		}
		if(one.size() < two.size()) {
			return false;
		}
		boolean boolValue=true;
		Iterator<Integer> iterator2 =two.iterator();
		int valueFromTwo = iterator2.next();
		if(one.contains(valueFromTwo)){
			Iterator<Integer> iterator1 = one.iterator();
			int valueFromOne = iterator1.next();
			while(valueFromOne!=valueFromTwo){
				valueFromOne = iterator1.next();
			}
			
			while(iterator2.hasNext()){
				if(iterator1.hasNext()){
				valueFromOne = iterator1.next();}
				else{ boolValue = false;}
			
				valueFromTwo = iterator2.next();

				if(valueFromOne!=valueFromTwo){
					boolValue = false;
				}
			}
			
		}
		else {
			return false;
		}
		return boolValue; // this line is here only so this code will compile if you don't modify it
	}
}
