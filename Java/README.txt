Justin James
jusrjame@ut.utm.edu
4/11/2017

Compilation:

	cmd:
	make

Removing risidual .class files:

	cmd:
	make clean



------------------Update Notes------------------

-4/11/2017 9:21pm

Come to find out my implementation wasn't undirected.  A simple change to my prim.graph file
pointed this out.  
a b 4
a h 10
b c 8
b h 11
c d 7
c f 4
c i 2
d e 9 
d f 14
e f 10
f g 2
g h 1
g i 6
h i 7
z h 10 	-- 
z t 2	-- the addition of these two lines pointed out the flaw.

if I changed z h 10 to h z 10, the current implementation would work fine. 

I changed a few things about how the adjacency list is being stored and how the 
vertsInTree is being built.  These changes seem to git rid of the problem; however,
they introduce a new problem with how the original graph prints out.  The idea I 
have to get around this issue is sort of hack-ish but as of right now I can not think of 
any other way to do it without having to re-think/rewrite a substatial amount of my code.


-4/12/2017 8:47pm

Instead of printing the original graph from the adjacency list, I print the edge as soon as 
it is read in from the file.  This circumvents the issues I was having because of how I had to
store my graph from the previously noted update.