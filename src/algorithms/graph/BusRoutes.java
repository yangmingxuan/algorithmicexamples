package algorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

    We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.
    
    Example:
    Input: 
    routes = [[1, 2, 7], [3, 6, 7]]
    S = 1
    T = 6
    Output: 2
    Explanation: 
    The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
     
    
    Constraints:
    
    1 <= routes.length <= 500.
    1 <= routes[i].length <= 10^5.
    0 <= routes[i][j] < 10 ^ 6.
 * @author miche
 *
 */
public class BusRoutes {

    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(routes == null) return -1;
        if(S == T) return 0;
        int count = 1;
        //map key = stop, value = list of routes which include the stop
        HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        HashSet<Integer> visited = new HashSet<Integer>();
        boolean[] visitedRoute = new boolean[routes.length];
        Queue<Integer> queue = new LinkedList<Integer>();
        
        //construct graph
        for(int i = 0; i < routes.length; i++) {
            for(int stop : routes[i]) {
                List<Integer> busroute; // = graph.computeIfAbsent(stop, x->new ArrayList<Integer>());
                if(!graph.containsKey(stop)) {
                    busroute = new ArrayList<Integer>();
                    graph.put(stop, busroute);
                } else {
                    busroute = graph.get(stop);
                }
                busroute.add(i);
            }
        }
        
        queue.offer(S);
        visited.add(S);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int stop = queue.poll();
                for(int route : graph.get(stop)) {
                    if(visitedRoute[route]) {
                        continue;
                    }
                    visitedRoute[route] = true;
                    
                    for(int nxtStop : routes[route]) {
                        if(visited.contains(nxtStop)) {
                            continue;
                        }
                        if(nxtStop == T) {
                            return count;
                        }
                        queue.offer(nxtStop);
                        visited.add(nxtStop);
                    }
                }
            }
            count++;
        }

        return -1;
    }

    /***
     * Time Limit Exceeded
     * @param routes
     * @param S
     * @param T
     * @return
     */
    public int numBusesToDestination2(int[][] routes, int S, int T) {
        if(routes == null) return -1;
        if(S == T) return 0;
        int count = 0;
        HashMap<Integer, List<int[]>> graph = new HashMap<Integer, List<int[]>>();
        HashSet<Integer> visited = new HashSet<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();
        
        //construct graph
        for(int[] route : routes) {
            for(int stop : route) {
                List<int[]> busroute;
                if(!graph.containsKey(stop)) {
                    busroute = new ArrayList<int[]>();
                    graph.put(stop, busroute);
                } else {
                    busroute = graph.get(stop);
                }
                busroute.add(route);
            }
        }
        
        queue.offer(S);
        visited.add(S);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int stop = queue.poll();
                if(stop == T) return count;
                if(!graph.containsKey(stop)) continue;
                List<int[]> busroute = graph.get(stop);
                for(int[] route : busroute) {
                    for(int nextStop : route) {
                        if(!visited.contains(nextStop)) {
                            queue.offer(nextStop);
                            visited.add(nextStop);
                        }
                    }
                }
            }
            count++;
        }

        return -1;
    }

    public int numBusesToDestination3(int[][] routes, int S, int T) {
        if(routes == null) return -1;
        if(S == T) return 0;
        //first find max number of stops
        int max=0;
        for(int [] stops: routes)
        {
            for(int stop: stops)
            max = Math.max(max, stop);
        }
        
        int[] busCount = new int[max+1];
        Arrays.fill(busCount, max);
        busCount[S]=0;
        boolean update=true;
        while(update)
        {
            update=false;
            //BFS
            
            for(int[] route: routes)
            {
                
                int min = max;
                
                for(int stop : route)
                {
                    min = Math.min(min, busCount[stop]);
                }
                
                min++;
                
                for(int stop:route)
                {
                    if(busCount[stop]>min)
                    {
                        busCount[stop]=min;
                        update=true;
                    }
                }
            }
            
        }
        
        return busCount[T]==max?-1:busCount[T];
    }

}
