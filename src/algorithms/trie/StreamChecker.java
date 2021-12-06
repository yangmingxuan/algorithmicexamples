package algorithms.trie;

import java.util.ArrayList;
import java.util.List;

/***
 * Stream of Characters
 * Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings words.

    For example, if words = ["abc", "xyz"] and the stream added the four characters (one by one) 'a', 'x', 'y', and 'z', your algorithm should detect that the suffix "xyz" of the characters "axyz" matches "xyz" from words.
    
    Implement the StreamChecker class:
    
    StreamChecker(String[] words) Initializes the object with the strings array words.
    boolean query(char letter) Accepts a new character from the stream and returns true if any non-empty suffix from the stream forms a word that is in words.
     
    
    Example 1:
    
    Input
    ["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
    [[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
    Output
    [null, false, false, false, true, false, true, false, false, false, false, false, true]
    
    Explanation
    StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
    streamChecker.query("a"); // return False
    streamChecker.query("b"); // return False
    streamChecker.query("c"); // return False
    streamChecker.query("d"); // return True, because 'cd' is in the wordlist
    streamChecker.query("e"); // return False
    streamChecker.query("f"); // return True, because 'f' is in the wordlist
    streamChecker.query("g"); // return False
    streamChecker.query("h"); // return False
    streamChecker.query("i"); // return False
    streamChecker.query("j"); // return False
    streamChecker.query("k"); // return False
    streamChecker.query("l"); // return True, because 'kl' is in the wordlist
     
    
    Constraints:
    
    1 <= words.length <= 2000
    1 <= words[i].length <= 2000
    words[i] consists of lowercase English letters.
    letter is a lowercase English letter.
    At most 4 * 10^4 calls will be made to query.
 * @author miche
 *
 */
public class StreamChecker {

    private TrieNode root;
    private List<Character> querystream = new ArrayList<Character>();
    public StreamChecker(String[] words) {
        root = new TrieNode();
        for(String word : words) {
            //reverse the word and record
            insert(new StringBuffer(word).reverse().toString());
        }
    }
    
    public boolean query(char letter) {
        querystream.add(letter);
        int n = querystream.size();
        TrieNode node = root;
        for(int i = n-1; i >= 0; i--) {
            char ch = querystream.get(i);
            if(!node.containsKey(ch)) {
                return false;
            } else {
                node = node.get(ch);
            }
            if(node.isEnd()) {
                return true;
            }
        }
        return false;
    }

    /** Inserts a word into the trie. */
    private void insert(String word) {
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

}
