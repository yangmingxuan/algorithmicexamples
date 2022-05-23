package algorithms.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/***
 * You are given an integer array nums and an integer k.

    In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
    
    Return the maximum number of operations you can perform on the array.
    
     
    
    Example 1:
    
    Input: nums = [1,2,3,4], k = 5
    Output: 2
    Explanation: Starting with nums = [1,2,3,4]:
    - Remove numbers 1 and 4, then nums = [2,3]
    - Remove numbers 2 and 3, then nums = []
    There are no more pairs that sum up to 5, hence a total of 2 operations.
    Example 2:
    
    Input: nums = [3,1,3,4,3], k = 6
    Output: 1
    Explanation: Starting with nums = [3,1,3,4,3]:
    - Remove the first two 3's, then nums = [1,4,3]
    There are no more pairs that sum up to 6, hence a total of 1 operation.
     
    
    Constraints:
    
    1 <= nums.length <= 10^5
    1 <= nums[i] <= 10^9
    1 <= k <= 10^9
 * @author miche
 *
 */
public class MaxNumberofKSumPairs {

    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = nums.length-1, count = 0;
        while(left < right) {
            if(nums[left]+nums[right] == k) {
                count++;
                left++;
                right--;
            } else if(nums[left]+nums[right] < k) {
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    public int maxOperations2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int n : nums) {
            map.put(n, map.getOrDefault(n, 0)+1);
        }
        int ans = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int c = entry.getKey();
            int diff = k - c;
            //if(!map.containsKey(diff)) continue;
            if(c == diff) {
                ans += entry.getValue() / 2;
            } else if(c < diff) {
                ans += Math.min(entry.getValue(), map.getOrDefault(diff,0));
            }
        }
        return ans;
    }
}
