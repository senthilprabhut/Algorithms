package pcreekprobs;

/**
 * Difficulty Level: EASY
 *
 * Problem:
 *  Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 *  For example, given the array [-2,1,-3,4,-1,2,1,-5,4], the contiguous subarray [4,-1,2,1] has the largest sum = 6
 *
 * Links:
 *  https://leetcode.com/problems/maximum-subarray/description/
 *  https://www.programcreek.com/2013/02/leetcode-maximum-subarray-java/
 *
 */
public class q04_MaximumSubarraySum {
    public static void main(String[] args) {
        q04_MaximumSubarraySum mss = new q04_MaximumSubarraySum();
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(mss.maxSubArray(arr));
    }

    /**
     * Approach: DP
     *  Kadane's Algorithm
     *
     * Links:
     *  https://leetcode.com/problems/maximum-subarray/discuss/20211/Accepted-O(n)-solution-in-java
     *
     * Complexity:
     *  Time is O(n)
     *  Space is O(1)
     */
    public int maxSubArray(int[] nums) {
        int maxSoFar = 0, maxEndingHere = 0;

        for (int i=0; i<nums.length; i++) {
            //int sum = maxEndingHere + nums[i];
            //maxEndingHere = (sum > 0) ? sum : 0;

            maxEndingHere = Math.max(maxEndingHere+nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    /**
     * Approach 2: Divide and Conquer
     *  CLRS approach
     *
     * Links:
     *  https://leetcode.com/problems/maximum-subarray/discuss/20372/How-to-solve-%22Maximum-Subarray%22-by-using-the-divide-and-conquer-approach
     *
     * Complexity:
     *  Time is O(n log n)
     */
    public int Subarray(int[] A,int left, int right){
        if(left == right){return A[left];}
        int mid = left + (right - left) / 2;
        int leftSum = Subarray(A,left,mid);// left part
        int rightSum = Subarray(A,mid+1,right);//right part
        int crossSum = crossSubarray(A,left,right);// cross part
        if(leftSum >= rightSum && leftSum >= crossSum){// left part is max
            return leftSum;
        }
        if(rightSum >= leftSum && rightSum >= crossSum){// right part is max
            return rightSum;
        }
        return crossSum; // cross part is max
    }
    public int crossSubarray(int[] nums,int left,int right){
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int sum = 0;
        int mid = left + (right - left) / 2;
        for(int i = mid; i >= left ; i--){
            sum = sum + nums[i];
            if(leftSum < sum){
                leftSum = sum;
            }
        }
        sum = 0;
        for(int j = mid + 1; j <= right; j++){
            sum = sum + nums[j];
            if(rightSum < sum){
                rightSum = sum;
            }
        }
        return leftSum + rightSum;
    }

    /**
     * Approach 3: Divide and Conquer
     *
     * Links:
     *
     * Complexity:
     *
     */
    int maxSubArray(int nums[], int n) {
        if(n == 0)
            return 0;
        else if(n == 1)
            return nums[0];

        int[] maxSub = {Integer.MIN_VALUE};
        int[] leftPrefix = {Integer.MIN_VALUE}, rightSuffix = {Integer.MIN_VALUE}, all = {Integer.MIN_VALUE};
        maxSubArrayHelper(nums, 0, n - 1, leftPrefix, rightSuffix, all, maxSub);
        return maxSub[0];
    }

    private void maxSubArrayHelper(int A[], int left, int right, int[] leftPrefix, int[] rightSuffix, int[] all, int[] maxSub)  {
        if(left == right) {
            leftPrefix[0] = A[left];
            rightSuffix[0] = A[left];
            all[0] = A[left];
            maxSub[0] = Math.max(maxSub[0], A[left]);
            return;
        }

        int middle = (left + right) / 2;
        int[] leftPrefix1={0}, rightSuffix1={0}, all1={0};
        int[] leftPrefix2={0}, rightSuffix2={0}, all2={0};
        maxSubArrayHelper(A, left, middle, leftPrefix1, rightSuffix1, all1, maxSub);
        maxSubArrayHelper(A, middle + 1, right, leftPrefix2, rightSuffix2, all2, maxSub);

        // Use the returned leftPrefix, rightSuffix instead of walling through the elements between left and right
        leftPrefix[0] = Math.max(all1[0], all1[0] + leftPrefix2[0]);
        leftPrefix[0] = Math.max(leftPrefix[0], leftPrefix1[0]);
        rightSuffix[0] = Math.max(all2[0], all2[0] + rightSuffix1[0]);
        rightSuffix[0] = Math.max(rightSuffix[0], rightSuffix2[0]);
        all[0] = all1[0] + all2[0];

        maxSub[0] = Math.max(leftPrefix[0], maxSub[0]);
        maxSub[0] = Math.max(rightSuffix[0], maxSub[0]);
        maxSub[0] = Math.max(rightSuffix1[0], maxSub[0]);
        maxSub[0] = Math.max(leftPrefix2[0], maxSub[0]);
        maxSub[0] = Math.max(rightSuffix1[0] + leftPrefix2[0], maxSub[0]);
    }
}
