import java.util.Scanner;
import java.util.Set;

/*
 * SD2x Homework #8
 * This class represents the Presentation Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below. 
 * Also implement the start method as described in the assignment description.
 */

public class PresentationTier {
	
	private LogicTier logicTier; // link to the Logic Tier
	
	public PresentationTier(LogicTier logicTier) {
		this.logicTier = logicTier;
	}
	
	public void start() {
		
		/* IMPLEMENT THIS METHOD */
		System.out.println("Which feature do you want to invoke? ");
		System.out.println("For book titles by a certain Author press \"A\"");
		System.out.println("For the number of books for a year press \"Y\"");
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		if(line.equals("A")){
			showBookTitlesByAuthor();		
		}else 
			if(line.equals("Y")){
				showNumberOfBooksInYear();
			}

	}
	
	public void showBookTitlesByAuthor(){
		System.out.println("Please enter the author name:  ");
		Scanner sc  = new Scanner(System.in);
		String line = sc.nextLine();
		Set<String> booksByAuthor = logicTier.findBookTitlesByAuthor(line);
		for(String bookTitles: booksByAuthor ){
			System.out.println(bookTitles);
		}
	}
	public void showNumberOfBooksInYear(){
		System.out.println("Please enter the year: ");
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		int year = logicTier.findNumberOfBooksInYear(Integer.parseInt(line));
		System.out.println(year);
	
	}
}
