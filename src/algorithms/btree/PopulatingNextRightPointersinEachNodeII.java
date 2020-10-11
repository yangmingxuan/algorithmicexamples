package algorithms.btree;

import java.util.LinkedList;
import java.util.Queue;

/***
 * Given a binary tree

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
          1                      1-->NULL
        /   \                  /   \
       2     3                2---->3-->NULL
      / \     \              / \     \
     4  5      7            4-->5---->7-->NULL
    
    Input: root = [1,2,3,4,5,null,7]
    Output: [1,#,2,3,#,4,5,7,#]
    Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
     
    
    Constraints:
    
    The number of nodes in the given tree is less than 6000.
    -100 <= node.val <= 100
 * @author miche
 *
 */
public class PopulatingNextRightPointersinEachNodeII {

    public Node connect(Node root) {
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

}
