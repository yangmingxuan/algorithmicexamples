package algorithms.array;

import java.util.HashMap;

/***
 * You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.

Return the maximum score you can get by erasing exactly one subarray.

An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).



Example 1:

Input: nums = [4,2,4,5,6]
Output: 17
Explanation: The optimal subarray here is [2,4,5,6].
Example 2:

Input: nums = [5,2,1,2,5,2,1,2,5]
Output: 8
Explanation: The optimal subarray here is [5,2,1] or [1,2,5].


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^4

 * @author michel
 *
 */
public class MaximumErasureValue {

    public int maximumUniqueSubarray(int[] nums) {
        int ans = 0, l = 0, r = 0, currsum = 0;
        HashMap<Integer, Integer> dict = new HashMap<Integer, Integer>();
        for(r = 0; r < nums.length; r++) {
            currsum += nums[r];
            dict.put(nums[r], dict.getOrDefault(nums[r], 0) + 1);
            while(dict.get(nums[r]) != 1) {
                currsum -= nums[l];
                dict.put(nums[l], dict.get(nums[l])-1);
                l++;
            }
            ans = Math.max(ans, currsum);
        }
        return ans;
    }

    public int maximumUniqueSubarray2(int[] nums) {
        int ans = 0, l = 0, r = 0, currsum = 0;
        int[] dict = new int[10001];
        for(r = 0; r < nums.length; r++) {
            currsum += nums[r];
            dict[nums[r]]++;
            while(dict[nums[r]] != 1) {
                currsum -= nums[l];
                dict[nums[l++]]--;
            }
            ans = Math.max(ans, currsum);
        }
        return ans;
    }
}
