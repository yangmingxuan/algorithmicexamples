package algorithms.array;

import java.util.Arrays;
import java.util.PriorityQueue;

/***
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

    You're given the startTime , endTime and profit arrays, you need to output the maximum profit you can take such that there are no 2 jobs in the subset with overlapping time range.
    
    If you choose a job that ends at time X you will be able to start another job that starts at time X.
    
     
    
    Example 1:
    
    
    
    Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
    Output: 120
    Explanation: The subset chosen is the first and fourth job. 
    Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
    Example 2:
    
    
    
    
    Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
    Output: 150
    Explanation: The subset chosen is the first, fourth and fifth job. 
    Profit obtained 150 = 20 + 70 + 60.
    Example 3:
    
    
    
    Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
    Output: 6
     
    
    Constraints:
    
    1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
    1 <= startTime[i] < endTime[i] <= 10^9
    1 <= profit[i] <= 10^4
 * @author miche
 *
 */
public class MaximumProfitinJobScheduling {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int accum = 0, n = profit.length;
        int[][] pro = new int[n][3];
        for(int i = 0; i < n; i++) {
            pro[i][0] = startTime[i];
            pro[i][1] = endTime[i];
            pro[i][2] = profit[i];
        }
        
        Arrays.sort(pro, (a,b)->a[0]!=b[0]?a[0]-b[0]:(a[1]!=b[1]?a[1]-b[1]:a[2]-b[2]));
        
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b)->a[0]-b[0]); //save endtime and profit
        for(int i = 0; i < n; i++) {
            while(!pq.isEmpty() && pq.peek()[0] <= pro[i][0]) {
                int[] endtimeProfitPair = pq.poll();
                accum = Math.max(accum, endtimeProfitPair[1]);
            }
            int[] endtimeProfitPair = {pro[i][1], accum + pro[i][2]};
            pq.offer(endtimeProfitPair);
        }
        int ans = 0;
        while(!pq.isEmpty()) {
            ans = Math.max(ans, pq.poll()[1]);
        }
        return ans;
    }

}
