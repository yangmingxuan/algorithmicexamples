package algorithms.btree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/***
 * Given a binary tree, return the inorder traversal of its nodes' values.

    Example:
    
    Input: [1,null,2,3]
       1
        \
         2
        /
       3
    
    Output: [1,3,2]
  Follow up: Recursive solution is trivial, could you do it iteratively?
 * @author miche
 *
 */
public class BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> lret = new ArrayList<Integer>();
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            lret.add(root.val);
            root = root.right;
        }
        return lret;
    }

    /***
     * Recursive solution
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> lret = new ArrayList<Integer>();
        inorderTraversalBackTrack(lret, root);
        return lret;
    }

    public void inorderTraversalBackTrack(List<Integer> lret, TreeNode root) {
        if(root == null) return;
        inorderTraversalBackTrack(lret, root.left);
        lret.add(root.val);
        inorderTraversalBackTrack(lret, root.right);        
    }
}
