package pcreekproblems;

import java.util.*;

/**
 * Problem:
 *  For a undirected graph with tree characteristics, we can choose any node as the root.
 *  The result graph is then a rooted tree. Among all possible rooted trees,
 *  those with minimum height are called minimum height trees (MHTs).
 *  Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 *
 * Links:
 *  https://www.programcreek.com/2014/07/leetcode-minimum-height-trees-java/
 *  https://youtu.be/tO7-GKlj2iQ?t=28m15s
 */
public class q02_MinimalHtTrees {
    public static void main(String[] args) {
        q02_MinimalHtTrees minHtTree = new q02_MinimalHtTrees();
        System.out.println( minHtTree.findMinHeightTrees(4, new int[][]{{1, 0}, {1, 2}, {1, 3}}) );
        System.out.println( minHtTree.findMinHeightTrees(6, new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}}) );
    }

    /**
     * Approach:
     *  In starting all leaf node are pushed into the queue, then they are removed from tree,
     *  next new leaf node are pushed in queue,
     *  this procedure keeps on going until we have only 1 or 2 node in our tree, which represent the result.
     *
     * Complexity:
     *  Time Complexity: As we are accessing each node once, total time complexity of solution is O(n)
     *  Space Complexity:
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();

        if(n==0) return result;
        if(n==1) {
            result.add(0);
            return result;
        }
        //Build Graph as Adjacency map
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for(int vertex=0; vertex<n; vertex++){
            graph.put(vertex, new HashSet<Integer>());
        }

        //Add the edge information
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]); //Since this is an undirected graph AB = BA
        }

        //first enqueue all leaf nodes in queue - since this is undirected graph, find degree=1
        Queue<Integer> leaves = new LinkedList<>();
        for(int i=0; i<n; i++) {
            if (graph.get(i).size() == 1) leaves.offer(i);
        }
        if(leaves.size() == 0) return result;

        //There can be only one root or at max two root for minimum height depending on tree structure
        //Loop untill total vertex remains less than 2
        while (n > 2) {
            n = n - leaves.size(); //Remove the leaf nodes from graph

            Queue<Integer> newLeaves = new LinkedList<>();
            // for each neighbour, decrease its degree and if it become leaf, insert into queue
            for(Integer i : leaves) {
                //Since this is leaf, there is only one neighbor and remove the leaf from the neighbor's adjacency list
                int neighbor = graph.get(i).iterator().next();
                graph.get(neighbor).remove(i);

                if(graph.get(neighbor).size() == 1)
                    newLeaves.add(neighbor);
            }
            leaves = newLeaves;
        }
        return new LinkedList<>(leaves);
    }
}
