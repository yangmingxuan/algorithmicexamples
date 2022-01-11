package algorithms.list;
/***
 * Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.

    The steps of the insertion sort algorithm:
    
    Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
    At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list and inserts it there.
    It repeats until no input elements remain.
    The following is a graphical example of the insertion sort algorithm. The partially sorted list (black) initially contains only the first element in the list. One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.
    
    
     
    
    Example 1:
    
    
    Input: head = [4,2,1,3]
    Output: [1,2,3,4]
    Example 2:
    
    
    Input: head = [-1,5,3,4,0]
    Output: [-1,0,3,4,5]
     
    
    Constraints:
    
    The number of nodes in the list is in the range [1, 5000].
    -5000 <= Node.val <= 5000
 * @author miche
 *
 */
public class InsertionSortList {

    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode original = new ListNode(0);
        original.next = head;
        ListNode cur = head.next, prev = head, tmp, tmppre;
        while(cur != null) {
            tmp = original.next;
            tmppre = original;
            while(tmp != cur && tmp.val <= cur.val) {
                tmp = tmp.next;
                tmppre = tmppre.next;
            }
            if(tmp == cur) {//cur.val is larger than previous node
                //move the cur to next
                cur = cur.next;
                prev = prev.next;
            } else { //tmp.val > cur.val
                //insert cur between tmppre and tmp
                prev.next = cur.next;
                cur.next = tmp;
                tmppre.next = cur;
                cur = prev.next;
            }
        }
        return original.next;
    }

}
