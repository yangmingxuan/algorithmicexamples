package algorithms.array;

import java.util.Arrays;

/***
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

 

    Example 1:
    
    Input: nums = [-1,2,1,-4], target = 1
    Output: 2
    Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     
    
    Constraints:
    
    3 <= nums.length <= 10^3
    -10^3 <= nums[i] <= 10^3
    -10^4 <= target <= 10^4
 * @author miche
 *
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE, length = nums.length, ans = 0;
        Arrays.sort(nums);
        for(int i = 0; i < length-2 && diff != 0; i++) {
            int left = i+1, right = length - 1;
            while(left < right) {
                ans = nums[i]+nums[left]+nums[right];
                if(Math.abs(ans-target) < Math.abs(diff))
                    diff = ans - target;
                if(diff == 0) break;
                else if(ans - target < 0) left++;
                else right--;
            }
        }
        return target+diff;
    }

}
