package algorithms.list;

import java.util.HashSet;
import java.util.Set;

/***
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

    To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
    
    Note: Do not modify the linked list.
    
     
    
    Example 1:
    
    Input: head = [3,2,0,-4], pos = 1
    Output: tail connects to node index 1
    Explanation: There is a cycle in the linked list, where tail connects to the second node.
    
    
    Example 2:
    
    Input: head = [1,2], pos = 0
    Output: tail connects to node index 0
    Explanation: There is a cycle in the linked list, where tail connects to the first node.
    
    
    Example 3:
    
    Input: head = [1], pos = -1
    Output: no cycle
    Explanation: There is no cycle in the linked list.
    
    
     
    
    Follow-up:
    Can you solve it without using extra space?
 * @author miche
 *
 */
public class LinkedListCycleII {

    /***
     * Space uses extra memory
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<ListNode>();
        while(head != null) {
            if(set.contains(head)) {
                return head;
            } else {
                set.add(head);
                head = head.next;
            }
        }
        
        return null;
    }

    /***
     * |___________|_____|___________|
     * |<-----m--->|<-l->|<--n-l=m-->|
     *             |<-------n------->|
     * The length without a loop is m, the length with cycle is n;
     * fast travels at twice the speed of slow and meets at l,
     * the length of slow travel is m + l, the length of fast travel is m + n + l,so
     * 2*(m+l) = m+n+l ====> m = n-l
     * @param head
     * @return
     */
    public ListNode detectCycle2(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head.next, fast = head.next.next;
        while(slow != fast) {
            if(fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
