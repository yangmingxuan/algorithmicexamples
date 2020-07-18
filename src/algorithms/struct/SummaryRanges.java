package algorithms.struct;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.

    For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
    
    [1, 1]
    [1, 1], [3, 3]
    [1, 1], [3, 3], [7, 7]
    [1, 3], [7, 7]
    [1, 3], [6, 7]
     
    
    Follow up:
    
    What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 * @author miche
 *
 */
public class SummaryRanges {

    /***
     * Generate the intervals when return
    List<Integer> list;
    public SummaryRanges() {
        list = new ArrayList<Integer>();
    }
    
    public void addNum(int val) {
        int found = binarySearch(val);
        if(found < 0) {
            list.add(-1*found-1, val);
        }
    }

    public int[][] getIntervals() {
        List<int[]> ans = new ArrayList<int[]>();
        if(list.size() == 0) {
            int[][] ret = new int[0][2];
            return ret;
        }
        int s = list.get(0), e = s;
        for(int i = 1; i < list.size(); i++) {
            if(list.get(i) != e + 1) {
                //disjoint 
                int[] element = {s, e};
                ans.add(element);
                s = list.get(i);
                e = s;
            } else {
                e = list.get(i);
            }
        }
        
        //for the last element
        int[] element = {s, e};
        ans.add(element);
        
        int[][] ret = new int[ans.size()][2];
        ans.toArray(ret);
        return ret;
    }

    public int binarySearch(int val) {
        int left = 0, right = list.size() - 1;
        while(left <= right) {
            int mid = (left+right) >>> 1; //(left+right) / 2;
            if(list.get(mid) == val) {
                return mid;  //found
            }
            if(list.get(mid) < val) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -(left+1); //not found
    }
    */
    
    /***
     * Generate the intervals when add element
     */
    List<int[]> list;
    /** Initialize your data structure here. */
    public SummaryRanges() {
        list = new ArrayList<int[]>();
    }
 
    public void addNum(int val) {
        int left = 0, right = list.size() - 1;
        while(left <= right) {
            int mid = (left+right) >>> 1; //(left+right) / 2;
            int[] interval = list.get(mid);
            if(interval[0] <= val && val <= interval[1]) {
                //in the interval, do nothing;
                return;
            } else if(val == interval[0]-1 || val == interval[1]+1) {
                //joint the interval
                interval[0] = Math.min(val, interval[0]);
                interval[1] = Math.max(val, interval[1]);
                merge();
                return;
            } else if(interval[1] + 1 < val) {
                left = mid + 1;
            } else /* if(interval[0] - 1 > val) */ {
                right = mid - 1;
            }
        }
        
        //no found, need add interval
        int[] interval = {val, val};
        list.add(left, interval);
    }
    
    public int[][] getIntervals() {
        return list.toArray(new int[list.size()][2]);
    }

    public void merge() {
        for(int i = 1; i < list.size(); i++) {
            if(list.get(i)[0] - 1 == list.get(i-1)[1]) {
                list.get(i-1)[1] = list.get(i)[1];
                list.remove(i);
                break;
            }
        }
    }
}
