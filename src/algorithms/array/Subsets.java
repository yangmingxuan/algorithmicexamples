package algorithms.array;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a set of distinct integers, nums, return all possible subsets (the power set).

    Note: The solution set must not contain duplicate subsets.
    
    Example:
    
    Input: nums = [1,2,3]
    Output:
    [
      [3],
      [1],
      [2],
      [1,2,3],
      [1,3],
      [2,3],
      [1,2],
      []
    ]
 * @author miche
 *
 */
public class Subsets {

    /***
     * Loop
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lret = new ArrayList<List<Integer>>();
        List<Integer> lint = new ArrayList<Integer>();
        lret.add(lint);
        for(int num : nums) {
            //get all the element from lret, and add the new num to them and add 
            int size = lret.size();
            for(int i = 0; i < size; i++) {
                lint = new ArrayList<Integer>(lret.get(i));
                lint.add(num);
                lret.add(lint);
            }
        }

        return lret;
    }

    /***
     * Recursion
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> lret = new ArrayList<List<Integer>>();
        List<Integer> lint = new ArrayList<Integer>();
        backtrack(lret, lint, nums, 0);
        return lret;
    }

    public void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start) {
        list.add(new ArrayList<Integer>(tempList));
        for(int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i+1);
            tempList.remove(tempList.size()-1);
        }
    }
}
