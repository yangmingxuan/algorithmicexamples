package algorithms.array;

import java.util.Deque;
import java.util.LinkedList;

/***
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].

    Return true if there is a 132 pattern in nums, otherwise, return false.
    
    
    
    Example 1:
    
    Input: nums = [1,2,3,4]
    Output: false
    Explanation: There is no 132 pattern in the sequence.
    Example 2:
    
    Input: nums = [3,1,4,2]
    Output: true
    Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
    Example 3:
    
    Input: nums = [-1,3,2,0]
    Output: true
    Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
    
    
    Constraints:
    
    n == nums.length
    1 <= n <= 2 * 10^5
    -10^9 <= nums[i] <= 10^9

 * @author miche
 *
 */
public class Find132Pattern {

    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if(n < 3) return false;
        int[] minval = new int[n];
        minval[0] = nums[0];
        for(int i = 1; i < n; i++) {
            //record the minimum number from the left
            minval[i] = Math.min(nums[i], minval[i-1]);
        }
        Deque<Integer> stack = new LinkedList<Integer>();
        for(int j = n-1; j >= 0; j--) {
            while(!stack.isEmpty() && stack.peekLast() <= minval[j]) stack.pollLast();
            if(!stack.isEmpty() && stack.peekLast() < nums[j]) {
                //now minval[j] < stack[-1] < nums[j]
                return true;
            }
            stack.offerLast(nums[j]);
        }
        return false;
    }

}
