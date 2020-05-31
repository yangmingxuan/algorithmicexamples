package algorithms.btree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/***
 * We are given a binary tree (with root node root), a target node, and an integer value K.

    Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
    
     
    
    Example 1:
    
    Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
    
    Output: [7,4,1]
    
    Explanation: 
    The nodes that are a distance 2 from the target node (with value 5)
    have values 7, 4, and 1.
                    3
                 /      \
                5        1
               / \      / \
              6   2    0   8
                 / \
                7   4
    
    Note that the inputs "root" and "target" are actually TreeNodes.
    The descriptions of the inputs above are just serializations of these objects.
     
    
    Note:
    
    The given tree is non-empty.
    Each node in the tree has unique values 0 <= node.val <= 500.
    The target node is a node in the tree.
    0 <= K <= 1000.
 * @author miche
 *
 */
public class AllNodesDistanceKinBinaryTree {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> ans = new ArrayList<Integer>();
        if(root == null || target == null) return ans;
        if(K == 0) {
            ans.add(target.val);
            return ans;
        }
        Map<TreeNode, TreeNode> parentNodes = new HashMap<TreeNode, TreeNode>();
        getParent(root, parentNodes);
        
        Set<TreeNode> visited = new HashSet<TreeNode>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        visited.add(target);
        queue.offer(target);
        int distance = 0;
        while(!queue.isEmpty()) {
            if(distance == K) {
                while(!queue.isEmpty()) {
                    ans.add(queue.poll().val);
                }
                return ans;
            }
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(parentNodes.get(node) != null && !visited.contains(parentNodes.get(node))) {
                    queue.add(parentNodes.get(node));
                    visited.add(parentNodes.get(node));
                }
                if(node.left != null && !visited.contains(node.left)) {
                    queue.add(node.left);
                    visited.add(node.left);
                }
                if(node.right != null && !visited.contains(node.right)) {
                    queue.add(node.right);
                    visited.add(node.right);
                }
            }
            //each loop increases the distance by 1;
            distance++;
        }
        
        return ans;
    }

    public void getParent(TreeNode node, Map<TreeNode, TreeNode> parentNodes) {
        if(node.left != null) {
            parentNodes.put(node.left, node);
            getParent(node.left, parentNodes);
        }
        if(node.right != null) {
            parentNodes.put(node.right, node);
            getParent(node.right, parentNodes);
        }
    }
}
