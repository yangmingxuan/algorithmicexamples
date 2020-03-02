package algorithms.sort;

import java.util.Arrays;

/***
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

    If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
    
    The replacement must be in-place and use only constant extra memory.
    
    Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
    
    1,2,3 → 1,3,2
    3,2,1 → 1,2,3
    1,1,5 → 1,5,1
 * @author miche
 *
 */
public class NextPermutation {

    public NextPermutation() {
        // TODO Auto-generated constructor stub
    }

    /***
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Next Permutation.
     * Memory Usage: 39.3 MB, less than 50.00% of Java online submissions for Next Permutation.
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int i;
        for(i = nums.length - 1; i > 0; i--) {
            if(nums[i-1] < nums[i]) {
                //find the min num larger than nums[i-1]
                int min = nums[i], ptr = i;
                for(int j = i + 1; j < nums.length ; j++) {
                    //Make sure the following arrays are descending
                    if(nums[j] <= min && nums[j] > nums[i-1]) {
                        min = nums[j];
                        ptr = j;
                    }
                }
                nums[ptr] = nums[i-1];
                nums[i-1] = min;
                break;
            }
        }
        //sort from i to end
        reverse(nums, i, nums.length-1);
    }

    public void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }

    public void reverse(int[] nums, int fromIndex, int toIndex) {
        while(fromIndex < toIndex) {
            swap(nums, fromIndex, toIndex);
            fromIndex++;
            toIndex--;
        }
    }
 
    public static void main(String argv[]) {
        int nums[] = {2,3,1,3,3};
        NextPermutation np = new NextPermutation();
        np.nextPermutation(nums);
        for(int i=0; i<nums.length; i++) {
            System.out.print(nums[i]);
        }
    }
}
