package algorithms.btree;

import java.util.HashSet;

/***
 * Given a binary tree, we install cameras on the nodes of the tree. 

    Each camera at a node can monitor its parent, itself, and its immediate children.
    
    Calculate the minimum number of cameras needed to monitor all nodes of the tree.
    
     
    
    Example 1:
    
    
    Input: [0,0,null,0,0]
    Output: 1
    Explanation: One camera is enough to monitor all nodes if placed as shown.
    Example 2:
    
    
    Input: [0,0,null,0,null,0,null,null,0]
    Output: 2
    Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
    
    Note:
    
    The number of nodes in the given tree will be in the range [1, 1000].
    Every node has value 0.
 * @author miche
 *
 */
public class BinaryTreeCameras {

    int ans = 0;
    public int minCameraCover(TreeNode root) {
        HashSet<TreeNode> canMonitor = new HashSet<TreeNode>();
        canMonitor.add(null); //include all node's null children (such as leaf node or one child node is null) 
        dfs(root, null, canMonitor);
        return ans;
    }

    public void dfs(TreeNode node, TreeNode parent, HashSet<TreeNode> canMonitor) {
        if(node == null) return;
        dfs(node.left, node, canMonitor);
        dfs(node.right, node, canMonitor);
        if(parent == null && !canMonitor.contains(node)  //root
                || !canMonitor.contains(node.left)
                || !canMonitor.contains(node.right)) {
            ans++; //if not in monitor answer + 1 and cover the all node can be monitored
            canMonitor.add(parent);
            canMonitor.add(node);
            canMonitor.add(node.left);
            canMonitor.add(node.right);
        }
    }
}
