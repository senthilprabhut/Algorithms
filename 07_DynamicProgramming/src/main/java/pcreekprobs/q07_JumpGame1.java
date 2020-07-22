package pcreekprobs;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *  Each element in the array represents your maximum jump length at that position.
 *  Determine if you are able to reach the last index.
 *  For example:    A = [2,3,1,1,4], return true.
 *                  A = [3,2,1,0,4], return false.
 * Links:
 *  https://leetcode.com/problems/jump-game/description/
 *  https://www.programcreek.com/2014/03/leetcode-jump-game-java/
 *
 */
public class q07_JumpGame1 {
    public static void main(String[] args) {
        q07_JumpGame1 jg = new q07_JumpGame1();
        System.out.println(jg.canJump(new int[] {2,3,1,1,4} ));
        System.out.println(jg.canJump(new int[] {3,2,1,0,4} ));
    }

    /**
     * Approach:
     *
     * Links:
     *  https://leetcode.com/problems/jump-game/discuss/20923/Java-Solution-easy-to-understand
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public boolean canJump(int[] nums) {
        int maxReach = 0;

        //The loop will stop when max cannot reach cur or max can reach the last index.
        for (int i=0; i<= maxReach && maxReach < nums.length-1; i++) {
            maxReach = Math.max(i + nums[i], maxReach);
        }

        //if maxReach is equal to or greater than array length, we have reached the end
        return  (maxReach >= nums.length-1);
    }
}
