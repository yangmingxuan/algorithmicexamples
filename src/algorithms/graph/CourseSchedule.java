package algorithms.graph;

import java.util.ArrayList;
import java.util.List;

/***
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

    Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
    
    Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
    
     
    
    Example 1:
    
    Input: numCourses = 2, prerequisites = [[1,0]]
    Output: true
    Explanation: There are a total of 2 courses to take. 
                 To take course 1 you should have finished course 0. So it is possible.
    Example 2:
    
    Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
    Output: false
    Explanation: There are a total of 2 courses to take. 
                 To take course 1 you should have finished course 0, and to take course 0 you should
                 also have finished course 1. So it is impossible.
     
    
    Constraints:
    
    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.
    1 <= numCourses <= 10^5
 * @author miche
 *
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null || prerequisites.length == 0) return true;
        Boolean[] dependedon = new Boolean[numCourses]; //flag if depended by other classes
        List<Integer>[] graph = new ArrayList[numCourses]; //Record the courses that each course depends on
        for(int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int depended = prerequisites[i][1];
            if(graph[course] == null) {
                graph[course] = new ArrayList<Integer>();
            }
            graph[course].add(depended);
        }
        for(int course = 0; course < numCourses; course++) {
            if(findCycle(course, graph, dependedon)) {
                return false;
            }
        }
        return true;
    }

    public boolean findCycle(int course, List<Integer>[] graph, Boolean[] dependedon) {
        Boolean depended = dependedon[course];
        if(depended != null) {
            return depended;
        }
        if(depended == null) {
            //First mark the course as dependent, if it is searched again, it means that the course is in cycle
            dependedon[course] = true;
        }
        List<Integer> edges = graph[course];
        if(edges != null) {
            for(int edge : edges) {
                if(findCycle(edge, graph, dependedon)) {
                    return true;
                }
            }
        }
        dependedon[course] = false; //The course is not in cycle
        return false;
    }
}
