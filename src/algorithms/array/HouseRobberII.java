package algorithms.array;

/***
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

    Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
    
    Example 1:
    
    Input: [2,3,2]
    Output: 3
    Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
                 because they are adjacent houses.
    Example 2:
    
    Input: [1,2,3,1]
    Output: 4
    Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
                 Total amount you can rob = 1 + 3 = 4.
 * @author miche
 *
 */
public class HouseRobberII {

    /***
     * cycle means that if you rob the first one, you cannot rob the last on, so
     * think of the array as two parts, the first one from 0 to n-2, the second from 1 to n-1
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        int preMax1 = nums[0], preMax2 = 0;
        for(int i = 1; i < nums.length-1; i++) {
            int tmp = preMax1;
            preMax1 = Math.max(nums[i]+preMax2, preMax1);
            preMax2 = tmp;
        }
        int firstPart = preMax1;
        preMax1 = nums[1];
        preMax2 = 0;
        for(int i = 2; i < nums.length; i++) {
            int tmp = preMax1;
            preMax1 = Math.max(nums[i]+preMax2, preMax1);
            preMax2 = tmp;
        }
        int secondPart = preMax1;
        return Math.max(firstPart, secondPart);
    }

}
