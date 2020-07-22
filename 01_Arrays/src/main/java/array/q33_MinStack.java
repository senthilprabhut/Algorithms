package array;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *   Design a stack that supports push, pop, top, and retrieving the minimum value in constant time.
 *   push(x) -- Push value x onto stack.
 *   pop() -- Removes the value on top of the stack.
 *   top() -- Get the top value.
 *   getMin() -- Retrieve the minimum value in the stack.
 *
 *   Example:
 *   MinStack minStack = new MinStack();
 *   minStack.push(-2);
 *   minStack.push(0);
 *   minStack.push(-3);
 *   minStack.getMin();   --> Returns -3.
 *   minStack.pop();
 *   minStack.top();      --> Returns 0.
 *   minStack.getMin();   --> Returns -2.
 *
 * Links:
 *  https://leetcode.com/problems/min-stack/description/
 *  https://www.programcreek.com/2014/02/leetcode-min-stack-java/
 */
public class q33_MinStack {
    public static void main(String[] args) {
        q33_MinStack minStack = new q33_MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    Element top;
    public q33_MinStack() {
    }

    public void push(int n) {
        Element curr = new Element();
        curr.value = n;
        curr.min = (top == null) ? n : Math.min(n,top.min);
        curr.next = top;
        top = curr;
    }

    public void pop() {
        if (top == null) return;
        Element temp = top;
        top = top.next;
        temp.next = null; //Reset ref to curr top
    }

    public int top() {
        if(top == null)
            return -1;
        return top.value;
    }

    public int getMin() {
        if (top == null) return  -1;
        return top.min;
    }
}

class Element {
    public int value;
    public int min;
    public Element next;
}
