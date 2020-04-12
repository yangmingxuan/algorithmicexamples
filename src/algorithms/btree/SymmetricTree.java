package algorithms.btree;

import java.util.LinkedList;

/***
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

    For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
    
        1
       / \
      2   2
     / \ / \
    3  4 4  3
     
    
    But the following [1,2,2,null,3,null,3] is not:
    
        1
       / \
      2   2
       \   \
       3    3
     
    
    Note:
    Bonus points if you could solve it both recursively and iteratively.
 * @author miche
 *
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        TreeNode tn1, tn2;
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        stack.add(root);
        stack.add(root);
        while(!stack.isEmpty()) {
            tn1 = stack.removeLast();
            tn2 = stack.removeLast();
            if(tn1 == null && tn2 == null) continue;
            if(tn1 == null || tn2 == null) return false;
            if(tn1.val != tn2.val) return false;
            stack.add(tn1.left);
            stack.add(tn2.right);
            stack.add(tn1.right);
            stack.add(tn2.left);
        }
        return true;
    }

    public boolean isSymmetric2(TreeNode root) {
        return isSymmertic(root, root);
    }

    public boolean isSymmertic(TreeNode tn1, TreeNode tn2) {
        if(tn1 == null && tn2 == null) return true;
        if(tn1 == null || tn2 == null) return false;
        if(tn1.val != tn2.val) return false;
        return isSymmertic(tn1.left, tn2.right) && isSymmertic(tn1.right, tn2.left);
    }
}
