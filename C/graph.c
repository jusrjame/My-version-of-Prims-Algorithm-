/**
 * @file graph.c
 * @author Justin James
 * @date 19 april 2017
 * @brief Implementation file for graph.h.
 *
 * This file houses the implementations for all prototypes found in 
 * graph.h
 *
 */

#include <stdio.h>
#include <stdlib.h>
//#include "mst.h"
#include "graph.h"





void storeEdge(struct Graph *g, char *row, char *col, char *weight){
  int i, j;
  /* int w = *weight; */
  /* printf("%i\n",w ); */
  i = toIndex(row);
  j = toIndex(col);

  g->numVertsInGraph = j + 1;
 
  g->graph[i][j] = atoi(weight);
  g->graph[j][i] = atoi(weight);
}

int toIndex(char *x){
  int i = *x;
  //printf("%i", i);
  return i - 97;
}

char toChar(int x){
  char vert = x + 97;
  return vert; 
}

void initPOS(struct MST *m){
  m->pos = 0;
}

void initNumVertsInTree(struct MST *m){
  m->numVertsInTree = 0;
}

void printMatrix(struct Graph g){
  int c;
  for (int i = 0; i < MAX_VERTS; i++){

    printf("%i.\t", i );
    for(int j = 0; j < MAX_VERTS; j++){
      c = g.graph[i][j];
      printf("%d\t", c);
    }
    printf("\n");
  }
}

void zeroOutMatrix(struct Graph *g){
  for (int i = 0; i < MAX_VERTS; i++){
    for(int j = 0; j < MAX_VERTS; j++){
      g->graph[i][j] = INF;
    }
  }
}

void buildMST(struct Graph *g, struct MST *m){
  debug();
 getMinEdge(g, m);  
}

void getMinEdge(struct Graph *g, struct MST *m){
  int minWeight = INF;
  int minStart, minEnd;
  int startVert = 0;
  int currentWeight = 0;
  debug();
  while(m->numVertsInTree != g->numVertsInGraph){
    for (int i = 0; i < m->numVertsInTree; i++){
      minWeight = INF;
      startVert = m->vertsInTree[i];
      for (int j = 0; j < g->numVertsInGraph; j++){
	currentWeight = g->graph[startVert][j];
	if(inTree(j, m->numVertsInTree, m->vertsInTree)){
	  
	  removeEdge(g, startVert , j);
	}
	else if(currentWeight < minWeight){
	  
	  //if(i and  j in tree) dont consider edge
	  minWeight = currentWeight;
	  minStart = m->vertsInTree[i]; // maybe just i??
	  minEnd = j;
	}
      }
    }
    //add once all edges are considered
    addToMST(m, minStart, minEnd, minWeight);
    removeEdge(g, minStart, minEnd);
  }
}

void initNumVertsInGraph(struct Graph *g){
  g->numVertsInGraph = 0;
}

void removeEdge(struct Graph *g, int i, int j){
  g->graph[i][j] = INF;
  g->graph[j][i] = INF;
}

void addToMST(struct MST *m, int minStart, int minEnd, int minWeight){
  m->start[m->pos] = minStart;
  m->end[m->pos] = minEnd;
  m->weight[m->pos] = minWeight;
  m->vertsInTree[m->pos] = minEnd; 
  m->pos = m->pos + 1;
  m->numVertsInTree = m->numVertsInTree + 1;
}

void addStartingVert(struct MST *m){
  m->vertsInTree[m->pos] = 0;
  m->numVertsInTree = m->numVertsInTree + 1;
  // m->pos = m->pos + 1;
}

void debug(){
  printf("debugging here");
}

int inTree(int v, int size, int *verts){
  for (int i = 0; i < size; i++){
    if(v == verts[i]) return 1;
  }
  return 0;
}
