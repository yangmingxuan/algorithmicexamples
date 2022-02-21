package algorithms.array;

/***
 * Given an array of integers arr, return true if and only if it is a valid mountain array.

    Recall that arr is a mountain array if and only if:
    
    arr.length >= 3
    There exists some i with 0 < i < arr.length - 1 such that:
    arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
    arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
    
    Strictly Increasing   Strictly Decreasing
    -------------------->------------------>
    0   2    3     4     5     2     1     0

    
    No Strictly Increasing  Strictly Decreasing
    --------->----->---->------------------>
    0   2    3     3     5     2     1     0
    
    
    Example 1:
    
    Input: arr = [2,1]
    Output: false
    Example 2:
    
    Input: arr = [3,5,5]
    Output: false
    Example 3:
    
    Input: arr = [0,3,2,1]
    Output: true
     
    
    Constraints:
    
    1 <= arr.length <= 10^4
    0 <= arr[i] <= 10^4
 * @author miche
 *
 */
public class ValidMountainArray {

    public boolean validMountainArray(int[] arr) {
        if(arr.length < 3) return false;
        int maxid = 0;
        for(; maxid < arr.length - 1; maxid++) {
            if(arr[maxid] == arr[maxid+1]) return false;
            if(arr[maxid] > arr[maxid+1]) {
                break;
            }
        }
        if(maxid == 0 || maxid == arr.length-1) return false;
        maxid++;
        for(;maxid < arr.length; maxid++) {
            if(arr[maxid] >= arr[maxid-1]) return false;
        }
        return true;
    }

}
