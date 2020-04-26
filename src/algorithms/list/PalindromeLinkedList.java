package algorithms.list;

/*
 * Given a singly linked list, determine if it is a palindrome.

    Example 1:
    
    Input: 1->2
    Output: false
    Example 2:
    
    Input: 1->2->2->1
    Output: true
    Follow up:
    Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode cur = head, node1 = head, node2 = head.next;
        while(node2 != null && node2.next != null) {
            cur = node1;
            node1 = node1.next;
            node2 = node2.next.next;
        }
        if(node2 == null) {
            //count of this LinkList is odd
        }
        cur = cur.next;
        //resever this link 
        ListNode newOne, prev = null, curr;
        newOne = cur;
        while(cur != null) {
            curr = cur.next;
            cur.next = prev;
            newOne = cur;
            cur = curr;
            prev = newOne;
        }
        cur = head;
        while(newOne != null && cur != null) {
            if(cur.val != newOne.val) {
                return false;
            }
            cur = cur.next;
            newOne = newOne.next;
        }
        return true;
    }

}
