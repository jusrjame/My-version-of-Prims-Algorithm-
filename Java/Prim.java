/**
 * This file holds the Prim class where <code>main(Strings [] args)</code> is located.
 * This class handles all of the I/O of the program such as reading 
 * the data from a file and printing data to the console.
 *
 * @author  Justin James
 * @version 1.0
 * @since   2017-04-09
 */

import java.io.*;
import java.util.*;
import java.lang.*;

public class Prim
{
	/**
	 * Declares a new <code>Graph</code> object 
	 */
	public static Graph g = new Graph();
	
	/**
	 * Calls all other methods in order to create a MST using 
	 * an implementation of Prim's algorithm. This will
	 * print an error message if no filename and <code>exit(-1)</code>.
	 * 
	 * @param args	a string holding command line arugments.
	 * 				In this case the argument is the file name
	 * 				and is accesible by using <code>args[0]</code>.
	 */
	public static void main(String[] args)
	{
		if(args.length > 0){
			File file = new File(args[0]);
			
			try{
				System.out.println("Graph:");
				readFile(args[0]);
				g.setStartNode();
				printLine();
				g.buildMST();
				printMST();
			}
			catch(Exception e){
				e.printStackTrace();
			}			
		}
		else{
			System.out.println("No filename entered.");
			System.exit(-1);
		}
	}// end main
		
	/**
	 * Reads the data from the file passed in from the command line.
	 * This method uses a <code>BufferedReader</code> to gather data
	 * line by line.  The line is then split based on whitespace and 
	 * stored into a variable called <code>tokens</code>.  Each 
	 * <code>token</code> is then passed into methods accordinly. Also, 
	 * all vertices from the graph are put into a set for later use.
	 * 
	 * @param file	the name of the file which was passed into the command
	 * 				line.  
	 */
	public static void readFile(String file){
		String edge;
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			while((edge = reader.readLine()) != null){
			
				String[] tokens = edge.split("\\s");
				
				Node n = new Node(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
				g.addKey(tokens[0]);
				g.addKey(tokens[1]);
				g.addAdjacentNode(tokens[0], n);
				g.addAdjacentNode(tokens[1], n);
				g.buildVertList(tokens[0], tokens[1]);
				
				printGraph(n);
			}
			g.keySet = g.list.keySet();
			reader.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//end readFile
	
	/**
	 * Prints all edges of the graph in the following format:
	 * 			(vertex1, vertex2, weight)
	 * 						...
	 */
	public static void printGraph(Node n){
		System.out.println(n);
	}
	
	/**
	 * Prints the edges inlcuded in the <code>MST</code> after 
	 * Prim's has been run in the following format:
	 * 			(vertex1, vertex2, weight)
	 * 						...
	 */
	public static void printMST(){
		Iterator<Node> it = g.MST.iterator();
		
		System.out.println("MST:");
		while(it.hasNext()){
			Node node = it.next();
			System.out.println(node);
		}
	}
	
	/**
	 * Prints a horizontal line used for formatting purposes only.
	 * Seperates the graph from the MST during output.
	 */
	public static void printLine(){
		System.out.println("------------------");
	}//end printLine
	
} // end prim class
	
