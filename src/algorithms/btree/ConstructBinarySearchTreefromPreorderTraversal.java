package algorithms.btree;

/***
 *Return the root node of a binary search tree that matches the given preorder traversal.

    (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)
    
    It's guaranteed that for the given test cases there is always possible to find a binary search tree with the given requirements.
    
    Example 1:
    
    Input: [8,5,1,7,10,12]
    Output: [8,5,10,1,7,null,12]
              8
            /   \
           5    10
          / \     \
         1   7    12
    
    Constraints:
    
    1 <= preorder.length <= 100
    1 <= preorder[i] <= 10^8
    The values of preorder are distinct. 
 * @author miche
 *
 */
public class ConstructBinarySearchTreefromPreorderTraversal {

    int[] preorder;
    int idx;
    int n;
    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder == null || preorder.length == 0) return null;
        this.preorder = preorder;
        this.idx = 0;
        this.n = preorder.length;
        return backtracking(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public TreeNode backtracking(int lower, int upper) {
        if(idx == n) return null;
        int val = preorder[idx];
        if(val < lower || val > upper) //not this sub tree
            return null;
        TreeNode node = new TreeNode(val);
        idx++;
        node.left = backtracking(lower, val);
        node.right = backtracking(val, upper);
        return node;
    }
}
