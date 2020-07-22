package adt;

public class ListNode<T> {
  public T data;
  public ListNode<T> next;

  ListNode(T data) {
    this.data = data;
  }

  public String toString() {
	//StringBuilder builder = new StringBuilder("{").append(data).append("}");
	//return builder.toString();
	return data.toString();
  }
}
