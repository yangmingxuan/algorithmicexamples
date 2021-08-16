package algorithms.array;

import java.util.Arrays;
import java.util.HashMap;

/***
 * Given an integer array of even length arr, return true if it is possible to reorder arr such that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2, or false otherwise.

 

    Example 1:
    
    Input: arr = [3,1,3,6]
    Output: false
    Example 2:
    
    Input: arr = [2,1,2,6]
    Output: false
    Example 3:
    
    Input: arr = [4,-2,2,-4]
    Output: true
    Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
    Example 4:
    
    Input: arr = [1,2,4,16,8,4]
    Output: false
     
    
    Constraints:
    
    2 <= arr.length <= 3 * 104
    arr.length is even.
    -10^5 <= arr[i] <= 10^5
 * @author miche
 *
 */
public class ArrayofDoubledPairs {

    public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
        
        for(int num : arr) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        for(int num : arr) {
            //already paired
            if(count.get(num) == 0) continue;
            if(num < 0) {
                //double number in the front
                if(num % 2 == 0) {
                    if(count.get(num/2) == null || count.get(num/2) <= 0) {
                        return false;
                    } else {
                        count.put(num, count.get(num)-1);
                        count.put(num/2, count.get(num/2)-1);
                    }
                } else {
                    return false;
                }
            } else {
                //double number after the origin number
                if(count.get(num*2) == null || count.get(num*2) <= 0) {
                    return false;
                } else {
                    count.put(num, count.get(num)-1);
                    count.put(num*2, count.get(num*2)-1);
                }
            }
        }
        
        return true;
    }

}
