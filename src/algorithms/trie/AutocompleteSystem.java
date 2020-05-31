package algorithms.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


/***
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

    The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
    The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
    If less than 3 hot sentences exist, then just return as many as you can.
    When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
    Your job is to implement the following functions:
    
    The constructor function:
    
    AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.
    
    Now, the user wants to input a new sentence. The following function will provide the next character the user types:
    
    List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
    
     
    Example:
    Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
    The system have already tracked down the following sentences and their corresponding times:
    "i love you" : 5 times
    "island" : 3 times
    "ironman" : 2 times
    "i love leetcode" : 2 times
    Now, the user begins another search:
    
    Operation: input('i')
    Output: ["i love you", "island","i love leetcode"]
    Explanation:
    There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
    
    Operation: input(' ')
    Output: ["i love you","i love leetcode"]
    Explanation:
    There are only two sentences that have prefix "i ".
    
    Operation: input('a')
    Output: []
    Explanation:
    There are no sentences that have prefix "i a".
    
    Operation: input('#')
    Output: []
    Explanation:
    The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
    
     
    Note:
    
    The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
    The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
    Please use double-quote instead of single-quote when you write test cases even for a character input.
    Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. Please see here for more details.
 * @author miche
 *
 */
public class AutocompleteSystem {

    private TNode root;
    private StringBuilder prefix;
    private List<Node> nodes;
    private int topcount = 3;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TNode();
        prefix = new StringBuilder();
        nodes = new ArrayList<Node>();
        for(int i = 0; i < times.length; i++) {
            insertSentence(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> ans = new ArrayList<String>();
        if(c == '#') {
            //end of the once input
            //The user finished the input, the sentence "i a" should be saved as a historical sentence in system.
            if(prefix.length() > 0) {
                insertSentence(prefix.toString(), 1);
            }
            
            //initial the prefix nodes
            prefix = new StringBuilder();
            nodes.clear();
            return ans;
        }
        if(prefix.length() == 0) {
            //first time input
            if(root.links[chToIdx(c)] != null) {
                getRestSentence(root.links[chToIdx(c)]);
                Collections.sort(nodes, (a,b)->a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
            }
        } else {
            //remove the not match from this char
            Stack<Integer> moved = new Stack<Integer>();
            int idx = prefix.length();
            int size = nodes.size();
            for(int i = 0; i < size; i++) {
                if(nodes.get(i).sentence.length() <= idx) {
                    moved.add(i);
                } else if(c != nodes.get(i).sentence.charAt(idx)) {
                    moved.add(i);
                }
            }
            while(!moved.isEmpty()) {
                int moveidx = moved.pop();
                nodes.remove(moveidx);
            }
        }
        prefix.append(c);
        for(int i = 0; i < topcount && i < nodes.size(); i++) {
            ans.add(nodes.get(i).sentence);
        }
        return ans;
    }

    
    public void insertSentence(String sentence, int time) {
        TNode node = root;
        for(char ch : sentence.toCharArray()) {
            if(node.links[chToIdx(ch)] == null) {
                node.links[chToIdx(ch)] = new TNode();
            }
            node = node.links[chToIdx(ch)];
        }
        node.sentence = sentence;
        node.times += time;
    }

    public int chToIdx(char ch) {
        return ch == ' ' ? 26 : ch-'a';
    }

    public void getRestSentence(TNode node) {
        if(node.sentence != null) {
            nodes.add(new Node(node.sentence, node.times));
        }
        for(int i = 0; i < 27; i++) {
            if(node.links[i] != null) {
                getRestSentence(node.links[i]);
            }
        }
    }
}
class Node {
    String sentence;
    int times;
    
    public Node(String sentence, int times) {
        this.sentence = sentence;
        this.times = times;
    }
}
class TNode {
    TNode[] links;
    String sentence;
    int times;
    
    public TNode() {
        links = new TNode[27];
    }
}
