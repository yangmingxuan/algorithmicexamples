package algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    public CombinationSum() {
        // TODO Auto-generated constructor stub
    }

    /***
     * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

        The same repeated number may be chosen from candidates unlimited number of times.
        
        Note:
        
        All numbers (including target) will be positive integers.
        The solution set must not contain duplicate combinations.
        Example 1:
        
        Input: candidates = [2,3,6,7], target = 7,
        A solution set is:
        [
          [7],
          [2,2,3]
        ]
        Example 2:
        
        Input: candidates = [2,3,5], target = 8,
        A solution set is:
        [
          [2,2,2,2],
          [2,3,3],
          [3,5]
        ]
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lret = new ArrayList<List<Integer>>();
        List<Integer> lslt = new ArrayList<Integer>();
        recursiveCount(lret, candidates, target, 0, lslt);
        return lret;
    }

    public void recursiveCount(List<List<Integer>> lret, int[] candidates, int target, int index, List<Integer> lslt) {
        if(target == 0) {
            lret.add(new ArrayList<Integer>(lslt));
            return;
        } else if(target < 0) {
            return;
        }
  
        for(int i = index; i < candidates.length; i++) {
            //from index because number can be repeated
            lslt.add(candidates[i]);
            recursiveCount(lret, candidates, target-candidates[i], i, lslt);
            lslt.remove(lslt.size()-1);
        }
    }

    /***
     * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

        Each number in candidates may only be used once in the combination.
        
        Note:
        
        All numbers (including target) will be positive integers.
        The solution set must not contain duplicate combinations.
        Example 1:
        
        Input: candidates = [10,1,2,7,6,1,5], target = 8,
        A solution set is:
        [
          [1, 7],
          [1, 2, 5],
          [2, 6],
          [1, 1, 6]
        ]
        Example 2:
        
        Input: candidates = [2,5,2,1,2], target = 5,
        A solution set is:
        [
          [1,2,2],
          [5]
        ]

     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> lret = new ArrayList<List<Integer>>();
        List<Integer> lslt = new ArrayList<Integer>();
        Arrays.sort(candidates);
        for(int index = 0; index < candidates.length; index++) {
            if(index > 0 && candidates[index-1] == candidates[index]) {
                continue;
            }
            lslt.add(candidates[index]);
            recursiveCount2(lret, candidates, target-candidates[index], index, lslt);
            lslt.remove(lslt.size()-1);
        }
        return lret;
    }

    public void recursiveCount2(List<List<Integer>> lret, int[] candidates, int target, int index, List<Integer> lslt) {
        if(target == 0) {
            lret.add(new ArrayList<Integer>(lslt));
            return;
        } else if(target < 0) {
            return;
        }
  
        for(int i = index+1; i < candidates.length; i++) {
            if(i > index+1 && candidates[i-1] == candidates[i]) {
                continue;
            }
            lslt.add(candidates[i]);
            recursiveCount2(lret, candidates, target-candidates[i], i, lslt);
            lslt.remove(lslt.size()-1);
        }
    }
}
