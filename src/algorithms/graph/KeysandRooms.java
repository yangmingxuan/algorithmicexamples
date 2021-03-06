package algorithms.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***
 * There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room. 

    Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.
    
    Initially, all the rooms start locked (except for room 0). 
    
    You can walk back and forth between rooms freely.
    
    Return true if and only if you can enter every room.
    
    Example 1:
    
    Input: [[1],[2],[3],[]]
    Output: true
    Explanation:  
    We start in room 0, and pick up key 1.
    We then go to room 1, and pick up key 2.
    We then go to room 2, and pick up key 3.
    We then go to room 3.  Since we were able to go to every room, we return true.
    Example 2:
    
    Input: [[1,3],[3,0,1],[2],[0]]
    Output: false
    Explanation: We can't enter the room with number 2.
    Note:
    
    1 <= rooms.length <= 1000
    0 <= rooms[i].length <= 1000
    The number of keys in all rooms combined is at most 3000.
 * @author miche
 *
 */
public class KeysandRooms {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        visited[0] = true;
        dfs(rooms.get(0), rooms, visited);
        for(int i = 1; i < visited.length; i++) {
            if(!visited[i]) return false;
        }
        return true;
    }

    public void dfs(List<Integer> keys, List<List<Integer>> rooms, boolean[] visited) {
        for(int key : keys) {
            if(!visited[key]) { //if have not entered
                visited[key] = true;
                dfs(rooms.get(key), rooms, visited);
            }
        }
    }

    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        Queue<List<Integer>> queue = new LinkedList<List<Integer>>();
        visited[0] = true;
        queue.offer(rooms.get(0));
        while(!queue.isEmpty()) {
            List<Integer> keys = queue.poll();
            for(int key : keys) { //if have not entered
                if(!visited[key]) {
                    visited[key] = true;
                    queue.offer(rooms.get(key));
                }
            }
        }
        for(int i = 1; i < visited.length; i++) {
            if(!visited[i]) return false;
        }
        return true;
    }
}
