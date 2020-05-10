package algorithnms.trie;

import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

/***
 * Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.

    If there is no answer, return the empty string.
    Example 1:
    Input: 
    words = ["w","wo","wor","worl", "world"]
    Output: "world"
    Explanation: 
    The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
    Example 2:
    Input: 
    words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
    Output: "apple"
    Explanation: 
    Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
    Note:
    
    All the strings in the input will only contain lowercase letters.
    The length of words will be in the range [1, 1000].
    The length of words[i] will be in the range [1, 30].
 * @author miche
 *
 */
public class LongestWordinDictionary {

    public String longestWord2(String[] words) {
        String ans = "";
        Set<String> dicts = new HashSet<String>();
        TreeMap<Integer, PriorityQueue<String>> trie = new TreeMap<Integer, PriorityQueue<String>>();
        for(String word : words) {
            int len = word.length();
            dicts.add(word);
            if(!trie.containsKey(len)) {
                trie.put(len, new PriorityQueue<String>());
            }
            trie.get(len).offer(word);
        }
        
        boolean isfind = false;
        while(!trie.isEmpty()) {
            Integer lastKey = trie.lastKey();
            PriorityQueue<String> maxLenWords = trie.remove(lastKey);
            while(!maxLenWords.isEmpty()) {
                String word = maxLenWords.poll();
                //if(word.length() > ans.length() ||  word.length() == ans.length() && word.compareTo(ans) < 0) {
                    isfind = true;
                    for(int i = 1; i < word.length(); i++) {
                        if(!dicts.contains(word.substring(0, i))) {
                            isfind = false;
                            break;
                        }
                    }
                    if(isfind) {
                        ans = word;
                        return ans;
                    }
                //}
            }
        }

        return ans;
    }

    private int R = 26;
    public String longestWord(String[] words) {
        TrieNoder root = new TrieNoder();
        for(String word : words) {
            insertWord(word, root);
        }
        return findTheWord(root);
    }

    public void insertWord(String word, TrieNoder root) {
        TrieNoder node = root;
        for(char ch : word.toCharArray()) {
            if(node.links[ch-'a'] == null) {
                node.links[ch-'a'] = new TrieNoder();
            }
            node = node.links[ch-'a'];
        }
        node.word = word;
    }

    public String findTheWord(TrieNoder root) {
        String ans = (root.word == null) ? "" : root.word;

        for(TrieNoder node : root.links) {
            if(node == null || node.word == null) {
                continue;
            }
            String str = findTheWord(node);
            if(str.length() > ans.length()) {
                ans = str;
            }
        }
        
        return ans;
    }

    class TrieNoder {
        String word;
        TrieNoder[] links;

        TrieNoder() {
            links = new TrieNoder[R];
        }
    }
}
