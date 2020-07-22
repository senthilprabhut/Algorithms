package adt;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
  public ListNode<T> head;  //Head pointer
  private int size = 0;  //Count of no of nodes in the list
  
  public LinkedList() {
    head = new ListNode<T>(null);
  }
  
 
  //Adds element to the beginning of the list
  public void insertBeginning (T data) {
    insert (0, data);    	
  }
 
 //Add element to the end of the list
  public void insert(T data) {
	//The following loop exits after reaching the last node
	ListNode<T> currentNode = null;
	for (currentNode = head; currentNode.next != null; currentNode=currentNode.next);

	ListNode<T> newNode = new ListNode<T>(data);
	newNode.next = null;

	currentNode.next = newNode;	
	++size;  //Increment the node count
  }
   
  //Add element at the specified index
  public void insert(int index, T data) {
	ListNode<T> newNode = new ListNode<T>(data);
	
	if (index < 0 || index > size())
		throw new IndexOutOfBoundsException("" + index);
	
	//We need to traverse till index-1 position and insert this new node at the index
	ListNode<T> currentNode = head;
	int position = 0;
	while ( position < index && currentNode != null ) {
	  currentNode = currentNode.next;
	  position++;
	}	  
	  
	//Insert the new node here
	ListNode<T> nextNode = currentNode.next; 
	currentNode.next = newNode;
	newNode.next = nextNode;
	
	++size;  //Increment the node count
  }
  
  //The following method deletes elements from the beginning of the list  
  public void removeBeginning() {
    remove(0);
  }
  
  public void remove(int index) {
	if (index < 0 || index > size())
		throw new IndexOutOfBoundsException("" + index);
	
	//We need to traverse till index-1 position and delete the node at the index
	ListNode<T> currentNode = head;
	int position = 0;
	while ( position < index && currentNode != null ) {
	  currentNode = currentNode.next;
	  position++;
	}

	//Remove the Node between CurrentNode and CurrentNode.next.next
	ListNode<T> obsolete = currentNode.next;
	currentNode.next = obsolete.next;
	
	obsolete.next = null;
	obsolete = null;
	
	--size;  //Decrement the node count	
  }
  
  //Return the no of nodes in the list
  public int size() {
    return size;
  }
  
  //Index of first occurance of the data
  public int indexOf(T data) {
	int index=-1;
    for (ListNode<T> listNode = head.next; listNode != null; listNode=listNode.next) {
		++index;
		if (listNode.data != null && listNode.data.equals(data) )
			return index;
	}
	return -1;
  }
  
  //Get the element at the specified index. NULL if too short.
  public T get(int index) {
	if (index < 0 || index > size())
		throw new IndexOutOfBoundsException("" + index);
	
	ListNode<T> currentNode = head;
	while (index >= 0 && currentNode != null ) {
	  currentNode = currentNode.next;
	  --index;
	}
	  
	return currentNode.data;
  }
  
  //Delete the whole list
  public void deleteList() {
	ListNode<T> currListNode = head;
	while ( currListNode != null) {
		ListNode<T> next = currListNode.next;
		currListNode.next = null; //The next pointer is set to null
		currListNode = next;
	}
  }
    
  //Method required to be implemented by Iterable interface
  public Iterator<T> iterator() {
    return new Iterator<T>() {
	  private ListNode<T> current = head;
	  
	  public boolean hasNext() {
	    return (current.next != null);
	  }
	  
	  public T next() {
	    current = current.next;
	    return current.data;
	  }
	  
	  public void remove() {
	    throw new UnsupportedOperationException();
	  }
	};
  }

  //print the linked list
  public String toString() {
	StringBuilder builder = new StringBuilder("(");
	/*for (ListNode<T> listNode = head.next; listNode != null; listNode=listNode.next) {
		builder.append(listNode.toString());
		if (listNode.next != null)
			builder.append(", ");
	}*/
	
	for (T data : this) {
	  builder.append(data.toString()).append(", ");
	}	
	builder.append(")");
	
	return builder.toString();
  }
  
  private boolean isListCyclic() {
	ListNode<T> slowPtr = head.next;
	ListNode<T> fastPtr = head.next;
	
	while (slowPtr != null && fastPtr != null) {
		fastPtr = fastPtr.next; //1 hop
		if (fastPtr == slowPtr)
			return true; //loop exists
		
		if (fastPtr == null)
			return false; //Return that there is no cycle
			
		fastPtr = fastPtr.next; //2nd hop
		if (fastPtr == slowPtr)
			return true;
		
		slowPtr = slowPtr.next; //ONLY 1 hop
	}
	
	return false;  //no cycle
  }
  
  public static void main(String[] array) {
	//Testing the LinkedList that we have created
	LinkedList<Integer> intList = new LinkedList<Integer>();
	intList.insertBeginning(10);
	System.out.println( "List after insert beginning is " + intList.toString() );
	intList.insertBeginning(75);
	System.out.println( "List after insert beginning is " + intList.toString() );
	intList.insert(1,40);
	System.out.println("List after insert at index is " +  intList.toString() );
	intList.insert(19);
	System.out.println("List after insert last is " +  intList.toString() );
	intList.insert(61);
	System.out.println("List after insert last is " +  intList.toString() );
	intList.insert(33);
	System.out.println("List after insert last is " +  intList.toString() );
		
	int index = intList.indexOf(10);
	intList.remove(index);
	System.out.println("List after remove at index is " +  intList.toString() );
	
	intList.removeBeginning();
	System.out.println("List after remove beginning is " + intList.toString() );
	
	intList.deleteList();
	System.out.println("List after clear is " + intList.toString() );

	//String list
	LinkedList<String> list2 = new LinkedList<String>();
	
	list2.insertBeginning("hi");
	System.out.println( list2.toString() );
	
	list2.insert(1,"how");
	System.out.println( list2.toString() );
	
	list2.insert(2, "you");
	System.out.println( list2.toString() );
	
	list2.remove(2);
	System.out.println( list2.toString() );
	
	list2.removeBeginning();
	System.out.println( list2.toString() );
	
  }

}
