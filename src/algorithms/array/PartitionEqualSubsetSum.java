package algorithms.array;

/***
 * Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

 

    Example 1:
    
    Input: nums = [1,5,11,5]
    Output: true
    Explanation: The array can be partitioned as [1, 5, 5] and [11].
    Example 2:
    
    Input: nums = [1,2,3,5]
    Output: false
    Explanation: The array cannot be partitioned into equal sum subsets.
     
    
    Constraints:
    
    1 <= nums.length <= 200
    1 <= nums[i] <= 100
 * @author miche
 *
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int ele: nums) sum += ele;
        if(sum % 2 != 0) return false;
        int n = nums.length;
        sum /= 2;
        //Boolean[][] dp = new Boolean[n][sum+1];
        Boolean[] dp = new Boolean[sum+1];
        
        return backtrack(nums, 0, sum, dp);
    }

    private boolean backtrack(int[] nums, int idx, int sum, Boolean[] dp) {
        if(sum == 0) return true;
        if(idx >= nums.length || sum < 0) return false;
        if(dp[sum] != null) return dp[sum];
        return dp[sum] = backtrack(nums, idx+1, sum-nums[idx], dp) || backtrack(nums, idx+1, sum, dp);
    }
}
