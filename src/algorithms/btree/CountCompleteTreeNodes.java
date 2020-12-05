package algorithms.btree;

/***
 * Given a complete binary tree, count the number of nodes.

    Note:
    
    Definition of a complete binary tree from Wikipedia:
    In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
    
    Example:
    
    Input: 
        1
       / \
      2   3
     / \  /
    4  5 6
    
    Output: 6
 * @author miche
 *
 */
public class CountCompleteTreeNodes {

    public int countNodes(TreeNode root) {
        return root != null ? 1 + countNodes(root.left) + countNodes(root.right) : 0;
    }

    public int getHeigh(TreeNode root) {
        if(root == null) return 0;
        else return 1+getHeigh(root.left);
    }
    
    /***
     * count = 1+2+4+...2*(h-1)+the number of the last level
     * @param root
     * @return
     */
    public int countNodes2(TreeNode root) {
        if(root == null) return 0;
        int leftHeight = getHeigh(root.left);
        int rightHeight = getHeigh(root.right);
        if(leftHeight == rightHeight) return (int)Math.pow(2, leftHeight) + countNodes2(root.right);
        return (int)Math.pow(2, rightHeight) + countNodes2(root.left);
    }
}
