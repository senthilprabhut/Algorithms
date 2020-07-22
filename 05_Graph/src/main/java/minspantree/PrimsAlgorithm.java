package minspantree;

import adt.Graph;
import adt.Graph.Edge;
import adt.Graph.Vertex;
import adt.BinaryMinHeap;

import java.util.*;

/**
 * Find minimum spanning tree using Prim's algorithm
 *
 * Links:
 *  Tushar - https://youtu.be/oP2-8ysT3QQ
 *  Solution - https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/PrimMST.java
 *
 * Complexity:
 *  Space complexity - O(E + V)
 *  Time complexity - O(ElogV)
 */
public class PrimsAlgorithm<T> {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(false);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 1, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(2, 4, 3);
        graph.addEdge(4, 5, 6);
        graph.addEdge(5, 6, 2);
        graph.addEdge(3, 5, 5);
        graph.addEdge(3, 6, 4);

        PrimsAlgorithm prims = new PrimsAlgorithm();
        Collection<Edge<Integer>> edges = prims.minSpanTree(graph, graph.getVertex(1));
        for(Edge<Integer> edge : edges){
            System.out.println(edge);
        }
    }

    public List<Edge<T>> minSpanTree(Graph<T> graph, Vertex<T> startVertex) {
        //1. Initialize
        Comparator<HeapNode<T>> myComp = Comparator.comparingInt(HeapNode::getWeight);
        BinaryMinHeap<HeapNode<T>> minHeap = new BinaryMinHeap<>(myComp);
        Set<Vertex<T>> processed = new HashSet<>(); //Tracks if a node's shortest path has been processed or not
        Map<Vertex<T>, Integer> distance = new HashMap<>(); //stores shortest distance from a node
        Map<Vertex<T>, Edge<T>> vertexToEdge = new HashMap<>(); //map of vertex to edge which gave minimum weight to this vertex.
        List<Edge<T>> result = new ArrayList<>();  //stores final result

        //2. Initialize distance of the nodes with Integer.MAX_VALUE
        for(Vertex<T> vertex : graph.getAllVertices()) {
            distance.put(vertex, Integer.MAX_VALUE);
        }

        //3. Build min heap with the weight and vertices. Initialize distance for start node to 0
        minHeap.add(new HeapNode(startVertex,0));
        distance.put(startVertex, 0);
        vertexToEdge.put(startVertex, null);

        while (minHeap.size() > 0) {
            //4. get the min value from heap node
            Vertex<T> currentVertex = minHeap.getMinValue().vertex;
            processed.add(currentVertex);

            Edge<T> spanningTreeEdge = vertexToEdge.get(currentVertex);
            if(spanningTreeEdge != null) result.add(spanningTreeEdge);

            //5. iterate through all edges of current vertex and relax them
            for(Edge<T> edge : currentVertex.edges) {
                Vertex<T> adjacent = getVertexForEdge(currentVertex, edge);
                if(!processed.contains(adjacent) && distance.get(adjacent) > edge.weight) {
                    //update the min heap
                    HeapNode<T> oldKey = new HeapNode<T>(adjacent, distance.get(adjacent));
                    HeapNode<T> newKey = new HeapNode<T>(adjacent,edge.weight);
                    if(minHeap.contains(oldKey))
                        minHeap.decreaseKey(oldKey,newKey);
                    else
                        minHeap.add(newKey);

                    //update the distance
                    distance.put(adjacent, edge.weight);
                    vertexToEdge.put(adjacent, edge);
                }
            }
        }
        return result;
    }

    private Vertex<T> getVertexForEdge(Vertex<T> current, Edge<T> edge) {
        return edge.vertex1.equals(current) ? edge.vertex2 : edge.vertex1;
    }

    private class HeapNode<T> {
        public int weight;
        public Vertex<T> vertex;

        public HeapNode(Vertex<T> vertex, int weight) {
            this.weight = weight;
            this.vertex = vertex;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int hashCode() {
            return vertex.hashCode();
        }

        @Override
        public boolean equals(Object other) {
            return this.vertex.equals( ((HeapNode<Vertex<T>>)other).vertex);
        }

        @Override
        public String toString() {
            return vertex.toString();
        }
    }
}
