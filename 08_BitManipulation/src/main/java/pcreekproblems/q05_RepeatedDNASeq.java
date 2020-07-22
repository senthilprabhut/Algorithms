package pcreekproblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Problem:
 *   All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
 *   When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 *   Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 *   For example, given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT", return: ["AAAAACCCCC", "CCCCCAAAAA"]
 *
 * Links:
 *  https://leetcode.com/problems/repeated-dna-sequences/description/
 *  https://www.programcreek.com/2014/03/leetcode-repeated-dna-sequences-java/
 *  https://discuss.leetcode.com/topic/27517/7-lines-simple-java-o-n
 */
public class q05_RepeatedDNASeq {
    public static void main(String[] args) {
        q05_RepeatedDNASeq rd = new q05_RepeatedDNASeq();
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        System.out.println(rd.findRepeatedDnaSequences1(s)); //[AAAAACCCCC, CCCCCAAAAA]
        System.out.println(rd.findRepeatedDnaSequences2(s));
    }

    /**
     * Approach 1:
     *  hashmap + bit manipulation
     *  The key to solve this problem is that each of the 4 nucleotides can be stored in 2 bits.
     *  So the 10-letter-long sequence can be converted to 20-bits-long integer.
     */
    public List<String> findRepeatedDnaSequences1(String s) {
        List<String> result = new ArrayList<>();
        int len = s.length();
        if (len < 10) return result;

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);

        Set<Integer> seen = new HashSet<Integer>();
        Set<Integer> added = new HashSet<Integer>();

        int hash = 0;
        for (int i=0; i<s.length(); i++) {
            if (i < 9) {
                //each ACGT fit 2 bits, so left shift by 2
                hash = (hash << 2) + map.get(s.charAt(i));
            } else {
                hash = (hash << 2) + map.get(s.charAt(i));
                //make length of hash to be 20
                hash = hash & (1 << 20) - 1;

                if (seen.contains(hash) && !added.contains(hash)) {
                    added.add(hash);
                    result.add(s.substring(i-9,i+1));
                } else {
                    seen.add(hash);
                }
            }
        }
        return result;
    }


    /**
     * Approach 2:
     *  Regular substring approach
     */
    public List<String> findRepeatedDnaSequences2(String s) {
        Set seen = new HashSet(), repeated = new HashSet();
        for(int i=0; (i+9) < s.length(); i++) {
            String ten = s.substring(i, i+10);
            if(! seen.add(ten))
                repeated.add(ten);
        }
        return new ArrayList<>(repeated);
    }
}
