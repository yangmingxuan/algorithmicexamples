package algorithms.btree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/***
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

    For example:
    Given binary tree [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    return its zigzag level order traversal as:
    [
      [3],
      [20,9],
      [15,7]
    ]
 * @author miche
 *
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lret = new ArrayList<List<Integer>>();
        if(root == null) return lret;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        boolean ltor = true;
        stack.add(root);
        while(!stack.isEmpty()) {
            int size = stack.size();
            List<Integer> layer = new ArrayList<Integer>();
            TreeNode[] nodes = new TreeNode[size];
            for(int i = 0; i < size; i++) {
                nodes[i] = stack.pop();
            }
            for(TreeNode node : nodes) {
                layer.add(node.val);
                if(ltor) {
                    if(node.left != null) stack.push(node.left);
                    if(node.right != null) stack.push(node.right);
                } else {
                    if(node.right != null) stack.push(node.right);
                    if(node.left != null) stack.push(node.left);
                }
            }
            lret.add(layer);
            ltor = !ltor;
        }
        
        return lret;
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> lret = new ArrayList<List<Integer>>();
        if(root == null) return lret;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        boolean ltor = true;
        queue.add(root);
        while(!queue.isEmpty()) {
            List<Integer> layer = new ArrayList<Integer>();
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(ltor) {
                    layer.add(node.val);
                } else {
                    layer.add(0, node.val);
                }
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            lret.add(layer);
            ltor = !ltor;
        }
    
        return lret;
    }
}
