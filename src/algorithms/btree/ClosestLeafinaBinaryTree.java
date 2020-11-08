package algorithms.btree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/***
 * Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.

    Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.
    
    In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.
    
    Example 1:
    
    Input:
    root = [1, 3, 2], k = 1
    Diagram of binary tree:
              1
             / \
            3   2
    
    Output: 2 (or 3)
    
    Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
    Example 2:
    
    Input:
    root = [1], k = 1
    Output: 1
    
    Explanation: The nearest leaf node is the root node itself.
    Example 3:
    
    Input:
    root = [1,2,3,4,null,null,null,5,null,6], k = 2
    Diagram of binary tree:
                 1
                / \
               2   3
              /
             4
            /
           5
          /
         6
    
    Output: 3
    Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
    Note:
    root represents a binary tree with at least 1 node and at most 1000 nodes.
    Every node has a unique node.val in range [1, 1000].
    There exists some node in the given binary tree for which node.val == k.
 * @author miche
 *
 */
public class ClosestLeafinaBinaryTree {

    public int findClosestLeaf(TreeNode root, int k) {
        if(root.left == null && root.right == null && root.val == k) return root.val;
        HashMap<TreeNode, List<TreeNode>> graph = new HashMap<TreeNode, List<TreeNode>>();
        //treat the BT as a graph, parent and children node as its neighbor
        dfs(root, null, graph);
        HashSet<TreeNode> visited = new HashSet<TreeNode>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        for(TreeNode node : graph.keySet()) {
            if(node.val == k) {
                queue.offer(node);
                visited.add(node);
                if(node.left == null && node.right == null) {
                    return node.val;
                }
                break;
            }
        }
        
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node.left == null && node.right == null) {
                //meet the first leaf node, return its val
                return node.val;
            }
            for(TreeNode nei : graph.get(node)) {
                if(!visited.contains(nei)) {
                    queue.offer(nei);
                    visited.add(nei);
                }
            }
        }

        return 0;
    }

    public void dfs(TreeNode node, TreeNode parent, HashMap<TreeNode, List<TreeNode>> graph) {
        if(node == null) return;
        if(parent != null) {
            if(!graph.containsKey(parent)) {
                graph.put(parent, new ArrayList<TreeNode>());
            }
            if(!graph.containsKey(node)) {
                graph.put(node, new ArrayList<TreeNode>());
            }
            graph.get(parent).add(node);
            graph.get(node).add(parent);
        }
        dfs(node.left, node, graph);
        dfs(node.right, node, graph);
    }
}
