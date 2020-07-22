package search;

import search.GraphNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem:
 *  Perform BFS and DFS on a graph
 *
 * Links:
 *  http://www.programcreek.com/2012/11/top-10-algorithms-for-coding-interview/
 *
 * Complexity:
 *  n is the number of graph nodes
 *  BFS Time Complexity is O(n) since we visit each node only once
 *  BFS Space Complexity is O(n) since in worst case queue has to store all graph nodes
 *  DFS Time Complexity is O(n) since we visit each node only once
 *  DFS Space Complexity is O(n) since there is a stack entry for each node's recursion call
 */
public class GraphSearch {
    public static void main(String[] args) {
        GraphNode n1 = new GraphNode(1);
        GraphNode n2 = new GraphNode(2);
        GraphNode n3 = new GraphNode(3);
        GraphNode n4 = new GraphNode(4);
        GraphNode n5 = new GraphNode(5);

        n1.neighbors = new GraphNode[]{n2, n3, n5};
        n2.neighbors = new GraphNode[]{n1, n4};
        n3.neighbors = new GraphNode[]{n1, n4, n5};
        n4.neighbors = new GraphNode[]{n2, n3, n5};
        n5.neighbors = new GraphNode[]{n1, n3, n4};

        breathFirstSearch(n1, 5);
        depthFirstSearch(n1, 5);
    }

    public static void breathFirstSearch(GraphNode root, int searchData) {
        if (root.data == searchData)
            System.out.println("Found in root");

        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            GraphNode node = queue.poll();
            for(GraphNode gn : node.neighbors) {
                if (! gn.visited) {
                    System.out.println(gn);
                    gn.visited = true;
                    if(gn.data == searchData)
                        System.out.println("Found in " + gn.data);
                    queue.offer(gn);
                }
            }
        }
    }

    public static void depthFirstSearch(GraphNode root, int searchData) {
        System.out.println(root);
        root.visited=true;
        if(root.data == searchData) {
            System.out.println("Found in " + root.data);
            return;
        }

        for(GraphNode node : root.neighbors)
            if(!node.visited)
                depthFirstSearch(node, searchData);
    }
}
