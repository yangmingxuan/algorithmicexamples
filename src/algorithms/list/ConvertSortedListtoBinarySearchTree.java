package algorithms.list;

import java.util.ArrayList;
import java.util.List;

import algorithms.btree.TreeNode;

/***
 * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

    For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
    
    Example 1:
    
    -10->-3->0->5->9
             |
            \/
            0
          /  \
        -3    9
        /    /
      -10   5
    
    Input: head = [-10,-3,0,5,9]
    Output: [0,-3,9,-10,null,5]
    Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
    Example 2:
    
    Input: head = []
    Output: []
    Example 3:
    
    Input: head = [0]
    Output: [0]
    Example 4:
    
    Input: head = [1,3]
    Output: [3,1]
     
    
    Constraints:
    
    The number of nodes in head is in the range [0, 2 * 104].
    -10^5 <= Node.val <= 10^5
 * @author miche
 *
 */
public class ConvertSortedListtoBinarySearchTree {

    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        //transfer ListNode to ArrayList
        List<Integer> sortedList = new ArrayList<Integer>();
        while(head != null) {
            sortedList.add(head.val);
            head = head.next;
        }
        return listToBST(0, sortedList.size()-1, sortedList);
    }

    public TreeNode listToBST(int l, int r, List<Integer> sortedList) {
        if(l > r) return null;
        if(l == r) return new TreeNode(sortedList.get(l));
        int mid = (l+r) / 2;
        TreeNode node = new TreeNode(sortedList.get(mid));
        node.left = listToBST(l, mid-1, sortedList);
        node.right = listToBST(mid+1, r, sortedList);
        return node;
    }
}
