package t02_heap.problems;


import java.util.Arrays;

/**
 * Problem:
 *  Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 *  determine if a person could attend all meetings.
 *
 * Links:
 *  https://leetcode.com/problems/meeting-rooms
 *  http://www.programcreek.com/2014/07/leetcode-meeting-rooms-java/
 *
 * Complexity:
 *  Time - O(n), n is the size of the array
 *  Space - O(1) no extra space is needed
 */
public class MeetingRooms {
    public boolean canAttendMeetings(Meeting[] meetings) {
        if(meetings == null || meetings.length==0) return true;

        Arrays.sort(meetings);

        for(int i=0; i<meetings.length-1; i++) {
            if(meetings[i].endTime > meetings[i+1].startTime)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        MeetingRooms mr = new MeetingRooms();
        Meeting[] meetings = new Meeting[4];
        meetings[0] = new Meeting(10, 12); //Make this 11 to 12 to get true
        meetings[1] = new Meeting(9, 10);
        meetings[2] = new Meeting(10, 11);
        meetings[3] = new Meeting(8, 9);
        System.out.println(mr.canAttendMeetings(meetings));
    }
}
class Meeting implements Comparable<Meeting> {
    int startTime;
    int endTime;

    public Meeting(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Meeting o) {
        return this.startTime - o.startTime;
    }
}