package algorithms.btree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/***
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

 

    Example 1:
    
    Input: root = [3,1,4,null,2], k = 1
       3
      / \
     1   4
      \
       2
    Output: 1
    Example 2:
    
    Input: root = [5,3,6,2,4,null,null,1], k = 3
           5
          / \
         3   6
        / \
       2   4
      /
     1
    Output: 3
    Follow up:
    What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
    
     
    
    Constraints:
    
    The number of elements of the BST is between 1 to 10^4.
    You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * @author miche
 *
 */
public class KthSmallestElementiaBST {

    /***
     * BST's feature is left < parent < right, its inorder traversal is an increasing array
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return -1;
        List<Integer> inorderarray = new ArrayList<Integer>();
        inorder(root, inorderarray, k);
        return inorderarray.get(k-1);
    }

    public void inorder(TreeNode root, List<Integer> inorderarray, int k) {
        if(root == null || inorderarray.size() >= k) {
            return;
        }
        inorder(root.left, inorderarray, k);
        inorderarray.add(root.val);
        if(inorderarray.size() >= k) return;
        inorder(root.right, inorderarray, k);
    }

    public int kthSmallest2(TreeNode root, int k) {
        if(root == null) return -1;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(true) {
            while(root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            if(--k == 0) return root.val;
            root = root.right;
        }
    }
}
