package algorithms.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

 

    Example 1:
    
    Input: nums = [5,2,6,1]
    Output: [2,1,1,0]
    Explanation:
    To the right of 5 there are 2 smaller elements (2 and 1).
    To the right of 2 there is only 1 smaller element (1).
    To the right of 6 there is 1 smaller element (1).
    To the right of 1 there is 0 smaller element.
    Example 2:
    
    Input: nums = [-1]
    Output: [0]
    Example 3:
    
    Input: nums = [-1,-1]
    Output: [0,0]
     
    
    Constraints:
    
    1 <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4
 * @author miche
 *
 */
public class CountofSmallerNumbersAfterSelf {

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<Integer>();
        List<Integer> queue = new ArrayList<Integer>();
        int idx;
        for(int i = nums.length-1; i >= 0; i--) {
            idx = binarysearch(queue, nums[i]);
            ans.add(idx);
            queue.add(idx, nums[i]);
        }
        /*
        List<Integer> res = new ArrayList<Integer>();
        for(int i = nums.length-1; i >= 0; i--) {
            res.add(ans.get(i));
        }
        */
        Collections.reverse(ans);
        return ans;
    }
    
    private int binarysearch(List<Integer> nums, int val) {
        int left = 0, right = nums.size() - 1;
        while(left <= right) {
            int mid = (left+right) / 2;
            //if(nums.get(mid) == val) {
                
            //}
            if(nums.get(mid) < val) left = mid+1;
            else right = mid-1;
        }
        return left;
    }

}
