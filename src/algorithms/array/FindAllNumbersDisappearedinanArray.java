package algorithms.array;

import java.util.ArrayList;
import java.util.List;

/***
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

    Find all the elements of [1, n] inclusive that do not appear in this array.
    
    Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
    
    Example:
    
    Input:
    [4,3,2,7,8,2,3,1]
    
    Output:
    [5,6]
 * @author miche
 *
 */
public class FindAllNumbersDisappearedinanArray {

    /**
     * spece O(n) time O(n)
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<Integer>();
        int counts[] = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            counts[nums[i]-1] += 1;
        }
        
        for(int i = 0; i < counts.length; i++) {
            if(counts[i] == 0) {
                ans.add(i+1);
            }
        }
        
        return ans;
    }

    /**
     * space O(n) time O(n)
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> ans = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; i++) {
            int newIdx = Math.abs(nums[i]) - 1;
            if(nums[newIdx] > 0) {
                nums[newIdx] *= -1;
            }
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                ans.add(i+1);
            }
        }
        
        return ans;
    }
}
