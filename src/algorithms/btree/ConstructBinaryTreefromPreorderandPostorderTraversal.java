package algorithms.btree;

import java.util.HashMap;

/***
 * Return any binary tree that matches the given preorder and postorder traversals.

    Values in the traversals pre and post are distinct positive integers.
    
     
    
    Example 1:
    
    Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
    Output: [1,2,3,4,5,6,7]
     
    
    Note:
    
    1 <= pre.length == post.length <= 30
    pre[] and post[] are both permutations of 1, 2, ..., pre.length.
    It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
 * @author miche
 *
 */
public class ConstructBinaryTreefromPreorderandPostorderTraversal {

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        HashMap<Integer, Integer> postMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < post.length; i++) {
            postMap.put(post[i], i);
        }
        return buildTreeFromPrePost(pre, 0, postMap, 0, pre.length);
    }

    public TreeNode buildTreeFromPrePost(int[] pre, int preFrom, HashMap<Integer, Integer> post, int postFrom, int len) {
        if(len <= 0) return null;
        //pre's first element is the root
        TreeNode node = new TreeNode(pre[preFrom]);
        //length == 1 indicates that there not sub tree
        if(len == 1) {
            return node;
        }
        
        int leftChild = pre[preFrom+1];
        //find the leftChild's index in post
        int idx = post.get(leftChild);
        
        int leftLen = idx - postFrom + 1;
        int rightLen = len - leftLen - 1;
        
        node.left = buildTreeFromPrePost(pre, preFrom+1, post, postFrom, leftLen);
        node.right = buildTreeFromPrePost(pre, preFrom+1+leftLen, post, idx+1, rightLen);
        
        return node;
    }
}
