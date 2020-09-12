package algorithms.array;

import java.util.ArrayList;
import java.util.List;

/***
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

    Find all the elements that appear twice in this array.
    
    Could you do it without extra space and in O(n) runtime?
    
    Example:
    Input:
    [4,3,2,7,8,2,3,1]
    
    Output:
    [2,3]
 * @author miche
 *
 */
public class FindAllDuplicatesinanArray {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<Integer>();
        if(nums == null || nums.length == 0) return ans;
        for(int num : nums) {
            if(nums[Math.abs(num)-1] < 0) {
                ans.add(Math.abs(num));
            }
            nums[Math.abs(num)-1] *= -1;
        }
        
        return ans;
    }

    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> ans = new ArrayList<Integer>();
        if(nums == null || nums.length == 0) return ans;
        boolean[] hits = new boolean[nums.length];
        for(int num : nums) {
            if(hits[num-1]) {
                ans.add(num);
            }
            hits[num-1] = true;
        }
        return ans;
    }
}
