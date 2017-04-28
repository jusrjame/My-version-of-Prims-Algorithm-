/**
 * This file holds the Node class.  A Node represents an edge of the graph.
 *
 * @author  Justin James
 * @version 1.0
 * @since   2017-04-10
 */

import java.io.*;
import java.util.*;
import java.lang.*;

public class Node implements Comparable<Node>{
	
	/**
	 * Represents the starting vertex of the edge.  Technically
	 * an edge does not have an explict notion of <code>start</code>
	 * because edges are undirected, but represeting a vertex as the
	 * <code>start</code> makes the edge easier to work with.
	 */
	public String start;
	
	/**
	 * Represents the ending vertex of the edge.  Technically
	 * an edge does not have an explict notion of <code>end</code>
	 * because edges are undirected, but represeting a vertex as the
	 * <code>end</code> makes the edge easier to work with.
	 */
	public String end;
	
	/**
	 * an interger representation of the cost of the edge from
	 * <code>start</code> to <code>end</code>.
	 */
	public int weight;
	
	/**
	 * Default constructor.  Sets values <code>start</code>, <code>end</code>, and <code>weight</code>
	 * accordinly.
	 * 
	 * @param s sets <code>start</code>.
	 * @param e sets <code>end</code>.
	 * @param w sets <code>weight</code>.
	 */
	public Node(String s, String e, Integer w){
		this.start = s;
		this.end = e;
		this.weight = w;
	}
	
	/**
	 * Overrides the method used for comparison within the prioroty queue.
	 * Compares the weights of <code>Node</code> objects and returns an integer
	 * value accordingly.  The lower the integer value the "higher" the priority.
	 * 
	 * @param n the <code>Node</code> object which is to be compared to the rest of the
	 * 			<code>Node</code> objects in the queue.
	 * 
	 * @return int an integer value of either -1, 0 or 1.
	 * 			
	 */
	public int compareTo(Node n){
		if(this.weight == n.weight){
			return 0;
		}
		if(this.weight > n.weight){
			return 1;
		}
		else{
			return -1;
		}
	}
	
	/**
	 * Overrides the method used by <code>println</code> inorder to streamline
	 * printing a <code>Node</code> object to the console.
	 * 
	 * @return String a 3-tuple representation of a <code>Node</code> object.
	 */
	public String toString(){
		return "(" + this.start + ", " + this.end + ", " + 
				Integer.toString(this.weight) + ")";
	}
}