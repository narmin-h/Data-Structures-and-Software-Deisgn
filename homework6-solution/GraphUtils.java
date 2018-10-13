

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class GraphUtils {

	public static int minDistance(Graph graph, String src, String dest) {

		/* IMPLEMENT THIS METHOD! */
		if(graph!=null 
		   && src!=null
		   && dest!=null 
		   && graph.containsElement(src)
		   && graph.containsElement(dest)){
			BreadthFirstSearch bfsearch = new BreadthFirstSearch(graph); 
			Node srcNode = graph.getNode(src);
			if(bfsearch.bfs(srcNode, dest)){
				return bfsearch.minPath;
			}
			
		}
		return -1; // this line is here only so this code will compile if you don't modify it
	}
	

	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {

		/* IMPLEMENT THIS METHOD! */
		if(graph!=null
		   && src!=null
		   && graph.containsElement(src)
		   && distance>=1){
			Set<String> nodesWithinDistance = new HashSet<String>();
			Set<Node> allNodes = graph.getAllNodes();
			Iterator<Node> it = allNodes.iterator();
			while(it.hasNext()){
				Node current = it.next();
				if(src!=current.getElement()){
					int minDistance = minDistance(graph,src,current.getElement());
					if(minDistance>=1 && minDistance<=distance){
						nodesWithinDistance.add(current.getElement());
					}
				}
			}
			return nodesWithinDistance;
		}
		
		return null; // this line is here only so this code will compile if you don't modify it
	}


	public static boolean isHamiltonianPath(Graph g, List<String> values) {

		/* IMPLEMENT THIS METHOD! */
		if(g!=null 
			&& 
			values!=null 
			&& !values.isEmpty() 
			&& (values.get(0)==values.get(values.size()-1))
			&& values.size()==g.getAllNodes().size()+1){
			Set<String> marked = new HashSet<String>();
			for(int i=0;i<values.size()-1;i++){
				String tempNodeElement1 = values.get(i);
				String tempNodeElement2 = values.get(i+1);
				if(g.containsElement(tempNodeElement1) && g.containsElement(tempNodeElement2)){
					Node temp1Node = g.getNode(tempNodeElement1);
					Node temp2Node = g.getNode(tempNodeElement2);
					Edge tempEdge = new Edge (temp1Node,temp2Node);
					if(g.getNodeEdges(temp1Node).contains(tempEdge)
						&& !marked.contains(tempNodeElement1)){
						marked.add(tempNodeElement1);
					}else
						return false;					
				}
				else return false;
			}
			return true;			
		}
		
		return false; // this line is here only so this code will compile if you don't modify it
	}
	
}
