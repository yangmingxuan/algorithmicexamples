package algorithms.array;
/***
 * Given an integer n, your task is to count how many strings of length n can be formed under the following rules:

    Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
    Each vowel 'a' may only be followed by an 'e'.
    Each vowel 'e' may only be followed by an 'a' or an 'i'.
    Each vowel 'i' may not be followed by another 'i'.
    Each vowel 'o' may only be followed by an 'i' or a 'u'.
    Each vowel 'u' may only be followed by an 'a'.
    Since the answer may be too large, return it modulo 10^9 + 7.
    
     
    
    Example 1:
    
    Input: n = 1
    Output: 5
    Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
    Example 2:
    
    Input: n = 2
    Output: 10
    Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
    Example 3: 
    
    Input: n = 5
    Output: 68
     
    
    Constraints:
    
    1 <= n <= 2 * 10^4
 * @author miche
 *
 */

public class CountVowelsPermutation {

    /***
     * Explanation: aDP[i] 表示以a为首长度为i+1有几个字符串，eDP,iDP等也相同；
     *      a元音之后只能跟随e元音，那么a首长度为i+1的字符串个数就是以e为首长度为i的字符串个数；
     *      同理，e之后只能跟a和i，那么e首长度为i+1的个数就是a为首长度为i和i为首长度为i的个数之和；
     *      以此类推，最终总的字符串就是aDP[n-1]+eDP[n-1]+iDP[n-1]+oDP[n-1]+uDP[n-1].
     * Explanation: aDP[i] indicates the number of strings starting with a and having a length of i+1, eDP, iDP, etc. are also the same;
     *     A vowel can only be followed by e, then the number of strings with length i+1 at the beginning of a is the number of strings with length i at the beginning of e;
     *     In the same way, e can only be followed by a and i, then the number of e with length i+1 is the sum of the number with a leading length of i and i leading with length i;
     *     By analogy, the final total string is aDP[n-1]+eDP[n-1]+iDP[n-1]+oDP[n-1]+uDP[n-1].
     * @param n
     * @return
     */
    public int countVowelPermutation(int n) {
        long modulo = 1000000007L;
        long aDP[] = new long[n];
        long eDP[] = new long[n];
        long iDP[] = new long[n];
        long oDP[] = new long[n];
        long uDP[] = new long[n];
        
        aDP[0] = eDP[0] = iDP[0] = oDP[0] = uDP[0] = 1;
        
        for(int i = 1; i < n; i++) {
            //Each vowel 'a' may only be followed by an 'e'.
            //So, the number of strings with length i+1 at the beginning of a is the number of strings with length i at the beginning of e
            aDP[i] = eDP[i-1];

            //Each vowel 'e' may only be followed by an 'a' or an 'i'
            eDP[i] = aDP[i-1] + iDP[i-1];
            //Since the answer may be too large, return it modulo 10^9 + 7.
            if(eDP[i] >= modulo) eDP[i] = eDP[i]  % modulo;

            //Each vowel 'i' may not be followed by another 'i'
            iDP[i] = aDP[i-1] + eDP[i-1] + oDP[i-1] + uDP[i-1];
            if(iDP[i] >= modulo) iDP[i] = iDP[i]  % modulo;
            
            //Each vowel 'o' may only be followed by an 'i' or a 'u'.
            oDP[i] = iDP[i-1] + uDP[i-1];
            if(oDP[i] >= modulo) oDP[i] = oDP[i]  % modulo;
            
            //Each vowel 'u' may only be followed by an 'a'
            uDP[i] = aDP[i-1];
        }
        
        long ans = aDP[n-1] + eDP[n-1] + iDP[n-1] + oDP[n-1] + uDP[n-1];
        
        return (int)(ans >= modulo ? ans % modulo : ans);
    }

}
