package combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Problem:
 *  Print all combinations of an array - since it is combination, AB is same as BA
 *  There can be repetition of values in the array
 *
 * Variants:
 *  all possible combinations of a string
 *  all combinations of a string
 *  print all combinations of an array
 *  print all possible combinations of a string
 *
 *
 * Links:
 *  Solution 1 - https://www.youtube.com/watch?v=p8SDPaX1wgw
 *  Solution 2 - https://github.com/mission-peace/interview/blob/master/src/com/interview/recursion/Combination.java
 *  Solution 3 - https://github.com/mission-peace/interview/blob/master/src/com/interview/recursion/Combination.java
 *
 * Complexity:
 *  Time complexity is O(2^n) and Space complexity is O(n)
 */
public class CombinationsOfArray {

    public static void main(String[] args) {
        int[] input = { 1, 1, 2, 3 };
        Arrays.sort(input); //Sort to handle duplicate elements in array

        CombinationsOfArray a = new CombinationsOfArray();

        boolean[] isSelected = new boolean[input.length];
        a.combinationsRec(input, 0, isSelected);
        System.out.println();

        List<List<Integer>> results = new ArrayList<>();
        a.combinationsEasy(input, 0, new ArrayList<Integer>(), results);
        results.forEach(System.out::println);
        System.out.println();

        results = new ArrayList<>();
        int[] numbers = new int[] {1, 2, 3};
        int[] count = new int[] {2,1,1};
        int[] output = new int[input.length];
        a.combinations3(numbers, count, 0, new ArrayList<>(), results);
        results.forEach(System.out::println);
    }

    /**
     * Method 2 - Include and Exclude every element
     * This method is mainly based on Pascal’s Identity, i.e. ncr = n-1cr + n-1cr-1
     * Use the logic of bit representation of array index
     *   First combination is 000 which is empty set, next is 001 which is 3, third is 010 which is 2,
     *   fourth is 011 which is 23, fifth is 100 which is 1 and so on and last is 111 which is 123
     *   isSelected is the tracking array which tracks which array elements have to be printed
     */
    /** Alternate base condition
     *        if (start == input.length - 1) {
     *            //We've reached the last element. Don't print array element (for conditions like 000, 010, 100, 110)
     *            isSelected[start] = false;
     *            printArray(input, isSelected);
     *            //And print array element (for conditions like 001, 011, 101, 111)
     *            isSelected[start] = true;
     *            printArray(input, isSelected);
     *            return;
     *        }
     */
    private void combinationsRec(int[] input, int start, boolean[] isSelected) {
        //Base condition
        if (start == input.length) {
            //We've crossed the last element
            printArray(input, isSelected);
            return;
        }

        //When we encounter a char, we first not print it and go further - for cases like 000..
        isSelected[start] = false;
        combinationsRec(input, start+1, isSelected);

        //In the second case, we'll print it and go further - for cases like 100 ...
        //Below condition handles duplicate values in the array - don't select the same no twice for the same index
        if(start >0 && isSelected[start-1]==false && input[start-1] == input[start]) return;

        isSelected[start] = true;
        combinationsRec(input, start+1, isSelected);
    }

    /**
     * Method 1 - Fix Elements and Recur
     * Prints the elements in the lexical order
     */
    private void combinationsEasy(int[] input, int start, List<Integer> selected, List<List<Integer>> results) {

        results.add(new ArrayList<>(selected)); //Initial results will have empty list which is also a combination

        for (int i = start; i < input.length; i++) {
            //Below condition handles duplicate values in the array - don't select the same no twice for the same index
            if (i != start && input[i] == input[i-1]) continue;

            selected.add(input[i]);
            combinationsEasy(input, i + 1, selected, results);
            selected.remove(selected.size() - 1);
        }
    }

    /**
     * Prints the elements in the lexical order
     */
    private void combinations3(int[] input, int[] count, int start, List<Integer> selected, List<List<Integer>> results){
        results.add(new ArrayList<>(selected));

        for(int i=start; i < input.length; i++){
            if (count[i] == 0) {
                continue;
            }
            selected.add(input[i]);
            count[i]--;
            combinations3(input, count, i, selected, results);
            count[i]++;
            selected.remove(selected.size()-1);
        }
    }

//    def combinations(solution, tempSolution, posibilities, size):
//            if size == 0:
//            solution.append(tempSolution)
//            return
//
//            for i in range(len(posibilities)):
//    combinations(solution, tempSolution + [posibilities[i]], posibilities[i+1:], size-1)
//
//    def main():
//    numbers = [5, 8, 12, 3]
//    solution = []
//
//            for i in range(1, len(numbers)+1):
//    combinations(solution, [], numbers, i)
//
//    print(numbers)
//    print(solution)
//    [5, 8, 12, 3]
//    [[5], [8], [12], [3], [5, 8], [5, 12], [5, 3], [8, 12], [8, 3], [12, 3], [5, 8, 12], [5, 8, 3], [5, 12, 3], [8, 12, 3], [5, 8, 12, 3]]

    public void printArray(int[] source, boolean[] isSelected) {
        boolean isNULL = true;
        System.out.print("{");
        for (int i = 0; i < source.length; i++) {
            if (isSelected[i] == true) {
                System.out.print(source[i] + "");
                isNULL = false;
            }
        }
        if (isNULL == false) {
            System.out.print("}");
            System.out.print("  ");
        }

        if (isNULL) {
            System.out.print("Empty");
            System.out.print("} ");
        }

        System.out.println();
    }
}
