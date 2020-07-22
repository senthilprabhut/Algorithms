package array;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 *  According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
 *  For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 *  Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.
 *
 *  Note: If there are several possible values for h, the maximum one is taken as the h-index.
 *
 * Links:
 *  https://leetcode.com/problems/h-index/description/
 *  https://www.programcreek.com/2014/05/leetcode-h-index-java/
 */
public class q62_HIndex1 {
    public static void main(String[] args) {
        q62_HIndex1 hi1 = new q62_HIndex1();
        System.out.println(hi1.hIndex(new int[]{3, 0, 6, 1, 5}));
    }

    /**
     * Approach: Bucket Sort
     *  The definition of the index is that a scholar with an index of h has published h papers each of which has been cited in other papers at least h times.
     *  Thus, the h-index reflects both the number of publications and the number of citations per publication.
     *  Since h index is both no of publication and no of citations, it is always <= no of papers - which from above example is 5
     *
     *  The counts vector stores the number of papers having a citation equal to its index for i=0 to L-1.
     *  For i=L, it stores the number of papers having a citation equal to or greater than L.
     *  After finalizing the counts vector, we can then easily locate his h-index by scanning from right (L) to left (0).
     *  By definition, index k is his h-index if the summation of all elements from counts[k] to counts[L] is no less than k.
     *
     * Explanation:
     *  https://leetcode.com/problems/h-index/discuss/70768/Java-bucket-sort-O(n)-solution-with-detail-explanation
     *
     * Links:
     *  https://leetcode.com/problems/h-index/discuss/70778/My-O(n)-time-solution-use-Java/72971
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(n)
     */
    public int hIndex(int[] citations) {
        int L = citations.length;
        if (L == 0) return 0;

        //Since our H-Index is max of L, anything beyond that is counted towards L
        int[] count = new int[L+1]; //counts from 0 citations to L+ Citations
        for(int val : citations) {
            if(val>L) count[L]++;
            else count[val]++;
        }

        //Now come in the reverse direction and check if the sum exceeds/equals the index
        //That is our H-Index
        int result = 0;
        for (int k=L; k>=0 ; k--) {
            result += count[k];
            if (result >= k) return k; //h+ citations are > than h papers
        }
        return 0; //unsuccessful
    }
}
