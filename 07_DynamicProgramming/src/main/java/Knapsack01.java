import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem:
 *  Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
 *  Given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with n items respectively.
 *  Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that sum
 *  of the weights of this subset is smaller than or equal to W.
 *  You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
 *
 * Links:
 *  http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
 *  Tushar Topdown DP - https://youtu.be/149WSzQ4E1g
 *  Tushar Bottomup DP - https://youtu.be/8LusJS5-AGo
 *  Solution 1 (Bottom up) - https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/Knapsack01.java
 *  Solution 2 (Space optimized DP) - Based on https://leetcode.com/problems/coin-change/solution/#approach-3-dynamic-programming-bottom-up-accepted
 *  Solution 3 (Top Down) - https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/Knapsack01.java
 *
 * Complexity:
 *  Solution 1
 *      Time complexity : O(S*n) On each step, the algorithm finds the next F(i) in n iterations, where 1≤i≤S.
 *      Therefore in total the iterations are S*n.
 *      Space complexity : O(S*n). We use extra space for the memoization table (dp[][])
 *  Solution 2
 *      Time complexity : O(S*n) On each step, the algorithm finds the next F(i) in n iterations, where 1≤i≤S.
 *      Therefore in total the iterations are S*n.
 *      Space complexity : O(S). We use extra space for the memoization table (dp[])
 *  Solution 3
 *      Time complexity : O(S*n). where S is the amount, n is denomination count.
 *      In the worst case the recursive tree of the algorithm has height of S and the algorithm solves only
 *      S subproblems because it caches precalculated solutions in a table.
 *      Each subproblem is computed with nn iterations, one by coin denomination.
 *      Therefore there is O(S*n) time complexity.
 *      Space complexity : O(S), where S is the amount to change We use extra space for the memoization table.
 */
