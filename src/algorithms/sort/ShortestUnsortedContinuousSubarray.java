package algorithms.sort;

import java.util.Arrays;
import java.util.Stack;

/***
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

    You need to find the shortest such subarray and output its length.
    
    Example 1:
    Input: [2, 6, 4, 8, 10, 9, 15]
    Output: 5
    Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
    Note:
    Then length of the input array is in range [1, 10,000].
    The input array may contain duplicates, so ascending order here means <=.
 * @author miche
 *
 */
public class ShortestUnsortedContinuousSubarray {

    public int findUnsortedSubarray(int[] nums) {
        int[] sums = nums.clone();
        int left = nums.length - 1, right = 0;
        Arrays.parallelSort(sums);
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != sums[i]) {
                left = Math.min(left, i);
                right = Math.max(right, i);
            }
        }
        
        return right > left ? right-left+1 : 0;
    }

    public int findUnsortedSubarray2(int[] nums) {
        Stack<Integer> stack = new Stack<Integer>();
        int left = nums.length - 1, right = 0;
        for(int i = 0; i < nums.length; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                left = Math.min(left, stack.pop());
            }
            stack.push(i);
        }
        stack.clear();
        for(int i = nums.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                right = Math.max(right, stack.pop());
            }
            stack.push(i);
        }
        
        return right > left ? right-left+1 : 0;
    }

    public int findUnsortedSubarray3(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < nums.length-1 && nums[left] <= nums[left+1]) {
            left++;
        }
        if(left == nums.length-1) return 0;
        
        while(right > 0 && nums[right] >= nums[right-1]) {
            right--;
        }
        
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        
        for(int i = left; i <= right; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        
        int tmp = 0;
        while(tmp < left && nums[tmp] <= min) tmp++;  //find the min to left
        left = tmp;
        
        tmp = nums.length-1;
        while(tmp > right && nums[tmp] >= max) tmp--; //find the max to right
        right = tmp;
        
        
        return right-left+1;
    }
}
