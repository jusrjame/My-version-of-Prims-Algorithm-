/**
 * @file graph.h
 * @author Justin James
 * @date 19 april 2017
 * @brief Header file associated with graph.c
 *
 * This file contains all prototypes of functions that
 * operate over the graph in order to find a MST using
 * Prim's algorithm.  It also contaons the main data 
 * structures that these functions will be operating over.
 *
 */

#ifndef GRAPH_H
#define GRAPH_H

#define MAX_VERTS 10
#define INF 999

struct MST{
  int start[MAX_VERTS];
  int end[MAX_VERTS];
  int weight[MAX_VERTS];
  int pos;
  int numVertsInTree;
  int vertsInTree[MAX_VERTS];
};

struct Graph {
  
  int graph[MAX_VERTS][MAX_VERTS];
  //int MST[MAX_VERTS][MAX_VERTS];
  char vertices[MAX_VERTS];
  int numVertsInGraph;
};

/**
 * This functions stores the edges into an adjacenty matrix
 * @author Justin James
 * @param g      A pointer to the matrix
 * @param row    the value that will be the row index
 * @param col    the value that will be the column index
 * @param weight the valee that will be stores at postion
 *               [row][col] of the matrix.
 * @date 4/19/2017
 * @return void
 */
void storeEdge(struct Graph *g, char *row, char *col, char *weight);

/**
 * This function converts a character (vertex) into its corrosponding 
 * index to be used with the 2D array representaiton of an adjacency matrix.
 * @author Justin James
 * @param x   The char to be converted into an array index.
 * @date 04/19/2017
 * @return int
 */
int toIndex(char *x);

/**
 * This function turn an index into its corrosponding vertex which is a char.
 * @author Justin James
 * @param x   The index to be converted to a char.
 * @date 04/19/2017
 * @return char
 */
char toChar(int x);

//debugging
void printMatrix(struct Graph g);

/**
 * This function zeros out all positions of the matrix
 * @author Justin James
 * @param g   A pointer to the matrix 
 * @date 04/19/2017
 * @return void
 */ 
void zeroOutMatrix(struct Graph *g);

void initPOS(struct MST *m);

void initNumVertsInTree(struct MST *m); 

void buildMST(struct Graph *g, struct MST *m);

void getMinEdge(struct Graph *g, struct MST *m);

void addToMST(struct MST *m, int minStart, int minEnd, int minWeight);

void addStartingVert(struct MST *m);

void initNumVertsInGraph(struct Graph *g);

void removeEdge(struct Graph *g, int i, int j);

int  inTree(int v, int size, int *verts);
void debug();

#endif
