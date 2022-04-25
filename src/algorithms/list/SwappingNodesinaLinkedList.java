package algorithms.list;
/***
 * You are given the head of a linked list, and an integer k.

    Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).
    
     
    
    Example 1:
    
    
    Input: head = [1,2,3,4,5], k = 2
    Output: [1,4,3,2,5]
    Example 2:
    
    Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
    Output: [7,9,6,6,8,7,3,0,9,5]
     
    
    Constraints:
    
    The number of nodes in the list is n.
    1 <= k <= n <= 10^5
    0 <= Node.val <= 100
 * @author miche
 *
 */
public class SwappingNodesinaLinkedList {

    public ListNode swapNodes(ListNode head, int k) {
        ListNode ptr = head;
        int n = 0, idx = 0;
        while(ptr != null) {
            n++;
            ptr = ptr.next;
        }
        ListNode kthnode=null, antikthnode=null;
        ptr = head;
        while(ptr != null) {
            idx++;
            if(idx == k) {
                kthnode = ptr;
            }
            if(idx == n-k+1) {
                antikthnode = ptr;
            }
            ptr = ptr.next;
        }
        int val = kthnode.val;
        kthnode.val = antikthnode.val;
        antikthnode.val = val;
        return head;
    }

    public ListNode swapNodes2(ListNode head, int k) {
        ListNode ptr = head, kthnode = head, antikthnode = head;
        int idx = 1;
        while(idx < k) {
            ptr = ptr.next;
            idx++;
        }
        //now the ptr is the kth node
        kthnode = ptr;
        //Because the gap between antikthnode and ptr.next is now k, 
        //if both move forward at the same time, when ptr reaches the end, antikthnode will be the kth node from the end.
        while(ptr.next != null) {
            ptr = ptr.next;
            antikthnode = antikthnode.next;
        }
        
        int val = kthnode.val;
        kthnode.val = antikthnode.val;
        antikthnode.val = val;
        return head;
    }
}
