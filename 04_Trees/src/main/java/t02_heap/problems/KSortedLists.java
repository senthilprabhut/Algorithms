package t02_heap.problems;

import java.util.*;

/**
 * Problem:
 *  Merge k sorted linked lists and return it as one sorted list.
 *
 * Links:
 *  Solution 1 - http://www.programcreek.com/2013/02/leetcode-merge-k-sorted-lists-java/
 *  Solution 2 - https://leetcode.com/problems/merge-k-sorted-lists/description/
 *
 * Complexity:
 *  Solution 1 Time - so O(kn log k) as log k to insert into t02_heap and insertion done for k*n elements
 *  Solution 1 Space - O(kn) which is the size of result array
 *
 *  Solution 2 Time - O(kn logk) as outer while loop in function mergeKLists() runs log k times and every time we are processing k*n elements.
 *  Solution 2 Space - O(kn) There is a result list created everytime in sortedMerge and the max list size is that of k*n
 */
public class KSortedLists {
    private class ListContainer<T extends Comparable> implements Comparable<ListContainer<T>> {
        List<T> list;
        ListIterator<T> iterator;

        public ListContainer(List<T> list, ListIterator<T> iterator) {
            this.list = list;
            this.iterator = iterator;
        }

        public int compareTo(ListContainer<T> next) {
            return list.get(iterator.nextIndex()).compareTo(next.list.get(next.iterator.nextIndex()));
        }
    }
    public List mergeKLists(List<List<Integer>> lists) {
        List<Integer> result = new LinkedList<>();
        if(lists == null || lists.size() == 0) return result;

        PriorityQueue<ListContainer<Integer>> queue = new PriorityQueue<>();
        for(List<Integer> l : lists) queue.add(new ListContainer<Integer>(l, l.listIterator()));

        while(! queue.isEmpty()) {
            ListContainer<Integer> container = queue.poll();
            result.add(container.iterator.next());
            if(container.iterator.hasNext()) queue.add(new ListContainer<>(container.list, container.iterator));
        }
        return result;
    }

    public List mergeKListsDivNKon(List<List<Integer>> lists, int last) {
        // repeat until only one list is left
        while(last > 0) {
            int i=0, j=last;  //Initialize i & j and do the inner while loop
            while(i<j) {
                // merge List i with List j and store merged list in List i
                lists.set(i, SortedMerge(lists.get(i), lists.get(j)) );

                // consider next pair
                i++;
                j--;

                // If all pairs are merged, update last
                if (i >= j)
                    last = j;
            }
        }
        return lists.get(0);
    }

    private List<Integer> SortedMerge(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new LinkedList<>();
        /* Base cases */
        if (list1 == null)
            return (list2);
        else if(list2 == null)
            return (list1);

        ListIterator<Integer> li1 = list1.listIterator();
        ListIterator<Integer> li2 = list2.listIterator();
        while (li1.hasNext() && li2.hasNext()) {
            if (list1.get(li1.nextIndex()) <= list2.get(li2.nextIndex()))
                result.add(li1.next());
            else
                result.add(li2.next());

        }
        while(li1.hasNext()) result.add(li1.next());
        while(li2.hasNext()) result.add(li2.next());
        return result;
    }

    public static void main(String[] args) {
        List<Integer> ll1 = new LinkedList<>();
        ll1.add(1);
        ll1.add(3);
        ll1.add(5);
        ll1.add(7);

        List<Integer> ll2 = new LinkedList<>();
        ll2.add(2);
        ll2.add(4);
        ll2.add(6);
        ll2.add(8);

        List<Integer> ll3 = new LinkedList<>();
        ll3.add(0);
        ll3.add(9);
        ll3.add(10);
        ll3.add(11);

        List<List<Integer>> lists = new LinkedList<>();
        lists.add(ll1);
        lists.add(ll2);
        lists.add(ll3);

        KSortedLists ksa = new KSortedLists();
//        List<Integer> result = ksa.mergeKLists(lists);
        List<Integer> result = ksa.mergeKListsDivNKon(lists,lists.size()-1);
        System.out.println(result);
    }
}
