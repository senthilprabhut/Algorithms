package pcreekproblems;

import java.util.*;

/**
 * Problem:
 *  There are a total of n courses you have to take, labeled from 0 to n - 1.
 *  Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 *  which is expressed as a pair: [0,1]. Given the total number of courses and a list of prerequisite pairs,
 *  is it possible for you to finish all courses?
 *
 * Analysis:
 *  This problem can be converted to finding if a graph contains a cycle.
 *
 * Links:
 *  https://leetcode.com/problems/course-schedule/description/
 *  https://www.programcreek.com/2014/05/leetcode-course-schedule-java/
 *
 */
public class q02_CourseSchedule1 {
    public static void main(String[] args) {
        q02_CourseSchedule1 cs1 = new q02_CourseSchedule1();
        System.out.println(cs1.canFinish(3, new int[][]{{1,2}, {1,0},{0,1}}));
        System.out.println(cs1.canFinish(3, new int[][]{{1,2}}));

        System.out.println(cs1.canFinish2(3, new int[][]{{1,2}, {1,0},{0,1}}));
        System.out.println(cs1.canFinish2(3, new int[][]{{1,2}}));
    }

    /**
     * Approach:
     *  Using BFS - Kahn's Topoligical Sort algorithm
     *
     * Complexity:
     *  Time Complexity: O(V + E)
     *  Space Complexity: O(V)
     */
    public boolean canFinish(int noOfCourses, int[][] prerequisites) {
        if(noOfCourses < 0) return false;
        if(noOfCourses ==0 || prerequisites.length == 0) return true;

        // counter for number of prerequisites
        int[] inDegree = new int[noOfCourses];
        // E part
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
        for(int[] pre : prerequisites) {
            int prereqCourse = pre[1];
            int dependentCourse = pre[0];
            //Create a list of adjacent vertices for current courseVertex
            if(! adjacencyList.containsKey(prereqCourse))
                adjacencyList.put(prereqCourse, new HashSet<>());
            adjacencyList.get(prereqCourse).add(dependentCourse);
            inDegree[dependentCourse]++;
        }

        //Select courses that have no prerequisites
        //Pick all the vertices with in-degree 0 and add them to the queue  - O(V)
        Queue<Integer> queue = new LinkedList<>();
        for(int course=0; course<noOfCourses; course++){
            if (inDegree[course] == 0) queue.offer(course);
        }

        // number of courses that have no prerequisites
        int finishedCourse = queue.size();

        //V Part
        while (!queue.isEmpty()) {
            int courseVertex = queue.poll();
            for(int adjacentVertex : adjacencyList.getOrDefault(courseVertex, new HashSet<>())) {
                // Decrease in-degree by 1 for all its neighboring nodes. E * O(1)
                inDegree[adjacentVertex] --;
                if(inDegree[adjacentVertex] == 0) {
                    finishedCourse++;
                    //If in-degree of a neighboring nodes is reduced to zero, then add it to the queue.
                    queue.add(adjacentVertex);
                }
            }
        }
        return finishedCourse == noOfCourses;
    }

    /**
     * Approach:
     *  Using DFS - Regular Topoligical Sort algorithm
     *
     * Complexity:
     *  Time Complexity: O(V + E)
     *  Space Complexity: O(V)
     */
    public boolean canFinish2(int noOfCourses, int[][] prerequisites) {
        if(noOfCourses < 0) return false;
        if(noOfCourses == 0 || prerequisites.length == 0) return true;

        //Track visited courses
        int[] visited = new int[noOfCourses];

        //Use the map to store what courses depend on a course
        //The course in [0] is dependent on course [1]
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for(int i=0; i<prerequisites.length; i++) {
            int courseVertex = prerequisites[i][1];
            if(! graph.containsKey(courseVertex))
                graph.put(courseVertex, new HashSet<>());
            int dependentCourse = prerequisites[i][0];
            graph.get(courseVertex).add(dependentCourse);
        }

        //For each vertex, do a DFS visit
        for(int i=0; i<noOfCourses; i++) {
            if(!canFinish2DFS(graph, visited, i))
                return false;
        }
        return true;
    }

    private boolean canFinish2DFS(Map<Integer, Set<Integer>> graph, int[] visited, int courseVertex) {
        if (visited[courseVertex] == -1) //There is a cycle since -1 is for currently processing vertex
            return false;

        if (visited[courseVertex] == 1 )
            return true; //The vertex is already visited

        visited[courseVertex] = -1;
        if(graph.containsKey(courseVertex)) {
            for (Integer adjacent : graph.get(courseVertex)) {
                if(! canFinish2DFS(graph, visited, adjacent))
                    return false;
            }
        }
        visited[courseVertex] = 1;
        return true;
    }
}
