package pcreekproblems;

import java.util.*;

/**
 * Problem:
 *  Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct
 *  the itinerary in order. All of the tickets belong to a man who departs from JFK.
 *  Thus, the itinerary must begin with JFK.
 *  Note:
 *      If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order
 *      when read as a single string. For eg, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 *      All airports are represented by three capital letters (IATA code).
 *      You may assume all tickets form at least one valid itinerary.
 *
 * Links:
 *  https://leetcode.com/problems/reconstruct-itinerary/description/
 *  Solution 1 - https://www.programcreek.com/2015/03/leetcode-reconstruct-itinerary-java/
 *  Solution 2 - https://discuss.leetcode.com/topic/36385/share-solution-java-greedy-stack-15ms-with-explanation
 *
 */
public class q04_ReconstructItinerary {

    public static void main(String[] args) {
        q04_ReconstructItinerary ri = new q04_ReconstructItinerary();
        String[][] tickets = new String[][]{{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        System.out.println(ri.findItinerary(tickets)); //[JFK, ATL, JFK, SFO, ATL, SFO]

        String[][] tickets2 = new String[][] {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
        System.out.println(ri.findItinerary(tickets2)); //[JFK, MUC, LHR, SFO, SJC]
    }

    /**
     * Approach:
     *  This is an application of Hierholzer’s algorithm to find a Eulerian path.
     *  PriorityQueue should be used instead of TreeSet, because there are duplicate entries.
     *  First start with a node and get all its adjacent vertices (destinations) in lexical order and
     *  dfs for each of the vertices
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
    public List<String> findItinerary(String[][] tickets ) {
        LinkedList<String> result = new LinkedList<>();
        if(tickets.length == 0) return result;

        Map<String,PriorityQueue<String>> ticketsMap = new HashMap<>();
        for(String[] ticket : tickets) {
            if(! ticketsMap.containsKey(ticket[0]))
                ticketsMap.put(ticket[0], new PriorityQueue<>()); //initialize map with a PQ for a source airport

            ticketsMap.get(ticket[0]).add(ticket[1]); //Add the destinations to the PQ - they will be returned from PQ in lexically sorted order
        }
        dfs("JFK", ticketsMap, result);
        return result;
    }

    private void dfs(String startAirport, Map<String, PriorityQueue<String>> ticketsMap, LinkedList<String> result) {
        PriorityQueue<String> destinationQ = ticketsMap.get(startAirport);
        while(destinationQ != null && !destinationQ.isEmpty()) {
            dfs(destinationQ.poll(), ticketsMap, result);
        }
        result.addFirst(startAirport);
    }

    /**
     * Approach:
     *  This is an application of Hierholzer’s algorithm to find a Eulerian path.
     *  PriorityQueue should be used instead of TreeSet, because there are duplicate entries.
     *  Looping here instead of DFS
     *
     * Complexity:
     *  Time Complexity
     *  Space Complexity
     */
//    public List<String> findItinerary2(String[][] tickets ) {
//        LinkedList<String> result = new LinkedList<>();
//        if(tickets.length == 0) return result;
//
//        Map<String, PriorityQueue<String>> ticketsMap = new HashMap<>();
//        for(String[] ticket : tickets) {
//            if(! ticketsMap.containsKey(ticket[0]))
//                ticketsMap.put(ticket[0], new PriorityQueue<>()); //initialize map with a PQ for a source airport
//
//            ticketsMap.get(ticket[0]).add(ticket[1]); //Add the destinations to the PQ - they will be returned from PQ in lexically sorted order
//        }
//
//        String curr = "JFK";
//        Stack<String> drawBack = new Stack<String>();
//        return result;
//    }

}
