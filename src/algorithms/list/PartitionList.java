package algorithms.list;

/***
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

    You should preserve the original relative order of the nodes in each of the two partitions.
    
    Example:
    
    Input: head = 1->4->3->2->5->2, x = 3
    Output: 1->2->2->4->3->5
 * @author miche
 *
 */
public class PartitionList {

    public ListNode partition(ListNode head, int x) {
        if(head == null) return head;
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode cur = head, firstGreater = null, preFirst = pre;
        head = pre;
        while(cur != null) {
            if(firstGreater == null && cur.val >= x) {
                firstGreater = cur;
            }
            if(firstGreater != null && cur.val < x) {
                pre.next = cur.next;
                preFirst.next = cur;
                cur.next = firstGreater;
                preFirst = cur;
                cur = pre.next;
                continue;
            }
            pre = pre.next;
            cur = cur.next;
            if(firstGreater == null) {
                preFirst = preFirst.next;
            }
        }
        return head.next;
    }

}
