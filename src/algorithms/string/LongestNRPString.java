package algorithms.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * 
 * @author miche
  Given a string, find the length of the longest substring without repeating characters.

    Example 1:
    
    Input: "abcabcbb"
    Output: 3 
    Explanation: The answer is "abc", with the length of 3. 
    Example 2:
    
    Input: "bbbbb"
    Output: 1
    Explanation: The answer is "b", with the length of 1.
    Example 3:
    
    Input: "pwwkew"
    Output: 3
    Explanation: The answer is "wke", with the length of 3. 
                 Note that the answer must be a substring, "pwke" is a subsequence and not a substring.


 * StringBuilder 92ms 37.8MB
 * HashSet       52ms 36.8MB
 * ArrayList    134ms 39.4MB
 * int[]          9ms 37.6MB
 */

public class LongestNRPString {

    public LongestNRPString() {
        // TODO Auto-generated constructor stub
    }

    public static int lengthOfLongestSubstring(String s) {
        int mexlen = 0;
        int len = s.length();
        int i = 0, lentmp = 0, j = 0;
        char a;
        int[] itmp;
        //StringBuilder sbtmp = new StringBuilder();
        //Set<Character> stmp = new HashSet<>();
        //List<Character> ltmp = new ArrayList<>();
        while(i < len - mexlen) {
            //sbtmp.setLength(0);
            //stmp.clear();
            //ltmp.clear();
            itmp = new int[128];
            lentmp = 0;
            //ipr = -1;
            for(j = i; j < len; j++) {
                a = s.charAt(j);
                //ipr = sbtmp.toString().indexOf(a);
                //ipr = ltmp.indexOf(a);
                if(itmp[a] != 0) {
                //if(ipr >= 0) {
                    //ipr = sbtmp.toString().indexOf(a);
                    mexlen = (mexlen < lentmp ? lentmp : mexlen);
                    break;
                } else {
                    lentmp++;
                }
                itmp[a] = itmp[a] + 1;
                //sbtmp.append(a);
                //stmp.add(a);
                //ltmp.add(a);
            }
            if(mexlen < lentmp) {
                mexlen = lentmp;
            }
            //if(ipr >= 0) {
            //    i = i + ipr + 1;
            //} else {
                i++;
            //}
        }
        
        return mexlen;
    }

    /***
     * 2ms 36.8MB
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s.length() < 2)
            return s.length();

        int[] c = new int[128];
        int res = 0;
        char ch;
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            ch = s.charAt(r);
            if (c[ch] == 0) {
                ++c[ch];
            } else {
                res = Math.max(res, r - l);
                ++c[ch];
                while (c[ch] != 1) {
                    --c[s.charAt(l)];
                    l++;
                }
            }
        }
        res = Math.max(res, s.length() - l);
        return res;
    }

    public static void main(String argv[]) {
        String s = "pewuvtw";
        int ret = lengthOfLongestSubstring2(s);
        System.out.print(ret);
    }
}
