package algorithms.btree;

import java.util.LinkedList;

/***
 * Serialize and Deserialize Binary Tree
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 * @author miche
 *
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "null";
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        StringBuilder sb = new StringBuilder();
        TreeNode node = root;
        stack.add(node);
        while(!stack.isEmpty()) {
            int count = stack.size();
            for(int i = 0; i < count; i++) {
                node = stack.poll();
                if(node != null) {
                    if(node == root) {
                        sb.append(node.val);
                    } else {
                        sb.append(',');
                        sb.append(node.val);
                    }
                    stack.add(node.left);
                    stack.add(node.right);
                } else {
                    sb.append(",null");
                }
            }
        }
        
        String ans = sb.toString();
        while(ans.endsWith(",null")) {
            ans = ans.substring(0, ans.length()-5);
        }
        
        return ans;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.isEmpty() || "null".equals(data)) return null;
        String[] nodes = data.split(",");
        String strNode = nodes[0];
        int val;
        try {
            val = Integer.parseInt(strNode);
        } catch(NumberFormatException e) {
            return null;
        }
        TreeNode root = new TreeNode(val), node = root;
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        stack.offer(node);
        for(int i = 1; i < nodes.length;i++) {
            node = stack.poll();
            strNode = nodes[i];
            if(!"null".equals(strNode)) {
                try {
                    val = Integer.parseInt(strNode);
                    node.left = new TreeNode(val);
                    stack.offer(node.left);
                } catch(NumberFormatException e) {
                    
                }
            }
            i++;
            if(i < nodes.length) {
                strNode = nodes[i];
                if(!"null".equals(strNode)) {
                    try {
                        val = Integer.parseInt(strNode);
                        node.right = new TreeNode(val);
                        stack.offer(node.right);
                    } catch(NumberFormatException e) {
                        
                    }
                }
            }
            if(stack.isEmpty()) break;
        }

        return root;
    }

}
