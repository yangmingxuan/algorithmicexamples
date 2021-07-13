package algorithms.sort;

import java.util.PriorityQueue;

/***
 * Given an n x n matrix where each of the rows and columns are sorted in ascending order, return the kth smallest element in the matrix.

    Note that it is the kth smallest element in the sorted order, not the kth distinct element.
    
     
    
    Example 1:
    
    Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
    Output: 13
    Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
    Example 2:
    
    Input: matrix = [[-5]], k = 1
    Output: -5
     
    
    Constraints:
    
    n == matrix.length
    n == matrix[i].length
    1 <= n <= 300
    -109 <= matrix[i][j] <= 109
    All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
    1 <= k <= n2
 * @author miche
 *
 */
public class KthSmallestElementinaSortedMatrix {

    public int kthSmallest2(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)->b-a);
        for(int[] row : matrix) {
            for(int ele : row) {
                pq.add(ele);
                //Keep the k smallest elements in the queue
                if(pq.size() > k) {
                    pq.poll();
                }
            }
        }
        return pq.peek();
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0], high = matrix[n-1][n-1];
        while(low <= high) {
            int mid = (low+high) / 2;
            if(countLess(matrix, n, mid) < k) {
                //kth smallest in the right
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private int countLess(int[][] matrix, int n, int mid) {
        int j = n -1, count = 0;
        for(int i = 0; i < n; i++) {
            while(j >= 0 && matrix[i][j] > mid) {
                j--;
            }
            count += j+1;
        }
        return count;
    }
}
