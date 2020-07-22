package pcreekproblems;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Problem:
 *  Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 *
 * Links:
 *  https://www.programcreek.com/2012/12/leetcode-clone-graph-java/
 */
public class q01_CloneGraph<T> {
    public static void main(String[] args) {
        q01_CloneGraph<Integer> cg = new q01_CloneGraph<>();
        UndirectedGraphNode node0 = new UndirectedGraphNode<>(0);
        UndirectedGraphNode node1 = new UndirectedGraphNode<>(1);
        UndirectedGraphNode node2 = new UndirectedGraphNode<>(2);
        UndirectedGraphNode node3 = new UndirectedGraphNode<>(3);
        node0.addNeighbours(node1,node2);
        node1.addNeighbours(node2);
        node2.addNeighbours(node0, node3);
        node3.addNeighbours(node3);

        UndirectedGraphNode clone = cg.cloneGraph(node0);
        System.out.println(clone.label);
    }

    /**
     * Approach:
     *  Create a dfsClone of the node and store it in the map. Go through its neighbours
     *  If the neighbour is found in the map, just modify the neighbour list.
     *  Else, create a new node and modify the neighbour list and add the new node to the map
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public UndirectedGraphNode<T> cloneGraph(UndirectedGraphNode<T> node) {
        if(node == null) return null;

        Queue<UndirectedGraphNode> queue = new LinkedList<>(); //store **original** nodes need to be visited
        Map<UndirectedGraphNode,UndirectedGraphNode> map = new HashMap<>(); //store visited nodes

        //Create a new node and store it in the map
        queue.add(node);
        UndirectedGraphNode<T> newHead = new UndirectedGraphNode<>(node.label); //new node for return
        map.put(node,newHead); //add first node to HashMap

        while (!queue.isEmpty()) {
            UndirectedGraphNode<T> current = queue.poll();
            List<UndirectedGraphNode> neighbours = current.neighbours;
            for(UndirectedGraphNode<T> neighbour : neighbours) {
                //add to map and queue if this node hasn't been searched before
                if (!map.containsKey(neighbour)) {
                    queue.add(neighbour);
                    //Create a copy of neighbour node
                    map.put(neighbour, new UndirectedGraphNode(neighbour.label));
                }
                map.get(current).addNeighbour(map.get(neighbour)); //add neighbor to new created nodes
            }
        }
        return newHead;
    }

    /**
     * Approach:
     *  Create a dfsClone of the node and store it in the map in the first pass
     *  In the second pass, just modify the neighbour list to add a new node
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public UndirectedGraphNode<T> cloneGraph2(UndirectedGraphNode<T> node) {
        if (node == null) return null;

        //Create a copy of each node and place it in a map of oldNode,copiedNode
        Queue<UndirectedGraphNode<T>> queue = new LinkedList<>();
        Map<UndirectedGraphNode<T>, UndirectedGraphNode<T>> map = new HashMap<>();
        queue.offer(node);
        while(! queue.isEmpty()) {
            UndirectedGraphNode<T> current = queue.poll();
            map.put(current, new UndirectedGraphNode<>(current.label));
            for (UndirectedGraphNode<T> adjacent : current.neighbours)
                if (!map.containsKey(adjacent)) queue.offer(adjacent);
        }

        //Now fill the neighbour list of the copied nodes
        queue.offer(node);
        HashSet<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
        visited.add(node);
        while (! queue.isEmpty()) {
            UndirectedGraphNode<T> current = queue.poll();
            for(UndirectedGraphNode<T> adjacent : current.neighbours) {
                if(!visited.contains(adjacent)) {
                    queue.add(adjacent);
                    visited.add(adjacent);
                }
                map.get(current).addNeighbour(map.get(adjacent));
            }
        }
        return map.get(node);
    }

    /**
     * Approach:
     *  Depth First Approach
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public UndirectedGraphNode<T> cloneGraph3(UndirectedGraphNode<T> node) {
        HashMap<UndirectedGraphNode<T>, UndirectedGraphNode<T>> map = new HashMap<>();
        return dfsClone(node,map);
    }

    private UndirectedGraphNode<T> dfsClone(UndirectedGraphNode<T> node, HashMap<UndirectedGraphNode<T>, UndirectedGraphNode<T>> map) {
        if (node == null) return null;

        if(map.containsKey(node))
            return map.get(node);

        UndirectedGraphNode<T> clone = new UndirectedGraphNode<>(node.label);
        map.put(node, clone);
        for(UndirectedGraphNode<T> neighbour : node.neighbours) {
            clone.addNeighbour(dfsClone(neighbour, map));
        }
        return clone;
    }

    public static class UndirectedGraphNode<T> {
        T label;
        List<UndirectedGraphNode> neighbours;

        public UndirectedGraphNode(T label) {
            this.label = label;
            this.neighbours = new ArrayList<>();
        }

        public void addNeighbours(UndirectedGraphNode<T>... neighbours) {
            this.neighbours = Arrays.stream(neighbours).collect(Collectors.toList());
        }

        public void addNeighbour(UndirectedGraphNode<T> neighbour) {
            this.neighbours.add(neighbour);
        }
    }
}
