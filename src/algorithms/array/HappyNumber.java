package algorithms.array;

import java.util.HashSet;
import java.util.Set;

/***
 * Write an algorithm to determine if a number n is "happy".

    A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
    
    Return True if n is a happy number, and False if not.
    
    Example: 
    
    Input: 19
    Output: true
    Explanation: 
    12 + 92 = 82
    82 + 22 = 68
    62 + 82 = 100
    12 + 02 + 02 = 1
 * @author miche
 *
 */
public class HappyNumber {

    public boolean isHappy(int n) {
        Set<Integer> loop = new HashSet<Integer>();
        int happy = n;
        while(happy != 1 && !loop.contains(happy)) {
            loop.add(happy);
            happy = square(happy);
        }
        
        return happy == 1;
    }

    public boolean isHappy2(int n) {
        int slow = square(n);
        int fast = square(slow);
        while(slow != 1 && slow != fast) {
            slow = square(slow);
            fast = square(square(fast));
        }
        
        return slow == 1;
    }

    public int square(int n) {
        int count = 0, rest = n, y = 0;
        while(rest >= 10) {
            y = rest % 10;
            count += (y*y);
            rest /= 10;
        }
        count += (rest*rest);
        return count;
    }
}
