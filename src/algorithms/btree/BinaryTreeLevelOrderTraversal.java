package algorithms.btree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/***
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

    For example:
    Given binary tree [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    return its level order traversal as:
    [
      [3],
      [9,20],
      [15,7]
    ]
 * @author miche
 *
 */
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lret = new ArrayList<List<Integer>>();
        if(root == null) return lret;
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        stack.add(root);
        while(!stack.isEmpty()) {
            List<Integer> list = new ArrayList<Integer>();
            int size = stack.size();
            for(int i = 0; i < size; i++) {
                TreeNode tree = stack.poll();
                list.add(tree.val);
                if(tree.left != null) stack.add(tree.left);
                if(tree.right != null) stack.add(tree.right);
            }
            lret.add(list);
        }

        return lret;
    }

}
