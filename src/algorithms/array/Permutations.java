package algorithms.array;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/***
 * Given a collection of distinct integers, return all possible permutations.

    Example:
    
    Input: [1,2,3]
    Output:
    [
      [1,2,3],
      [1,3,2],
      [2,1,3],
      [2,3,1],
      [3,1,2],
      [3,2,1]
    ]
 * @author miche
 *
 */
public class Permutations {

    public Permutations() {
        // TODO Auto-generated constructor stub
    }

    public List<List<Integer>> permute(int[] nums) {
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
        for(int i = k; i < nums.length; i++) {
            if(i != k) {
                int tmp = nums[k];
                nums[k] = nums[i];
                nums[i] = tmp;
            }
            swap(lret, nums, k+1);
            if(i != k) {
                int tmp = nums[k];
                nums[k] = nums[i];
                nums[i] = tmp;
            }
        }
    }

    public static void main(String[] argv) {
        int[] nums = {1,2,3,4};
        Permutations pmt = new Permutations();
        List<List<Integer>> lret = pmt.permute(nums);
        for(List<Integer> ltmp: lret) {
            for(Integer i: ltmp) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
