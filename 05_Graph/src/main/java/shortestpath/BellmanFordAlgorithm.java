package shortestpath;

import adt.Graph;
import adt.Graph.Vertex;
import adt.Graph.Edge;

import java.util.HashMap;
import java.util.Map;

/**
 * Bellman Ford algorithm is used to find single source shortest path in directed graph.
 *  Bellman ford works with negative edges as well unlike Dijksra's algorithm.
 *  If there is negative weight cycle it detects it - negative weight cycle is a cycle with total wt in -ve
 *  Floyd-Warshall algorithm has better time complexity O(V³) than this for all sources
 *
 * Links:
 *  Tushar - https://youtu.be/-mOEd_3gTK0
 *  Solution - https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/BellmanFordShortestPath.java
 *
 * Complexity:
 *  Time complexity - O(VE) for Single source
 *      O(V²E) for All sources. In worst case, E could be V². So complexity in worst case will be O(V⁴E)
 *  Space complexity - O(V)
 */
public class BellmanFordAlgorithm<T> {
    //some random big number is treated as infinity. I m not taking INTEGER_MAX as infinity because
    //doing any addition on that causes integer overflow
    private static int INFINITY = 10000000;

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(false);
        graph.addEdge(0, 3, 8);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 2, -3);
        graph.addEdge(2, 4, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 3, 1);

        BellmanFordAlgorithm<Integer> bfa = new BellmanFordAlgorithm();
        Map<Vertex<Integer>,Integer> distance = bfa.shortestPath(graph, graph.getVertex(0));
        System.out.println(distance);
    }

    public Map<Vertex<T>, Integer> shortestPath(Graph<T> graph, Vertex<T> startVertex) {
        Map<Vertex<T>, Integer> distance = new HashMap<>();
        Map<Vertex<T>, Vertex<T>> parent = new HashMap<>();

        //set distance of every vertex to be infinity initially
        for(Vertex<T> v : graph.getAllVertices()) {
            distance.put(v, INFINITY);
            parent.put(v, null);
        }

        //set distance of source vertex to be 0
        distance.put(startVertex, 0);
        int V = graph.getAllVertices().size();

        //relax edges repeatedly V - 1 times
        for (int i = 0; i < V - 1 ; i++) {
            for(Edge<T> edge : graph.getAllEdges())  {
                Vertex<T> u = edge.vertex1;
                Vertex<T> v = edge.vertex2;
                //if we get better distance to v via u then use this distance and set u as parent of v.
                if (distance.get(u) + edge.weight < distance.get(v)) {
                    distance.put(v, distance.get(u) + edge.weight);
                    parent.put(v, u);
                }
            }
        }

        //relax all edges again. If we still get lesser distance it means
        //there is negative weight cycle in the graph. Throw exception in that case
        for (Edge<T> edge : graph.getAllEdges()) {
            Vertex<T> u = edge.vertex1;
            Vertex<T> v = edge.vertex2;
            if (distance.get(u) + edge.weight < distance.get(v)) {
                throw new NegativeWeightCycleException();
            }
        }
        return distance;
    }
}
class NegativeWeightCycleException extends RuntimeException {}
