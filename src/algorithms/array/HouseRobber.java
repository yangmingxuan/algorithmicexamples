package algorithms.array;

/***
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

    Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
    
    Example 1:
    
    Input: [1,2,3,1]
    Output: 4
    Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
                 Total amount you can rob = 1 + 3 = 4.
    Example 2:
    
    Input: [2,7,9,3,1]
    Output: 12
    Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
                 Total amount you can rob = 2 + 9 + 1 = 12.
 * @author miche
 *
 */
public class HouseRobber {

    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        //if(nums.length == 1) return nums[0];
        int[] dp = new int[nums.length+1];
        dp[0] = 0;
        dp[1] = nums[0];
        //dp[2] = Math.max(nums[1]+dp[0], dp[1]);
        for(int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(nums[i-1]+dp[i-2], dp[i-1]);
        }
        return dp[nums.length];
    }

    /***
     * replace dp with 2 variables 
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        //if(nums.length == 1) return nums[0];
        int preMax1 = nums[0], preMax2 = 0;
        for(int i = 1; i < nums.length; i++) {
            int tmp = preMax1;
            preMax1 = Math.max(nums[i]+preMax2, preMax1);
            preMax2 = tmp;
        }
        return preMax1;
    }
    public static void main(String[] argv) {
        int[] nums = {226,174,214,16,218,48,153,131,128,17,157,142,88,43,37,157,43,221,191,68,206,23,225,82,54,118,111,46,80,49,245,63,25,194,72,80,143,55,209,18,55,122,65,66,177,101,63,201,172,130,103,225,142,46,86,185,62,138,212,192,125,77,223,188,99,228,90,25,193,211,84,239,119,234,85,83,123,120,131,203,219,10,82,35,120,180,249,106,37,169,225,54,103,55,166,124};
        HouseRobber hr = new HouseRobber();
        int count = hr.rob2(nums);
        System.out.println(count);
    }
}
