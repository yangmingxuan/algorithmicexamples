package algorithms.array;

/***
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

    Example:
    
    Input:  [1,2,3,4]
    Output: [24,12,8,6]
    Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.
    
    Note: Please solve it without division and in O(n).
    
    Follow up:
    Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 * @author miche
 *
 */
public class ProductofArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] L = new int[length];
        int[] R = new int[length];
        L[0] = 1;
        for(int i = 1; i < length; i++) {
            L[i] = nums[i-1] * L[i-1];
        }
        R[length-1] = 1;
        for(int i = length - 2; i >= 0; i--) {
            R[i] = nums[i+1] * R[i+1];
        }
        int[] ans = new int[length];
        for(int i = 0; i < length; i++) {
            ans[i] = L[i] * R[i];
        }
        return ans;
    }

    /***
     * Simplify the R array
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        int length = nums.length;
        int[] ans = new int[length];
        ans[0] = 1;
        for(int i = 1; i < length; i++) {
            ans[i] = nums[i-1] * ans[i-1];
        }
        int R = 1;
        for(int i = length - 1; i >= 0; i--) {
            ans[i] *= R;
            R *= nums[i];
        }
        return ans;
    }
}
