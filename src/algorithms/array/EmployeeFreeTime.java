package algorithms.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * We are given a list schedule of employees, which represents the working time for each employee.

    Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
    
    Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
    
    (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
    
     
    
    Example 1:
    
    Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
    Output: [[3,4]]
    Explanation: There are a total of three employees, and all common
    free time intervals would be [-inf, 1], [3, 4], [10, inf].
    We discard any intervals that contain inf as they aren't finite.
    Example 2:
    
    Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
    Output: [[5,6],[7,9]]
     
    
    Constraints:
    
    1 <= schedule.length , schedule[i].length <= 50
    0 <= schedule[i].start < schedule[i].end <= 10^8
 * @author miche
 *
 */
public class EmployeeFreeTime {

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> ans = new ArrayList<Interval>();
        if(schedule == null) return ans;
        List<Interval> allSchedule = new ArrayList<Interval>();
        for(List<Interval> employeeTime : schedule) {
            allSchedule.addAll(employeeTime);
        }
        Collections.sort(allSchedule, (a,b)->a.start == b.start ? a.end - b.end : a.start - b.start);
        int maxFreeTime = Integer.MIN_VALUE;
        for(int i = 0; i < allSchedule.size() - 1; i++) {
            if(allSchedule.get(i).start == allSchedule.get(i+1).start) {
                continue;
            }
            maxFreeTime = Math.max(maxFreeTime, allSchedule.get(i).end);
            if(maxFreeTime < allSchedule.get(i+1).start) {
                Interval freeTime = new Interval(maxFreeTime, allSchedule.get(i+1).start);
                ans.add(freeTime);
            }
        }
        return ans;
    }

}
