package adt;

/*
 * Link:
 *  https://www.programcreek.com/2012/11/top-10-algorithms-for-coding-interview/
 */
public class Stack<T> {
    ListNode<T> top;

    public ListNode<T> peek() {
        if (top != null) {
            return new ListNode<T>(top.data);
        }
        return null;
    }

    public void push(ListNode<T> n){
        if(n != null){
            n.next = top;
            top = n;
        }
    }

    public ListNode<T> pop() {
        if (top != null) {
            ListNode<T> temp = top;
            top = top.next;
            return temp;
        }
        return null;
    }
}
