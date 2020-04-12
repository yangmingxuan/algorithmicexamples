package algorithms.btree;

/***
 * Given a binary tree, determine if it is a valid binary search tree (BST).

    Assume a BST is defined as follows:
    
    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.
     
    
    Example 1:
    
        2
       / \
      1   3
    
    Input: [2,1,3]
    Output: true
    Example 2:
    
        5
       / \
      1   4
         / \
        3   6
    
    Input: [5,1,4,null,null,3,6]
    Output: false
    Explanation: The root node's value is 5 but its right child's value is 4.
 * @author miche
 *
 */
public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(TreeNode root, Integer lower, Integer upper) {
        if(root == null) return true;
        if(lower != null && root.val <= lower.intValue()) return false;
        if(upper != null && root.val >= upper.intValue()) return false;

        return isValidBST(root.left, lower, root.val) && isValidBST(root.right, root.val, upper);
    }
}
