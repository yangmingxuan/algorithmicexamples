package algorithms.btree;

/***
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

    Note:
    
    Given target value is a floating point.
    You are guaranteed to have only one unique value in the BST that is closest to the target.
    Example:
    
    Input: root = [4,2,5,1,3], target = 3.714286
    
        4
       / \
      2   5
     / \
    1   3
    
    Output: 4
 * @author miche
 *
 */
public class ClosestBinarySearchTreeValue {

    /***
     * binary search tree: left < parent < right
     * @param root
     * @param target
     * @return
     */
    public int closestValue(TreeNode root, double target) {
        if(root == null) return 0;
        int val, closest = root.val;
        while(root != null) {
            val = root.val;
            closest = Math.abs(val - target) < Math.abs(closest - target) ? val : closest;
            root = target < val ? root.left : root.right;
        }
        return closest;
    }

}
