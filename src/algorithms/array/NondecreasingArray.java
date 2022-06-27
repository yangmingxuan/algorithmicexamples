package algorithms.array;
/***
 * Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most one element.

    We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).
    
    
    
    Example 1:
    
    Input: nums = [4,2,3]
    Output: true
    Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
    Example 2:
    
    Input: nums = [4,2,1]
    Output: false
    Explanation: You can't get a non-decreasing array by modify at most one element.
    
    
    Constraints:
    
    n == nums.length
    1 <= n <= 10^4
    -10^5 <= nums[i] <= 10^5

 * @author miche
 *
 */
public class NondecreasingArray {

    public boolean checkPossibility(int[] nums) {
        int pos = -1;
        for(int i = 0; i < nums.length-1; i++) {
            if(nums[i] > nums[i+1]) {
                if(pos != -1) return false;
                else pos = i;
            }
        }
        if(pos == -1 || pos == 0 || pos == nums.length-2) {
            //no any modify or (modify 0 or length-2 it means only modify once)
            return true;
        } else {
            return nums[pos-1] <= nums[pos+1] || nums[pos] <= nums[pos+2];
        }
    }

}
