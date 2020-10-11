package algorithms.btree;

/***
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

    For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
    
    Example:
    
    Given the sorted array: [-10,-3,0,5,9],
    
    One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
    
          0
         / \
       -3   9
       /   /
     -10  5
 * @author miche
 *
 */
public class ConvertSortedArraytoBinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0) return null;
        
        return helper(0, nums.length-1, nums);
    }

    public TreeNode helper(int left, int right, int[] nums) {
        if(left > right) return null;
        int mid = (left+right) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(left, mid-1, nums);
        node.right = helper(mid+1, right, nums);
        return node;
    }
}
