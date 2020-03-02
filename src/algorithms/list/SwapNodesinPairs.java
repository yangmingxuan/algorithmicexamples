package algorithms.list;

/***
 * Given a linked list, swap every two adjacent nodes and return its head.

    You may not modify the values in the list's nodes, only nodes itself may be changed.
    
     
    
    Example:
    
    Given 1->2->3->4, you should return the list as 2->1->4->3.
 * @author miche
 *
 */
public class SwapNodesinPairs {

    public SwapNodesinPairs() {
        // TODO Auto-generated constructor stub
    }

    /***
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Swap Nodes in Pairs.
     * Memory Usage: 36.8 MB, less than 5.50% of Java online submissions for Swap Nodes in Pairs.
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode pre = head, nxt = head.next, lret;
        pre.next = nxt.next;
        nxt.next = pre;
        lret = nxt;
        lret.next.next = swapPairs(pre.next);
        return lret;
    }
}
