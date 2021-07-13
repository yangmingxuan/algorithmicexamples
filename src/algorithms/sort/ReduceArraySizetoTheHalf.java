package algorithms.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/***
 * Given an array arr.  You can choose a set of integers and remove all the occurrences of these integers in the array.

    Return the minimum size of the set so that at least half of the integers of the array are removed.
    
     
    
    Example 1:
    
    Input: arr = [3,3,3,3,5,5,5,2,2,7]
    Output: 2
    Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
    Possible sets of size 2 are {3,5},{3,2},{5,2}.
    Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has size greater than half of the size of the old array.
    Example 2:
    
    Input: arr = [7,7,7,7,7,7]
    Output: 1
    Explanation: The only possible set you can choose is {7}. This will make the new array empty.
    Example 3:
    
    Input: arr = [1,9]
    Output: 1
    Example 4:
    
    Input: arr = [1000,1000,3,7]
    Output: 1
    Example 5:
    
    Input: arr = [1,2,3,4,5,6,7,8,9,10]
    Output: 5
     
    
    Constraints:
    
    1 <= arr.length <= 10^5
    arr.length is even.
    1 <= arr[i] <= 10^5
 * @author miche
 *
 */
public class ReduceArraySizetoTheHalf {

    public int minSetSize(int[] arr) {
        int len = arr.length;
        HashMap<Integer, Integer> numCount = new HashMap<Integer, Integer>();
        //Count the frequency of each integer in the array.
        for(int num : arr) {
            numCount.put(num, numCount.getOrDefault(num, 0) + 1);
        }
        //Start with an empty set, add to the set the integer with the maximum frequency.
        List<Integer> nc = new ArrayList<Integer>(numCount.values());
        nc.sort((a,b)->b-a);
        int ans = 0, allcount = 0;
        //Keep Adding the integer with the max frequency until you remove at least half of the integers.
        for(int count : nc) {
            ans++;
            allcount += count;
            if(allcount >= len / 2) return ans;
        }
        return ans;
    }

}
