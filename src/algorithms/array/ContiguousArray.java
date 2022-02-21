package algorithms.array;

import java.util.HashMap;

/***
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.

 

    Example 1:
    
    Input: nums = [0,1]
    Output: 2
    Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
    Example 2:
    
    Input: nums = [0,1,0]
    Output: 2
    Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
     
    
    Constraints:
    
    1 <= nums.length <= 10^5
    nums[i] is either 0 or 1.
 * @author miche
 *
 */
public class ContiguousArray {

    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxlen = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                sum--;
            } else {
                sum++;
            }
            
            if(map.containsKey(sum)) {
                //If there have been equal values before, it means that the sum from the next position to the current position is 0
                maxlen = Math.max(maxlen, i-map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        
        return maxlen;
    }

}
