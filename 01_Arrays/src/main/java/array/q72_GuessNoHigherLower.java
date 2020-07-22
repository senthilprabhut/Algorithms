package array;

import java.util.Random;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  We are playing the Guess Game. The game is as follows:
 *  I pick a number from 1 to n. You have to guess which number I picked.
 *  Every time you guess wrong, I'll tell you whether the number is higher or lower.
 *  You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 *      -1 : My number is lower
 *      1 : My number is higher
 *      0 : Congrats! You got it!
 *  Example: n = 10, I pick 6.
 *  Return 6 (which is the number I picked)
 *
 * Links:
 *  https://leetcode.com/problems/moving-average-from-data-stream
 *  https://www.programcreek.com/2014/05/leetcode-moving-average-from-data-stream-java/
 */
public class q72_GuessNoHigherLower {
    private int picked;

    public static void main(String[] args) {
        q72_GuessNoHigherLower gn = new q72_GuessNoHigherLower(15);
        System.out.println(gn.guessNumber(20));
    }

    public q72_GuessNoHigherLower(int picked) {
        this.picked = picked;
    }

    public int guessNumber(int n) {
        int start=0, end=n;
        while (start < end) {
            int myGuess = getRandomNumber(start, end);
            int retVal = guess(myGuess);

            if (retVal < 0) end=myGuess-1;
            else if (retVal > 0) start=myGuess+1;
            else return myGuess;
        }
        return 0;
    }

    private int guess(int num) {
        if (num > picked) return -1;
        else if (num < picked) return 1;
        return 0;
    }

    private int getRandomNumber(int min, int max) {
        Random r = new Random();
        return r.nextInt((max-min)+1) + min;
    }
}
