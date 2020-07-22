package pcreekprobs;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *  Each element in the array represents your maximum jump length at that position.
 *  Your goal is to reach the last index in the minimum number of jumps.
 *  For example:    Given array A = [2,3,1,1,4]
 *  The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 *  Note:  You can assume that you can always reach the last index.
 *
 * Links:
 *  https://leetcode.com/problems/jump-game-ii/description/
 *  https://www.programcreek.com/2014/06/leetcode-jump-game-ii-java/
 *
 */
public class q07_JumpGame2 {
    public static void main(String[] args) {
        q07_JumpGame2 jg = new q07_JumpGame2();

        int[] arr1 = {2,3,1,1,4};
        System.out.println(jg.jump(arr1));  //2
    }

    /**
     * Approach: Single Loop DP
     *  Let’s say the range of the current jump is [curBegin, curEnd], curFarthest is the farthest point that all points in [curBegin, curEnd] can reach.
     *  Once the current point reaches curEnd, then trigger another jump, and set the new curEnd with curFarthest, then keep the above steps
     *
     * Links:
     *  https://leetcode.com/problems/jump-game-ii/discuss/18014/Concise-O(n)-one-loop-JAVA-solution-based-on-Greedy
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int jump(int[] nums) {
        //minimum steps for reaching curEnd
        int jumpCount=0;
        //The range of current jump is [i, currEnd]
        int curEnd=0;
        //curFarthest is the farthest point that all points in [curBegin, curEnd] can reach
        int curFarthest=0;


        //We loop only till less than nums.length-1 since if we reach nums.length-1, we have reached the end of the array
        for (int i=0; i<nums.length-1; i++) {
            //See what is the farthest we can reach within the curEnd range
            curFarthest = Math.max(curFarthest, i+nums[i]);

            //If the farthest we can go
            if (curFarthest >= nums.length - 1) {
                jumpCount++;
                break;
            }

            if (i == curEnd) {
                jumpCount++; //Increment the count - this will be the no of steps to reach the new curEnd
                curEnd=curFarthest; //Move the end to the farthest reachable by the points till now
            }
        }
        return jumpCount;
    }
}
