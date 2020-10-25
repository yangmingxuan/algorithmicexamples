package algorithms.list;

/***
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

    Return the linked list sorted as well.
    
    Example 1:
    
    Input: 1->2->3->3->4->4->5
    Output: 1->2->5
    Example 2:
    
    Input: 1->1->1->2->3
    Output: 2->3
 * @author miche
 *
 */
public class RemoveDuplicatesfromSortedListII {

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode node = new ListNode(0);
        node.next = head;
        ListNode pre = node, cur = head, nxt = head.next;
        while(nxt != null) {
            boolean rep = false;
            while(nxt != null && cur.val == nxt.val) {
                rep = true;
                nxt = nxt.next;
            }
            if(rep) {
                pre.next = nxt;
                if(nxt == null) break;
                else {
                    cur = nxt;
                    nxt = cur.next;
                }
            } else {
                pre = pre.next;
                cur = cur.next;
                nxt = nxt.next;
            }
        }
        return node.next;
    }

}
