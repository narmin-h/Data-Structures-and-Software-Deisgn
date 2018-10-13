
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
 * SD2x Homework #8
 * This class represents the Logic Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below.
 */

public class LogicTier {
	
	private DataTier dataTier; // link to the Data Tier
	
	public LogicTier(DataTier dataTier) {
		this.dataTier = dataTier;
	}
	public Set<String>findBookTitlesByAuthor(String author){
		if(author!=null && author!=""){
			List<Book> books = dataTier.getAllBooks();
			Set<String> booksByAuthor = new TreeSet<String>();
			for(Book book: books){
				String authorName = book.getAuthor().toLowerCase();
				if(authorName.contains(author.toLowerCase())){
					booksByAuthor.add(book.getTitle());
				}
			}
			return booksByAuthor;
		}
		return null;
		
	}
	
	public int findNumberOfBooksInYear(int year){
		int count = 0;
		if(year>0){
			List<Book> books = dataTier.getAllBooks();
			for(Book book: books){
				int tempYear = book.getPublicationYear();
				if(tempYear == year){
					count++;
				}
			}
		}
		return count;
	}

} 
