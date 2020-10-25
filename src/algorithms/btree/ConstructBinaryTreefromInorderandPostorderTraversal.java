package algorithms.btree;

import java.util.HashMap;

/***
 * Given inorder and postorder traversal of a tree, construct the binary tree.

    Note:
    You may assume that duplicates do not exist in the tree.
    
    For example, given
    
    inorder = [9,3,15,20,7]
    postorder = [9,15,7,20,3]
    Return the following binary tree:
    
        3
       / \
      9  20
        /  \
       15   7
 * @author miche
 *
 */
public class ConstructBinaryTreefromInorderandPostorderTraversal {

    HashMap<Integer, Integer> valIndex;
    int iPostIdx;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null || inorder.length == 0 || inorder.length != postorder.length) {
            return null;
        }
        valIndex = new HashMap<Integer, Integer>();
        for(int i = 0; i < inorder.length; i++) {
            valIndex.put(inorder[i], i);
        }
        iPostIdx = inorder.length-1;
        //return helper(0, postorder, 0, inorder.length);
        return helper(0, inorder.length-1, postorder);
    }

    public TreeNode helper(int infrom, int[] postorder, int postfrom, int len) {
        if(len == 0) return null;
        int rootval = postorder[postfrom+len-1];
        TreeNode node = new TreeNode(rootval);
        int inorderindex = valIndex.get(rootval);
        int leftlen = inorderindex - infrom;
        int rightlen = len - 1 - leftlen;
        node.left = helper(infrom, postorder, postfrom, leftlen);
        node.right = helper(infrom+leftlen+1, postorder, postfrom+leftlen, rightlen);
        
        return node;
    }

    public TreeNode helper(int low, int hight, int[] postorder) {
        if(low > hight) return null;
        int rootval = postorder[iPostIdx--];
        TreeNode node = new TreeNode(rootval);
        int mid = valIndex.get(rootval);
        node.right = helper(mid+1, hight, postorder);
        node.left = helper(low, mid-1, postorder);
        return node;
    }
}
