package algorithms.struct;

import java.util.ArrayList;
import java.util.List;

import algorithms.trie.TrieNode;

/***
 * Prefix and Suffix Search
 * Design a special dictionary with some words that searchs the words in it by a prefix and a suffix.

    Implement the WordFilter class:
    
    WordFilter(string[] words) Initializes the object with the words in the dictionary.
    f(string prefix, string suffix) Returns the index of the word in the dictionary, which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
     
    
    Example 1:
    
    Input
    ["WordFilter", "f"]
    [[["apple"]], ["a", "e"]]
    Output
    [null, 0]
    
    Explanation
    WordFilter wordFilter = new WordFilter(["apple"]);
    wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
     
    
    Constraints:
    
    1 <= words.length <= 15000
    1 <= words[i].length <= 10
    1 <= prefix.length, suffix.length <= 10
    words[i], prefix and suffix consist of lower-case English letters only.
    At most 15000 calls will be made to the function f.
 * @author miche
 *
 */
public class WordFilter {
    //For another idea, see the Python algorithm
    Trie root;
    public WordFilter(String[] words) {
        root = new Trie();
        for(int i = 0; i < words.length; i++) {
            char[] wordchars = words[i].toCharArray();
            insertChars(wordchars, i);
        }
    }
    
    public int f(String prefix, String suffix) {
        Trie node = root;
        char[] pres = prefix.toCharArray(), sufs = suffix.toCharArray();
        for(char ch : pres) {
            if(node.containsKey(ch)) {
                node = node.get(ch);
            } else {
                return -1;
            }
        }
        node = node.links[26]; //the hyphen
        if(node == null) return -1;
        for(int j = sufs.length-1; j >= 0; j--) {
            if(node.containsKey(sufs[j])) {
                node = node.get(sufs[j]);
            } else {
                return -1;
            }
        }
        return node.idx;
    }

    private void insertChars(char[] chars, int idx) {
        //store all prefix + hyphen(such as '.' '+' '-' 'X' etc) + suffix
        Trie node = root;
        for(int i = 0; i < chars.length; i++) {
            if(!node.containsKey(chars[i])) {
                node.put(chars[i], new Trie());
            }
            node = node.get(chars[i]);
            Trie sufnode = node;
            if(sufnode.links[26] == null) {
                sufnode.links[26] = new Trie(); //the hyphen
            }
            sufnode = sufnode.links[26]; //the hyphen
            for(int j = chars.length-1; j >= 0; j--) {
                //store all suffix
                if(!sufnode.containsKey(chars[j])) {
                    sufnode.put(chars[j], new Trie());
                }
                sufnode = sufnode.get(chars[j]);
                sufnode.idx = idx;
            }
        }
    }
}

class Trie {
    Trie[] links;
    int idx;
    
    public Trie() {
        links = new Trie[27]; //the 27th element is hyphen
        idx = -1;
    }
    public boolean containsKey(char ch) {
        return links[ch -'a'] != null;
    }
    public Trie get(char ch) {
        return links[ch -'a'];
    }
    public void put(char ch, Trie node) {
        links[ch -'a'] = node;
    }
}
