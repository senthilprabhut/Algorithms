package math;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Links:
 *  https://leetcode.com/problems/moving-average-from-data-stream
 *  https://www.programcreek.com/2014/05/leetcode-moving-average-from-data-stream-java/
 */
public class q71_MovingAvgFromDataStream {
    private Queue<Integer> window = new LinkedList<>();
    private double sum = 0;   //To prevent integer overflow
    private int size;

    public q71_MovingAvgFromDataStream (int size) {
        this.size = size;
    }

    public static void main(String[] args) {

    }

    /**
     * Approach:
     *  This problem is solved by using a queue.
     *  The idea is to keep the sum so far and update the sum just by replacing the oldest number with the new entry.
     *
     * Links:
     *  https://discuss.leetcode.com/topic/44108/java-o-1-time-solution/2 - comment Jerry
     */
    public double next (int val) {
        if(window.size() == size){
            sum -= window.poll();
        }
        sum += val;
        window.offer(val);
        return sum / window.size();
    }

}
