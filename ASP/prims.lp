% Justin James
% jusrjame@ut.utm.edu
% 3-21-2017
% ASP implementation of Prim's Algorithm


%nodes
node(a; b; c; d; e; f; g; h; i).

%edges
edge(a, b, 4).
edge(a, h, 10).
edge(b, c, 8).
edge(b, h, 11).
edge(c, d, 7).
edge(c, f, 4).
edge(c, i, 2).
edge(d, e, 9).
edge(d, f, 14).
edge(e, f, 10).
edge(f, g, 2).
edge(g, h, 1).
edge(g, i, 6).
edge(h, i, 7).

%starting node
start(root).

%% Problem instance 

% bi-directional graph
edge(Y,X,Z) :- edge(X,Y,Z).

% an edge is either in our MST or not in our MST
tree(X,Y,Z); not tree(X,Y,Z) :- edge(X,Y,Z).

% no duplicate edges
:- tree(X,Y,Z), tree(A,Y,Z1), X != A.

% disregard all nodes that can't be reached
:- node(X), not reached(X).

% a node is able to be reached if it is adjacent to the start node
reached(X) :- start(X).

% node Y is able to be reached if the node connected to it has been reached
reached(Y) :- tree(X,Y,Z), reached(X).

#minimize {Z,X,Y : tree(X,Y,Z), edge(X,Y,Z)}.
#show tree/3.