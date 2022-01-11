package algorithms.sort;
/***
 * Given the head of a linked list, return the list after sorting it in ascending order.

 

    Example 1:
    
    
    Input: head = [4,2,1,3]
    Output: [1,2,3,4]
    Example 2:
    
    
    Input: head = [-1,5,3,4,0]
    Output: [-1,0,3,4,5]
    Example 3:
    
    Input: head = []
    Output: []
     
    
    Constraints:
    
    The number of nodes in the list is in the range [0, 5 * 10^4].
    -10^5 <= Node.val <= 10^5
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import algorithms.list.ListNode;

public class SortList {

    public ListNode sortList1(ListNode head) {
        if(head == null) return null;
        int count = 0;
        ListNode cur = head;
        while(cur != null) {
            count++;
            cur = cur.next;
        }
        ListNode[] arrs = new ListNode[count];
        cur = head;
        count = 0;
        while(cur != null) {
            arrs[count++] = cur;
            cur = cur.next;
        }
        Arrays.sort(arrs, (a, b) -> a.val - b.val);
        for(int i = 1; i < arrs.length; i++) {
            arrs[i-1].next = arrs[i];
        }
        arrs[arrs.length-1].next = null;
        
        return arrs[0];
    }

    public ListNode sortList2(ListNode head) {
        if(head == null) return null;
        List<ListNode> list = new ArrayList<ListNode>();
        while(head != null) {
            list.add(head);
            head = head.next;
        }
        ListNode[] arrs = list.toArray(new ListNode[list.size()]);
        Arrays.sort(arrs, (a, b) -> a.val - b.val);
        for(int i = 1; i < arrs.length; i++) {
            arrs[i-1].next = arrs[i];
        }
        arrs[arrs.length-1].next = null;
        
        return arrs[0];
    }

    public ListNode sortList3(ListNode head) {
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>((a, b) -> a.val - b.val);
        ListNode cur, tmp = new ListNode(-1);
        cur = head;
        while(cur != null) {
            queue.add(cur);
            cur = cur.next;
        }
        cur = tmp;
        while(!queue.isEmpty()) {
            cur.next = queue.poll();
            cur = cur.next;
        }
        cur.next = null;

        return tmp.next;
    }

    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        //Split a LinkNode into two LinkNodes
        ListNode cur=head, node1 = head, node2 = head;
        while(node2 != null && node2.next != null) {
            cur = node1;
            node1 = node1.next;
            node2 = node2.next.next;
        }
        node2 = cur.next;
        cur.next = null;
        node1 = head;
        node1 = sortList(node1);
        node2 = sortList(node2);
        
        return mergeTwoSortedLists(node1, node2);
    }

    /***
     * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

        Example:
        
        Input: 1->2->4, 1->3->4
        Output: 1->1->2->3->4->4
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        ListNode lret = new ListNode(0);
        ListNode lptr = lret, ll=l1,lr=l2;
        while(ll != null && lr != null) {
            if(ll.val < lr.val) {
                lptr.next = ll;
                ll = ll.next;
            } else {
                lptr.next = lr;
                lr = lr.next;
            }
            lptr = lptr.next;
        }
        if(ll != null) {
            lptr.next = ll;
        } else {
            lptr.next = lr;
        }
        return lret.next;
    }
}
