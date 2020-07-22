package math;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Find the total area covered by two rectilinear rectangles in a 2D plane.
 *  Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 *  Assume that the total area is never beyond the maximum possible value of int.
 *
 * Links:
 *  https://leetcode.com/problems/rectangle-area/description/
 *  https://www.programcreek.com/2014/06/leetcode-rectangle-area-java/
 */
public class q51_RectangleArea {
    public static void main(String[] args) {
        q51_RectangleArea ra = new q51_RectangleArea();
        System.out.println(ra.computeArea(-3,0,3,4,0,-1,9,2));
    }

    /**
     * Approach:
     *  This problem can be converted as a overlap internal problem. On the x-axis, there are (A,C) and (E,G);
     *  on the y-axis, there are (F,H) and (B,D). If they do not have overlap, the total area is the sum of 2 rectangle areas.
     *  If they have overlap, the total area should minus the overlap area
     *  To check the overlap of two rectangles, consider the non-overlap conditions:
     *   1. Rect1 is on the left of Rect2.  (Rect1's right edge is on the left of Rect2's left edge)
     *   2. Rect1 is on the right of Rect2.  (Rect1's left edge is on the right of Rect2's right edge)
     *   3. Rect1 is on top of Rect2.  (Rect1's bottom edge is on top of Rect2's top edge)
     *   4. Rect1 is on the bottom of Rect2.  (Rect1's top edge is on the bottom of Rect2's bottom edge)
     *  In this problem: Conditions A>G, C<E, D<F, B>H are corresponding to the above 4 conditions to check if the two rectangles are overlap.
     *  To compute the overlap area, according to the figure in the question, it is not hard to derive the area is: (min(D,H)−max(B,F))×(min(C,G)−max(A,E)).
     *
     * Links:
     *  https://leetcode.com/problems/rectangle-area/discuss/62138/My-Java-solution-Sum-of-areas-Overlapped-area?page=1
     *
     * Explanation:
     *  http://yucoding.blogspot.com/2015/10/leetcode-question-rectangle-area.html
     *
     * Complexity:
     *  Time is O(1)
     *  Space is O(1)
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int areaOfRect1 = (C-A) * (D-B);
        int areaOfRect2 = (G-E) * (H-F);

        //If no overlap (Rect1 is above or below Rect2 or Rect1 is to right or left of Rect2), just return sum of area
        int overlapArea = 0;
        if (!(A > G || C < E || B > H || D < F)) {
            //calculate the overlap area - calculate the left, right, top and bottom of the overlap area
            int overlapBottom = Math.max(A,E);
            int overlapTop = Math.min(C,G);
            int overlapLeft = Math.max(B,F);
            int overlapRight = Math.min(D,H);
            overlapArea = (overlapRight-overlapLeft) * (overlapTop-overlapBottom);
        }
        return areaOfRect1 + areaOfRect2 - overlapArea;
    }
}
