package string;


import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Difficulty Level: HARD
 *
 * Problem:
 *   Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s)
 *   from beginWord to endWord, such that:
 *   Only one letter can be changed at a time
 *   Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 *   For example, Given:
 *   beginWord = "hit"
 *   endWord = "cog"
 *   wordList = ["hot","dot","dog","lot","log","cog"]
 *   Return
 *   [
 *      ["hit","hot","dot","dog","cog"],
 *      ["hit","hot","lot","log","cog"]
 *   ]
 *
 *   Note:
 *   Return an empty list if there is no such transformation sequence.
 *   All words have the same length.
 *   All words contain only lowercase alphabetic characters.
 *   You may assume no duplicates in the word list.
 *   You may assume beginWord and endWord are non-empty and are not the same.
 *
 * Links:
 *  https://leetcode.com/problems/word-ladder-ii/description/
 *  https://www.programcreek.com/2014/06/leetcode-word-ladder-ii-java/
 */
public class q04_WordLadder2 {
    public static void main(String[] args) {
        q04_WordLadder2 wl = new q04_WordLadder2();
        Set<String> dict = new HashSet<String>(){{add("hot");add("dot");add("dog");add("lot");add("log");add("cog");}};
        System.out.println(wl.findLadders("hit", "cog", dict));
    }

    /**
     * Approach1:
     *  The solution contains two steps
     *  1 Use BFS to construct a graph.
     *  2. Use DFS to construct the paths from end to start
     *  The first step BFS is quite important. I summarized three tricks
     *      Use a SET to store the words visited in current ladder, and when the current ladder was completed,
     *      delete the visited words from unvisited. That's why I have two similar solutions.
     *      Use Character iteration to find all possible paths.
     *      Do not compare one word to all the other words and check if they only differ by one character.
     *      One word is allowed to be inserted into the queue only ONCE
     *
     * Links:
     *  https://discuss.leetcode.com/topic/2857/share-two-similar-java-solution-that-accpted-by-oj
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> results = new ArrayList<List<String>>();
        if (dict.size() == 0)
            return results;

        int currLevelNodes = 1, nextLevelNodes = 0;
        boolean found = false;
        Map<String,List<String>> map = new HashMap<>();

        Deque<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>(), unvisited = new HashSet<>(dict);

        queue.add(start);
        unvisited.remove(start);
        unvisited.add(end);

        //BFS - Since we are going level by level, when we get to end for the first time, we break at that LEVEL - since those are the shortest path
        while (!queue.isEmpty()) {
            String word = queue.poll();
            char[] wordArr = word.toCharArray();
            currLevelNodes--;

            for(int i=0; i<wordArr.length; i++) {  //Go through each char in the word
                for(char currAlphabet = 'a'; currAlphabet <= 'z'; currAlphabet++) { //Replace each position of the word with a new alphabet
                    char save = wordArr[i];
                    if(wordArr[i] != currAlphabet)
                        wordArr[i] = currAlphabet;

                    String newWord = new String(wordArr);
                    if (unvisited.contains(newWord)) {
                        //Avoid adding duplicate entries to the queue
                        if (visited.add(newWord)) { //returns true only if newWord is not in visited set
                            nextLevelNodes++;
                            queue.add(newWord);
                        }

                        //Build adjacency list for the new word
                        map.computeIfAbsent(newWord, (key) -> new ArrayList<>()).add(word);

                        if(newWord.equals(end) && !found) found = true;
                    }
                    wordArr[i] = save;
                } //End:Iteration from 'a' to 'z'
            } //End:Iteration from the first to the last

            if (currLevelNodes == 0) {
                //Once we find the end node, we are at the shortest path (since we are going level by level)
                //and any other shortest path should be from this level only
                if (found == true) break;

                //If not, continue the while loop until we find the end string
                currLevelNodes = nextLevelNodes;
                nextLevelNodes = 0;
                unvisited.removeAll(visited);
                visited.clear(); //Since we clear visited here, the prev step of removeAll makes sense. It removes only the nodes visited at each level
            }

        } //End While


        //DFS to find the path
        backtrace(end, start, new LinkedList<>(), results, map);
        return results;
    }

    private void backtrace(String currWord, String start, List<String> current, List<List<String>> results, Map<String,List<String>> map) {
        if(currWord.equals(start)) {
            current.add(0, start);
            results.add(new LinkedList<>(current));
            current.remove(0);
            return;
        }

        current.add(0, currWord);
        if (map.get(currWord) != null) {
            for(String s : map.get(currWord))
                backtrace(s, start, current, results, map);
        }
        current.remove(0);
    }
}
