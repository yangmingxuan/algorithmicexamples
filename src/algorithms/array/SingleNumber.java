package algorithms.array;

import java.util.Arrays;

/***
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.

    Note:
    
    Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
    
    Example 1:
    
    Input: [2,2,1]
    Output: 1
    Example 2:
    
    Input: [4,1,2,1,2]
    Output: 4
 * @author miche
 *
 */
public class SingleNumber {

    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = nums[0];
        for(int i = 0; i < nums.length; i+=2) {
            if(i == nums.length - 1) {
                ans = nums[i];
                break;
            } else if(nums[i] != nums[i+1]) {
                ans = nums[i];
                break;
            }
        }
        return ans;
    }

    /***
     * 0 XOR a = a; a XOR a = 0
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        int xorbit = 0;
        for(int i = 0; i < nums.length; i++) {
            xorbit ^= nums[i];
        }
        return xorbit;
    }
}
