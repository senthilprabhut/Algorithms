package adt;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import static adt.Graph.Vertex;
/**
 * Breadth First Traversal (or Search) for a graph is similar to Breadth First Traversal of a tree.
 * The only catch here is, unlike trees, graphs may contain cycles, so we may come to the same node again.
 * To avoid processing a node more than once, we use a boolean visited array.
 *
 * Links:
 *  Algorithms with Attitude - https://youtu.be/ls4cHglfc0g
 *  Solution - http://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
 *  Applications of BFS - http://www.geeksforgeeks.org/applications-of-breadth-first-traversal/
 *
 * Complexity:
 *  Time Complexity: O(V+E) where V is number of vertices in the graph and E is number of edges in the graph.
 */
public class BreadthFirstTraversal<T> {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(true);
        graph.addVertex(0,0);
        graph.addVertex(1,1);
        graph.addVertex(2,2);
        graph.addVertex(3,3);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal (starting from vertex 2)");
        BreadthFirstTraversal<Integer> bfs = new BreadthFirstTraversal<>();
        bfs.perform(graph.getVertex(2));
        //2 0 3 1

        System.out.println("Following is Breadth First Traversal");
        bfs.performAll(graph);
        //0 1 2 3
    }

    public void perform(Vertex<T> start) {
        Set<Vertex<T>> visited = new HashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        // Mark the current node as visited and enqueue it
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            // Dequeue a vertex from queue and print it
            Vertex<T> vertex = queue.poll();
            System.out.println(vertex.data);

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            for(Vertex<T> adjacent : vertex.adjacentVertices) {
                if(!visited.contains(adjacent)) {
                    visited.add(adjacent);
                    queue.offer(adjacent);
                }
            }
        }
    }

    public void performAll(Graph<T> graph) {
        Set<Vertex<T>> visited = new HashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        for(Vertex<T> vertex : graph.getAllVertices()) {
            if(visited.contains(vertex)) continue;
            queue.add(vertex);
            while(!queue.isEmpty()) {
                Vertex<T> childVertex = queue.poll();
                if(! visited.contains(childVertex)) {
                    visited.add(childVertex);
                    System.out.println(childVertex.data);
                    for(Vertex<T> adjacent : childVertex.adjacentVertices) {
                        if(! visited.contains(adjacent)) queue.offer(adjacent);
                    }
                }
            }
        }
    }
}
