package algorithms.struct;

/***
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.
     
    
    Example:
    
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    minStack.getMin();   --> Returns -3.
    minStack.pop();
    minStack.top();      --> Returns 0.
    minStack.getMin();   --> Returns -2.
 * @author miche
 *
 */
public class MinStack2 {

    private LinkMinNode top;
    public MinStack2() {
        
    }

    public void push(int x) {
        if(top == null) {
            top = new LinkMinNode(x, x);
        } else {
            LinkMinNode newNode = new LinkMinNode(x, Math.min(x, top.min));
            newNode.next = top;
            top = newNode;
        }
    }

    public void pop() {
        if(top == null) return;
        LinkMinNode tmp = top.next;
        top.next = null;
        top = tmp;
    }

    public int top() {
        if(top == null) return -1;
        return top.val;
    }

    public int getMin() {
        if(top == null) return Integer.MIN_VALUE;
        return top.min;
    }
}

class LinkMinNode {
    int val;
    int min;
    LinkMinNode next;
    
    public LinkMinNode(int val, int min) {
        this.val = val;
        this.min = min;
    }
}
