package algorithms.btree;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a binary tree, flatten it to a linked list in-place.

    For example, given the following tree:
    
        1
       / \
      2   5
     / \   \
    3   4   6
    The flattened tree should look like:
    
    1
     \
      2
       \
        3
         \
          4
           \
            5
             \
              6
 * @author miche
 *
 */
public class FlattenBinaryTreetoLinkedList {

    public void flatten(TreeNode root) {
        flatten(root, null);
    }

    public TreeNode flatten(TreeNode node, TreeNode parent) {
        if(node == null) return parent;

        if(parent != null) {
            parent.left = null;
            parent.right = node;
        }
        
        if(node.left == null && node.right == null) return node;

        TreeNode right = node.right;
        TreeNode left = flatten(node.left, node);
        TreeNode treenode = flatten(right, left);
        
        return treenode;
    }

    /***
     * Rearrange with preorder
     * @param root
     */
    public void flatten2(TreeNode root) {
        List<TreeNode> preorder = new ArrayList<TreeNode>();
        preorderList(root, preorder);
        for(int i = 1; i < preorder.size(); i++) {
            root.left = null;
            root.right = preorder.get(i);
            root = root.right;
        }
    }

    public void preorderList(TreeNode root, List<TreeNode> preorder) {
        if(root == null) return;
        preorder.add(root);
        preorderList(root.left, preorder);
        preorderList(root.right, preorder);
    }
}
