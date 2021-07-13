package algorithms.array;
/***
 * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.

 

    Example 1:
    
    Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
    Output: 3
    Explanation: The repeated subarray with maximum length is [3,2,1].
    Example 2:
    
    Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
    Output: 5
     
    
    Constraints:
    
    1 <= nums1.length, nums2.length <= 1000
    0 <= nums1[i], nums2[i] <= 100
 * @author miche
 *
 */

public class MaximumLengthofRepeatedSubarray {

    /***
     * Explanation: dp[i][j]表示数组1的第i-1位与数组2的第j-1位相同长度，
     *     dp[0][j],dp[i][0]表示数组1或数组2长度为0时，有相同长度的子串长度也为0
     * Explanation: dp[i][j] indicates that the i-1th bit of array 1 has the same length as the j-1th bit of array 2,
     *     dp[0][j],dp[i][0] means that when the length of array 1 or array 2 is 0, the length of subarray with the same length is also 0
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        int ans = 0;
        for(int i = 1; i <= nums1.length; i++) {
            for(int j = 1; j <= nums2.length; j++) {
                if(nums1[i-1] == nums2[j-1]) {
                    //If a bit is the same, then the length is the same as the previous one plus 1
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if(ans < dp[i][j]) ans = dp[i][j];
                }
            }
        }
        return ans;
    }

}
