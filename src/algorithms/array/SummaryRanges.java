package algorithms.array;

import java.util.ArrayList;
import java.util.List;

/***
 * You are given a sorted unique integer array nums.

    Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
    
    Each range [a,b] in the list should be output as:
    
    "a->b" if a != b
    "a" if a == b
     
    
    Example 1:
    
    Input: nums = [0,1,2,4,5,7]
    Output: ["0->2","4->5","7"]
    Explanation: The ranges are:
    [0,2] --> "0->2"
    [4,5] --> "4->5"
    [7,7] --> "7"
    Example 2:
    
    Input: nums = [0,2,3,4,6,8,9]
    Output: ["0","2->4","6","8->9"]
    Explanation: The ranges are:
    [0,0] --> "0"
    [2,4] --> "2->4"
    [6,6] --> "6"
    [8,9] --> "8->9"
     
    
    Constraints:
    
    0 <= nums.length <= 20
    -231 <= nums[i] <= 231 - 1
    All the values of nums are unique.
    nums is sorted in ascending order.
 * @author miche
 *
 */

public class SummaryRanges {

    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<String>();
        if(nums == null || nums.length == 0) return ans;
        
        int preNum = nums[0], n = nums.length;
        StringBuilder range = new StringBuilder();  //String range = ""+preNum; slower than StringBuilder
        range.append(preNum);
        for(int i = 1; i < n; i++) {
            if(nums[i] != nums[i-1]+1) {
                if(nums[i-1] != preNum) {
                    range.append("->");
                    range.append(nums[i-1]);
                }
                ans.add(range.toString());
                range = new StringBuilder();
                preNum = nums[i];
                range.append(preNum);
            }
        }
        if(nums[n-1] != preNum) {
            range.append("->");
            range.append(nums[n-1]);
        }
        ans.add(range.toString());
        
        return ans;
    }

}
