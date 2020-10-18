package algorithms.btree;

/***
 * Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

    In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)
    
    Return the number of moves required to make every node have exactly one coin.
    
    
    
    Example 1:
       3        3      1
      / \     1/ \1   / \
     0   0    0   0  1   1
    
    
    Input: [3,0,0]
    Output: 2
    Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
    Example 2:
       0        0      1
      / \     2/ \1   / \
     3   0    3   0  1   1
    
    Input: [0,3,0]
    Output: 3
    Explanation: From the left child of the root, we move two coins to the root [taking two moves].  Then, we move one coin from the root of the tree to the right child.
    Example 3:
    
    
    
    Input: [1,0,2]
    Output: 2
    Example 4:
    
    
    
    Input: [1,0,0,null,3]
    Output: 4
    
    
    Note:
    
    1<= N <= 100
    0 <= node.val <= N

 * @author miche
 *
 */
public class DistributeCoinsinBinaryTree {

    /***
     * Explanation: 叶结点移动的次数为结点值与1的差值，而父母结点移动数量为它的子结点移动次数的绝对值和。
     * 移动之后，父母结点的剩余需移动数为原值+向子结点移动的数值-1.
     * The number of leaf node moves is the difference between the node value and 1, and the number of parent node moves is the absolute sum of the number of child node moves.
     * After the movement, the remaining movement amount of the parent node is the original value + the numbers moved to the child node -1.
     * @param root
     * @return
     */
    int ans;
    public int distributeCoins(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    public int dfs(TreeNode node) {
        if(node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        ans += (Math.abs(left)+Math.abs(right));
        return node.val + left + right - 1;
    }
}
