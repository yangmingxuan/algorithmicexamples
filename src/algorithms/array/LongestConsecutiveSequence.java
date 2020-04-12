package algorithms.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/***
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

    Your algorithm should run in O(n) complexity.
    
    Example:
    
    Input: [100, 4, 200, 1, 3, 2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * @author miche
 *
 */
public class LongestConsecutiveSequence {

    /***
     * Time is approximately O(n) 
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int longest = 1;
        Set<Integer> numset = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++) {
            numset.add(nums[i]);
        }
        for(int num : numset) {
            if(numset.contains(num-1)) continue;
            int count = 1;
            while(numset.contains(++num)) {
                count++;
            }
            longest = Math.max(longest, count);
        }
        
        return longest;
    }

    public int longestConsecutive2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        Arrays.parallelSort(nums); //O(nlogn)
        int longest = 1, count = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i-1]) continue;
            if(nums[i] == nums[i-1] + 1) {
                count++;
            } else {
                longest = Math.max(longest, count);
                count = 1;
            }
        }
        
        return Math.max(longest, count);
    }
}
