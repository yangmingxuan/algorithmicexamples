package algorithms.array;

/***
 * Nearly everyone has used the Multiplication Table. The multiplication table of size m x n is an integer matrix mat where mat[i][j] == i * j (1-indexed).

    Given three integers m, n, and k, return the kth smallest element in the m x n multiplication table.
    
     
    
    Example 1:
    
          1  2  3
          2  4  6
          3  6  9
       1 2 2 3 3 4 6 6 9
    
    Input: m = 3, n = 3, k = 5
    Output: 3
    Explanation: The 5th smallest number is 3.
    Example 2:
    
    
    Input: m = 2, n = 3, k = 6
    Output: 6
    Explanation: The 6th smallest number is 6.
     
    
    Constraints:
    
    1 <= m, n <= 3 * 10^4
    1 <= k <= m * n
 * @author miche
 *
 */
public class KthSmallestNumberinMultiplicationTable {

    public int findKthNumber(int m, int n, int k) {
        int lo = 1, hi = m*n;
        while(lo < hi) {
            int mid = lo + (hi-lo) / 2;
            if(!ifMoreThank(mid, m, n, k)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private boolean ifMoreThank(int mid, int m, int n, int k) {
        int ranking = 0;
        for(int i = 1; i <= m; i++) {
            ranking += Math.min(mid/i, n);
        }
        return ranking >= k;
    }
}
