package algorithms.btree;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.


    Example:
    
    Input: [1,2,3,4,5]
      
              1
             / \
            2   3
           / \     
          4   5    
    
    Output: [[4,5,3],[2],[1]]
     
    
    Explanation:
    
    1. Removing the leaves [4,5,3] would result in this tree:
    
              1
             / 
            2          
     
    
    2. Now removing the leaf [2] would result in this tree:
    
              1          
     
    
    3. Now removing the leaf [1] would result in the empty tree:
    
              []         
    [[3,5,4],[2],[1]], [[3,4,5],[2],[1]], etc, are also consider correct answers since per each level it doesn't matter the order on which elements are returned.
 * @author miche
 *
 */
public class FindLeavesofBinaryTree {

    /***
     * null is level 0, leaf node is level 1, its parent is level 2, and so on
     * @param root
     * @return
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        helper(root, ans);
        return ans;
    }

    public int helper(TreeNode node, List<List<Integer>> ans) {
        if(node == null) return 0;
        int level = 1 + Math.max(helper(node.left, ans), helper(node.right, ans));
        if(ans.size() < level) {
            ans.add(new ArrayList<Integer>());
        }
        ans.get(level-1).add(node.val);
        return level;
    }
}
