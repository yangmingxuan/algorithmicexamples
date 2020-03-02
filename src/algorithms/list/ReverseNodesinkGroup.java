package algorithms.list;

/***
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

    k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
    
    Example:
    
    Given this linked list: 1->2->3->4->5
    
    For k = 2, you should return: 2->1->4->3->5
    
    For k = 3, you should return: 3->2->1->4->5
    
    Note:
    
    Only constant extra memory is allowed.
    You may not alter the values in the list's nodes, only nodes itself may be changed.
 * @author miche
 *
 */
public class ReverseNodesinkGroup {

    public ReverseNodesinkGroup() {
        // TODO Auto-generated constructor stub
    }

    public int ListNodeLength(ListNode head) {
        int count = 0;
        while(head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    /***
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Nodes in k-Group.
     * Memory Usage: 40.6 MB, less than 5.17% of Java online submissions for Reverse Nodes in k-Group.

     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        if(ListNodeLength(head) < k) return head;
        ListNode firstKNode[] = new ListNode[k];
        ListNode ltmp = head;
        for(int count = 0; count < k; count++) {
            firstKNode[count] = ltmp;
            ltmp = ltmp.next;
        }
        ListNode lret = firstKNode[k-1];
        for(int count = k - 2; count >= 0; count--) {
            lret.next = firstKNode[count];
            lret = lret.next;
        }
        lret.next = reverseKGroup2(ltmp, k);
        lret = firstKNode[k-1];
        firstKNode = null;
        return lret;
    }

    /***
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Nodes in k-Group.
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        int count = 0;
        ListNode ltmp = head;
        while(count < k && ltmp != null) {
            ltmp = ltmp.next;
            count++;
        }
        if(count < k) return head;
        ListNode cur = reverseKGroup(ltmp, k);
        while(count > 0) {
            ltmp = head.next;
            head.next = cur;
            cur = head;
            head = ltmp;
            count --;
        }
        head = cur;
        return head;
    }
}
