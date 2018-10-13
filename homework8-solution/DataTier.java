import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * SD2x Homework #8
 * This class represents the Data Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below.
 */

public class DataTier {
	
	private String fileName; // the name of the file to read
	
	public DataTier(String inputSource) {
		fileName = inputSource;
	}
	
	public List<Book> getAllBooks(){
		
		if(fileName!=null && fileName!=""){
			try {
				List<Book> books = new ArrayList<Book>();
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				String line = br.readLine();
				
				while(line!=null){
					String [] bookDetails = line.split("\\t");
					Book tempBook = new Book(bookDetails[0], bookDetails[1], Integer.parseInt(bookDetails[2]));
					books.add(tempBook);
					line=br.readLine();
					
				}
				return books;
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(IOException e){
			
			}
		}
		return null;
		
	}
	

}
