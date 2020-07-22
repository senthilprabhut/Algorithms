package adt;

import java.util.HashMap;
import java.util.Map;

/**
 * Disjoint Set:
 *  One of the implementation is Union By Rank - Path Compression
 *  makeSet - Create a set with only one element
 *  union - Take 2 differnt sets and merge them into one
 *  findSet - Return an identity of the set which is usually an element a set which acts as a representative for that set
 *  Used in Kruskal's Algorithm, Finding cycle in undirected graph
 *
 * Links:
 *  Tushar - https://youtu.be/ID00PMy0-vE
 *  Solution - https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/DisjointSet.java
 *
 * Complexity:
 *  Space - O(n)
 *  Time - For m operations and n elements, complexity id O(m 𝛂(n)), where 𝛂(n) is a slowly growing function
 *      and evaluates to 4 (Proof in CLRS book). = O(4m) = O(m)
 */
public class DisjointSet<T> {
    private class Node {
        int rank;
        T data;
        Node parent;
    }
    private Map<T, Node> map = new HashMap<>();

    public static void main(String args[]) {
        DisjointSet ds = new DisjointSet();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);

        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        ds.union(3, 7);

        System.out.println(ds.findSet(1));
        System.out.println(ds.findSet(2));
        System.out.println(ds.findSet(3));
        System.out.println(ds.findSet(4));
        System.out.println(ds.findSet(5));
        System.out.println(ds.findSet(6));
        System.out.println(ds.findSet(7));
    }

    //Create a set with only one element
    public void makeSet(T data) {
        Node n = new Node();
        n.data = data;
        n.rank = 0;
        n.parent = n;
        map.put(data, n);
    }

    /**
     * Find the representative recursively and does path
     * compression as well.
     */
    private Node findSet(Node node) {
        Node parent = node.parent;
        if (parent == node) {
            return parent;
        }
        node.parent = findSet(node.parent);
        return node.parent;
    }

    /**
     * Finds the representative of this set
     */
    public T findSet(T data) {
        return findSet(map.get(data)).data;
    }

    /**
     * Combines two sets together to one. Does union by rank
     * @return true if data1 and data2 are in different set before union else false.
     */
    public boolean union(T data1, T data2) {
        Node node1 = map.get(data1);
        Node node2 = map.get(data2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        //if they are part of same set do nothing
        if (parent1.data == parent2.data) {
            return false;
        }

        //else whoever's rank is higher becomes parent of other
        if(parent1.rank >= parent2.rank) {
            //increment rank only if both sets have same rank
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
        return true;
    }

}
