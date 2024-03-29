package algorithms.btree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/***
 * Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.

    Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 10^9 + 7.
    
    Note that you need to maximize the answer before taking the mod and not after taking it.
    
     
    
    Example 1:
    
    
    Input: root = [1,2,3,4,5,6]
    Output: 110
    Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
    Example 2:
    
    
    Input: root = [1,null,2,3,4,null,null,5,6]
    Output: 90
    Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
    Example 3:
    
    Input: root = [2,3,9,10,7,8,6,5,4,11,1]
    Output: 1025
    Example 4:
    
    Input: root = [1,1]
    Output: 1
     
    
    Constraints:
    
    The number of nodes in the tree is in the range [2, 5 * 10^4].
    1 <= Node.val <= 10^4
 * @author miche
 *
 */
public class MaximumProductofSplittedBinaryTree {

    /***
     * If we know the sum of a subtree, the answer is max( (total_sum - subtree_sum) * subtree_sum) in each node.
     * @param root
     * @return
     */
    public int maxProduct(TreeNode root) {
        long ans = Integer.MIN_VALUE;
        List<Long> subtreesum = new ArrayList<Long>();
        long total_sum = subsum(subtreesum, root);
        for(long subtree_sum : subtreesum) {
            ans = Math.max(ans, (total_sum - subtree_sum) * subtree_sum);
            
        }
        return (int)(ans % (1e9+7));
    }

    private long subsum(List<Long> subtreesum, TreeNode node) {
        if(node == null) return 0;
        long totalsum = node.val + subsum(subtreesum, node.left) + subsum(subtreesum, node.right);
        subtreesum.add(totalsum);
        return totalsum;
    }
}
