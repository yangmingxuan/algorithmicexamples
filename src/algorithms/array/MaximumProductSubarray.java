package algorithms.array;

/***
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

    Example 1:
    
    Input: [2,3,-2,4]
    Output: 6
    Explanation: [2,3] has the largest product 6.
    Example 2:
    
    Input: [-2,0,-1]
    Output: 0
    Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * @author miche
 *
 */
public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        int submax = nums[0], max = nums[0], submin = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] < 0) {
                int tmp = submin;
                submin = submax;
                submax = tmp;
            }
            submax = Math.max(submax*nums[i], nums[i]);
            submin = Math.min(submin*nums[i], nums[i]);
            max = Math.max(max, submax);
        }
        return max;
    }

    /***
     * Except for 0, the absolute value of other multiplications is the largest, 
     * just calculate which positive value is the largest from left to right and from right to left.
     * @param nums
     * @return
     */
    public int maxProduct2(int[] nums) {
        int product = 1, max = nums[0];
        //from left to right
        for(int i = 0; i < nums.length; i++) {
            product *= nums[i];
            max = Math.max(max, product);
            if(nums[i] == 0) {
                product = 1;
            }
        }
        product = 1;
        //from right to left
        for(int i = nums.length-1; i >= 0; i--) {
            product *= nums[i];
            max = Math.max(max, product);
            if(nums[i] == 0) {
                product = 1;
            }
        }
        return max;
    }
    
    public static void main(String argv[]) {
        int[] nums = {1,-2,3,-4,-3,-4,-3};
        MaximumProductSubarray mps = new MaximumProductSubarray();
        int max = mps.maxProduct(nums);
        System.out.println(max);
    }
}
