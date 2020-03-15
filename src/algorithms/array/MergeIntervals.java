package algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/***
 * Given a collection of intervals, merge all overlapping intervals.

    Example 1:
    
    Input: [[1,3],[2,6],[8,10],[15,18]]
    Output: [[1,6],[8,10],[15,18]]
    Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
    Example 2:
    
    Input: [[1,4],[4,5]]
    Output: [[1,5]]
    Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * @author miche
 *
 */
public class MergeIntervals {

    public MergeIntervals() {
        // TODO Auto-generated constructor stub
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new IntervalComparator());
        List<int[]> lmerge = new ArrayList<int[]>();
        for(int[] interval:intervals) {
            if(lmerge.isEmpty() || lmerge.get(lmerge.size()-1)[1] < interval[0]) {
                lmerge.add(interval);
            } else if(lmerge.get(lmerge.size()-1)[1] < interval[1]){
                lmerge.get(lmerge.size()-1)[1] = interval[1];
            }
        }
        return lmerge.toArray(new int[lmerge.size()][2]);
    }

    public static void main(String[] argv) {
        int[][] intervals = {
                {1,3},{8,10},{15,18},{2,6}
        };
        MergeIntervals mi = new MergeIntervals();
        int[][] ans = mi.merge(intervals);
        System.out.print("[["+ans[0][0]+","+ans[0][1]+"]");
        for(int i = 1; i < ans.length; i++) {
            System.out.print(",["+ans[i][0]+","+ans[i][1]+"]");
        }
        System.out.println("]");
    }
}


class IntervalComparator implements Comparator<int[]> {

    @Override
    public int compare(int[] o1, int[] o2) {
        if(o1[0] < o2[0]) {
            return -1;
        } else if(o1[0] == o2[0]) {
            return 0;
        } else {
            return 1;
        }
    }
    
}