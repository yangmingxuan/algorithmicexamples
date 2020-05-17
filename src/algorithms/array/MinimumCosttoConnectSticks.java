package algorithms.array;

import java.util.Arrays;
import java.util.PriorityQueue;

/***
 * You have some sticks with positive integer lengths.

    You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.  You perform this action until there is one stick remaining.
    
    Return the minimum cost of connecting all the given sticks into one stick in this way.
    
     
    
    Example 1:
    
    Input: sticks = [2,4,3]
    Output: 14
    Explanation: 1st time 2+3 cost 5 [4,5]; 2nd time 4+5 cost 9[9]; all cost 5+9 = 14;
    
    Example 2:
    
    Input: sticks = [1,8,3,5]
    Output: 30
    Explanation: 1st time 1+3 cost 4[4,5,8]; 2nd time 4+5 cost 9[8,9]; 3rd time 8+9 cost 17[17]
                 all cost 4+9+17 = 30
     
    
    Constraints:
    
    1 <= sticks.length <= 10^4
    1 <= sticks[i] <= 10^4
 * @author miche
 *
 */
public class MinimumCosttoConnectSticks {

    public int connectSticks(int[] sticks) {
        int allCost = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)->a-b);
        for(int i = 0; i < sticks.length; i++) {
            pq.offer(sticks[i]);
        }
        
        while(pq.size() > 1) {
            int stick1 = pq.poll();
            int stick2 = pq.poll();
            int cost = stick1 + stick2;
            allCost += cost;
            pq.offer(cost);
        }
        
        return allCost;
    }

    public int connectSticks2(int[] sticks) {
        if(sticks.length < 2) return 0;
        int allCost = 0;
        Arrays.sort(sticks);
        int[] midCost = new int[sticks.length - 1];
        Arrays.fill(midCost, Integer.MAX_VALUE);
        
        int i = 0, j = 0, midCur = 0, cur = 0;
        while(cur < sticks.length - 1) {
            int cost = 0, times = 2;
            while(times > 0) {
                if(i < sticks.length && sticks[i] <= midCost[j]) {
                    cost += sticks[i];
                    i++;
                } else if(j < midCost.length) {
                    cost += midCost[j];
                    j++;
                }
                times--;
            }
            //save the mid cost to midCost
            midCost[midCur++] = cost;
            allCost += cost;
            cur++;
        }
        
        return allCost;
    }
}
