package algorithms.list;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
    reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
    
    You may not modify the values in the list's nodes, only nodes itself may be changed.
    
    Example 1:
    
    Given 1->2->3->4, reorder it to 1->4->2->3.
    Example 2:
    
    Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 * @author miche
 *
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode cur = head;
        while(cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        int left = 0, right = list.size() - 1;
        while(left < right - 1) {
            list.get(left).next = list.get(right);
            list.get(right).next = list.get(left+1);
            list.get(right-1).next = null;
            left++;
            right--;
        }
    }

    public void reorderList2(ListNode head) {
        if(head == null) {
            return;
        }
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //now slow is in the middle
        
        //reverse the rest list [4,5,6]==>[6,5,4]
        ListNode prev = null, cur = slow, tmp;
        while(cur != null) {
            tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        
        //merge the two list
        ListNode first = head, second = prev;
        while(second.next != null) {
            tmp = first.next;
            first.next = second;
            first = tmp;
            
            tmp = second.next;
            second.next = first;
            second = tmp;
        }
    }

}
