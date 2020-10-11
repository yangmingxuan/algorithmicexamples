package algorithms.btree;

import java.util.LinkedList;
import java.util.Queue;

/***
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

    struct Node {
      int val;
      Node *left;
      Node *right;
      Node *next;
    }
    Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
    
    Initially, all next pointers are set to NULL.
    
     
    
    Follow up:
    
    You may only use constant extra space.
    Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
     
    
    Example 1:
          1                      1
        /   \                  /    \
       2     3                2----->3-->NULL
      / \   / \              / \    / \
     4  5  6   7            4-->5->6-->7-->NULL
    
    Input: root = [1,2,3,4,5,6,7]
    Output: [1,#,2,3,#,4,5,6,7,#]
    Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
     
    
    Constraints:
    
    The number of nodes in the given tree is less than 4096.
-1000 <= node.val <= 1000
 * @author miche
 *
 */
public class PopulatingNextRightPointersinEachNode {

    public Node connect2(Node root) {
        if(root == null) return root;
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Node node = queue.poll();
                if(i < size - 1) {
                    node.next = queue.peek();
                }
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
        }
        return root;
    }

    public Node connect(Node root) {
        if(root == null) return null;
        if(root.left != null) root.left.next = root.right;
        if(root.right != null) root.right.next = root.next != null ? root.next.left : null;
        connect(root.left);
        connect(root.right);
        return root;
    }

}
