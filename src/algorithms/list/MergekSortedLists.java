package algorithms.list;

/***
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

    Example:
    
    Input:
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    Output: 1->1->2->3->4->4->5->6
 * @author miche
 *
 */
public class MergekSortedLists {

    public MergekSortedLists() {
        // TODO Auto-generated constructor stub
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }
        int k = lists.length;
        ListNode lret = lists[0];
        for(int i = 1; i < k; i++) {
            lret = mergeTwoLists(lret, lists[i]);
        }

        return lret;
    }

    /***
     * Runtime: 2 ms, faster than 98.91% of Java online submissions for Merge k Sorted Lists.
     * Memory Usage: 40.9 MB, less than 46.45% of Java online submissions for Merge k Sorted Lists.
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }
        return mergeKListWithIndex(lists, 0, lists.length-1);
    }
    public ListNode mergeKListWithIndex(ListNode[] lists, int l, int r) {
        if(l == r) return lists[l];
        int m = (r - l) / 2 + l;
        ListNode leftNode = mergeKListWithIndex(lists, l, m);
        ListNode rightNode = mergeKListWithIndex(lists, m+1, r);
        return mergeTwoLists(leftNode, rightNode);
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
