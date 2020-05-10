package algorithms.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/***
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

    Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.
    
     
    
    Example:
    
    Input: 
    paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
    banned = ["hit"]
    Output: "ball"
    Explanation: 
    "hit" occurs 3 times, but it is a banned word.
    "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
    Note that words in the paragraph are not case sensitive,
    that punctuation is ignored (even if adjacent to words, such as "ball,"), 
    and that "hit" isn't the answer even though it occurs more because it is banned.
     
    
    Note:
    
    1 <= paragraph.length <= 1000.
    0 <= banned.length <= 100.
    1 <= banned[i].length <= 10.
    The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
    paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
    There are no hyphens or hyphenated words.
    Words only consist of letters, never apostrophes or other punctuation symbols.
 * @author miche
 *
 */
public class MostCommonWord {

    public String mostCommonWord(String paragraph, String[] banned) {
        String ans = "";
        int mostFrequent = Integer.MIN_VALUE;
        HashSet<String> ban = new HashSet<String>();
        for(int i = 0; i < banned.length; i++) {
            ban.add(banned[i].toLowerCase());
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        char[] arrays = paragraph.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arrays.length; i++) {
            if(arrays[i] >= 'a' && arrays[i] <= 'z') {
                sb.append(arrays[i]);
            } else if(sb.length() == 0) {
                continue;
            } else {
                String word = sb.toString();
                if(!ban.contains(word)) {
                    int count = map.getOrDefault(word, 0) + 1;
                    if(count >= mostFrequent) {
                        mostFrequent = count;
                        ans = word;
                    }
                    map.put(word, count);
                }
                sb = new StringBuilder();
            }
        }
        if(sb.length() > 0) {
            String word = sb.toString();
            if(!ban.contains(word)) {
                int count = map.getOrDefault(word, 0) + 1;
                if(count >= mostFrequent) {
                    mostFrequent = count;
                    ans = word;
                }
                map.put(word, count);
            }
        }

        return ans;
    }

}
