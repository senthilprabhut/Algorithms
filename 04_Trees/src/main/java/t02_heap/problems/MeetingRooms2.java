package t02_heap.problems;


import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Problem:
 *  Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
 *  find the minimum number of conference rooms required.
 *
 * Links:
 *  https://leetcode.com/problems/meeting-rooms-ii
 *  https://www.programcreek.com/2014/05/leetcode-meeting-rooms-ii-java/
 *
 * Complexity:
 *  Time - O(n log n) + O(n) ~= O(n log n), n is the meeting array length
 *  Space - O(n) for storing values in the priority queue. Sorting can be done in-place which is O(1) space
 *
 */
public class MeetingRooms2 {
    public int minMeetingRooms(Meeting2[] meetings) {
        Arrays.sort(meetings);

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(meetings[0].endTime);
        int count=1; //Min 1 room is selected

        for(int i=1; i<meetings.length; i++) {
            if(meetings[i].startTime < queue.peek())
                count++; //A new meeting room is required
            else
                queue.poll(); //Remove the meeting room

            queue.offer(meetings[i].endTime);
        }
        return count;
    }

    public static void main(String[] args) {
        MeetingRooms2 mr = new MeetingRooms2();
        Meeting2[] meetings = new Meeting2[4];
        meetings[0] = new Meeting2(10, 12);
        meetings[1] = new Meeting2(9, 12);
        meetings[2] = new Meeting2(10, 11);
        meetings[3] = new Meeting2(8, 9);
        System.out.println(mr.minMeetingRooms(meetings));
    }
}

class Meeting2 implements Comparable<Meeting2> {
    int startTime;
    int endTime;

    public Meeting2(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Meeting2 o) {
        return this.startTime - o.startTime;
    }
}