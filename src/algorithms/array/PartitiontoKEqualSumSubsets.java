package algorithms.array;

import java.util.HashMap;

/***
 * Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.

 

    Example 1:
    
    Input: nums = [4,3,2,3,5,2,1], k = 4
    Output: true
    Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
    Example 2:
    
    Input: nums = [1,2,3,4], k = 3
    Output: false
     
    
    Constraints:
    
    1 <= k <= nums.length <= 16
    1 <= nums[i] <= 104
    The frequency of each element is in the range [1, 4].
 * @author miche
 *
 */
public class PartitiontoKEqualSumSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        
        int quotient = sum / k;
        if(sum - quotient * k != 0) {
            return false;
        }
        boolean[] visited = new boolean[nums.length];
        return backtrace(nums, k, 0, quotient, quotient, visited);
    }

    private boolean backtrace(int[] nums, int k, int start, int target, int sum, boolean[] visited) {
        //If a pile of items is divided into K parts evenly, and the first K-1 parts are distributed, the remaining 1 part must also be an average number
        if(k == 1) return true;
        for(int i = start; i < nums.length; i++) {
            if(!visited[i] && nums[i] <= target) {
                visited[i] = true;
                if(nums[i] == target) {
                    if(backtrace(nums, k-1, 0, sum, sum, visited)) return true;
                } else {
                    if(backtrace(nums, k, i+1, target-nums[i], sum, visited)) return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}
