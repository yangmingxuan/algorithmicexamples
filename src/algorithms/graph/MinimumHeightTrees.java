package algorithms.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/***
 * A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.

    Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
    
    Return a list of all MHTs' root labels. You can return the answer in any order.
    
    The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
    
     
    
    Example 1:
    
    
    Input: n = 4, edges = [[1,0],[1,2],[1,3]]
    Output: [1]
    Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
    Example 2:
    
    
    Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
    Output: [3,4]
    Example 3:
    
    Input: n = 1, edges = []
    Output: [0]
    Example 4:
    
    Input: n = 2, edges = [[0,1]]
    Output: [0,1]
     
    
    Constraints:
    
    1 <= n <= 2 * 10^4
    edges.length == n - 1
    0 <= ai, bi < n
    ai != bi
    All the pairs (ai, bi) are distinct.
    The given input is guaranteed to be a tree and there will be no repeated edges.
 * @author michel
 *
 */
public class MinimumHeightTrees {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n <= 2) {
            List<Integer> ans = new ArrayList<Integer>();
            for(int i = 0; i < n; i++) {
                ans.add(i);
            }
            return ans;
        }
        
        //build the graph
        List<HashSet<Integer>> graph = new ArrayList<HashSet<Integer>>();
        for(int i = 0; i < n; i++) {
            graph.add(new HashSet<Integer>());
        }
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        // Initialize the first layer of leaves
        ArrayList<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (graph.get(i).size() == 1)
                leaves.add(i);

        // Trim the leaves until reaching the centroids
        int remainingNodes = n;
        while (remainingNodes > 2) {
            remainingNodes -= leaves.size();
            ArrayList<Integer> newLeaves = new ArrayList<>();

            // remove the current leaves along with the edges
            for (Integer leaf : leaves) {
                // the only neighbor left for the leaf node
                Integer neighbor = graph.get(leaf).iterator().next();
                // remove the edge along with the leaf node
                graph.get(neighbor).remove(leaf);
                if (graph.get(neighbor).size() == 1)
                    newLeaves.add(neighbor);
            }

            // prepare for the next round
            leaves = newLeaves;
        }

        // The remaining nodes are the centroids of the graph
        return leaves;
    }

}
