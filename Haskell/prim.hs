{- |
Module      :  prim.hs
Description :  Haskell implementation of Prim's Algorithm
Copyright   :  Justin James
Licensed    :  N/A
      
Maintainer  :  jusrjame@ut.utm.edu
Stability   :  stable 
Portability :  portable
-}

import Data.List 

-- | example graph
graph = [('b','c',8),('b','h',11),('c','i',2),('a','h',10),('c','d',7),('c','f',4),('d','e',9),('a','b',4),('d','f',14),('e','f',10),('f','g',2),('g','h',1),('g','i',6),('h','i',7)]


prim :: (Ord t, Num t) => [(Char, Char, t)] -> [(Char, Char, t)]
-- ^ The main function which brings everything together.  It calls a recursive helper function prim'
prim graph = (getMinEdge $ xor' graph $ startingvertex graph ) ++ (prim' graph $ listverts $ getMinEdge $ xor' graph $ startingvertex graph)



prim':: (Ord t, Num t) => [(Char, Char, t)] -> [Char] -> [(Char, Char, t)]
{- 
prim' takes thegraph and a list of vertices that have been added to the MST.  
It then gets the next minimum edge. The vertices of that edge are concatenated with
the list of vertices that were passed originally and the new list of vertices are passed back into
prim'.  It checks the list of vertices being built against the vertices of the main graph to determine
if all vertices have been visited.  Once all vertices have been visited, an empty list is returned
thus completing the algorithm allowing all the edges of the MST to be concatenated together.
-}
prim' [] _ = []
prim' graph verts 
     | sort verts == listverts graph = []
     | otherwise = (getMinEdge $ xor' graph verts) ++ (prim' graph $ nub $ verts ++  (listverts $ getMinEdge $ xor' graph verts))



startingvertex :: Num t => [(Char, Char, t)] -> [Char]
-- ^ returns a list containing a single vertex 
startingvertex graph = [head $ listverts graph]


xor':: (Foldable t, Eq t1) => [(t1, t1, t2)] -> t t1 -> [(t1, t1, t2)]
{-
returns a list of edges that contain vertices previously visited but 
edges that have both starting and ending vertices in the list are excluded
this eliminates the risk of cycles in the MST.
-}
xor' [] _ = []
xor' ((a,b,c):rest) verts = if ((a `elem` verts ) && (not (b `elem` verts))) || ((not (a `elem` verts)) && (b `elem` verts)) then 
    (a,b,c):xor' rest verts else 
    xor' rest verts

getMinEdge :: Ord t => [(Char, Char, t)] -> [(Char, Char, t)]
-- ^ gets the minimum weighted edge from a list of weighted edges
getMinEdge [] = []
getMinEdge list = [head $ sortbyweight list]


weight:: Num t => [(Char, Char, t)] -> t
{-
takes a list of 3 tuples that represent edges and outputs the sum 
of the weights.
-}
weight graph = sum $ map third graph


sortbyweight:: Ord t => [(Char,Char,t)] -> [(Char,Char,t)]
{-
takes a list of edges represented as a 3 tuple and returns a list of 
edges ordered by weight
-}
sortbyweight [] = []
sortbyweight graph = sortBy sortbyweight' graph


sortbyweight' :: Ord a => (t, t1, a) -> (t2, t3, a) -> Ordering
{-
comparison function used by the "sortBy" function.  Compares two tuples based
on values in c1 and c2.
-}
sortbyweight' (a1,b1,c1) (a2,b2,c2) | c1 <= c2 = LT | otherwise = GT


listverts :: Num t => [(Char, Char, t)] -> [Char]
-- ^ returns a list of vertices from the graph
listverts [] = []
listverts graph = sort $ nub $ map first graph ++ map second graph


first :: (t, t1, t2) -> t
-- ^ returns the value in the first position of a 3-tuple
first (a,_,_) = a


second :: (t, t1, t2) -> t1
-- ^ returns the value in the second position of a 3-tuple
second (_,b,_) = b


third:: (t, t1, t2) -> t2
-- ^ returns the value in the third position of a 3-tuple
third (_,_,c) = c


