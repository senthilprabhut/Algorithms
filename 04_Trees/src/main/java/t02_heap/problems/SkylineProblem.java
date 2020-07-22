package t02_heap.problems;

import java.util.*;

/**
 * Problem:
 *  A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
 *  Now suppose you are given the locations and heightWithDiameter of all the buildings as shown on a cityscape photo (Figure A),
 *  write a program to output the skyline formed by these buildings collectively
 *
 * Links:
 *  https://leetcode.com/problems/the-skyline-problem
 *  https://leetcode.com/discuss/67091/once-for-all-explanation-with-clean-java-code-nlog-time-space
 *  https://www.programcreek.com/2014/06/leetcode-the-skyline-problem-java/
 *  Tushar - https://youtu.be/GSBLe8cKu0s
 *  Solution 1 - https://github.com/mission-peace/interview/blob/master/src/com/interview/geometry/SkylineDrawing.java
 *               https://discuss.leetcode.com/topic/22482/short-java-solution
 *  Solution 2 - http://www.geeksforgeeks.org/divide-and-conquer-set-7-the-skyline-problem/
 *
 * Complexity:
 *  Solution 1 Time complexity - O(n log n)
 *  Solution 1 Space complexity - O(n)
 *  Solution 2 Time Complexity - Time complexity of above recursive implementation is same as Merge Sort.
 *                               T(n) = T(n/2) + Θ(n) = O(n log n)
 */
public class SkylineProblem {

    public static void main(String args[]) {
        int [][] buildings = {{1,3,4},{3,4,4},{2,6,2},{8,11,4}, {7,9,3},{10,11,2}};
        SkylineProblem sd = new SkylineProblem();
        List<int[]> criticalPoints = sd.getSkyline(buildings);
        criticalPoints.forEach(cp -> System.out.println(cp[0] + "(" + cp[1] + ")"));

        System.out.println();
        List<int[]> criticalPoints2 = sd.recurSkyline(buildings, 0, buildings.length-1);
        criticalPoints2.forEach(cp -> System.out.println(cp[0] + "(" + cp[1] + ")"));
    }

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> skyLine = new ArrayList<>();
        List<int[]> height = new ArrayList<>();

        //Add entries for start and end index with heightWithDiameter (-ve heightWithDiameter implies this is a start node) - O(n)
        for(int[] b : buildings) {
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }

        //If both indexes are equal, one with start index comes first (a[1] - b[1])
        //heightWithDiameter array is of size 2n and sorting it takes O(n log n)
        Collections.sort(height, (a,b) -> (a[0] != b[0]) ? (a[0] - b[0]) : a[1]-b[1]);

        //Queue<Integer> heightQ = new PriorityQueue<>((a,b) -> b - a); //Max Heap
        //Priority queue remove in Java is O(n) - Replacing with TreeMap gives O(log n) remove
        TreeMap<Integer, Integer> heightMap = new TreeMap<>(Collections.reverseOrder()); //Max values first
        heightMap.put(0, 1); //2nd parameter is the count of ht - workaround for PQ which can handle duplicate heights
        int prevMaxHt=0;

        //heightWithDiameter array has 2n elements and add/remove to treemap takes log n time - so n (log n)
        for(int[] htArr : height) {      //O(n)
            if(htArr[1] < 0)   //start index
                heightMap.merge(-htArr[1], 1, (data, one) -> data + one);
            else {  //end index
                heightMap.compute(htArr[1], (key,value) -> value - 1); //Reduce the count by 1 - this handles duplicate heights
                heightMap.remove(htArr[1], 0); //Remove if the count turns 0
            }

            int currMaxHt = heightMap.firstKey();
            if(currMaxHt != prevMaxHt) { //a new Max
                skyLine.add(new int[]{htArr[0], currMaxHt});
                prevMaxHt = currMaxHt;
            }
        }
        return skyLine;
    }

    public LinkedList<int[]> recurSkyline(int[][] buildings, int start, int end) {
        if(start == end) {
            LinkedList<int[]> rs = new LinkedList<>();
            rs.add(new int[] {buildings[start][0], buildings[start][2]});
            rs.add(new int[] {buildings[start][1], 0});
            return rs;
        }

        // Recur for left and right halves and merge the two results
        int mid = (start+end)/2;
        return merge(recurSkyline(buildings, start, mid), recurSkyline(buildings, mid+1, end));
    }

    private LinkedList<int[]> merge(LinkedList<int[]> left, LinkedList<int[]> right) {
        // Create a resultant skyline with capacity as sum of two skylines
        LinkedList<int[]> result = new LinkedList<>();
        int ht1=0, ht2=0;  // To store current heights of two skylines

        //Compare list1 and list2 and add one with less x value to result list
        while (left.size() > 0 && right.size() > 0) {
            int idx=0, ht = 0;
            if(left.getFirst()[0] < right.getFirst()[0]) {
                idx = left.getFirst()[0];
                ht1 = left.getFirst()[1];
                ht = Math.max(ht1, ht2);
                left.removeFirst();
            } else if (left.getFirst()[0] > right.getFirst()[0]) {
                idx = right.getFirst()[0];
                ht2 = right.getFirst()[1];
                ht = Math.max(ht1, ht2);
                right.removeFirst();
            } else {
                //If both index are equal
                idx = left.getFirst()[0];
                ht1 = left.getFirst()[1];
                ht2 = right.getFirst()[1];
                ht = Math.max(ht1,ht2);
                left.removeFirst();
                right.removeFirst();
            }

            //Don't add the index if the current ht is same as the previously added index
            if(result.size() == 0 || ht != result.getLast()[1])
                result.add(new int[]{idx, ht});
        }
        result.addAll(left);
        result.addAll(right);
        return result;
    }

}
