package algorithms.btree;
/***
 * Given the root of a binary tree, return the sum of all left leaves.

 
    
    Example 1:
                3
               / \
              9   20
                  / \
                 15  7
    
    Input: root = [3,9,20,null,null,15,7]
    Output: 24
    Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
    Example 2:
    
    Input: root = [1]
    Output: 0
     
    
    Constraints:
    
    The number of nodes in the tree is in the range [1, 1000].
    -1000 <= Node.val <= 1000
 * @author miche
 *
 */
public class SumofLeftLeaves {

    int ans = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        if(root.left != null && root.left.left == null && root.left.right == null) {
            ans += root.left.val;
        }
        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);
        return ans;
    }

}
