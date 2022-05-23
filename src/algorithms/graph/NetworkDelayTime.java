package algorithms.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/***
 * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

    We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
    
    Example 1:
            1             1           1
    1<-------------2------------>3---------->4
    
    
    Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
    Output: 2

    Example 2:
    
    Input: times = [[1,2,1]], n = 2, k = 1
    Output: 1

    Example 3:
    
    Input: times = [[1,2,1]], n = 2, k = 2
    Output: -1
     
    
    Constraints:
    
    1 <= k <= n <= 100
    1 <= times.length <= 6000
    times[i].length == 3
    1 <= ui, vi <= n
    ui != vi
    0 <= wi <= 100
    All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 * @author miche
 *
 */
public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {
        if(n == 1 && k == 1) return 0;
        int miniTime = 0;
        HashMap<Integer, List<NodeTime>> graph = new HashMap<Integer, List<NodeTime>>(); //record each node's edges and delay time
        HashMap<Integer, Integer> visited = new HashMap<Integer, Integer>(); //record visited node and minimum time to reach this node
        
        //construct the graph
        for(int[] edge: times) {
            int node = edge[0], nxtnode = edge[1], time = edge[2];
            if(!graph.containsKey(node)) {
                graph.put(node, new ArrayList<NodeTime>());
            }
            List<NodeTime> nodetime = graph.get(node);
            nodetime.add(new NodeTime(nxtnode, time));
        }
        
        Deque<Integer> queue = new LinkedList<Integer>();
        //put the start node into queue
        queue.offer(k);
        visited.put(k, 0);
        
        while(!queue.isEmpty()) {
            int node = queue.poll();
            if(graph.containsKey(node)) {
                for(NodeTime nodetime : graph.get(node)) {
                    if(!visited.containsKey(nodetime.node) || visited.get(node) + nodetime.delayTime < visited.get(nodetime.node)) {
                        //if not visited or get a shorter path
                        queue.offer(nodetime.node);
                        visited.put(nodetime.node, visited.get(node) + nodetime.delayTime);
                    }
                }
            }
        }
        
        if(visited.size() != n) return -1;
        for(int i = 1; i <= n; i++) {
            miniTime = Math.max(miniTime, visited.get(i));
        }
        return miniTime;
   }

}

class NodeTime {
    public int node;
    public int delayTime;
    public NodeTime(int node, int time) {
        this.node = node;
        this.delayTime = time;
    }
}
