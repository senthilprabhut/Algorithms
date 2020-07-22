package search;

import adt.Graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static adt.Graph.Vertex;

/**
 * Problem:
 *  Given a directed acyclic graph, do a topological sort on this graph.
 *  Topological Sorting for a graph is NOT possible if the graph is not a DAG.
 *
 *  Unlike Kahn's algorithm, even in a graph with cycles, this can order
 *  all of the vertices (even though it isn't topological)
 *
 * Approach:
 *  Do DFS by keeping visited. Put the vertex which are completely explored into a stack.
 *  Pop from stack to get sorted order.
 *
 *  Used in build system to build dependencies first b4 building the current project
 *
 * Links:
 *  Tushar - https://youtu.be/ddTC4Zovtbc
 *  Algorithms with Attitude - https://youtu.be/yV8gPO5nTzQ
 *  http://www.geeksforgeeks.org/topological-sorting/
 *  Solution - https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/TopologicalSort.java
 *
 * Complexity:
 *  The algorithm is simply DFS with an extra stack. So time complexity is same as DFS which is O(V+E)
 *
 */
public class TopologicalSortDFS<T> {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(true);
        //Add vertices and edges to the graph
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(5, 6);
        graph.addEdge(6, 3);
        graph.addEdge(3, 8);
        graph.addEdge(8, 11);

        //Get the sorted output
        TopologicalSortDFS<Integer> sort = new TopologicalSortDFS<>();
        Deque<Vertex<Integer>> stack = sort.topSort(graph);
        while (!stack.isEmpty())
            System.out.printf("%d\t", stack.pop().id);
        System.out.println();
    }

    /**
     * Main method to be invoked to do topological sorting.
     */
    public Deque<Vertex<T>> topSort(Graph<T> graph) {
        Deque<Vertex<T>> stack = new ArrayDeque<>();
        Set<Vertex<T>> visited = new HashSet<>();

        for(Vertex<T> vertex : graph.getAllVertices()) {
            if (visited.contains(vertex)) {
                continue;
            }
            topSortUtil(vertex, stack, visited);
        }
        return stack;
    }

    private void topSortUtil(Vertex<T> vertex, Deque<Vertex<T>> stack, Set<Vertex<T>> visited) {
        visited.add(vertex);
        //Get all the neighbors of vertex and check if they are visited
        //If not visited, recurse for the adjacent nodes
        for(Vertex<T> adjacent : vertex.adjacentVertices) {
            if(visited.contains(adjacent)) continue;
            topSortUtil(adjacent, stack, visited);
        }
        //If no adjacent or all adjacents are processed, add it to stack
        stack.push(vertex);
    }
}
