package algorithms.btree;

/***
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

    Example:
    Given a binary tree
              1
             / \
            2   3
           / \     
          4   5    
    Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
    
    Note: The length of path between two nodes is represented by the number of edges between them.
 * @author miche
 *
 */
public class DiameterofBinaryTree {

    int maxDiameter;
    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 1;
        dfs(root);
        return maxDiameter - 1;
    }

    public int dfs(TreeNode node) {
        if(node == null) return 0;
        int lmeter = dfs(node.left);
        int rmeter = dfs(node.right);
        
        maxDiameter = Math.max(maxDiameter, lmeter+rmeter+1);
        
        return Math.max(lmeter, rmeter) + 1;
    }
}
