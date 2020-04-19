package algorithms.btree;

import java.util.HashMap;
import java.util.Map;

/***
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

    Determine the maximum amount of money the thief can rob tonight without alerting the police.
    
    Example 1:
    
    Input: [3,2,3,null,3,null,1]
    
         3
        / \
       2   3
        \   \ 
         3   1
    
    Output: 7 
    Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
    Example 2:
    
    Input: [3,4,5,1,3,null,1]
    
         3
        / \
       4   5
      / \   \ 
     1   3   1
    
    Output: 9
    Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 * @author miche
 *
 */
public class HouseRobberIII {

    /***
     * Record dp with map
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        //Record dp with map
        return rob(root, new HashMap<TreeNode, Integer>());
    }

    public int rob(TreeNode root, Map<TreeNode, Integer> dp) {
        if(root == null) {
            return 0;
        }
        if(dp.containsKey(root)) {
            return dp.get(root);
        }
        
        int robCount = 0;
        if(root.left != null) {
            robCount = robCount + rob(root.left.left, dp) + rob(root.left.right, dp);
        }
        if(root.right != null) {
            robCount = robCount + rob(root.right.left, dp) + rob(root.right.right, dp);
        }

        robCount = Math.max(robCount+root.val, rob(root.left, dp) + rob(root.right, dp));
        dp.put(root, robCount);
 
        return robCount;
    }


    /***
     * Record dp with int[2], 
     * the first element record node and grandson node's max value, 
     * the second element record the children's max value
     * @param root
     * @return
     */
    public int rob2(TreeNode root) {
        //Record dp with int[2]
        int[] dp = backtrack(root);
        return Math.max(dp[0], dp[1]);
    }
    
    public int[] backtrack(TreeNode root) {
        if(root == null) {
            int[] dp = {0, 0};
            return dp;
        }
        int[] dp = new int[2];
        int[] left = backtrack(root.left);
        int[] right = backtrack(root.right);
        dp[0] = root.val + left[1] + right[1];
        dp[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return dp;
    }
}
