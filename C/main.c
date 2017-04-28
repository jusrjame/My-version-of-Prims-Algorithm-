/**
 * @file main.c
 * @author Justin James
 * @date 19 april 2017
 * @brief Driver file for Prim's.
 *
 * This program will takes in a filename from the command line.
 * It open the file for reading and reads the edges of a graph
 * then stores the edges in an adjacency matrix.
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "graph.h"
//#include "mst.h"

/**
 * This function reads data from a file.
 * @author Justin James
 * @param filename  A pointer to the name of the file.
 * @param g         A pointer to a struct that contains the graph.
 * @date 4/19/2017
 * @return void
 */
void readFile(char *filename, struct Graph *g);

int main(int argc, char *argv[])
{
  struct Graph g;
  struct MST mst;
  if (argc == 1) {
    printf("Enter a filename\n");
    return -1;
  }
  else {

    zeroOutMatrix(&g);
    initNumVertsInGraph(&g);
    readFile(argv[1], &g);
    printMatrix(g);
    initPOS(&mst);
    initNumVertsInTree(&mst);
    addStartingVert(&mst);
    buildMST(&g, &mst);

    char x = toChar(0);
    printf("%c\n", x);
  }
  return 0;
}

void readFile(char *filename, struct Graph *g){
  
  FILE *fp= fopen(filename, "r");

  if (fp == NULL) {
    printf("Could not open file\n");
  }
  else {
    char x[10];
    char *token, *p, start[2], end[2], weight[3];
   
    while (fgets(x, 10, fp) != NULL) {
         
      p = strchr(x, '\n');  // finds first, if any, \n
      if (p != NULL) *p = '\0';
      //splits "x" into tokens
      strcpy(start, strtok(x, " "));
      strcpy(end, strtok(NULL, " "));
      strcpy(weight, strtok(NULL, "\0"));
      
      printf("%s, %s, %s\n", start, end, weight);

      storeEdge(g, start, end, weight);
     
    }
    fclose(fp);
  }
}
