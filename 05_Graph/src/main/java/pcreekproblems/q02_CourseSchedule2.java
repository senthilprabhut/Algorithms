package pcreekproblems;

import java.util.*;

/**
 * Problem:
 *  This is an extension of Course Schedule. This time a valid sequence of courses is required as output.
 *
 * Links:
 *  https://www.programcreek.com/2014/06/leetcode-course-schedule-ii-java/
 */
public class q02_CourseSchedule2 {
    public static void main(String[] args) {
        q02_CourseSchedule2 cs1 = new q02_CourseSchedule2();
        System.out.println(Arrays.toString(cs1.getOrder(3, new int[][]{{1,2}, {1,0},{0,1}})) );
        System.out.println(Arrays.toString(cs1.getOrder(3, new int[][]{{1,2}, {0,1}})) );
    }

    /**
     * Approach:
     *  If we use the DFS solution of Course Schedule, a valid sequence can easily be recorded.
     *  This is nothing but Topological Sort
     *
     * Complexity:
     *  Time Complexity: O(V+E)
     *  Space Complexity: O(V)
     */
    public int[] getOrder(int noOfCourses, int[][] prerequisites) {
        List<Integer> vertexOrdering = new ArrayList<>();

        //If there are no courses, return empty list
        if(noOfCourses == 0) {
            return new int[]{-1};
        }

        //if there is no prerequisites, return a sequence of courses
        if(prerequisites.length == 0) {
            int[] result = new int[noOfCourses];
            for(int i=0; i<noOfCourses;i++)
                result[i] = i;
        }

        //Calculate indegree for each node in prerequisite
        //Also create an adjacency list
        int[] inDegree = new int[noOfCourses];
        Map<Integer,Set<Integer>> adjacencyMap = new HashMap<>();
        for(int[] pre : prerequisites) {
            int dependentCourse = pre[0], prereqCourse = pre[1];
            if(! adjacencyMap.containsKey(prereqCourse))
                adjacencyMap.put(prereqCourse, new HashSet<>());
            adjacencyMap.get(prereqCourse).add(dependentCourse);
            inDegree[dependentCourse]++;
        }

        //Create a list of vertex with 0 in-degree
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<noOfCourses; i++)
            if(inDegree[i] == 0) queue.add(i);

        int finishedCourses = queue.size();
        int[] results = new int[noOfCourses];
        int resCntr = 0;
        //Loop through the vertices from queue and get the node order
        while(!queue.isEmpty()) {
            Integer courseVertex = queue.poll();
            results[resCntr++] = courseVertex;
            for(Integer adjacent : adjacencyMap.getOrDefault(courseVertex, new HashSet<>())) {
                // Decrease in-degree by 1 for all its neighboring nodes. E * O(1)
                inDegree[adjacent]--;
                //If in-degree of a neighboring nodes is reduced to zero, then add it to the queue.
                if(inDegree[adjacent] == 0) {
                    finishedCourses++;
                    queue.offer(adjacent);
                }
            }
        }
        if (finishedCourses == noOfCourses)
            return results;
        else
            return new int[] {-1};
    }

}
