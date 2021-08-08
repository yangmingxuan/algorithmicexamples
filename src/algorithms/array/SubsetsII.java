package algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

    The solution set must not contain duplicate subsets. Return the solution in any order.
    
     
    
    Example 1:
    
    Input: nums = [1,2,2]
    Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
    Example 2:
    
    Input: nums = [0]
    Output: [[],[0]]
     
    
    Constraints:
    
    1 <= nums.length <= 10
    -10 <= nums[i] <= 10
 * @author miche
 *
 */
public class SubsetsII {

    /***
     * Recursion
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> lret = new ArrayList<List<Integer>>();
        List<Integer> lint = new ArrayList<Integer>();
        backtrack(lret, lint, nums, 0);
        return lret;
    }

    public void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start) {
        list.add(new ArrayList<Integer>(tempList));
        for(int i = start; i < nums.length; i++) {
            //if the number == the last number, ignore
            if(i != start && nums[i] == nums[i-1]) {
                continue;
            }
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i+1);
            tempList.remove(tempList.size()-1);
        }
    }
}
