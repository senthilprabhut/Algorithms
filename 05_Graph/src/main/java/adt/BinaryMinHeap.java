package adt;

import java.util.*;

public class BinaryMinHeap<T> {
    private static class DefaultComparator implements Comparator {
        public int compare (Object o1, Object o2) {
            return ((Comparable) o1).compareTo(o2);
        }
    }
    private Map<T, Integer> nodePosition = new HashMap<>();
    private Comparator myComp = new DefaultComparator();
    private int mySize;
    private List<T> myList;

    public BinaryMinHeap() {
        myList = new ArrayList<>();
    }

    public BinaryMinHeap(Comparator comparator) {
        this();
        this.myComp = comparator;
    }

    //Time Complexity: O(n)
    public void buildMinHeap(List<T> input) {
        myList = new ArrayList<>(input);
        mySize = input.size();

        //Store the node position before building the heap
        for (int i = 0; i < myList.size(); i++) nodePosition.put(myList.get(i), i);

        int n = myList.size();
        //0 to n/2-1 are non-leaf nodes
        for (int i = (n / 2 - 1); i >= 0; i--) {
            minHeapify(myList, i, n);
        }
    }

    public void add(T key) {
        myList.add(key);
        mySize++;

        int currentIndex = mySize - 1;
        nodePosition.put(key,mySize-1);

        int parentIndex = (currentIndex - 1) / 2;
        while (parentIndex >= 0 && myComp.compare(myList.get(parentIndex), myList.get(currentIndex)) > 0) {
            swap(myList, parentIndex, currentIndex);
            currentIndex = parentIndex;
            parentIndex = (currentIndex - 1) / 2;
        }
    }

    public T getMinValue() {
        if(mySize==0) throw new RuntimeException("Heap underflow");
        T minValue = getMinValue(myList,mySize);
        mySize--;
        return minValue;
    }

    //Time Complexity: O(1)
    private T getMinValue(List<T> input, int totalLength) {
        nodePosition.put(input.get(totalLength - 1), 0); //Update the node position b4 updating value
        nodePosition.remove(input.get(0));
        T min = input.get(0); //get the element in index 0
        input.set(0, input.get(totalLength - 1));
        input.remove(totalLength - 1);
        minHeapify(input, 0, totalLength - 1);
        return min;
    }

    public void decreaseKey(T oldValue, T newValue) {
        int keyIndex = nodePosition.get(oldValue);
        decreaseKey(myList, keyIndex, newValue);
    }

    //Time Complexity: O(log n)
    private void decreaseKey(List<T> input, int keyIndex, T newValue) {
        if (myComp.compare(newValue, input.get(keyIndex)) > 0) return; //If bigger value, don't update
        input.set(keyIndex,newValue);

        int parentIndex = (keyIndex - 1) / 2;
        while (parentIndex >= 0 && myComp.compare(input.get(parentIndex), input.get(keyIndex)) > 0) {
            swap(input, parentIndex, keyIndex);
            keyIndex = parentIndex;
            parentIndex = (keyIndex - 1) / 2;
        }
    }

    //Time Complexity: O(log n)
    public void minHeapify(List<T> input, int startIndex, int totalLength) {
        int leftChild = 2 * startIndex + 1;
        int rightChild = 2 * startIndex + 2;

        int smallest = startIndex;
        if (leftChild < totalLength && myComp.compare(input.get(leftChild),input.get(startIndex)) < 0) smallest = leftChild;
        if (rightChild < totalLength && myComp.compare(input.get(rightChild),input.get(smallest)) < 0) smallest = rightChild;

        if (smallest != startIndex) {
            swap(input, startIndex, smallest);
            minHeapify(input, smallest, totalLength);
        }
    }

    //Time Complexity: O(1)
    public boolean contains(T key) {
        return nodePosition.containsKey(key);
    }

    //Time Complexity: O(1)
    public Integer getIndex(T key) {
        return nodePosition.get(key);
    }

    public Integer size() {
        return mySize;
    }

    //Time Complexity: O(1)
    private void swap(List<T> input, int firstIdx, int secondIdx) {
        //Update the node position b4 swap
        nodePosition.put(input.get(firstIdx), secondIdx);
        nodePosition.put(input.get(secondIdx), firstIdx);

        T temp = input.get(firstIdx);
        input.set(firstIdx, input.get(secondIdx));
        input.set(secondIdx, temp);
    }
}
