package algorithms.btree;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

    Note: A leaf is a node with no children.
    
    Example:
    
    Given the below binary tree and sum = 22,
    
          5
         / \
        4   8
       /   / \
      11  13  4
     /  \    / \
    7    2  5   1
    Return:
    
    [
       [5,4,11,2],
       [5,8,4,5]
    ]
 * @author miche
 *
 */
public class PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> eles = new ArrayList<Integer>();
        path(root, sum, ans, eles);
        return ans;
    }

    public void path(TreeNode root, int sum, List<List<Integer>> ans, List<Integer> eles) {
        if(root == null) return;
        eles.add(root.val);
        if(root.val == sum && root.left == null && root.right == null) {
            ans.add(new ArrayList<Integer>(eles));
        } else {
            path(root.left, sum-root.val, ans, eles);
            path(root.right, sum-root.val, ans, eles);
        }
        eles.remove(eles.size()-1);
    }
}
