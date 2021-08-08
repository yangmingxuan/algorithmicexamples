package algorithms.btree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***
 * Given an n-ary tree, return the level order traversal of its nodes' values.

    Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
    
     
    
    Example 1:
    
    
    
    Input: root = [1,null,3,2,4,null,5,6]
    Output: [[1],[3,2,4],[5,6]]
    Example 2:
    
    
    
    Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
    Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
     
    
    Constraints:
    
    The height of the n-ary tree is less than or equal to 1000
    The total number of nodes is between [0, 104]
 * @author miche
 *
 */
public class NaryTreeLevelOrderTraversal {

    /***
     * BFS
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(NNode root) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if(root == null) return ans;
        Queue<NNode> queue = new LinkedList<NNode>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<Integer>(size);
            for(int i = 0; i < size; i++) {
                NNode node = queue.poll();
                level.add(node.val);
                List<NNode> children = node.children;
                if(children != null) {
                    for(NNode child : children) {
                        queue.offer(child);
                    }
                }
            }
            ans.add(level);
        }
        
        return ans;
    }

}
