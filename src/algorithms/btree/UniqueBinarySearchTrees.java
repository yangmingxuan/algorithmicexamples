package algorithms.btree;

import java.util.ArrayList;
import java.util.List;

/***
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

    Example:
    
    Input: 3
    Output: 5
    Explanation:
    Given n = 3, there are a total of 5 unique BST's:
    
       1         3     3      2      1
        \       /     /      / \      \
         3     2     1      1   3      2
        /     /       \                 \
       2     1         2                 3
 * @author miche
 *
 */
public class UniqueBinarySearchTrees {

    /***
     * Catalan number f(n) = C(2n,n)/(n+1) 
     * @param n
     * @return
     */
    public int numTrees(int n) {
        long sum = 1;
        
        for(int i = 2*n; i > n; i--) {
            sum = sum*i/(2*n-i+1);
        }
        return (int)(sum/(n+1));
    }

    /***
     * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

        Example:
        
        Input: 3
        Output:
        [
          [1,null,3,2],
          [3,2,null,1],
          [3,1,null,null,2],
          [2,1,3],
          [1,null,2,null,3]
        ]
        Explanation:
        The above output corresponds to the 5 unique BST's shown below:
        
           1         3     3      2      1
            \       /     /      / \      \
             3     2     1      1   3      2
            /     /       \                 \
           2     1         2                 3
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new ArrayList<TreeNode>();
        return backtrack(1, n);
    }

    public List<TreeNode> backtrack(int start, int end) {
        List<TreeNode> lret = new ArrayList<TreeNode>();
        if(start > end) {
            lret.add(null);
            return lret;
        }
        for(int i = start; i <= end; i++) {
            List<TreeNode> leftNode = backtrack(start, i-1);
            List<TreeNode> rightNode = backtrack(i+1, end);
            for(int lcount = 0; lcount < leftNode.size(); lcount++) {
                for(int rcount = 0; rcount < rightNode.size(); rcount++) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode.get(lcount);
                    root.right = rightNode.get(rcount);
                    lret.add(root);
                }
            }
        }
        
        return lret;
    }
}
