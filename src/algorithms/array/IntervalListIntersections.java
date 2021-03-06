package algorithms.array;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList<int[]>();
        int i = 0, j = 0;
        while(i < A.length && j < B.length) {
            int low = Math.max(A[i][0], B[j][0]);
            int hight = Math.min(A[i][1], B[j][1]);
            if(low <= hight) {
                ans.add(new int[] {low, hight});
            }
            if(A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        
        return ans.toArray(new int[ans.size()][]);
    }

}
