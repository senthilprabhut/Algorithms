package array;

import java.util.HashMap;
import java.util.Map;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given an array of integers, find out whether there are two distinct indices i and j in the array such that the
 *  absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 *
 * Links:
 *  https://leetcode.com/problems/contains-duplicate-iii/description/
 *  https://www.programcreek.com/2014/06/leetcode-contains-duplicate-iii-java/
 */
public class q21_ContainsDuplicate3 {
    public static void main(String[] args) {
        int[] duplicates1 = new int[] {1, 2, 5, 6,1};
        q21_ContainsDuplicate3 cd = new q21_ContainsDuplicate3();
        System.out.println(cd.containsNearbyAlmostDuplicate(duplicates1, 3, 7));

        int[] duplicates2 = new int[] {1, 2, 5, 1, 6};
        System.out.println(cd.containsNearbyAlmostDuplicate(duplicates2, 3, 7));
    }

    /**
     * Approach 1:
     *  As a followup question, it naturally also requires maintaining a window of size k. When t == 0,
     *  it reduces to the previous question so we just reuse the solution.
     *  Since there is now a constraint on the range of the values of the elements to be considered duplicates,
     *  it reminds us of doing a range check which is implemented in tree data structure and would take O(LogN)
     *  if a balanced tree structure is used, or doing a bucket check which is constant time.
     *  We shall just discuss the idea using bucket here.
     *
     *  Bucketing means we map a range of values to the a bucket. For example, if the bucket size is 3,
     *  we consider 0, 1, 2 all map to the same bucket. However, if t == 3, (0, 3) is a considered duplicates
     *  but does not map to the same bucket. This is fine since we are checking the buckets immediately before and
     *  after as well. So, as a rule of thumb, just make sure the size of the bucket is reasonable such that
     *  elements having the same bucket is immediately considered duplicates or duplicates must lie within
     *  adjacent buckets. So this actually gives us a range of possible bucket size, i.e. t and t + 1.
     *  We just choose it to be t and a bucket mapping to be value / t. Another complication is that negative ints
     *  are allowed. A simple value / t just shrinks everything towards 0. Therefore, we can just reposition every
     *  value to start from Integer.MIN_VALUE.
     *
     *  Edit: Actually, we can use t + 1 as the bucket size to get rid of the case when t == 0.
     *  It simplifies the code. The above code is therefore the updated version.
     *
     * Links:
     *  https://discuss.leetcode.com/topic/15199/ac-o-n-solution-in-java-using-buckets-with-explanation
     *
     * Complexity:
     *  Time complexity: O(N)
     *  Memory: O(K), More than K elements are not present in map
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;
        Map<Long, Long> map = new HashMap<>(); //bucket

        for(int i=0; i<nums.length; i++) {
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE; //Converts negative numbers to positive
            long bucket = remappedNum / ((long) t+1); //The buckets will be in range 0, 1, 2 .... t
            if( map.containsKey(bucket) || (map.containsKey(bucket-1) && remappedNum - map.get(bucket-1) <= t)
                || (map.containsKey(bucket+1) && map.get(bucket+1) - remappedNum <= t) ) {
                return true;
            }
            if(map.entrySet().size() >= k) {  //If there are more entries than k (window size), remove them
                long lastRemappedNum = (long) nums[i-k] - Integer.MIN_VALUE;
                long lastBucket = lastRemappedNum / (long) t+1;
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum);
        }
        return false; //no duplicates
    }

}
