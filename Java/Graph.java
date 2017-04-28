/**
 * This file holds the Graph class where all the methods.  This class
 * handles all of work done on a given graph to find a MST.
 *
 * @author  Justin James
 * @version 1.0
 * @since   2017-04-11
 */

import java.io.*;
import java.util.*;
import java.lang.*;


public class Graph
{
	/**
	 * This is the adjaceny list which holds the initial graph.  The keys are Strings
	 * and the values are lists of <code>Node</code> objects.
	 */
	public Map<String, ArrayList<Node>> list;
	
	/**
	 * This is the priority	queue which helps find the minimum edge within a list of
	 * <code>Node</code> of objects
	 */
	public PriorityQueue<Node> pQueue;
	
	/**
	 * Represents the vertices which have been added to the MST.
	 * This collection grows as adges are added to the MST until all
	 * vertices have been added.
	 */
	public Collection<String> vertsInTree;
	
	/**
	 * Holds all vertices of the initial graph.
	 */
	public Collection<String> allVertices;
	
	/**
	 * Hold the edges of the MST.  Edges are represented by <code>Node</code> objects.
	 */
	public ArrayList<Node> MST;
	
	/**
	 * Represents the starting vertice of the MST.
	 */
	public String startingVert = null;
	
	/**
	 * A collection of keys that are within the adjacency list.
	 */
	public Set<String> keySet = null;
	
	
	public Graph(){
		this.list = new HashMap<String, ArrayList<Node>>();
		this.pQueue = new PriorityQueue<Node>();
		this.vertsInTree = new HashSet<String>();
		this.MST = new ArrayList<Node>();
		this.allVertices = new HashSet<String>();
		this.keySet = new HashSet<String>();
	}
	
	/**
	 * Adds an entry to the adjacency list iff the key does not 
	 * already exist.
	 * 
	 * @param v	the vertex to add to the adjacency list.
	 */
	public void addKey(String v){
		this.list.putIfAbsent(v, new ArrayList<Node>());
	}
	
	/**
	 * Stores an adjacent edge for a given vertex in the adjacency list.  
	 * 
	 * @param v1 the starting vertex in which the edge is connected to
	 * @param n a <code>Node</code> object that represents the edge connected 
	 * to v1 and holds the weight of that adjacent edge.
	 */
	public void addAdjacentNode(String v1, Node n){
		this.list.get(v1).add(n);
	}
	
	/**
	 * Returns a list of edges that are connected to a given vertex.
	 * 
	 * @param v a collection of vertices that have been added to the MST.
	 * 
	 * @return newlist a list of edges that are connected to vertices within <code>v</code>
	 */
	public List<Node> getEdges(Collection<String> v){
		List<Node> newList = new ArrayList<Node>();
		
		try{
			for( String verts: v){
				if(keySet.contains(verts)){
					newList.addAll(list.get(verts));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return newList;
	}

	/**
	 * Adds a <code>Node</code> object to a minimum priority queue.
	 * 
	 * @param n	the <code>Node</code> object to be added to the queue.
	 */
	public void addToQ(Node n){
			this.pQueue.add(n);
	}
	
	/**
	 * Updates the queue by adding <code>Node</node> objects to it.  This method 
	 * calls <code>getEdges(vertsInTree)</code> to have relevant <code>Node</code> 
	 * objects to add.
	 */
	public void updateQueue(){
		List<Node> allEdges = getEdges(vertsInTree);
		Iterator<Node> it = allEdges.iterator() ;
		
        while(it.hasNext()) {
    		Node n = it.next();
        	if(!(vertsInTree.contains(n.start) && vertsInTree.contains(n.end))){
        	//n.priority = n.weight;
        	this.pQueue.add(n);
        	}
        }
	}
	
	/**
	 * Adds a vertex to the list of visited vertices
	 * 
	 * @param v a vertice to add to the tree.
	 * @param v1 a vertice to add to the tree.
	 */
	public void addVertToTree(String v, String v1){
		if(!vertsInTree.contains(v)){
			vertsInTree.add(v);
		}
		if(!vertsInTree.contains(v1)){
			vertsInTree.add(v1);
		}
	}
	
	/**
	 * Sets the starting node based on the first node read into the 
	 * adjacency list.
	 */
	public void setStartNode(){
		this.startingVert = this.list.keySet().iterator().next();
	}
	
	/**
	 * Adds a <code>Node</code> object to the list that contains the final MST.
	 * 
	 * @param n	a <code>Node</code> object to be added to the MST.
	 */
	public void addToMST(Node n){
		this.MST.add(n);
	}
	
	/**
	 * Polls the head of the <code>pQueue</code> which is the minimum weighted edge 
	 * in the queue, adds it to <code>MST</code> and updates <code>vertsInTree</code>
	 * accordingly.
	 */
	public void getMinEdge(){
		if(!pQueue.isEmpty()){
			Node min = this.pQueue.poll();
			addToMST(min);	
			addVertToTree(min.start, min.end);
			this.pQueue.clear();
		}
	}
	
	/**
	 * Builds a list of all vertices in the original graph.
	 * 
	 * @param v	the first vertice of an edge to be stored in the list
	 * @param v2 the seconf vertice of an edge to be stored in the list.
	 */
	public void buildVertList(String v, String v2){
		this.allVertices.add(v);
		this.allVertices.add(v2);
	}
	
	/**
	 * A wrapper method that calls the methods <code>updatePriority</code> 
	 * and <code>gitMinEdge</code> which do the heavy lifting to build the 
	 * MST from the original graph.  This method stops looping when <code>vertsInTree</code>
	 * is the same size as <code>allVertices</code> which means all vertices have been visited.
	 */
	public void buildMST(){
		this.vertsInTree.add(startingVert);
		while(this.vertsInTree.size() != this.allVertices.size()){
			updateQueue();
			getMinEdge();
		}
	}	
}
