package algorithms.btree;

/***
 * Invert a binary tree.

    Example:
    
    Input:
    
         4
       /   \
      2     7
     / \   / \
    1   3 6   9
    Output:
    
         4
       /   \
      7     2
     / \   / \
    9   6 3   1
 * @author miche
 *
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        TreeNode left = invertTree(root.right);
        TreeNode right = invertTree(root.left);
        root.left = left;
        root.right = right;
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        if(root == null) return root;
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        invertTree2(root.left);
        invertTree2(root.right);
        return root;
    }
}
