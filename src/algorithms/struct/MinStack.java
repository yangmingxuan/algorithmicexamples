package algorithms.struct;

import java.util.LinkedList;

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
public class MinStack {

    private int min;
    private LinkedList<Integer> stack;
    public MinStack() {
        this.min = Integer.MAX_VALUE;
        this.stack = new LinkedList<Integer>();
    }

    public void push(int x) {
        stack.addLast(x);
        if(x < min) {
            min = x;
        }
    }

    public void pop() {
        stack.pollLast();
        min = Integer.MAX_VALUE;
        for(int e : stack) {
            if(e < min) {
                min = e;
            }
        }
    }

    public int top() {
        return stack.peekLast();
    }

    public int getMin() {
        return min;
    }
}
