package search;

public class GraphNode {
    public int data;
    public GraphNode[] neighbors;
    public boolean visited;

    public GraphNode(int data) {
        this.data = data;
    }

    public GraphNode(int data, GraphNode[] neighbors) {
        this.data = data;
        this.neighbors = neighbors;
    }

    public String toString(){
        return "value: "+ this.data;
    }
}
