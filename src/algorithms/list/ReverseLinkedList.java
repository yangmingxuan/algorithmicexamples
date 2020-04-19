package algorithms.list;

/***
 * Reverse a singly linked list.

    Example:
    
    Input: 1->2->3->4->5->NULL
    Output: 5->4->3->2->1->NULL
    Follow up:
    
    A linked list can be reversed either iteratively or recursively. Could you implement both?
 * @author miche
 *
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        ListNode newOne, cur, prev = null;
        newOne = head;
        while(head != null) {
            cur = head.next;
            head.next = prev;
            newOne = head;
            head = cur;
            prev = newOne;
        }
        return newOne;
    }

    public ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode newOne = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newOne;
    }
}
