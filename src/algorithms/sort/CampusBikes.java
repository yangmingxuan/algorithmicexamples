package algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/***
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

    Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.
    
    The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
    
    Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.
    
     
    
    Example 1:
    
    
    
    Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
    Output: [1,0]
    Explanation: 
    Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
    Example 2:
    
    
    
    Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
    Output: [0,2,1]
    Explanation: 
    Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
     
    
    Note:
    
    0 <= workers[i][j], bikes[i][j] < 1000
    All worker and bike locations are distinct.
    1 <= workers.length <= bikes.length <= 1000
 * @author miche
 *
 */
public class CampusBikes {

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers == null || bikes == null) return null;
        int[] ans = new int[workers.length];
        Arrays.fill(ans, -1);
        TreeMap<Integer, List<int[]>> distances = new TreeMap<Integer, List<int[]>>();
        HashSet<Integer> assignBike = new HashSet<Integer>();
        
        //calculate all the distances between workers and bikes
        for(int i = 0; i < workers.length; i++) {
            for(int j = 0; j < bikes.length; j++) {
                int distance = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                List<int[]> pairs;
                if(!distances.containsKey(distance)) {
                    pairs = new ArrayList<int[]>();
                    distances.put(distance, pairs);
                } else {
                    pairs = distances.get(distance);
                }
                pairs.add(new int[] {i,j});
            }
        }

        int count = 0;
        for(Map.Entry<Integer, List<int[]>> entry : distances.entrySet()) {
            List<int[]> pairs = entry.getValue();
            for(int[] pair : pairs) {
                if(ans[pair[0]] == -1 && !assignBike.contains(pair[1])) {
                    //if the worker has not been assigned to the bike,
                    //and the bike has not been assigned
                    ans[pair[0]] = pair[1];
                    assignBike.add(pair[1]);
                    count++;
                    if(count >= workers.length) break;
                }
            }
            if(count >= workers.length) break;
        }
        
        return ans;
    }

    public int[] assignBikes2(int[][] workers, int[][] bikes) {
        if(workers == null || bikes == null) return null;
        int[] ans = new int[workers.length];
        
        //PriorityQueue order by distance, workerIdx, bikeIdx
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a,b)->a[0]!=b[0]?a[0]-b[0]:
                (a[1]!=b[1]?a[1]-b[1]:a[2]-b[2]));
        boolean[] assignBike = new boolean[bikes.length];
        for(int i = 0; i < workers.length; i++) {
            int[] pair = getMinDistancePair(workers[i], i, bikes, assignBike);
            if(pair[2] != -1) queue.offer(pair);
        }
        
        int count = 0;
        while(!queue.isEmpty() && count < workers.length) {
            int[] pair = queue.poll();
            if(!assignBike[pair[2]]) {
                ans[pair[1]] = pair[2];
                assignBike[pair[2]] = true;
                count++;
            } else {
                pair = getMinDistancePair(workers[pair[1]], pair[1], bikes, assignBike);
                if(pair[2] != -1) queue.offer(pair);
            }
        }
        
        return ans;
    }

    public int[] getMinDistancePair(int[] worker, int workerIdx, int[][]bikes, boolean[] assignBike) {
        int minDist = Integer.MAX_VALUE, minBikeIdx = -1;
        for(int j = 0; j < bikes.length; j++) {
            if(!assignBike[j]) {
                //the bike has not been assigned
                int dist = Math.abs(worker[0]-bikes[j][0])+Math.abs(worker[1]-bikes[j][1]);
                if(dist < minDist) {
                    minDist = dist;
                    minBikeIdx = j;
                }
            }
        }
        return new int[] {minDist, workerIdx, minBikeIdx};
    }
}
