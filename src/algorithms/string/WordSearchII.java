package algorithms.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/***
 * Given a 2D board and a list of words from the dictionary, find all words in the board.

    Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
    
     
    
    Example:
    
    Input: 
    board = [
      ['o','a','a','n'],
      ['e','t','a','e'],
      ['i','h','k','r'],
      ['i','f','l','v']
    ]
    words = ["oath","pea","eat","rain"]
    
    Output: ["eat","oath"]
     
    
    Note:
    
    All inputs are consist of lowercase letters a-z.
    The values of words are distinct.
 * @author miche
 *
 */
public class WordSearchII {

    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<String>();
        if(board == null || board.length == 0 || board[0].length == 0) return ans;
        if(words == null || words.length == 0) return ans;
        for(String word: words) {
            if(exist(board, word)) {
                ans.add(word);
            }
        }
        return ans;
    }

    public boolean exist(char[][] board, String word) {
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length; col++) {
                if(dfs(board, word, row, col, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, String word, int row, int col, int idx) {
        if(word.length() == idx) return true; //if the entire string is compared
        boolean ret = false;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        //if move out of bound or characters are not equal
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || board[row][col] != word.charAt(idx)) {
            return false;
        }
        
        board[row][col] = '*'; //it means this character is compared
        for(int i = 0; i < dx.length; i++) {
            if(dfs(board, word, row+dy[i], col+dx[i], idx+1)) {
                ret = true;
                break;
            }
        }
        board[row][col] = word.charAt(idx); //Assign the original value back
        return ret;
    }

    /***
     * Use the TrieNode, one less loop
     * @param board
     * @param words
     * @return
     */
    private TrieNode root;
    public List<String> findWords2(char[][] board, String[] words) {
        List<String> ans = new ArrayList<String>();
        if(board == null || board.length == 0 || board[0].length == 0) return ans;
        if(words == null || words.length == 0) return ans;
        root = new TrieNode();
        for(String word : words) {
            insertWord(word);
        }

        HashSet<String> set = new HashSet<String>();
        
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length; col++) {
                backtrack(board, root, row, col, set);
            }
        }

        ans.addAll(set);
        
        return ans;
    }

    public void insertWord(String word) {
        TrieNode node = root;
        for(char ch : word.toCharArray()) {
            if(node.links[ch-'a'] == null) {
                node.links[ch-'a'] = new TrieNode();
            }
            node = node.links[ch-'a'];
        }
        node.word = word;
    }

    public void backtrack(char[][] board, TrieNode node, int row, int col, HashSet<String> set) {
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || board[row][col] == '*' ) {
            return;
        }
        char ch = board[row][col];
        if(node.links[ch-'a'] == null) {
            return;
        }
        node = node.links[ch-'a'];
        if(node.word != null) {
            set.add(node.word);
        }

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        board[row][col] = '*'; //it means this character is compared
        for(int i = 0; i < dx.length; i++) {
            backtrack(board, node, row+dy[i], col+dx[i], set);
        }
        board[row][col] = ch; //Assign the original value back
    }
}
class TrieNode {
    TrieNode[] links;
    String word;
    
    public TrieNode() {
        links = new TrieNode[26];
    }
}
