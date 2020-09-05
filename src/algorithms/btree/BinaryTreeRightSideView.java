package algorithms.btree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

    Example:
    
    Input: [1,2,3,null,5,null,4]
    Output: [1, 3, 4]
    Explanation:
    
       1            <---
     /   \
    2     3         <---
     \     \
      5     4       <---
 * @author miche
 *
 */
public class BinaryTreeRightSideView {

    List<Integer> ans;
    public List<Integer> rightSideView(TreeNode root) {
        ans = new ArrayList<Integer>();
        if(root == null) return ans;
        backTrack(root, 0);
        return ans;
    }

    public void backTrack(TreeNode node, int level) {
        if(level == ans.size()) {
            ans.add(node.val);
        }
        if(node.right != null) {
            backTrack(node.right, level+1);
        }
        if(node.left != null) {
            backTrack(node.left, level+1);
        }
    }

    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int count = queue.size();
            for(int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                if(i == 0) {
                    //the first one means the rightest one
                    result.add(node.val);
                }
                if(node.right != null) queue.offer(node.right);
                if(node.left != null) queue.offer(node.left);
            }
        }
        return result;
    }
}
