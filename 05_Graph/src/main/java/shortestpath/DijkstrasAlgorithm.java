package shortestpath;

import adt.BinaryMinHeap;
import adt.Graph;

import java.util.*;

/**
 * Problem:
 *  Dijkstra's algorithm is used to find single source shortest path in directed graph.
 *  Doesn't work with negative edges
 *
 * Approach:
 *  Dijkstra(Graph G, Source S)
 *      Initialize Source with distance 0 and rest of the nodes with distance from source as ∞
 *      Shortest path set SP = empty set
 *      Build min heap with list of Vertices - the min heap is sorted on the distance of vertices from Source
 *      While (heap is not empty)
 *          u = Extract-Min(heap)
 *          SP = SP{u}
 *          for each Vertex v in Adjacents[u]
 *              Relax(u,v) //Relax is decrease-key operation
 *
 * Links:
 *  Ravindra Dijkstra algorithm
 *  Tushar - https://youtu.be/lAXZGERcDf4
 *  Solution - https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/DijkstraShortestPath.java
 *
 * Complexity:
 *  Space complexity - O(E + V)
 *  Time complexity - O(E logV)
 *      Initialize - O(V), Build heap - O(V), Extract Min - V*log(V), Decrease key (Relax) - E*log(V)
 *      Total = O(V + V + V logV + E logV) = E logV (most significant value)
 */
public class DijkstrasAlgorithm<T> {

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(false);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 4, 9);
        graph.addEdge(1, 5, 3);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 3);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 4, 2);

        DijkstrasAlgorithm<Integer> dsp = new DijkstrasAlgorithm<Integer>();
        dsp.shortestPath(graph, graph.getVertex(1));
        //{1=0, 2=5, 3=7, 4=7, 5=3, 6=5}
        //{1=null, 2=1, 3=2, 4=6, 5=1, 6=5}
    }

    public Map<Graph.Vertex<T>, Integer> shortestPath(Graph<T> graph, Graph.Vertex<T> startVertex) {
        //1. Initialize the data structures
        //heap + map data structure - it wraps the node with its weight
        Comparator<HeapNode<T>> myComp = Comparator.comparingInt(HeapNode::getWeight);
        BinaryMinHeap<HeapNode<T>> minHeap = new BinaryMinHeap<>(myComp);
        Set<Graph.Vertex<T>> shortestPathSet = new HashSet<>(); //Tracks if a node's shortest path has been processed or not
        Map<Graph.Vertex<T>,Integer> distance = new HashMap<>(); //stores shortest distance from root to every vertex
        Map<Graph.Vertex<T>, Graph.Vertex<T>> parent = new HashMap<>(); //stores parent of every vertex in shortest distance

        //2. Initialize distance of the nodes with Integer.MAX_VALUE
        for(Graph.Vertex<T> vertex : graph.getAllVertices()) {
            distance.put(vertex, Integer.MAX_VALUE);
        }

        //3. Build min heap with the weight and vertices. Initialize distance for start node to 0
        minHeap.add(new HeapNode(startVertex,0));
        distance.put(startVertex, 0);
        parent.put(startVertex, null);

        while(minHeap.size() != 0) {
            //4. get the min value from heap node which has vertex and distance of that vertex from source vertex.
            Graph.Vertex<T> current = minHeap.getMinValue().vertex;
            System.out.println(distance.get(current));
            shortestPathSet.add(current);

            //5. iterate through all edges of current vertex and relax them
            for (Graph.Edge<T> edge : current.edges) {
                Graph.Vertex<T> adjacent = getVertexForEdge(current, edge);

                //if visited set contains adjacent vertex means adjacent vertex already has shortest distance from source vertex
                if(shortestPathSet.contains(adjacent)) continue;

                int newWeight = distance.get(current) + edge.weight;
                int currentWeight = distance.get(adjacent);
                if(currentWeight > newWeight) {
                    //update the min heap
                    HeapNode<T> key = new HeapNode<>(adjacent, currentWeight);
                    HeapNode<T> newKey = new HeapNode<>(adjacent, newWeight);
                    if(minHeap.contains(key))
                        minHeap.decreaseKey(key, newKey);
                    else
                        minHeap.add(newKey);

                    //update shortest distance of current vertex from source vertex
                    distance.put(adjacent, newWeight);
                    parent.put(adjacent,current);
                }
            }
        }
        System.out.println(distance);
        System.out.println(parent);
        return distance;
    }

    private Graph.Vertex<T> getVertexForEdge(Graph.Vertex<T> current, Graph.Edge<T> edge) {
        return edge.vertex1.equals(current) ? edge.vertex2 : edge.vertex1;
    }

    private class HeapNode<T> {
        public Graph.Vertex<T> vertex;
        public int weight;

        public HeapNode(Graph.Vertex<T> vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public boolean equals(Object other) {
            return this.vertex.equals( ((HeapNode<Graph.Vertex<T>>)other).vertex);
        }

        @Override
        public int hashCode(){
            return vertex.hashCode();
        }
    }
}
