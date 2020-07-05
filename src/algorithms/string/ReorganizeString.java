package algorithms.string;

import java.util.Arrays;

/***
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

    If possible, output any possible result.  If not possible, return the empty string.
    
    Example 1:
    
    Input: S = "aab"
    Output: "aba"
    Example 2:
    
    Input: S = "aaab"
    Output: ""
    Note:
    
    S will consist of lowercase letters and have length in range [1, 500].
 * @author miche
 *
 */
public class ReorganizeString {

    public String reorganizeString(String S) {
        if(S == null || S.isEmpty()) return "";
        int len = S.length();
        int[] counts = new int[26];
        char[] ans = new char[len];
        for(char ch : S.toCharArray()) {
            counts[ch-'a'] += 100;
        }
        for(int i = 0; i < 26; i++) {
            counts[i] += i;  //so counts[i] = count*100 + ch
        }
        Arrays.parallelSort(counts);
        int idx = 1;
        for(int i = 0; i < 26; i++) {
            char ch = (char)(counts[i] % 100 + 'a');  //which char
            int act = counts[i] / 100; // the char's actual count
            if(act > (len+1)/2) {
                //if some char's count larger than half of length, cannot be rearranged
                return "";
            }
            for(int j = 0; j < act; j++) {
                if(idx >= len) idx = 0;
                ans[idx] = ch;
                idx += 2;
            }
        }
        
        return String.valueOf(ans);
    }

}