public class Knapsack01 {
    public static void main(String[] args) {
//        int[] wt = {3,4,5};
//        int[] val = {4,5,7};
//        int totalWeight = 2;
//        int[] wt = {1, 3, 4, 5};
//        int[] val = {1, 4, 6, 7};
//        int totalWeight = 8;
        int wt[] = {4, 2, 3, 5, 5, 6, 9, 7, 8, 10};
        int val[] = {22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
        int totalWeight = 30;
        Knapsack01 ks = new Knapsack01();
        int maxValue = ks.bottomUpDP(wt, val, totalWeight);
        System.out.printf("BottomUp: Maximum value from Weights %s and Values %s for total weight %d is %d \n \n", Arrays.toString(wt),
                Arrays.toString(val), totalWeight, maxValue);

        maxValue = ks.bottomUpDP2(wt, val, totalWeight);
        System.out.printf("BottomUp (Space Efficient): Maximum value from Weights %s and Values %s for total weight %d is %d \n \n", Arrays.toString(wt),
                Arrays.toString(val), totalWeight, maxValue);

        maxValue = ks.topDownDP(wt, val, totalWeight);
        System.out.printf("TopDown: Maximum value from Weights %s and Values %s for total weight %d is %d", Arrays.toString(wt),
                Arrays.toString(val), totalWeight, maxValue);
    }

    public int bottomUpDP(int[] wt, int[] val, int totalWt) {
        //Wt and totalWt are increased by 1 to calculate the situations of 0 Wt chosen and 0 total Wt
        int[][] dp = new int[wt.length + 1][totalWt + 1];
        for (int i = 0; i <= wt.length; i++)
            dp[i][0] = 0; //With 0 total wt, 0 value is derived using any of the available weights
        for (int j = 0; j <= totalWt; j++)
            dp[0][j] = 0; //Also with 0 Wt chosen, 0 value is derived for any totalWt

        //OPTIONAL - Create a new weight and value array - this is to make formula readable
        int[] newWT = new int[wt.length + 1];
        System.arraycopy(wt, 0, newWT, 1, wt.length);
        int[] newVal = new int[val.length + 1];
        System.arraycopy(val, 0, newVal, 1, val.length);

        for (int curr = 1; curr < newWT.length; curr++) {
            for (int currTotalWt = 1; currTotalWt <= totalWt; currTotalWt++) {
                if (newWT[curr] <= currTotalWt) {
                    //Maximum of 1) NOT choosing the current weight and achieving totalWt
                    //2) choosing current wt and max val of remaining wt
                    dp[curr][currTotalWt] = Math.max(dp[curr - 1][currTotalWt], newVal[curr] + dp[curr - 1][currTotalWt - newWT[curr]]);
                } else {
                    dp[curr][currTotalWt] = dp[curr - 1][currTotalWt];
                }
            }
        }
        printCombinations1(dp, wt, totalWt);
        return dp[wt.length][totalWt];
    }

    //Space efficient DP solution
    public int bottomUpDP2(int[] wt, int[] value, int totalWt) {
        int[] dp = new int[totalWt + 1];
        int[] idxTrackr = new int[totalWt + 1];

        dp[0] = 0; //With 0 totWt, 0 value is derived
        for (int i = 1; i < dp.length; i++) {
            idxTrackr[i] = -1;
        }

        for (int currTotalWt = 1; currTotalWt <= totalWt; currTotalWt++) {
            for (int curr = 0; curr < wt.length; curr++) {
                //2nd condition makes sure that the previous dp value was also not achieved using the same weight
                if (wt[curr] <= currTotalWt && !dpChainContains(idxTrackr, wt, currTotalWt, curr)) {
                    if ((value[curr] + dp[currTotalWt - wt[curr]]) > dp[currTotalWt]) {
                        dp[currTotalWt] = value[curr] + dp[currTotalWt - wt[curr]];
                        idxTrackr[currTotalWt] = curr;
                    }
                }
            }
        }
        printCombinations2(idxTrackr,wt,totalWt);
        return dp[totalWt];
    }

    public int topDownDP(int[] wt, int[] value, int totalWt) {
        //map of key(remainingWeight, remainingCount) to maximumValue they can get.
        Map<MapKey, Integer> map = new HashMap<>();
        return topDownRec(wt, value, totalWt, 0, map);
    }

    private int topDownRec(int[] wt, int[] value, int remainingWt, int currentItemIdx, Map<MapKey,Integer> memoizationMap) {
        //if currentItem exceeds total item count or remainingWeight is less than 0
        if(currentItemIdx >= wt.length || remainingWt <= 0) return 0;

        //Build a key based on remainingWeight and remainingCount
        MapKey key = new MapKey();
        key.remainingItems = wt.length - (currentItemIdx+1);
        key.remainingWeight = remainingWt;

        //See if key exists in map. If so then return the maximumValue for key stored in map.
        if(memoizationMap.containsKey(key)) return memoizationMap.get(key);

        int maxValue = 0;
        //if weight of item is more than remainingWeight then try next item by skipping current item
        if(wt[currentItemIdx] > remainingWt)
            maxValue = topDownRec(wt, value, remainingWt, currentItemIdx+1, memoizationMap);
        else {
            //try to get maximumValue of either by picking the currentItem or not picking currentItem
            maxValue = Math.max(value[currentItemIdx] + topDownRec(wt, value, remainingWt-wt[currentItemIdx], currentItemIdx+1, memoizationMap),
                    topDownRec(wt, value, remainingWt, currentItemIdx+1, memoizationMap));
        }
        //memoize the key with maxValue found to avoid recalculation
        memoizationMap.put(key, maxValue);
        return maxValue;
    }

    private void printCombinations1(int[][] dp, int[] wt, int totalWt) {
        int weightY = wt.length, totalX = totalWt;
        if (dp[weightY][totalX] == 0) {
            System.out.println("No solution is possible");
            return;
        }

        System.out.printf("Weights making up %d are:\t", totalWt);
        while (totalX != 0 && weightY != 0) {
            if (dp[weightY][totalX] == dp[weightY - 1][totalX]) {
                weightY--;
            } else {
                System.out.printf("%d \t", wt[weightY - 1]);
                totalX = totalX - wt[weightY - 1];
                weightY = weightY - 1;
            }

        }
        System.out.println();
    }

    private void printCombinations2(int[] trackr, int[] wt, int totalWt) {
        if(trackr[trackr.length-1] == -1) {
            System.out.println("No solution is possible");
            return;
        }

        int start = trackr.length - 1;
        System.out.printf("Weights making up %d are: \t", totalWt);
        int index = trackr[start];
        while (start != 0 && index!= -1) {
            System.out.printf("%d \t", wt[index]);
            start = start - wt[index];
            index = trackr[start];
        }
        System.out.println();
    }

    private boolean dpChainContains(int[] trackr, int[] wt, int currTotalWt, int current) {
        currTotalWt = currTotalWt - wt[current];
        while (currTotalWt > 0) {
            int wtIndex = trackr[currTotalWt];  // what weight contributed to max value for that totalWt
            if(wtIndex == current) return true; //check if that wt is the same as current weight
            if(wtIndex == -1) break;
            currTotalWt = currTotalWt - wt[wtIndex]; //reduce total wt
        }
        return false;
    }

    private class MapKey {
        int remainingWeight;
        int remainingItems;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MapKey index = (MapKey) o;

            if (remainingWeight != index.remainingWeight) return false;
            return remainingItems == index.remainingItems;

        }

        @Override
        public int hashCode() {
            int result = remainingWeight;
            result = 31 * result + remainingItems;
            return result;
        }
    }
}
