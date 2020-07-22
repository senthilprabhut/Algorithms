package t03_trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Trie:
 *  Great for Word VALIDATION problems
 *  In a list of strings, find all celebrity names
 *  Autocomplete - maintain state in trie or return the node to the caller
 *
 *
 * Links:
 *  https://www.youtube.com/watch?v=AXjmTQ8LEoI
 *  https://www.youtube.com/watch?v=vlYZb68kAY0
 *  https://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/
 *
 * Complexity:
 *  If average length of the word is l and m words, time complexity is O(l * m)
 */
public class Trie {
    class TrieNode {
        private Map<Character,TrieNode> children;
        private boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    };
    private TrieNode rootNode;

    public Trie() {
        rootNode = new TrieNode();
    }

    /* Iterative implementation of Trie */
    public void add(String word) {
        TrieNode currentTrieNode = rootNode;
        for(int i=0; i<word.length(); i++) {
            Character currentChar = word.charAt(i);
            TrieNode node = currentTrieNode.children.get(currentChar);
            if(node == null) {
                node = new TrieNode();
                currentTrieNode.children.put(currentChar,node);
            }
            currentTrieNode = node;
        }
        currentTrieNode.isEndOfWord = true; //Set true if we reached end of word
    }

    /* Recursive implementation of Trie */
    public void addRec(String word, int index, TrieNode currentNode) {
        if(index == word.length()) {
            currentNode.isEndOfWord = true;
            return;
        }
        Character currentChar = word.charAt(index);
        TrieNode node = currentNode.children.get(currentChar);
        if(node == null) {
            node = new TrieNode();
            currentNode.children.put(currentChar,node);
        }
        addRec(word,index+1,node);
    }

    /* Iterative implementation of search */
    public boolean search(String word) {
        TrieNode current = rootNode;
        for(int i=0; i<word.length(); i++) {
           char ch = word.charAt(i);
           TrieNode node = current.children.get(ch);
           if(node==null) return false;
           current = node;
        }
        return current.isEndOfWord;
    }

    /* Recursive implementation of search */
    public boolean searchRec(String word, int index, TrieNode current) {
        if (index == word.length()) return current.isEndOfWord;

         char ch = word.charAt(index);
         TrieNode node = current.children.get(ch);
         if(node==null) return false;
         return searchRec(word, index+1, node);
    }

    public void delete(String word) {
        delete(word,0,rootNode);
    }

    private boolean delete(String word, int index, TrieNode current) {
        if(index == word.length()) {
            //When end of the word is reached, delete if it is a whole world (isEndOfWord=true)
            return current.isEndOfWord;
        }
        char ch = word.charAt(index);
        TrieNode n = current.children.get(ch);
        if(n == null) return false;

        boolean shouldDelete = delete(word,index+1, n);

        //if true, then delete the mapping of character and trienode reference from map
        if(shouldDelete) {
            current.children.remove(ch);
            //return true if no mapping are left in the map
            //Don't delete intermediate chars if other words use them
            return current.children.size() == 0;
        }
        return false;
    }

//    public int findCount(String word, int index, TrieNode current){
//        if(index == word.length()) return current.size;
//
//        char ch = word.charAt(index);
//        TrieNode child = current.children.get(ch);
//        if(child == null) return 0;
//
//        return findCount(word,index+1,child);
//    }
}
