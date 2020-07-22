package array;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  There are N children standing in a line. Each child is assigned a rating value.
 *  You are giving candies to these children subjected to the following requirements:
 *  1. Each child must have at least one candy1.
 *  2. Children with a higher rating get more candies than their neighbors.
 *  What is the minimum candies you must give?
 *
 * Links:
 *  https://leetcode.com/problems/candy/description/
 *  https://www.programcreek.com/2014/03/leetcode-candy-java/
 */
public class q45_Candy {
    public static void main(String[] args) {
        int[] ratings = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        q45_Candy c = new q45_Candy();
        System.out.println(c.candy2(ratings));

        int[] ratings2 = new int[] {1,3,2,1,1};
        System.out.println(c.candy1(ratings2));
    }

    /**
     * Approach 1: Single Pass Approach with Constant Space
     *  This solution picks each element from the input array only once.
     *  First, we give a candy1 to the first child. Then for each child we have three cases:
     *      His/her rating is equal to the previous one -> give 1 candy1.
     *      His/her rating is greater than the previous one -> give him (previous + 1) candies.
     *      His/her rating is less than the previous one -> don't know what to do yet, let's just count the number of such consequent cases.
     *
     *  When we enter 1 or 2 condition we can check our count from 3. If it's not zero then we know that
     *      we were descending before and we have everything to update our total candies amount:
     *      number of children in descending sequence of ratings - countDown, number of candies given at peak - prev (we don't update prev when descending).
     *      Total number of candies for "descending" children can be found through arithmetic progression formula (1+2+...+countDown).
     *      Plus we need to update our peak child if his number of candies is less then or equal to countDown.
     *
     * Links:
     *  https://leetcode.com/problems/candy/discuss/42770
     *  Blog explanation - http://www.allenlipeng47.com/blog/index.php/2016/07/21/candy/
     *  Leetcode explanation - https://leetcode.com/articles/candy/#approach-4-single-pass-approach-with-constant-space-accepted
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1) since the change is done in-place
     */
    public int candy1(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;

        int candies = 1, prev=1, countDown=0;
        for (int i=1; i<ratings.length; i++) {
            if (ratings[i] >= ratings[i-1]) {
                if (countDown > 0) {
                    //sum is arithmetic progression 1 + 2 + 3 ... since current is 1 more than prev
                    candies += (countDown * (countDown+1)) / 2;
                    // check if prev is tall enough - meaning, if the current countDown children have 3,2,1 candies..
                    // the prev child should have more than the highest candy value 3 by atleast 1
                    if (countDown >= prev) candies += countDown - prev + 1;
                    countDown = 0;
                    prev = 1; // when ascending and there is countDown, prev should be 1
                }
                prev = (ratings[i] == ratings[i-1]) ? 1 : prev+1;
                candies += prev;
            } else {
                //ratings is below prev value
                countDown++;
            }
        }
        if (countDown > 0) { // if we were descending at the end
            candies += countDown*(countDown+1)/2;
            if (countDown >= prev) candies += countDown - prev + 1;
        }
        return candies;
    }

    /**
     * Approach 2: Using Two Arrays
     *  Similar to Trapping Rain Water Approach 3
     *  The left2rightleft2right array is used to store the number of candies required by the current student taking care
     *  of the distribution relative to the left neighbours only. i.e. Assuming the distribution rule is:
     *  The student with a higher ratings than its left neighbour should always get more candies than its left neighbour.
     *  Similarly, the right2leftright2left array is used to store the number of candies candies required by the current
     *  student taking care of the distribution relative to the right neighbours only. i.e.
     *  Assuming the distribution rule to be: The student with a higher ratings than its right neighbour should always
     *  get more candies than its right neighbour
     *
     * Links:
     *  Based on https://www.programcreek.com/2014/03/leetcode-candy-java/
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(n) since we have two temp arrays
     */
    public int candy2(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;

        int[] children = new int[ratings.length];
        children[0] = 1;

        //Forward loop
        for (int i=1; i< children.length; i++) {
            if (ratings[i] > ratings[i-1]) {
                children[i] = children[i-1] + 1;
            } else {
                children[i] = 1; //Each child must have atleast 1 candy
            }
        }

        //Reverse loop
        int candies = children[children.length-1];
        for (int i=children.length-2; i>=0; i--) {
            if (ratings[i] > ratings[i+1]) {
                children[i] = Math.max(children[i+1]+1, children[i]); //children[i] should have more candies than its neighbours. If already more, nothing to add
            }
            candies += children[i];
        }

        return candies;
    }
}
