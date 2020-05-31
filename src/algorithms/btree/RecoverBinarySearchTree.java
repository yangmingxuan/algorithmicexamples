package algorithms.btree;

import java.util.LinkedList;
import java.util.Queue;

import javafx.util.Pair;

/***
 * Two elements of a binary search tree (BST) are swapped by mistake.

    Recover the tree without changing its structure.
    
    Example 1:
    
    Input: [1,3,null,null,2]
    
       1
      /
     3
      \
       2
    
    Output: [3,1,null,null,2]
    
       3
      /
     1
      \
       2
    Example 2:
    
    Input: [3,1,4,null,null,2]
    
      3
     / \
    1   4
       /
      2
    
    Output: [2,1,4,null,null,3]
    
      2
     / \
    1   4
       /
      3
    Follow up:
    
    A solution using O(n) space is pretty straight forward.
    Could you devise a constant space solution?
 * @author miche
 *
 */
public class RecoverBinarySearchTree {

    TreeNode x = null, y = null, pre = null;
    public void recoverTree(TreeNode root) {
        if(root == null) return;
        findTwoSwap(root);
        if(x != null && y != null) {
            int tmp = x.val;
            x.val = y.val;
            y.val = tmp;
        }
    }

    public void findTwoSwap(TreeNode root) {
        if(root == null) return;
        findTwoSwap(root.left);
        if(pre != null && root.val < pre.val) {
            y = root;
            if(x == null) x = pre;
            else return;
        }
        pre = root;
        findTwoSwap(root.right);
    }
}
