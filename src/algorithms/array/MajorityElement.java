package algorithms.array;

import java.util.Arrays;

/***
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

    You may assume that the array is non-empty and the majority element always exist in the array.
    
    Example 1:
    
    Input: [3,2,3]
    Output: 3
    Example 2:
    
    Input: [2,2,1,1,1,2,2]
    Output: 2
 * @author miche
 *
 */
public class MajorityElement {

    /**
     * if a num's count more then [n/2], sort it and it will be int the n/2 position
     * Time O(nlgn)
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    /***
     * Boyer-Moore Voting Algorithm
     * Add 1 to the possible number, and subtract 1 from the number that is not. 
     * When the count is 0, the next value is counted. The last num won is the count more than n/2
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        int count = 0, majority = nums[0];
        for(int num : nums) {
            if(count == 0) {
                majority = num;
            }
            count += (majority == num) ? 1 : -1;
        }
        return majority;
    }
}
