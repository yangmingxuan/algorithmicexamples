package algorithms.btree;

/***
 * Given a non-empty binary tree, find the maximum path sum.

    For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
    
    Example 1:
    
    Input: [1,2,3]
    
           1
          / \
         2   3
    
    Output: 6
    Example 2:
    
    Input: [-10,9,20,null,null,15,7]
    
       -10
       / \
      9  20
        /  \
       15   7
    
    Output: 42
 * @author miche
 *
 */
public class BinaryTreeMaximumPathSum {

    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        return maxSum(root, new MutableInt());
    }

    public int maxSum(TreeNode node, MutableInt maxPath) {
        if(node == null) return Integer.MIN_VALUE;
        MutableInt leftMaxPath = new MutableInt();
        MutableInt rightMaxpath = new MutableInt();
        int leftMaxSum = maxSum(node.left, leftMaxPath);
        int rightMaxSum = maxSum(node.right, rightMaxpath);
        
        //1. With self node only
        //2  Self + left
        //3. Self + right
        //4. Self + left + right
        int withNode = node.val;
        withNode = Math.max(withNode, leftMaxPath.value+node.val);
        withNode = Math.max(withNode, rightMaxpath.value+node.val);
        withNode = Math.max(withNode, leftMaxPath.value+rightMaxpath.value+node.val);
        
        int withoutNode = Math.max(leftMaxSum, rightMaxSum);

        maxPath.value = Math.max(leftMaxPath.value, rightMaxpath.value) + node.val;
        maxPath.value = Math.max(maxPath.value, node.val);
        
        return Math.max(withNode, withoutNode);
    }


    private int maxVal = Integer.MIN_VALUE;
    public int maxPathSum2(TreeNode root) {
        maxSum2(root);
        return maxVal;
    }

    public int maxSum2(TreeNode node) {
        if(node == null) return 0;
        int leftMaxSum = Math.max(0, maxSum2(node.left));
        int rightMaxSum = Math.max(0, maxSum2(node.right));
        maxVal = Math.max(maxVal, leftMaxSum+rightMaxSum+node.val);

        return Math.max(leftMaxSum, rightMaxSum) + node.val;
    }
}

class MutableInt {
    public int value;

    public MutableInt() {
        value = 0;
    }
}
