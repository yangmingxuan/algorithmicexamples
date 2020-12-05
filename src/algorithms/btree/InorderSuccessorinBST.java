package algorithms.btree;

import java.util.Stack;

/***
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

    The successor of a node p is the node with the smallest key greater than p.val.
    
     
    
    Example 1:
            2
           / \
          1   3

    Input: root = [2,1,3], p = 1
    Output: 2
    Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
    Example 2:
              5
             / \
            3   6
           / \
          2   4
         /
        1
    
    Input: root = [5,3,6,2,4,null,null,1], p = 6
    Output: null
    Explanation: There is no in-order successor of the current node, so the answer is null.
     
    
    Note:
    
    If the given node has no in-order successor in the tree, return null.
    It's guaranteed that the values of the tree are unique.
 * @author miche
 *
 */
public class InorderSuccessorinBST {

    /***
     * BST's feature left < parent < right, if p has right sub tree, its right sub tree's most lest leaf node is the answer,
     * else the answer is the inorder traversal's next node
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode node;
        if(p.right != null) {
            node = p.right;
            while(node.left != null) node = node.left;
            return node;
        }
        //set a pre_value variable, initialize it to the minimum, when we traverse the tree via inorder to a node
        //and its pre_value is p, this node is the answer
        int preValue = Integer.MIN_VALUE;
        node = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(!stack.isEmpty() || node != null) {
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if(preValue == p.val) return node;
            preValue = node.val;
            node = node.right;
        }
        return null;
    }

    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        TreeNode curr;
        if(p.right != null) {
            curr = p.right;
            while(curr.left != null) curr = curr.left;
            return curr;
        }
        curr = root;
        TreeNode ancestor = null;
        while (curr.val != p.val) {
            if (p.val <curr.val) {
                ancestor = curr;
                curr = curr.left;
            } else 
                curr = curr.right;
        }
        return ancestor;
    }

}
