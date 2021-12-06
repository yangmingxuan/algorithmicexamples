package algorithms.graph;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

    The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
    
     
    
    Example 1:
    
    0----->1
    |      |
    |      |
    ↓      ↓
    2----->3
    Input: graph = [[1,2],[3],[3],[]]
    Output: [[0,1,3],[0,2,3]]
    Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
    Example 2:
    
    
    Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
    Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
    Example 3:
    
    Input: graph = [[1],[]]
    Output: [[0,1]]
    Example 4:
    
    Input: graph = [[1,2,3],[2],[3],[]]
    Output: [[0,1,2,3],[0,2,3],[0,3]]
    Example 5:
    
    Input: graph = [[1,3],[2],[3],[]]
    Output: [[0,1,2,3],[0,3]]
     
    
    Constraints:
    
    n == graph.length
    2 <= n <= 15
    0 <= graph[i][j] < n
    graph[i][j] != i (i.e., there will be no self-loops).
    All the elements of graph[i] are unique.
    The input graph is guaranteed to be a DAG.
 * @author miche
 *
 */
public class AllPathsFromSourcetoTarget {

    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    boolean[] visited;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        visited = new boolean[n];
        List<Integer> path = new ArrayList<Integer>();
        path.add(0);
        visited[0] = true;
        dfs(0, n-1, graph, path);
        
        return ans;
    }

    private void dfs(int start, int end, int[][] graph, List<Integer> path) {
        if(start == end) {
            ans.add(new ArrayList<Integer>(path));
            return;
        }
        for(int neighbor : graph[start]) {
            if(!visited[neighbor]) {
                path.add(neighbor);
                visited[neighbor] = true;
                dfs(neighbor, end, graph, path);
                visited[neighbor] = false;
                path.remove(path.size()-1);
            }
        }
    }
}
