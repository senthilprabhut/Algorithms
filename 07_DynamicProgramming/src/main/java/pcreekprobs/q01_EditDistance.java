package pcreekprobs;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
 *  (each operation is counted as 1 step). You have the following 3 operations permitted on a word:
 *      a) Insert a character
 *      b) Delete a character
 *      c) Replace a character
 *
 * Links:
 *  https://leetcode.com/problems/edit-distance/description/
 *  https://www.programcreek.com/2013/12/edit-distance-in-java/
 *  Tushar - https://www.youtube.com/watch?v=We3YDTzNXEk
 */
public class q01_EditDistance {
    public static void main(String[] args) {
        q01_EditDistance ed = new q01_EditDistance();
        System.out.println(ed.minDistance1("abcdef", "azced"));
        System.out.println(ed.minDistance2("abcdef", "azced"));
    }

    /**
     * Approach: Space Efficient DP
     *  ou may have noticed that each time when we update dp[i][j], we only need dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j].
     *  In fact, we need not maintain the full m*n matrix. Instead, maintaining one row is enough.
     *  The code can be optimized to O(m) or O(n) space, depending on whether you maintain a row or a column of the original matrix.
     *
     * Links:
     *  https://leetcode.com/problems/edit-distance/discuss/25846/20ms-Detailed-Explained-C++-Solutions-(O(n)-Space)
     *
     * Complexity:
     *  Time is O(mn)
     *  Space is O(n)
     */
    public int minDistance1(String word1, String word2) {
        int m=word1.length(), n=word2.length();

        int[] dp = new int[m+1];
        //int[] pre = new int[m+1];

        for (int i=0; i<=m; i++) {
            dp[i] = i;
        }

        //You can use "pre" as the old row array and optimize it
        //Here we go column wise - match every letter from word 2 against one char of word 1 and onwards
        for (int j=1; j<=n; j++) {
            //prev refers to value from previous row
            int prev = dp[0]; //this is dp[i-1][j-1], i at start is 1
            dp[0] = j;  //remember dp[0][j] = j. Every column we go down, set this value

            //Loop through all chars in word1 and calculate the changes reqd
            for (int i=1; i<=m; i++) {
                int currI = dp[i]; //maintain this state since it gets changed later. It'll become prev for next iteration

                if (word2.charAt(j-1) == word1.charAt(i-1)) {
                    dp[i] = prev;
                } else {
                    dp[i] = Math.min( Math.min(dp[i-1], dp[i]), prev) + 1; //Min of insert, delete and replace in that order
                }
                prev = currI;
            }
        }
        return dp[m];
    }

    /**
     * Approach 2: DFS
     *  Let dp[i][j] stands for the edit distance between two strings with length i and j, i.e., word1[0,...,i-1] and word2[0,...,j-1].
     *  if x == y, then dp[i][j] == dp[i-1][j-1]
     *  if x != y, and we insert y for word1, then dp[i][j] = dp[i][j-1] + 1
     *  if x != y, and we delete x for word1, then dp[i][j] = dp[i-1][j] + 1
     *  if x != y, and we replace x with y for word1, then dp[i][j] = dp[i-1][j-1] + 1
     *  When x!=y, dp[i][j] is the min of the three situations.
     *
     *  For the boundary case, that is, to convert a string to an empty string, it is easy to see that the mininum number
     *  of operations to convert word1[0..i - 1] to "" requires at least i operations (deletions).
     *  In fact, the boundary case is simply the Initial condition:  dp[i][0] = i, dp[0][j] = j
     *
     * Links:
     *  https://leetcode.com/problems/edit-distance/discuss/25849/Java-DP-solution-O(nm)
     *  https://www.programcreek.com/2013/12/edit-distance-in-java/
     *
     * Complexity:
     *  Time is O(mn)
     *  Space is O(mn)
     */
    public int minDistance2(String word1, String word2) {
        int m=word1.length(), n=word2.length();
        int[][] dp = new int[m+1][n+1];

        //Base condition - convert a string to empty string
        for (int i=0; i<=m; i++) {
            dp[i][0] = i;
        }

        for (int j=0; j<=n; j++) {
            dp[0][j] = j;
        }

        for (int i=1; i<=m; i++) {
            for (int j=1; j<=n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min( Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;  //Min of insert, delete and replace in that order
                }
            }
        }

        printActualEdits(dp, word1.toCharArray(), word2.toCharArray());
        return dp[m][n];
    }

    /**
     * Approach 3: Recursion
     *
     * Links:
     *  https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/EditDistance.java
     *
     * Complexity:
     *
     */
    public int recEditDistance(char[]  str1, char str2[], int len1,int len2){

        if(len1 == str1.length){
            return str2.length - len2;
        }
        if(len2 == str2.length){
            return str1.length - len1;
        }
        return Math.min(recEditDistance(str1, str2, len1 + 1, len2 + 1) + str1[len1] == str2[len2] ? 0 : 1,
                Math.min(recEditDistance(str1, str2, len1, len2 + 1) + 1, recEditDistance(str1, str2, len1 + 1, len2) + 1));
    }


    /**
     * Prints the actual edits which needs to be done.
     */
    public void printActualEdits(int T[][], char[] str1, char[] str2) {
        int i = T.length - 1;
        int j = T[0].length - 1;
        while(true) {
            if (i == 0 || j == 0) {
                break;
            }
            if (str1[i-1] == str2[j-1]) {
                i = i-1;
                j = j-1;
            } else if (T[i][j] == T[i-1][j-1] + 1){
                System.out.println("Replace " + str2[j-1] + " in string2 to " + str1[i-1] + " in string1");
                i = i-1;
                j = j-1;
            } else if (T[i][j] == T[i-1][j] + 1) {
                System.out.println("Delete in string1 " + str1[i-1]);
                i = i-1;
            } else if (T[i][j] == T[i][j-1] + 1){
                System.out.println("Delete in string2 " + str2[j-1]);
                j = j -1;
            } else {
                throw new IllegalArgumentException("Some wrong with given data");
            }

        }
    }
}
