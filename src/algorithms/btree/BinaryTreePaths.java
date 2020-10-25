package algorithms.btree;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a binary tree, return all root-to-leaf paths.

    Note: A leaf is a node with no children.
    
    Example:
    
    Input:
    
       1
     /   \
    2     3
     \
      5
    
    Output: ["1->2->5", "1->3"]
    
    Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 * @author miche
 *
 */
public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<String>();
        List<Integer> path = new ArrayList<Integer>();
        dfs(root, ans, path);
        return ans;
    }

    public void dfs(TreeNode node, List<String> ans, List<Integer> path) {
        if(node == null) return;
        path.add(node.val);
        if(node.left == null && node.right == null) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < path.size(); i++) {
                if(i == 0) {
                    sb.append(path.get(i));
                } else {
                    sb.append("->").append(path.get(i));
                }
            }
            ans.add(sb.toString());
        } else {
            dfs(node.left, ans, path);
            dfs(node.right, ans, path);
        }
        path.remove(path.size()-1);
    }
}
