package pcreekproblems;

import adt.DisjointSet;

import java.util.*;

/**
 * Problem:
 *  Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 *  check if these edges form a valid tree.
 *  You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the
 *  same as [1, 0] and thus will not appear together in edges.
 *
 * Links:
 *  https://www.programcreek.com/2014/05/graph-valid-tree-java/
 *  https://discuss.leetcode.com/topic/22486/ac-java-solutions-union-find-bfs-dfs
 *  https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/CycleUndirectedGraph.java
 */
public class q03_GraphValidTree {
    public static void main(String[] args) {
        q03_GraphValidTree gvt = new q03_GraphValidTree();
        int[][] edges = new int[][] { {0, 1}, {1, 2}, {0, 2}};
        System.out.println("Is valid tree: " + gvt.validTree3(3, edges));
    }

    /**
     * Approach:
     *  This problem can be converted to finding a cycle in a graph.
     *  Below is DFS (recursion) approach
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public boolean validTree1(int n, int[][] edges) {
        //Create an adjacency map for the graph
        Map<Integer, HashSet<Integer>> graph = new HashMap<>();
        for (int i=0; i<n; i++)
            graph.put(i, new HashSet<>()); //Create a graph entry for n vertices

        for(int[] edge : edges) {
            int startVertex = edge[0], endVertex = edge[1];
            graph.get(startVertex).add(endVertex);
            graph.get(endVertex).add(startVertex);
        }

        boolean[] visited = new boolean[n];

        //Parent is passed since this is an undirected graph and we should not come back to the same node from child
        if (! dfs(0, -1, graph, visited) ) return false;

        //check if fully connected
        for(boolean visit : visited)
            if(visit == false) return false;

        return true;
    }

    private boolean dfs(int current, int parent, Map<Integer, HashSet<Integer>> graph, boolean[] visited) {
        //If the node is already visited and we come here again, then there is a cycle
        if (visited[current] == true) return false;
        visited[current] = true;

        for(int adjacent : graph.get(current)) {
            if (adjacent != parent && ! dfs(adjacent, current, graph, visited) ) //If any of the adjacents return false, then return false here
                return false;
        }
        return true; //No cycle detected
    }

    /**
     * Approach:
     *  This problem can be converted to finding a cycle in a graph.
     *  Below is BFS (queue) approach
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public boolean validTree2(int n, int[][] edges) {
        //Create an adjacency map for the graph
        Map<Integer, HashSet<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++)
            graph.put(i, new HashSet<>()); //Create a graph entry for n vertices

        for (int[] edge : edges) {
            int startVertex = edge[0], endVertex = edge[1];
            graph.get(startVertex).add(endVertex);
            graph.get(endVertex).add(startVertex);
        }

        boolean[] visited = new boolean[n];

        // no cycle
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (visited[current] == true) return false;
            visited[current] = true;
            for (int adjacent : graph.get(current)) {
                queue.offer(adjacent);
                graph.get(adjacent).remove(current); //Remove the back ref from the adjacent to the current node
            }
        }

        // fully connected
        for (boolean result : visited) {
            if (!result)
                return false;
        }

        return true;
    }

    /**
     * Approach:
     *  This problem can be converted to finding a cycle in a graph.
     *  Below is Disjoint Set based approach
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public boolean validTree3(int n, int[][] edges) {
        DisjointSet<Integer> disjointSet = new DisjointSet<>();

        //Makeset for all vertices in the graph
        for(int i=0; i<n; i++) {
            disjointSet.makeSet(i);
        }

        //For the two vertice of each edge, find if they are in the same set,
        //If so, there is a cycle, return false.
        for(int[] edge : edges) {
            int startVertex = edge[0], endVertex = edge[1];
            if (disjointSet.findSet(startVertex) == disjointSet.findSet(endVertex))
                return false;

            disjointSet.union(startVertex, endVertex);
        }
        return true;
    }
}
