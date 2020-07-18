package algorithms.btree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/***
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.  (The values of the nodes may still be duplicates.)

    Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.
    
    The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.
    
    The right-most node is also defined by the same way with left and right exchanged.
    
    Example 1
    
    Input:
      1
       \
        2
       / \
      3   4
    
    Ouput:
    [1, 3, 4, 2]
    
    Explanation:
    The root doesn't have left subtree, so the root itself is left boundary.
    The leaves are node 3 and 4.
    The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
    So order them in anti-clockwise without duplicates and we have [1,3,4,2].
     
    
    Example 2
    
    Input:
        ____1_____
       /          \
      2            3
     / \          / 
    4   5        6   
       / \      / \
      7   8    9  10  
           
    Ouput:
    [1,2,4,7,8,9,10,6,3]
    
    Explanation:
    The left boundary are node 1,2,4. (4 is the left-most node according to definition)
    The leaves are node 4,7,8,9,10.
    The right boundary are node 1,3,6,10. (10 is the right-most node).
    So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
 * @author miche
 *
 */
public class BoundaryofBinaryTree {

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        if(root == null) return ans;
        ans.add(root.val);
        if(isLeaf(root)) return ans;
        
        //add the left boundary
        TreeNode node = root.left;
        while(node != null) {
            if(!isLeaf(node)) {
                ans.add(node.val);
            }
            if(node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        //add the leaf
        addLeaves(ans, root);
        
        //add the right boundary
        /*
        Stack<Integer> stack = new Stack<Integer>();
        node = root.right;
        while(node != null) {
            if(!isLeaf(node)) {
                stack.push(node.val);
            }
            if(node.right != null) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        while(!stack.isEmpty()) {
            ans.add(stack.pop());
        }
        */
        rightBoundary(ans, root.right);
        
        return ans;
    }

    public boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public void addLeaves(List<Integer> ans, TreeNode root) {
        if(isLeaf(root)) {
            ans.add(root.val);
            return;
        }
        if(root.left != null) {
            addLeaves(ans, root.left);
        }
        if(root.right != null) {
            addLeaves(ans, root.right);
        }
    }

    public void rightBoundary(List<Integer> ans, TreeNode root) {
        if(root == null || isLeaf(root)) return;
        if(root.right != null) rightBoundary(ans, root.right);
        else rightBoundary(ans, root.left);
        ans.add(root.val);  //reverse order;
    }
}
