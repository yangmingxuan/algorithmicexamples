package algorithms.array;

/***
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

    Given two integers x and y, return the Hamming distance between them.
    
     
    
    Example 1:
    
    Input: x = 1, y = 4
    Output: 2
    Explanation:
    1   (0 0 0 1)
    4   (0 1 0 0)
           ↑   ↑
    The above arrows point to positions where the corresponding bits are different.
    Example 2:
    
    Input: x = 3, y = 1
    Output: 1
     
    
    Constraints:
    
    0 <= x, y <= 231 - 1

 * @author miche
 *
 */
public class HammingDistance {

    public int hammingDistance(int x, int y) {
        int diff = x ^ y;
        int count = 0;
        while(diff > 0) {
            if(diff % 2 > 0) count++;
            diff >>= 1;
        }
        return count;
    }

    public int hammingDistance2(int x, int y) {
        int diff = x ^ y;
        int count = 0;
        while(diff > 0) {
            count++;
            diff &= (diff-1);  //remove the last digit 1
        }
        return count;
    }
}
