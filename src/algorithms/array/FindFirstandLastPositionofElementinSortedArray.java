package algorithms.array;

public class FindFirstandLastPositionofElementinSortedArray {

    /***
     * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

        Your algorithm's runtime complexity must be in the order of O(log n).
        
        If the target is not found in the array, return [-1, -1].
        
        Example 1:
        
        Input: nums = [5,7,7,8,8,10], target = 8
        Output: [3,4]
        Example 2:
        
        Input: nums = [5,7,7,8,8,10], target = 6
        Output: [-1,-1]
     */
    public FindFirstandLastPositionofElementinSortedArray() {
        // TODO Auto-generated constructor stub
    }

    public int[] searchRange(int[] nums, int target) {
        int[] ret = {-1, -1};
        if(nums == null || nums.length == 0) return ret;

        //find the first position
        int leftPosition = positionOfArray(nums, target, true);
        if(leftPosition == -1 || nums[leftPosition] != target) {
            return ret;
        }
        ret[0] = leftPosition;
        //find the last position
        ret[1] = positionOfArray(nums, target, false);
        return ret;
    }

    public int positionOfArray(int[] nums, int target, boolean leftMost) {
        int start = 0, end = nums.length-1, mid;
        while(start+1 < end) {
            mid = start + (end - start) / 2;
            if(nums[mid] > target || (leftMost && nums[mid] == target)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if(leftMost) {
            if(nums[start] == target) {
                return start;
            } else if(nums[end] == target) {
                return end;
            }
        } else {
            if(nums[end] == target) {
                return end;
            } else if(nums[start] == target) {
                return start;
            }
        }
        return -1;
    }
}
