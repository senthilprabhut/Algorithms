package adt;

/*
 * Link:
 *  https://www.programcreek.com/2012/11/top-10-algorithms-for-coding-interview/
 */
public class Queue<T> {
    ListNode<T> first, last;

    public void enqueue(ListNode<T> n){
        if (first == null) {
            first = n;
            last = first;
        } else {
            last.next = n;
            last = n;
        }
    }

    public ListNode<T> dequeue() {
        if (first == null) {
            return null;
        } else {
            ListNode<T> temp = first;
            first = first.next;
            return temp;
        }
    }
}
