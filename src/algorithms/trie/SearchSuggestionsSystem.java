package algorithms.trie;

import java.util.ArrayList;
import java.util.List;

/***
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

    Return list of lists of the suggested products after each character of searchWord is typed. 
    
     
    
    Example 1:
    
    Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
    Output: [
    ["mobile","moneypot","monitor"],
    ["mobile","moneypot","monitor"],
    ["mouse","mousepad"],
    ["mouse","mousepad"],
    ["mouse","mousepad"]
    ]
    Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
    After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
    After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
    Example 2:
    
    Input: products = ["havana"], searchWord = "havana"
    Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
    Example 3:
    
    Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
    Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
    Example 4:
    
    Input: products = ["havana"], searchWord = "tatiana"
    Output: [[],[],[],[],[],[],[]]
     
    
    Constraints:
    
    1 <= products.length <= 1000
    There are no repeated elements in products.
    1 <= Σ products[i].length <= 2 * 10^4
    All characters of products[i] are lower-case English letters.
    1 <= searchWord.length <= 1000
    All characters of searchWord are lower-case English letters.

 * @author miche
 *
 */
public class SearchSuggestionsSystem {

    private List<List<String>> lret;
    private List<String> eachWords;
    private TrieNode root;
    private static int wordscount = 3;

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        lret = new ArrayList<List<String>>();
        root = new TrieNode();
        if(products == null || products.length == 0) return lret;
        for(String word : products) {
            insertWords(word);
        }

        StringBuilder sb = new StringBuilder();
        char[] arrs = searchWord.toCharArray();
        for(int i = 0; i < arrs.length; i++) {
            sb.append(arrs[i]);
            String prefix = sb.toString();
            eachWords = new ArrayList<String>();
            getWords(prefix);
            lret.add(eachWords);
        }
        return lret;
    }

    public void insertWords(String word) {
        TrieNode node = root;
        char[] arrs = word.toCharArray();
        for(char ch : arrs) {
            if(!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEnd();
        node.setWord(word);
    }

    public void getWords(String prefix) {
        TrieNode node = searchPrefix(prefix);
        if(node == null) return;
        getRestWord(node);
    }

    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        char[] arrs = prefix.toCharArray();
        for(char ch : arrs) {
            if(node.containsKey(ch)) {
                node = node.get(ch);
            } else {
                return null;
            }
        }
        return node;
    }

    private void getRestWord(TrieNode node) {
        if(eachWords.size() >= wordscount) return;
        if(node.isEnd()) eachWords.add(node.getWord());
        for(char ch = 'a'; ch <= 'z'; ch++) {
            if(node.containsKey(ch)) {
                getRestWord(node.get(ch));
            }
        }
    }
}
