import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

public class HtmlValidator {
	
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {

		/* IMPLEMENT THIS METHOD! */
		Stack<HtmlTag> stack = new Stack<HtmlTag>();
		Iterator<HtmlTag> iterator = tags.iterator();
		while(iterator.hasNext()){
			HtmlTag tag = iterator.next();
			if (tag.isOpenTag())
			{
				stack.push(tag);
			}
			else if(!tag.isSelfClosing()){
				String tagElement=tag.getElement();
				if(!stack.isEmpty())
				{
					HtmlTag tagFromStack=stack.peek();					
					if(!tagFromStack.getElement().equals(tagElement)){
						return stack;
					}else{
						stack.pop();
				}
				}
				else 
					return null;
							
			}
			
		}
		
		return stack; // this line is here only so this code will compile if you don't modify it
	}
	

}

