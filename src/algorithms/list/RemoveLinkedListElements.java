package algorithms.list;

/***
 * Remove all elements from a linked list of integers that have value val.

    Example:
    
    Input:  1->2->6->3->4->5->6, val = 6
    Output: 1->2->3->4->5
 * @author miche
 *
 */
public class RemoveLinkedListElements {

    public ListNode removeElements(ListNode head, int val) {
        ListNode firstNode = new ListNode(0);
        firstNode.next = head;
        ListNode pre = firstNode, cur = head;
        while(cur != null) {
            if(cur.val == val) pre.next = cur.next;
            else pre = cur;
            cur = cur.next;
        }
        
        return firstNode.next;
    }

}
