package adt;

import java.util.HashSet;
import java.util.Set;

import static adt.Graph.Vertex;
/**
 * Depth First Traversal (or Search) for a graph is similar to Depth First Traversal of a tree.
 * The only catch here is, unlike trees, graphs may contain cycles, so we may come to the same node again.
 * To avoid processing a node more than once, we use a boolean visited array.
 *
 * Links:
 *  Algorithms with Attitude - https://youtu.be/qH-mHxkoK0Q
 *  Solution - http://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/
 *  Applications of Depth First Search - http://www.geeksforgeeks.org/applications-of-depth-first-search/
 *
 * Complexity:
 *  Time Complexity - O(V+E), V is no of vertices and E is no of Edges
 */
public class DepthFirstTraversal<T> {
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
        System.out.println("Following is Depth First Traversal (starting from vertex 2)");
        DepthFirstTraversal<Integer> dfs = new DepthFirstTraversal();
        dfs.performRec(graph.getVertex(2));
        //2 0 1 3

        System.out.println("Following is Depth First Traversal");
        dfs.performAll(graph);
        //0 1 2 3
    }

    public void performLinear(Vertex<T> vertex) {
        Set<Vertex<T>> visited = new HashSet<>();


    }

    public void performRec(Vertex<T> vertex) {
        Set<Vertex<T>> visited = new HashSet<>();
        dfsRec(vertex, visited);
    }

    //All the vertices may not be reachable from a given vertex (example Disconnected graph).
    //To do complete DFS traversal of such graphs, we must call DFSUtil() for every vertex.
    public void performAll(Graph<T> graph) {
        Set<Vertex<T>> visited = new HashSet<>();
        // Recur for all the vertices adjacent to this vertex
        for(Vertex<T> vertex : graph.getAllVertices()) {
            if(visited.contains(vertex)) continue;
            dfsRec(vertex, visited);
        }
    }

    private void dfsRec(Vertex<T> vertex, Set<Vertex<T>> visited) {
        visited.add(vertex);
        System.out.println(vertex.data);
        for(Vertex<T> adjacent : vertex.adjacentVertices) {
            if(! visited.contains(adjacent)) dfsRec(adjacent, visited);
        }
    }
}
