package algorithms.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.

    Example:
    
    Input: [1,1,2]
    Output:
    [
      [1,1,2],
      [1,2,1],
      [2,1,1]
    ]
 * @author miche
 *
 */
public class PermutationsII {

    public PermutationsII() {
        // TODO Auto-generated constructor stub
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lret = new ArrayList<List<Integer>>();
        swap(lret, nums, 0);
        return lret;
    }

    public void swap(List<List<Integer>> lret, int[] nums, int k) {
        if(k == nums.length - 1) {
            List<Integer> ltmp =  new ArrayList<Integer>();//Arrays.stream(nums).boxed().collect(Collectors.toList());
            for(int ele:nums) {
                ltmp.add(ele); //quicker than Arrays.stream(nums).boxed().collect(Collectors.toList())
            }
            lret.add(ltmp);
        }
        Set<Integer> had = new HashSet<Integer>();
        for(int i = k; i < nums.length; i++) {
            if(!had.contains(nums[i])) {
                had.add(nums[i]);
                if(i != k && nums[i] != nums[k]) {
                    int tmp = nums[k];
                    nums[k] = nums[i];
                    nums[i] = tmp;
                }
                swap(lret, nums, k+1);
                if(i != k && nums[i] != nums[k]) {
                    int tmp = nums[k];
                    nums[k] = nums[i];
                    nums[i] = tmp;
                }
            }
        }
    }

    public static void main(String[] argv) {
        int[] nums = {1,1,2,2};
        PermutationsII pmt = new PermutationsII();
        List<List<Integer>> lret = pmt.permuteUnique(nums);
        for(List<Integer> ltmp: lret) {
            for(Integer i: ltmp) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
