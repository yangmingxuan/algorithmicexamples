package algorithms.btree;

import java.util.Deque;
import java.util.LinkedList;

/***
 * Given the root of a binary tree, return the sum of values of its deepest leaves.
 

    Example 1:
                 1
               /    \
              2      3
             / \      \
            4   5      6
           /            \
          7              8
    
    
    Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
    Output: 15
    Example 2:
    
    Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
    Output: 19
     
    
    Constraints:
    
    The number of nodes in the tree is in the range [1, 10^4].
    1 <= Node.val <= 100
 * @author miche
 *
 */
public class DeepestLeavesSum {

    public int deepestLeavesSum(TreeNode root) {
        if(root == null) return 0;
        int dlsum = 0;
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            dlsum = 0; //there is a deeper layer than before
            while(size > 0) {
                TreeNode node = queue.poll();
                if(node.left == null && node.right == null) dlsum += node.val;
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
                size--;
            }
        }
        return dlsum;
    }

}
