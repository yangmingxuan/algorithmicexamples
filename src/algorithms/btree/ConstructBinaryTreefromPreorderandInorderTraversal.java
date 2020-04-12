package algorithms.btree;

import java.util.HashMap;
import java.util.Map;

/***
 * Given preorder and inorder traversal of a tree, construct the binary tree.

    Note:
    You may assume that duplicates do not exist in the tree.
    
    For example, given
    
    preorder = [3,9,20,15,7]
    inorder = [9,3,15,20,7]
    Return the following binary tree:
    
        3
       / \
      9  20
        /  \
       15   7
 * @author miche
 *
 */
public class ConstructBinaryTreefromPreorderandInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildFromPreIn(preorder, inorder, 0, 0, inorder.length);
    }

    public TreeNode buildFromPreIn(int[] preorder, int[] inorder, int preIndex, int inIndex, int len) {
        if(len <= 0 || preIndex + len > preorder.length || inIndex + len > inorder.length) {
            return null;
        }
        int val = preorder[preIndex];
        TreeNode root = new TreeNode(val);
        //find the val index in the inorder queue
        int findIndex = inIndex;
        for(findIndex = inIndex; findIndex < inIndex + len; findIndex++) {
            if(inorder[findIndex] == val) break;
        }
        if(findIndex >= inIndex + len) return null;
        //left child's length
        int leftLen = findIndex - inIndex;
        //right child's length
        int rightLen = len - leftLen - 1;
        
        //inqueue's left is left child
        root.left = buildFromPreIn(preorder, inorder, preIndex+1, inIndex, leftLen);

        //inqueue's right is right child
        root.right = buildFromPreIn(preorder, inorder, preIndex+1+leftLen, findIndex+1, rightLen);

        return root;
    }

    /***
     * Turn inorder array into map, find it's element faster
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inmap = new HashMap<Integer, Integer>();
        for(int i = 0; i < inorder.length; i++) {
            inmap.put(inorder[i], i);
        }
        return buildFromPreIn(preorder, 0, 0, inorder.length, inmap);
    }

    public TreeNode buildFromPreIn(int[] preorder, int preIndex, int inIndex, int len, Map<Integer, Integer> inmap) {
        if(len <= 0 || preIndex + len > preorder.length) {
            return null;
        }
        int val = preorder[preIndex];
        TreeNode root = new TreeNode(val);
        //find the val index in the inorder queue
        int findIndex = inmap.get(val);
        if(findIndex >= inIndex + len) return null;
        //left child's length
        int leftLen = findIndex - inIndex;
        //right child's length
        int rightLen = len - leftLen - 1;
        
        //inqueue's left is left child
        root.left = buildFromPreIn(preorder, preIndex+1, inIndex, leftLen, inmap);

        //inqueue's right is right child
        root.right = buildFromPreIn(preorder, preIndex+1+leftLen, findIndex+1, rightLen, inmap);

        return root;
    }

}
