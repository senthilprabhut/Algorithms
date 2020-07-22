package string;

import java.util.LinkedList;
import java.util.List;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *  Given an array of words and a length L, format the text such that each line has exactly L characters and
 *  is fully (left and right) justified.
 *  You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 *  Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 *  Extra spaces between words should be distributed as evenly as possible.
 *  If the number of spaces on a line do not divide evenly between words,
 *  the empty slots on the left will be assigned more spaces than the slots on the right.
 *  For the last line of text, it should be left justified and no extra space is inserted between words.
 *  For example, words: ["This", "is", "an", "example", "of", "text", "justification."]  L: 16.
 *  Return the formatted lines as:
 *  [
 *      "This    is    an",
 *      "example  of text",
 *      "justification.  "
 *  ]
 *  Note: Each word is guaranteed not to exceed L in length.
 *  Corner Cases:
 *      A line other than the last line might contain only one word. What should you do in this case?
 *      In this case, that line should be left-justified.
 *
 * Links:
 *  https://leetcode.com/problems/text-justification/description/
 *  https://www.programcreek.com/2014/05/leetcode-text-justification-java/
 */
public class q68_TextJustification {
    public static void main(String[] args) {
        q68_TextJustification tj = new q68_TextJustification();
        String[] arr = new String[] {"This", "is", "an", "example", "of", "text", "justification."};
        System.out.println(tj.fullJustify(arr,16));
    }

    /**
     * Approach:
     *  For each line, I first figure out which words can fit in. According to the code, these words are words[i] through words[i+k-1].
     *  Then spaces are added between the words.
     *  The trick here is to use mod operation to manage the spaces that can’t be evenly distrubuted:
     *  the first (L-l) % (k-1) gaps acquire an additional space.
     *
     * Links:
     *  https://leetcode.com/problems/text-justification/discuss/24873/Share-my-concise-c++-solution-less-than-20-lines - comment jayesch
     *
     * Complexity:
     *
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new LinkedList<>();

        for (int i=0, w=0; i < words.length; i = w) {
            int len=-1; //We need to skip the space for last word hence start len = -1

            //Find no of words that can be packed in a row
            for (w=i; w<words.length && len+words[w].length()+1 < maxWidth; w++) {
                len += words[w].length() + 1;
            }

            //calculate the number of extra spaces that can be equally distributed also calculate number of extra
            // spaces that need to be added to first few words till we fill the line width
            //For example line width is 20 we have three words of 3 4 2 4 length
            //[our_,life_,is_,good_,_,_,_,_,] ==> [our_,_,_,life_,_,_is_,_,good]
            //Note _, indicates space
            //Count number of empty spaces at end of line:= width-len = 20-(15) = 5
            //These five spaces need to be equally distributed between 4-1 = 3 gaps
            //n words will have n-1 gaps between them
            // 5 / 3 = 1 extra space between each word (in addition to default 1 space,
            //                                          total space count = 2)
            // 5 % 3 = 2 extra spaces between first three words as shown above
            int evenlyDistributedSpaces = 1, extraSpaces=0;
            int numOfGapsBwWords = w-i -1; //w is already pointing to next index and -1 since n words have n-1 gaps between them


            //we don't need to do this computation if we reached the last word of array or there is only one word
            // that can be accommodate on the line then we don't need to do any justify text
            if (w != i+1 || w != words.length) {
                evenlyDistributedSpaces = ((maxWidth-len) / numOfGapsBwWords) + 1;
                extraSpaces = (maxWidth-len)%numOfGapsBwWords;
            }

            StringBuilder sb = new StringBuilder(words[i]);
            for (int j=i+1; j<w; j++) {
                for (int k=0; k<evenlyDistributedSpaces; k++)
                    sb.append(' ');

                if (extraSpaces > 0) {
                    sb.append(' ');
                    extraSpaces--;
                }

                sb.append(words[j]);
            }

            //Handle the above two cases we skipped, where there is only one word on line
            //or we reached end of word array. Last line should remain left aligned.
            int remaining = maxWidth-sb.length();
            while(remaining > 0) {
                sb.append(' ');
                remaining--;
            }
            list.add(sb.toString());
        }
        return list;
    }
}
