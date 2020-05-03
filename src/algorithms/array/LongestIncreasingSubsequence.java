package algorithms.array;

import java.util.TreeSet;

/***
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

    Example:
    
    Input: [10,9,2,5,3,7,101,18]
    Output: 4 
    Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
    Note:
    
    There may be more than one LIS combination, it is only necessary for you to return the length.
    Your algorithm should run in O(n2) complexity.
    Follow up: Could you improve it to O(n log n) time complexity?
 * @author miche
 *
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        //dp[i] save the number of the array before the subscript is smaller than its value
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for(int i = 1; i < nums.length; i++) {
            int submax = 0;
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    submax = Math.max(submax, dp[j]);
                }
            }
            dp[i] = submax + 1;
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    /***
     * Take advantage of the ordered features of TreeSet
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        TreeSet<Integer> set = new TreeSet<Integer>();
        for(int num : nums) {
            Integer cell = set.ceiling(num);
            if(cell != null) set.remove(cell);
            set.add(num);
        }
        
        return set.size();
    }
}
