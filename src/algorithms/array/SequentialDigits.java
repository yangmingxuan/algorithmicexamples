package algorithms.array;

import java.util.ArrayList;
import java.util.List;

/***
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

    Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
    
     
    
    Example 1:
    
    Input: low = 100, high = 300
    Output: [123,234]
    Example 2:
    
    Input: low = 1000, high = 13000
    Output: [1234,2345,3456,4567,5678,6789,12345]
     
    
    Constraints:
    
    10 <= low <= high <= 10^9
 * @author miche
 *
 */
public class SequentialDigits {

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<Integer>();
        int tl = low, count = 1;
        while(tl >= 10) {
            tl /= 10;
            count++;
        }
        int req = 0;
        while(req < high && count < 10) {
            req = 0;
            int addon = 0;
            for(int i = 1; i <= 10-count; i++) {
                if(i == 1) {
                    for(int k = i; k <= count; k++) {
                        req = req*10 + k;
                        addon = addon * 10 + 1;
                    }
                } else {
                    req += addon;
                }
                if(req >= low && req <= high) {
                    ans.add(req);
                }
                if(req > high) break;
            }
            count++;
        }
        return ans;
    }
    
    /***
     * the recursive method see https://github.com/yangmingxuan/pythonalgorithms/blob/master/arrayandsort/SequentialDigits.py
     */

}
