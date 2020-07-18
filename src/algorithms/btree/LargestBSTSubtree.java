package algorithms.btree;

/***
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

    Note:
    A subtree must include all of its descendants.
    
    Example:
    
    Input: [10,5,15,1,8,null,7]
    
       10 
       / \ 
      5  15 
     / \   \ 
    1   8   7
    
    Output: 3
    Explanation: The Largest BST Subtree in this case is the highlighted one.
                 The return value is the subtree's size, which is 3.
    Follow up:
    Can you figure out ways to solve it with O(n) time complexity?
 * @author miche
 *
 */
public class LargestBSTSubtree {

    int ans = 0;
    public int largestBSTSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    public int[] dfs(TreeNode node) {
        if(node == null) {
            return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        if(node.val > left[1] && node.val < right[0]) {
            ans = Math.max(ans, left[2]+right[2]+1);
            int min = Math.min(node.val, left[0]);
            int max = Math.max(node.val, right[1]);
            return new int[] {min, max, left[2]+right[2]+1};
        }
        
        return new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
    }
}
