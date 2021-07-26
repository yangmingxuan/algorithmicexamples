package algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * You are given an array arr which consists of only zeros and ones, divide the array into three non-empty parts such that all of these parts represent the same binary value.

    If it is possible, return any [i, j] with i + 1 < j, such that:
    
    arr[0], arr[1], ..., arr[i] is the first part,
    arr[i + 1], arr[i + 2], ..., arr[j - 1] is the second part, and
    arr[j], arr[j + 1], ..., arr[arr.length - 1] is the third part.
    All three parts have equal binary values.
    If it is not possible, return [-1, -1].
    
    Note that the entire part is used when considering what binary value it represents. For example, [1,1,0] represents 6 in decimal, not 3. Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.
    
     
    
    Example 1:
    
    Input: arr = [1,0,1,0,1]
    Output: [0,3]
    Example 2:
    
    Input: arr = [1,1,0,1,1]
    Output: [-1,-1]
    Example 3:
    
    Input: arr = [1,1,0,0,1]
    Output: [0,2]
     
    
    Constraints:
    
    3 <= arr.length <= 3 * 104
    arr[i] is 0 or 1
 * @author miche
 *
 */
public class ThreeEqualParts {

    public int[] threeEqualParts(int[] arr) {
        int[] IMPOSSIBLE = new int[] {-1,-1};
        int n = arr.length;
        
        //要能平分成三个部分值都相等，每个部分的1必须一样多
        //To be equally divided into three parts with equal values, each part must have the same number of 1s
        //先记录下每个1的位置
        //Firstly, record the each 1's position
        List<Integer> mindex = new ArrayList<Integer>();
        for(int i = 0; i < n; i++) {
            if(arr[i] == 1) {
                mindex.add(i);
            }
        }
        int m = mindex.size();
        
        if(m == 0) {
            //none 1
            return new int[] {0,n-1};
        }
        
        if(m % 3 != 0) return IMPOSSIBLE;
        
        int p1 = mindex.get(0), p2 = mindex.get(m/3-1);
        int p3 = mindex.get(m/3), p4 = mindex.get(2*m/3-1);
        int p5 = mindex.get(2*m/3), p6 = mindex.get(m-1);
        int[] part1 = Arrays.copyOfRange(arr, p1, p2+1);
        int[] part2 = Arrays.copyOfRange(arr, p3, p4+1);
        int[] part3 = Arrays.copyOfRange(arr, p5, p6+1);
        
        if(!Arrays.equals(part1, part2) || !Arrays.equals(part1, part3)) return IMPOSSIBLE;
        
        //1前面的0不影响值的大小，值的大小取决于最后的1后面的0的个数，所以最后的0的长度只能最小
        //The 0 in front of 1 does not affect the value of the array, the value of the array depends on the number of 0s after the last 1, so the length of the last 0 can only be the smallest
        int lenZeroLeft = p3 - p2 - 1;
        int lenZeroMid = p5 - p4 -1;
        int lenZeroTail = n - p6 -1;
        if(lenZeroTail > lenZeroLeft || lenZeroTail > lenZeroMid) return IMPOSSIBLE;
        
        return new int[] {p2+lenZeroTail, p4+lenZeroTail+1};
    }

}
