package algorithms.btree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/***
 * Given the root of a binary tree, each node in the tree has a distinct value.

    After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
    
    Return the roots of the trees in the remaining forest.  You may return the result in any order.
    
     
    
    Example 1:
    
    
    
    Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
    Output: [[1,2,null,4],[6],[7]]
     
    
    Constraints:
    
    The number of nodes in the given tree is at most 1000.
    Each node has a distinct value between 1 and 1000.
    to_delete.length <= 1000
    to_delete contains distinct values between 1 and 1000.
 * @author miche
 *
 */
public class DeleteNodesAndReturnForest {

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ans = new ArrayList<TreeNode>();
        if(root == null || to_delete == null) return ans;
        HashSet<Integer> todelete = new HashSet<Integer>();
        for(int val : to_delete) {
            todelete.add(val);
        }
        ans.add(root);
        dfs(root, null, true, ans, todelete);
        //delete the to delete head
        int size = ans.size();
        for(int i = size-1; i >= 0; i--) {
            if(ans.get(i) == null || todelete.contains(ans.get(i).val)) {
                ans.remove(i);
            }
        }
        return ans;
    }

    public void dfs(TreeNode node, TreeNode parent, boolean isleft, List<TreeNode> ans, HashSet<Integer> todelete) {
        if(node == null) return;
        if(todelete.contains(node.val)) {
            if(parent != null) {
                if(isleft) parent.left = null;
                else parent.right = null;
            }
            if(node.left != null) ans.add(node.left);
            if(node.right != null) ans.add(node.right);
        }
        dfs(node.left, node, true, ans, todelete);
        dfs(node.right, node, false, ans, todelete);
    }
}
