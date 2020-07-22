package minspantree;

import adt.DisjointSet;
import adt.Graph;
import adt.Graph.Edge;
import adt.Graph.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Kruskal's Algorithm:
 *
 *
 * Links:
 *  Tushar - https://youtu.be/fAuF0EuZVCk
 *  Solution - https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/KruskalMST.java
 *
 * Complexity:
 *  Time complexity - O(ElogE + E) = O(E log E)
 *  Space complexity - O(E + V)
 */
public class KruskalsAlgorithm<T> {
    /**
     * Comparator to sort edges by weight in non decreasing order
     */
    private class EdgeComparator implements Comparator<Edge<T>> {
        public int compare(Edge<T> edge1, Edge<T> edge2) {
            return Integer.compare(edge1.weight,edge2.weight);
        }
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<Integer>(false);
//        graph.addEdge(1, 2, 4);
//        graph.addEdge(1, 3, 1);
//        graph.addEdge(2, 5, 1);
//        graph.addEdge(2, 6, 3);
//        graph.addEdge(2, 4, 2);
//        graph.addEdge(6, 5, 2);
//        graph.addEdge(6, 4, 3);
//        graph.addEdge(4, 7, 2);
//        graph.addEdge(3, 4, 5);
//        graph.addEdge(3, 7, 8);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 1, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(2, 4, 3);
        graph.addEdge(4, 5, 6);
        graph.addEdge(5, 6, 2);
        graph.addEdge(3, 5, 5);
        graph.addEdge(3, 6, 4);
        KruskalsAlgorithm<Integer> mst = new KruskalsAlgorithm<>();
        List<Edge<Integer>> edges = mst.minSpanTree(graph);
        for(Edge<Integer> edge : edges)
            System.out.println(edge);

    }

    public List<Edge<T>> minSpanTree(Graph<T> graph) {
        //sort all edges in non decreasing order
        List<Edge<T>> edges = graph.getAllEdges();
        Collections.sort(edges, new EdgeComparator());

        //create as many disjoint sets as the total vertices
        DisjointSet<Long> disjointSet = new DisjointSet();
        for (Vertex<T> vertex : graph.getAllVertices()) {
            disjointSet.makeSet(vertex.id);
        }


        List<Edge<T>> resultEdge = new ArrayList<>();
        for(Edge<T> edge : graph.getAllEdges()) {
            //get the sets of two vertices of the edge
            Long parent1 = disjointSet.findSet(edge.vertex1.id);
            Long parent2 = disjointSet.findSet(edge.vertex2.id);

            //check if the vertices are in same set or different set
            //if verties are in same set then ignore the edge
            if(parent1 == parent2) continue;
            else {
                //if vertices are in different set then add the edge to result and union these two sets into one
                resultEdge.add(edge);
                disjointSet.union(edge.vertex1.id,edge.vertex2.id);
            }
        }

        return resultEdge;
    }
}
