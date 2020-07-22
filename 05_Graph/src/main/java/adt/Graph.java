package adt;

import java.util.*;

/**
 * Links:
 *  Solution - https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/Graph.java
 */
public class Graph<T> {
    public boolean isDirected;
    public List<Edge<T>> allEdges;
    public Map<Long, Vertex<T>> allVertices;

    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
        this.allEdges = new ArrayList<>();
        this.allVertices = new HashMap<>();
    }

    public void addEdge(long id1, long id2) {
        addEdge(id1, id2, 0);
    }

    public void addEdge(long id1, long id2, int weight) {
        Vertex<T> vertex1 = findOrCreateVertex(id1);
        Vertex<T> vertex2 = findOrCreateVertex(id2);
        Edge<T> edge = new Edge<T>(isDirected, vertex1, vertex2, weight);
        allEdges.add(edge);

        vertex1.addAdjacentVertex(edge, vertex2);
        if (!isDirected) vertex2.addAdjacentVertex(edge, vertex1);
    }

    private Vertex<T> findOrCreateVertex(long id) {
        Vertex<T> vertex = allVertices.get(id);
        if(vertex == null) vertex = addVertex(id);
        return vertex;
    }

    public Vertex<T> addVertex(long id) {
        return addVertex(id, null);
    }

    public Vertex<T> addVertex(long id, T data) {
        Vertex<T> vertex = new Vertex<>(id);
        allVertices.put(id, vertex);
        setDataForVertex(id,data);
        return vertex;
    }

    public void setDataForVertex(long id, T data){
        Vertex<T> vertex = getVertex(id);
        vertex.data = data;
    }

    public Vertex<T> getVertex(long id) {
        Vertex<T> vertex = allVertices.get(id);
        if(vertex == null) throw new RuntimeException("Vertex not found: " + id);
        return vertex;
    }

    public Collection<Vertex<T>> getAllVertices() {
        return allVertices.values();
    }

    public List<Edge<T>> getAllEdges() {
        return allEdges;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (Edge<T> edge : getAllEdges()) {
            buffer.append(edge.vertex1 + " " + edge.vertex2 + " " + edge.weight);
            buffer.append("\n");
        }
        return buffer.toString();
    }

    public static class Vertex<T> {
        public long id;
        public T data;
        public Set<Edge<T>> edges = new LinkedHashSet<>();
        public Set<Vertex<T>> adjacentVertices = new LinkedHashSet<>();

        public Vertex(long id) {
            this.id = id;
        }

        public void addAdjacentVertex(Edge<T> e, Vertex<T> v) {
            edges.add(e);
            adjacentVertices.add(v);
        }

        public int getDegree() {
            return edges.size();
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (id ^ (id >>> 32));
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex other = (Vertex) obj;
            if (id != other.id)
                return false;
            return true;
        }

        public String toString() {
            return String.valueOf(id);
        }
    }

    public static class Edge<T> {
        public boolean isDirected = false;
        public Vertex<T> vertex1;
        public Vertex<T> vertex2;
        public int weight;

        public Edge(Vertex<T> vertex1, Vertex<T> vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }

        public Edge(boolean isDirected, Vertex<T> vertex1, Vertex<T> vertex2, int weight) {
            this.isDirected = isDirected;
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((vertex1 == null) ? 0 : vertex1.hashCode());
            result = prime * result + ((vertex2 == null) ? 0 : vertex2.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;

            if (this == obj) return true;

            if (getClass() != obj.getClass()) return false;

            Edge other = (Edge) obj;
            if (isDirected) {
                //For directed graph, edge A->B is different from edge B->A
                if ((vertex1 == null && other.vertex1 != null)) return false;

                if ((vertex2 == null && other.vertex2 != null)) return false;

                if (vertex1.equals(other.vertex1) && vertex2.equals(other.vertex2)) return true;
            } else {
                //For undirected graph, Edge AB = Edge BA
                if (vertex1.equals(other.vertex1) && vertex2.equals(other.vertex2)) return true;

                if (vertex1.equals(other.vertex2) && vertex2.equals(other.vertex1)) return true;
            }
            return false;
        }

        @Override
        public String toString() {
//            return "Edge [isDirected=" + isDirected + ", vertex1=" + vertex1
//                    + ", vertex2=" + vertex2 + ", weight=" + weight + "]";
            return "Edge [isDirected=" + isDirected + ", (" + vertex1 + "," + vertex2 + ")" + ", weight=" + weight + "]";
        }
    }
}


