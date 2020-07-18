package algorithms.array;

/***
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

    (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
    
    Find the minimum element.
    
    You may assume no duplicate exists in the array.
    
    Example 1:
    
    Input: [3,4,5,1,2] 
    Output: 1
    Example 2:
    
    Input: [4,5,6,7,0,1,2]
Output: 0
 * @author miche
 *
 */
public class FindMinimuminRotatedSortedArray {

    public int findMin(int[] nums) {
        if(nums.length == 1) return nums[0];

        int left = 0, right = nums.length - 1;
        //if the array is sorted
        if(nums[left] < nums[right]) {
            return nums[left];
        }
        while(left <= right) {
            int mid = (left+right) / 2;
            //if in the rotated place
            if(nums[mid] > nums[mid+1]) {
                return nums[mid+1];
            }
            if(nums[mid] < nums[mid-1]) {
                return nums[mid];
            }
            
            if(nums[mid] > nums[0]) {
                //the left is sorted, so the minimum in the right
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return Integer.MIN_VALUE;
    }

}
