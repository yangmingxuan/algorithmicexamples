package algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

    Example 1:
    
    Input: [[0, 30],[5, 10],[15, 20]]
    Output: 2
    Example 2:
    
    Input: [[7,10],[2,4]]
    Output: 1
 * @author miche
 *
 */
public class MeetingRoomsII {

    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (a,b)->a[0]-b[0]);
        List<List<int[]>> lret = new ArrayList<List<int[]>>();
        List<int[]> lroom = new ArrayList<int[]>();
        lroom.add(intervals[0]);
        lret.add(lroom);
        for(int i = 1; i < intervals.length; i++) {
            int[] meet = intervals[i];
            boolean isnew = true;
            for(int j = 0; j < lret.size(); j++) {
                lroom = lret.get(j);
                if(meet[0] >= (lroom.get(lroom.size()-1))[1]) {
                    //can use same meetroom
                    lroom.add(meet);
                    isnew = false;
                    break;
                }
            }
            if(isnew) {
                lroom = new ArrayList<int[]>();
                lroom.add(meet);
                lret.add(lroom);
            }
        }
        
        return lret.size();
    }

    public int minMeetingRooms2(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        int[] startTime = new int[intervals.length];
        int[] endTime = new int[intervals.length];
        int rooms = 0, endPtr = 0;
        for(int i = 0; i < intervals.length; i++) {
            startTime[i] = intervals[i][0];
            endTime[i] = intervals[i][1];
        }
        Arrays.parallelSort(startTime);
        Arrays.parallelSort(endTime);

        for(int i = 0; i < startTime.length; i++) {
            if(startTime[i] < endTime[endPtr]) {
                rooms++;
            } else {
                endPtr++;
            }
        }
        return rooms;
    }
}
