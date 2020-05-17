package algorithms.string;

import java.util.ArrayList;
import java.util.List;

/***
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

    Example 1:
    Input: S = "ababcbacadefegdehijhklij"
    Output: [9,7,8]
    Explanation:
    The partition is "ababcbaca", "defegde", "hijhklij".
    This is a partition so that each letter appears in at most one part.
    A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
    Note:
    
    S will have length in range [1, 500].
    S will consist of lowercase letters ('a' to 'z') only.
 * @author miche
 *
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        List<Integer> lret = new ArrayList<Integer>();
        int[] lastIdx = new int[26];
        for(int i = 0; i < S.length(); i++) {
            //remark the last index of each letter
            lastIdx[S.charAt(i) - 'a'] = i;
        }
        int j = 0, spilt = 0;
        for(int i = 0; i < S.length(); i++) {
            j = Math.max(j, lastIdx[S.charAt(i) - 'a']);
            if(i == j) {
                lret.add(i - spilt + 1);
                spilt = i + 1;
            }
        }
        
        return lret;
    }

}
