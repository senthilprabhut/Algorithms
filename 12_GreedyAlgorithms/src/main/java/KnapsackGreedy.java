import java.util.Arrays;
import java.util.Comparator;

/**
 * Problem:
 *  Given weights and values of n items, put these items in a knapsack of capacity W
 *  to get the maximum total value in the knapsack.
 *
 * Approach:
 *  GreedyKnapsack(Profit[] P, Weight[] W, MaxWt m)
 *      for i = 1 to n
 *          compute Pi/Wi;  O(n)
 *      sort objects in non-increasing order; O(n log n)
 *      for i = 1 to n in sorted list     O(n)
 *          if (m>0 && Wi<=m)
 *              m=m-Wi
 *              Total=Total + Pi
 *          else
 *              break;
 *      if(m>0)
 *          Total = Total + m(Pi/Wi)  O(1)
 *
 * Links:
 *  Ravula Greedy knap sack algorithm
 *
 *
 * Complexity:
 *  Time Complexity: O(n + n log n) = O(n log n). This is the same if you sort array or use Max heap for sorted ratios
 *  Space Complexity: O(n) for the ratio array
 */
public class KnapsackGreedy {
    public static void main(String[] args) {
        int[] wt = {2, 3, 5, 7, 1, 4 ,1};
        int[] values = {10, 5, 15, 7, 6, 18, 3};
        KnapsackGreedy kg = new KnapsackGreedy();
        System.out.println(kg.knapsackGreedy(wt, values, 15));
        //55.33
    }

    public double knapsackGreedy(int[] wt, int[] values, int maxWt) {
        double[] ratio = new double[wt.length];
        Integer[] indexArr = new Integer[wt.length];

        //Compute Ratio - O(n)
        for (int i=0; i<wt.length; i++) {
            ratio[i] = (double)values[i] / wt[i];
            indexArr[i] = i;
        }

        //Sort Index array based on Ratio array in descending order O(n log n)
        ArrayIndexComparator comparator = new ArrayIndexComparator(ratio);
        Arrays.sort(indexArr, comparator.reversed());

        //Choose a weight and add the value from it and reduce the wt from maxWt
        double totalValue = 0;
        int i = 0;
        for(i=0; i<indexArr.length; i++){
            int sortIdx = indexArr[i];
            if(maxWt>0 && wt[sortIdx]<=maxWt) {
                maxWt=maxWt-wt[sortIdx];
                totalValue += values[sortIdx];
            } else
                break;
        }

        //Add fraction if there is remaining weight
        if(maxWt > 0) {
            int sortIdx = indexArr[i];
            totalValue += maxWt * ratio[sortIdx];
        }
        return totalValue;
    }

    private class ArrayIndexComparator implements Comparator<Integer> {
        private double[] fractionArray;

        public ArrayIndexComparator(double[] array) {
            fractionArray = array;
        }

        public int compare(Integer a, Integer b) {
            return Double.compare(fractionArray[a], fractionArray[b]);
        }
    }
}
