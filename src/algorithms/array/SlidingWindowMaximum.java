package algorithms.array;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/***
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

    Follow up:
    Could you solve it in linear time?
    
    Example:
    
    Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
    Output: [3,3,5,5,6,7] 
    Explanation: 
    
    Window position                Max
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7
     
    
    Constraints:
    
    1 <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4
    1 <= k <= nums.length
 * @author miche
 *
 */
public class SlidingWindowMaximum {

    /***
     * Use TreeMap (lastKey() firstKey())
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length+1-k];
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for(int i = 0; i < k; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        
        for(int i = k; i < nums.length; i++) {
            ans[i-k] = map.lastKey();
            //move window
            int tmp = map.get(nums[i-k]);
            if(tmp == 1) {
                map.remove(nums[i-k]);
            } else {
                map.put(nums[i-k], tmp - 1);
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        ans[nums.length-k] = map.lastKey();
        
        return ans;
    }

    /***
     * Use LinkedList
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int[] ans = new int[nums.length+1-k];
        Deque<Integer> que = new LinkedList<Integer>();
        for(int i = 0; i < k; i++) {
            while(!que.isEmpty() && nums[que.peekLast()] <= nums[i]) {
                que.removeLast();
            }
            que.addLast(i);
        }
        
        for(int i = k; i < nums.length; i++) {
            ans[i-k] = nums[que.peekFirst()];
            //move the window
            while(!que.isEmpty() && que.peekFirst() <= i-k) {
                que.removeFirst();
            }
            while(!que.isEmpty() && nums[que.peekLast()] <= nums[i]) {
                que.removeLast();
            }
            que.addLast(i);
        }
        ans[nums.length-k] = nums[que.peekFirst()];

        return ans;
    }

    public int[] maxSlidingWindow3(int[] nums, int k) {
        int[] ans = new int[nums.length+1-k];
        int index = 0;
        for(int i = 0; i < nums.length; i++){
            if(i < k){
                if(nums[i] > nums[index])
                    index = i;
            }else{
                ans[i-k] = nums[index];
                if(nums[i] >= nums[index]){
                    index = i;
                }
                else if(index < i-k+1){
                    int max = Integer.MIN_VALUE;
                    for(int j = i-k+1; j <= i; j++){
                        if(nums[j] > max){
                            max = nums[j];
                            index = j;
                        }
                    }
                }
            }
        }
        ans[nums.length-k] = nums[index];
        
        return ans;
    }
}
