package algorithms.list;

/***
 * Given a linked list, remove the n-th node from the end of list and return its head.

    Example:
    
    Given linked list: 1->2->3->4->5, and n = 2.
    
    After removing the second node from the end, the linked list becomes 1->2->3->5.
    Note:
    
    Given n will always be valid.
    
    Follow up:
    
    Could you do this in one pass?
 * @author miche
 *
 */
public class RemoveNthNodeFromEndofList {

    public RemoveNthNodeFromEndofList() {
        // TODO Auto-generated constructor stub
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 0;
        ListNode pre = head;
        while(pre != null) {
            length++;
            pre = pre.next;
        }
        if(length == n) {
            return head.next;
        }
        pre = head;
        int ptr = 0;
        while(ptr < length - n - 1) {
            pre = pre.next;
            ptr++;
        }
        pre.next = pre.next.next;
        return head;
    }

}
