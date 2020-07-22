package search;

import adt.Graph;
import adt.Graph.Edge;
import adt.Graph.Vertex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Problem:
 *  DFS based Topological Sort is more flexible than this algorithm
 *
 * Approach:
 *  A vertex with no incoming edges can go first.
 *      A DAG should have a vertex with no incoming edges.
 *  TopSort(G)
 *      Find indegree for each vertex
 *      Q = empty FIFO queue
 *      add all vertices with no incoming edges to the queue
 *
 *      while (Q isn't empty)
 *          remove any vertex u from Q
 *          put u next in the topological ordering
 *          for each outgoing edge u->v
 *              decrement v's Degree
 *              if v's Degree is 0
 *                  add v to Q
 * Links:
 *  http://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/
 *  https://discuss.leetcode.com/topic/13854/easy-bfs-topological-sort-java/6
 *
 * Complexity:
 *  Time Complexity: O(V + E)
 *  Space Complexity: O(V)
 */
public class TopoligicalSortKahn<T> {
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
        TopoligicalSortKahn<Integer> sort = new TopoligicalSortKahn<>();
        Queue<Vertex<Integer>> queue = sort.topSort(graph);
        while (!queue.isEmpty())
            System.out.printf("%d\t", queue.poll().id);
        System.out.println();
    }

    public Queue<Vertex<T>> topSort(Graph<T> graph) {
        Map<Vertex<T>,Integer> indegree = new HashMap<>();
        //Traverse the array of edges and increase the counter of the destination node by 1. Time Complexity: O(V+E)
        for(Vertex<T> vertex : graph.getAllVertices())
            indegree.put(vertex, 0);
        for(Edge<T> edge : graph.getAllEdges()) {
            Vertex<T> vertex2 = edge.vertex2;
            indegree.merge(vertex2, 1, (oldValue, one) -> oldValue + one);
        }

        //Pick all the vertices with in-degree 0 and add them to the queue  - O(V)
        Queue<Vertex<T>> queue = indegree.entrySet().stream()
                .filter(entry -> entry.getValue() == 0)
                .map(entry -> entry.getKey())
                .collect(Collectors.toCollection(LinkedList::new));

        Queue<Vertex<T>> result = new LinkedList<>();
        while (! queue.isEmpty()) {
            Vertex<T> vertex = queue.poll(); //V * O(1)
            result.add(vertex);

            //Decrease in-degree by 1 for all its neighboring nodes. E * O(1)
            for(Vertex<T> adjacent : vertex.adjacentVertices) {
                indegree.merge(adjacent, 1, (oldValue, one) -> oldValue - one);

                //If in-degree of a neighboring nodes is reduced to zero, then add it to the queue.
                if(indegree.get(adjacent) == 0) queue.add(adjacent);  //V * O(1)
            }
        }

        if(result.size() != graph.getAllVertices().size())
            throw new RuntimeException("There exists a cycle in the graph");

        return result;
    }
}
