package algorithms.list;

import java.util.Stack;

/***
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

    You may assume the two numbers do not contain any leading zero, except the number 0 itself.
    
    Follow up:
    What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
    
    Example:
    
    Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 8 -> 0 -> 7
 * @author miche
 *
 */
public class AddTwoNumbersII {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = null, nxt;
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        nxt = l1;
        while(nxt != null) {
            s1.add(nxt.val);
            nxt = nxt.next;
        }
        nxt = l2;
        while(nxt != null) {
            s2.add(nxt.val);
            nxt = nxt.next;
        }
        
        nxt = null;
        int sum = 0, carry = 0;
        while(!s1.isEmpty() && !s2.isEmpty()) {
            sum = s1.pop() + s2.pop() + carry;
            carry = sum / 10;
            ans = new ListNode(sum%10);
            ans.next = nxt;
            nxt = ans;
        }
        if(!s2.isEmpty()) {
            s1 = s2;
        }
        while(!s1.isEmpty()) {
            sum = s1.pop() + carry;
            carry = sum / 10;
            ans = new ListNode(sum%10);
            ans.next = nxt;
            nxt = ans;
        }
        if(carry > 0) {
            ans = new ListNode(carry);
            ans.next = nxt;
            nxt = ans;
        }
        
        return ans;
    }

}
