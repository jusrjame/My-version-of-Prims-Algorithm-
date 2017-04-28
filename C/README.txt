Justin James
jusrjame@ut.utm.edu
4/19/2017

Compilation:

	cmd:
	make

Removing risidual .class files:

	cmd:
	make clean




------------------Update Notes------------------

update 1: 4/19/2017 10:38pm

As of this update the program reads the edges from a file and stores them into a adjacecny matrix and
comments have been added where needed. 

update 2: 4/20/2017 8:37pm

Going for a very naive approach but I have a seg fault.  My C skills are very rusty.

update 3: 4/24/2107 10:11 pm

Added some functions to initialize values in my structs to 0 and added onto my getMinEdge fucntion. 
In thoery it should exclude all edges where both veritces are already in the graph and mark them as such.
Fixed the original segfault but now I have another one.  So many pointers!!!
