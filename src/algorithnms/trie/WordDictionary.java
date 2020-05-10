package algorithnms.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Design a data structure that supports the following two operations:

    void addWord(word)
    bool search(word)
    search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
    
    Example:
    
    addWord("bad")
    addWord("dad")
    addWord("mad")
    search("pad") -> false
    search("bad") -> true
    search(".ad") -> true
    search("b..") -> true
    Note:
    You may assume that all words are consist of lowercase letters a-z.
 * @author miche
 *
 */
public class WordDictionary {

    private TrieNode root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    public boolean dfs(String word, int idx, TrieNode node) {
        if(idx == word.length()) {
            return node.isEnd();
        }
        if(word.charAt(idx) == '.') {
            for(char ch = 'a'; ch <= 'z'; ch++) {
                TrieNode next = node.get(ch);
                if(next != null && dfs(word, idx+1, next)) {
                    return true;
                }
            }
        } else {
            TrieNode next = node.get(word.charAt(idx));
            if(next != null && dfs(word, idx+1, next)) {
                return true;
            }
        }
        return false;
    }
}

class WordDictionary2 {
    private Map<Integer, List<String>> dictionary;
    
    public WordDictionary2() {
        dictionary = new HashMap<Integer, List<String>>();
    }
    
    public void addWord(String word) {
        int len = word.length();
        if(!dictionary.containsKey(len)) {
            dictionary.put(len, new ArrayList<String>());
        }
        dictionary.get(len).add(word);
    }
    
    public boolean search(String word) {
        int len = word.length();
        if(!dictionary.containsKey(len)) {
            return false;
        }
        for(String words : dictionary.get(len)) {
            if(isSameWord(words, word)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isSameWord(String word1, String word2) {
        //if len1 != len2 return false
        for(int i = 0; i < word1.length(); i++) {
            if(word2.charAt(i) != '.' && word1.charAt(i) != word2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}