package algorithms.sort;

import java.util.Arrays;

/***
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

    Example 1:
    
    Input: [3,2,1,5,6,4] and k = 2
    Output: 5
    Example 2:
    
    Input: [3,2,3,1,2,4,5,5,6] and k = 4
    Output: 4
    Note:
    You may assume k is always valid, 1 ≤ k ≤ array's length.
 * @author miche
 *
 */
public class KthLargestElementinanArray {

    public int findKthLargest2(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargest(int[] nums, int k) {
        return quickSortK(nums, 0, nums.length-1, k); //the Kth is nums[k-1]
    }

    public int quickSortK(int[] nums, int start, int end, int k) {
        if(start >= end) return nums[k-1];
        int basic = nums[(start+end)/2], i = start, j = end;
        while(i <= j) {
            while(i <= j && nums[i] > basic) {
                //if left is bigger than basic, do not need swap
                i++;
            }
            while(i <= j && nums[j] < basic) {
                //if right is less than basic, do not need swap
                j--;
            }
            if(i <= j) {
                //swap, so that left bigger than basic, and right less than basic
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
                j--;
            }
        }
        
        if(k-1 <= j) {
            //it means that the kth largest element is in the left
            return quickSortK(nums, start, j, k);
        }
        if(k-1 >= i) {
            //it means that the kth largest element is in the right
            return quickSortK(nums, i, end, k);
        }
        
        return nums[k-1];
    }
}
