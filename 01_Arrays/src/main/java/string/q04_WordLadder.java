package string;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *  Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 *  Only one letter can be changed at a time.
 *  Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 *
 *  For example,
 *  Given:
 *  beginWord = "hit"
 *  endWord = "cog"
 *  wordList = ["hot","dot","dog","lot","log","cog"]
 *
 *  As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *  return its length 5.
 *
 *  Note:
 *  Return 0 if there is no such transformation sequence.
 *  All words have the same length.
 *  All words contain only lowercase alphabetic characters.
 *  You may assume no duplicates in the word list.
 *  You may assume beginWord and endWord are non-empty and are not the same.
 *
 * Links:
 *  https://leetcode.com/problems/word-ladder/description/
 *  https://www.programcreek.com/2012/12/leetcode-word-ladder/
 */
public class q04_WordLadder {
    public static void main(String[] args) {
        q04_WordLadder wl =new q04_WordLadder();
        Set<String> dict = new HashSet<String>(){ {add("hot"); add("dot"); add("dog"); add("lot"); add("log"); add("cog");} };
        System.out.println(wl.ladderLength1("hit", "cog", dict));
    }

    /**
     * Approach1:
     *  Breadth first search approach
     *
     * Link:
     *  https://www.programcreek.com/2012/12/leetcode-word-ladder/
     */
    public int ladderLength1(String beginWord, String endWord, Set<String> wordDict) {
        LinkedList<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, 1));

        wordDict.add(endWord); //Add the endWord to the dictionary if it is not present

        while (!queue.isEmpty()) {
            WordNode current = queue.remove();
            String word = current.word;

            if(word.equals(endWord)) return current.numSteps;

            //Try and replace every character in the word and check if the new word is present in the dictionary
            char[] wordArr = word.toCharArray();
            for(int i=0; i<wordArr.length; i++) {
                for (char currAlphabet = 'a'; currAlphabet <= 'z'; currAlphabet++) {
                    char save = wordArr[i];
                    if(wordArr[i] != currAlphabet) wordArr[i] = currAlphabet;

                    String newWord = new String(wordArr);
                    if (wordDict.contains(newWord)) {
                        wordDict.remove(newWord); //Remove the word so that it is not visited again
                        queue.add(new WordNode(newWord, current.numSteps+1));
                    }

                    wordArr[i] = save; //Restore old value if the current char didn't result in the answer
                }
            }
        }
        return 0;
    }

    /**
     * Approach2:
     *  traverse the path simultaneously from start node and end node, and merge in the middle
     *  the speed will increase (logN/2)^2 times compared with one-end method
     *
     * Link:
     *  https://discuss.leetcode.com/topic/29303/two-end-bfs-in-java-31ms
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();

        int len = 1, strLen = beginWord.length();
        HashSet<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {

            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }
            beginSet = temp;
            len++;
        }
        return 0;
    }
}

class WordNode{
    String word;
    int numSteps;

    public WordNode(String word, int numSteps){
        this.word = word;
        this.numSteps = numSteps;
    }
}