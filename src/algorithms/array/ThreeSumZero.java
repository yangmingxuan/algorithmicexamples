package algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/***
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

    Note:
    
    The solution set must not contain duplicate triplets.
    
    Example:
    
    Given array nums = [-1, 0, 1, 2, -1, -4],
    
    A solution set is:
    [
      [-1, 0, 1],
      [-1, -1, 2]
    ]
 * @author miche
 *
 */
public class ThreeSumZero {

    public ThreeSumZero() {
        // TODO Auto-generated constructor stub
    }

    /***
     * Runtime: 30 ms, faster than 82.89% of Java online submissions for 3Sum.
     * Memory Usage: 54.6 MB, less than 5.29% of Java online submissions for 3Sum.
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        int l,r;
        for(int i=0;i<nums.length-2;i++) {
            l = i + 1;
            r = nums.length-1;
            while(l < r) {
                if(nums[i]+nums[l]+nums[r] == 0) {
                    List<Integer> lsum3 = new ArrayList<Integer>();
                    lsum3.add(nums[i]);
                    lsum3.add(nums[l]);
                    lsum3.add(nums[r]);
                    //if(!list.contains(lsum3))
                        list.add(lsum3);
                    l++;
                    r--;
                    while(l<r && nums[l]==nums[l-1]) l++;
                    while(l<r && nums[r]==nums[r+1]) r--;
                } else if(nums[i]+nums[l]+nums[r] > 0) {
                    r--;
                } else {
                    l++;
                }
            }
            while(i< nums.length-1 && nums[i]== nums[i+1])
                i++;
        }
        return list;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for(int i=0;i<nums.length-2;i++) {
            for(int j=i+1;j<nums.length-1;j++) {
                for(int k=j+1;k<nums.length;k++) {
                    if(nums[i]+nums[j]+nums[k]==0) {
                        List<Integer> lsum3 = new ArrayList<Integer>();
                        lsum3.add(nums[i]);
                        lsum3.add(nums[j]);
                        lsum3.add(nums[k]);
                        Collections.sort(lsum3);
                        if(!list.contains(lsum3)) list.add(lsum3);
                    }
                }
            }
        }
        return list;
    }
}

/***
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

    Note:
    
    The solution set must not contain duplicate quadruplets.
    
    Example:
    
    Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
    
    A solution set is:
    [
      [-1,  0, 0, 1],
      [-2, -1, 1, 2],
      [-2,  0, 0, 2]
    ]

 * 
 */
class FourSumTarget {

    /***
     * Runtime: 16 ms, faster than 80.01% of Java online submissions for 4Sum.
     * Memory Usage: 39.9 MB, less than 62.32% of Java online submissions for 4Sum.

     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        int l, r;
        for(int i = 0; i < nums.length - 3; i++) {
            for(int j = nums.length - 1; j > i + 2; j--) {
                l = i +1;
                r = j - 1;
                while(l < r) {
                    if(nums[i]+nums[l]+nums[r]+nums[j] == target) {
                        List<Integer> lsum3 = new ArrayList<Integer>();
                        lsum3.add(nums[i]);
                        lsum3.add(nums[l]);
                        lsum3.add(nums[r]);
                        lsum3.add(nums[j]);
                        //if(!list.contains(lsum3))
                            list.add(lsum3);
                        l++;
                        r--;
                        while(l<r && nums[l]==nums[l-1]) l++;
                        while(l<r && nums[r]==nums[r+1]) r--;
                    } else if(nums[i]+nums[l]+nums[r]+nums[j] > target) {
                        r--;
                    } else {
                        l++;
                    }
                }
                while(j > i+2 && nums[j]== nums[j-1])
                    j--;
            }
            while(i< nums.length-3 && nums[i]== nums[i+1])
                i++;
        }
        return list;
    }
}
