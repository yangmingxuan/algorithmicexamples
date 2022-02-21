package algorithms.btree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

/***
 * Given two binary search trees root1 and root2, return a list containing all the integers from both trees sorted in ascending order.

 

    Example 1:
      2            1
     / \          / \
    1   4        0   3
    
    Input: root1 = [2,1,4], root2 = [1,0,3]
    Output: [0,1,1,2,3,4]

    Example 2:
      1            8
       \          /
        8        1
    
    Input: root1 = [1,null,8], root2 = [8,1]
    Output: [1,1,8,8]
     
    
    Constraints:
    
    The number of nodes in each tree is in the range [0, 5000].
    -10^5 <= Node.val <= 10^5
 * @author miche
 *
 */
public class AllElementsinTwoBinarySearchTrees {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> ans = new ArrayList<Integer>();
        Deque<TreeNode> tree1 = new ArrayDeque<TreeNode>();  //or Stack
        Deque<TreeNode> tree2 = new ArrayDeque<TreeNode>();
        Deque<TreeNode> tree;
        TreeNode node1 = root1, node2 = root2, node;
        while(node1 != null) {
            tree1.add(node1);
            node1 = node1.left;
        }
        while(node2 != null) {
            tree2.add(node2);
            node2 = node2.left;
        }
        
        while(!tree1.isEmpty() || !tree2.isEmpty()) {
            tree = tree1;
            if(tree2.isEmpty()) {
                tree = tree1;
            } else if(tree1.isEmpty()) {
                tree = tree2;
            } else if(tree1.peekLast().val > tree2.peekLast().val) {
                tree = tree2;
            }
            node = tree.pollLast();
            ans.add(node.val);
            if(node.right != null) {
                node = node.right;
                while(node != null) {
                    tree.add(node);
                    node = node.left;
                }
            }
        }
        
        return ans;
    }

}
