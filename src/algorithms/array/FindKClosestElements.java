package algorithms.array;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

    An integer a is closer to x than an integer b if:
    
    |a - x| < |b - x|, or
    |a - x| == |b - x| and a < b
     
    
    Example 1:
    
    Input: arr = [1,2,3,4,5], k = 4, x = 3
    Output: [1,2,3,4]
    Example 2:
    
    Input: arr = [1,2,3,4,5], k = 4, x = -1
    Output: [1,2,3,4]
     
    
    Constraints:
    
    1 <= k <= arr.length
    1 <= arr.length <= 104
    arr is sorted in ascending order.
    -104 <= arr[i], x <= 104
 * @author miche
 *
 */
public class FindKClosestElements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = -1, right = arr.length, low = 0, high = arr.length - 1;
        //using binary search
        while(low <= high) {
            int mid = (low+high) / 2;
            if(arr[mid] == x) {
                left = mid;
                right = mid + 1;
                break;
            } else if(arr[mid] < x) {
                low = mid + 1;
                left = mid;
            } else {
                high = mid - 1;
                right = mid;
            }
        }
        
        while(left >= 0 && right < arr.length && k > 0) {
            if(Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                left--;
            } else {
                right++;
            }
            k--;
        }
        while(left >= 0 && k > 0) {
            left--;
            k--;
        }
        while(right < arr.length && k > 0) {
            right++;
            k--;
        }
        
        List<Integer> ans = new ArrayList<Integer>();
        for(int i = left+1; i < right; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

}
