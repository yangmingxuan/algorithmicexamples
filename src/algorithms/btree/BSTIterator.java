package algorithms.btree;

import java.util.LinkedList;
import java.util.Queue;

/***
 * Binary Search Tree Iterator
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

    Calling next() will return the next smallest number in the BST.
    
     
    
    Example:
    
    
    
    BSTIterator iterator = new BSTIterator(root);
    iterator.next();    // return 3
    iterator.next();    // return 7
    iterator.hasNext(); // return true
    iterator.next();    // return 9
    iterator.hasNext(); // return true
    iterator.next();    // return 15
    iterator.hasNext(); // return true
    iterator.next();    // return 20
    iterator.hasNext(); // return false
     
    
    Note:
    
    next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
    You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
 * @author miche
 *
 */
public class BSTIterator {

    private Queue<Integer> queue;
    public BSTIterator(TreeNode root) {
        queue = new LinkedList<Integer>();
        inorderTraversal(root);
    }

    public void inorderTraversal(TreeNode root) {
        if(root == null) return;
        inorderTraversal(root.left);
        queue.offer(root.val);
        inorderTraversal(root.right);
    }

    /** @return the next smallest number */
    public int next() {
        return queue.poll();
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
