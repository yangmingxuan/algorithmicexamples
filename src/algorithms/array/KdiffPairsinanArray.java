package algorithms.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/***
 * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.

    A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
    
    0 <= i, j < nums.length
    i != j
    |nums[i] - nums[j]| == k
    Notice that |val| denotes the absolute value of val.
    
     
    
    Example 1:
    
    Input: nums = [3,1,4,1,5], k = 2
    Output: 2
    Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
    Although we have two 1s in the input, we should only return the number of unique pairs.
    Example 2:
    
    Input: nums = [1,2,3,4,5], k = 1
    Output: 4
    Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
    Example 3:
    
    Input: nums = [1,3,1,5,4], k = 0
    Output: 1
    Explanation: There is one 0-diff pair in the array, (1, 1).
    Example 4:
    
    Input: nums = [1,2,4,4,3,3,0,9,2,3], k = 3
    Output: 2
    Example 5:
    
    Input: nums = [-1,-2,-3], k = 1
    Output: 2
 * @author miche
 *
 */
public class KdiffPairsinanArray {

    public int findPairs(int[] nums, int k) {
        int ans = 0;
        if(k == 0) {
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            for(int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if(entry.getValue() > 1) {
                    ans++;
                }
            }
        } else {
            HashSet<Integer> set = new HashSet<Integer>();
            for(int num: nums) {
                set.add(num);
            }
            for(int num : set) {
                if(set.contains(num+k)) {
                    ans++;
                }
            }
        }
        
        return ans;
    }

}
