package algorithms.list;

/***
 * Reverse a linked list from position m to n. Do it in one-pass.

    Note: 1 ≤ m ≤ n ≤ length of list.
    
    Example:
    
    Input: 1->2->3->4->5->NULL, m = 2, n = 4
    Output: 1->4->3->2->5->NULL
 * @author miche
 *
 */
public class ReverseLinkedListII {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null) return head;
        ListNode cur = head, pre = null;
        while(m > 1) {
            pre = cur;
            cur = cur.next;
            m--;
            n--;
        }
        ListNode node = pre, tail = cur, tmp;
        while(n > 0) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
            n--;
        }
        if(node != null) {
            node.next = pre;
        } else {
            head = pre;
        }
        tail.next = cur;
        return head;
    }

}
