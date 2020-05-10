package algorithms.graph;

import java.util.ArrayList;
import java.util.List;

/***
 * There are a total of n courses you have to take, labeled from 0 to n-1.

    Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
    
    Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
    
    There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
    
    Example 1:
    
    Input: 2, [[1,0]] 
    Output: [0,1]
    Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
                 course 0. So the correct course order is [0,1] .
    Example 2:
    
    Input: 4, [[1,0],[2,0],[3,1],[3,2]]
    Output: [0,1,2,3] or [0,2,1,3]
    Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
                 courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
                 So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
    Note:
    
    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.
 * @author miche
 *
 */
public class CourseScheduleII {

    private static int WHITE = 0;
    private static int GRAY = 1;
    private static int BLACK = 2;
    private List<Integer> courseOrder;
    private int[] courseColor;
    private boolean isCycle;
    private List<Integer>[] courseDependon;
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans;
        courseOrder = new ArrayList<Integer>();
        courseColor = new int[numCourses];
        courseDependon = new ArrayList[numCourses];
        //initial the courses dependedon
        for(int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int dependedon = prerequisites[i][1];
            if(courseDependon[course] == null) {
                courseDependon[course] = new ArrayList<Integer>();
            }
            courseDependon[course].add(dependedon);
        }
        
        //mark the courses order
        for(int i = 0; i < numCourses; i++) {
            if(courseColor[i] == WHITE) dfs(i);
        }
        
        if(isCycle || courseOrder.isEmpty()) {
            ans = new int[0];
        } else {
            ans = new int[numCourses];
            for(int i = 0; i < numCourses; i++) {
                ans[i] = courseOrder.get(i);
            }
        }
        return ans;
    }

    public void dfs(int course) {
        if(isCycle) return;
        //set color
        courseColor[course] = GRAY;
        List<Integer> edges = courseDependon[course];
        if(edges != null) {
            for(int edge : edges) {
                if(courseColor[edge] == WHITE) {
                    dfs(edge);
                } else if(courseColor[edge] == GRAY) {
                    //course depended on cycle
                    isCycle = true;
                    return;
                }
            }
        }
        
        courseColor[course] = BLACK;
        courseOrder.add(course);
    }
}
