package algorithms.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javafx.util.Pair;

/***
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

    Only one letter can be changed at a time.
    Each transformed word must exist in the word list.
    Note:
    
    Return 0 if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.
    Example 1:
    
    Input:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]
    
    Output: 5
    
    Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
    return its length 5.
    Example 2:
    
    Input:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]
    
    Output: 0
    
    Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 * @author miche
 *
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(wordList == null || wordList.isEmpty()) return 0;
        Map<String, List<String>> originWords = new HashMap<String, List<String>>();
        Map<String, List<String>> deformWords = new HashMap<String, List<String>>();
        Map<String, String> done = new HashMap<String, String>();
        
        //add the wordList to Map
        for(String word : wordList) {
            //get all the one letter deform
            for(int i = 0; i < word.length(); i++) {
                String deformWord = word.substring(0, i) + "#" + word.substring(i+1);
                List<String> oTod = originWords.getOrDefault(word, new ArrayList<String>());
                oTod.add(deformWord);
                originWords.put(word, oTod);
                List<String> dToo = deformWords.getOrDefault(deformWord, new ArrayList<String>());
                dToo.add(word);
                deformWords.put(deformWord, dToo);
            }
        }
        //endWord not in the wordList
        if(!originWords.containsKey(endWord)) return 0;
        
        //add the beginWord to Map
        for(int i = 0; i < beginWord.length(); i++) {
            String deformWord = beginWord.substring(0, i) + "#" + beginWord.substring(i+1);
            List<String> oTod = originWords.getOrDefault(beginWord, new ArrayList<String>());
            oTod.add(deformWord);
            originWords.put(beginWord, oTod);
            List<String> dToo = deformWords.getOrDefault(deformWord, new ArrayList<String>());
            dToo.add(beginWord);
            deformWords.put(deformWord, dToo);
        }
        
        //initial
        Queue<Pair<String, Integer>> words = new LinkedList<Pair<String, Integer>>();
        words.offer(new Pair(beginWord, 1));
        done.put(beginWord, "");
        
        while(!words.isEmpty()) {
            Pair<String, Integer> node = words.poll();
            String originWord = node.getKey();
            int step = node.getValue();
            List<String> oTod = originWords.get(originWord);
            for(String deformWord : oTod) {
                List<String> dToo = deformWords.get(deformWord);
                for(String newWord : dToo) {
                    if(endWord.equals(newWord)) {
                        return step+1;
                    }
                    if(!done.containsKey(newWord)) {
                        done.put(newWord, originWord);
                        words.offer(new Pair(newWord, step+1));
                    }
                }
            }
        }
        
        return 0;
    }

}
