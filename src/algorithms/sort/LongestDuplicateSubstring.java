package algorithms.sort;

import java.util.HashSet;

/***
 * Given a string S, consider all duplicated substrings: (contiguous) substrings of S that occur 2 or more times.  (The occurrences may overlap.)

    Return any duplicated substring that has the longest possible length.  (If S does not have a duplicated substring, the answer is "".)
    
     
    
    Example 1:
    
    Input: "banana"
    Output: "ana"
    Example 2:
    
    Input: "abcd"
    Output: ""
     
    
    Note:
    
    2 <= S.length <= 10^5
    S consists of lowercase English letters.
 * @author miche
 *
 */
public class LongestDuplicateSubstring {

    /***
     * Time Limit Exceeded
    public String longestDupSubstring(String S) {
        if(S == null || S.length() == 0) return S;
        TrieNode root = new TrieNode();
        String ans = "";
        int maxLen = 0;
        char[] arr = S.toCharArray();
        for(int i = 0; i < arr.length; i++) {
            StringBuilder sb = new StringBuilder();
            int len = 0;
            TrieNode node = root;
            for(int j = i; j < arr.length; j++) {
                if(node.containsKey(arr[j])) {
                    len++;
                    sb.append(arr[j]);
                } else {
                    node.put(arr[j], new TrieNode());
                }
                node = node.get(arr[j]);
            }
            if(len > maxLen) {
                maxLen = len;
                ans = sb.toString();
            }
        }
        
        return ans;
    }
    */

    /***
     * 
    int maxLen;
    String ans;
    public String longestDupSubstring(String S) {
        if(S == null || S.length() == 0) return S;
        maxLen = 0;
        ans = "";
        int left = 0, right = S.length() - 1;
        
        while(left <= right) {
            int len = (left+right) / 2;
            if(existDupString(S, len)) {
                //search the longer duplicate string
                left = len + 1;
            } else {
                //narrow the window
                right = len - 1;
            }
        }
        
        return ans;
    }

    public boolean existDupString(String S, int len) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(i = 0; i < S.length() && i < len; i++) {
            sb.append(S.charAt(i));
        }
        HashSet<String> exist = new HashSet<String>();
        exist.add(sb.toString());
        //move the window
        while(i < S.length()) {
            sb.delete(0, 1);
            sb.append(S.charAt(i));
            if(exist.contains(sb.toString())) {
                if(maxLen < len) {
                    maxLen = len;
                    ans = sb.toString();
                    return true;
                }
            } else {
                exist.add(sb.toString());
            }
            i++;
        }
        return false;
    }
    */

    int base = 26;
    long modulus;
    int maxLen;
    String ans;
    /***
     * Treat the string as a number base 26
     * @param S
     * @return
     */
    public String longestDupSubstring(String S) {
        if(S == null || S.length() == 0) return S;
        modulus = (long)Math.pow(2, 32); //Integer.Max + 1
        maxLen = 0;
        ans = "";
        int[] nums = new int[S.length()];
        for(int i = 0; i < nums.length; i++) {
            nums[i] = S.charAt(i) - 'a';
        }
        
        int left = 1, right = nums.length;
        while(left <= right) {
            int len = (left+right) / 2;
            if(existDupString(nums, len) != -1) {
                //search the longer duplicate string
                left = len + 1;
            } else {
                right = len - 1;
            }
        }

        //int start = existDupString(nums, left-1);
        //if(start != -1) return S.substring(start, start + left - 1);
        return ans;
    }

    public int existDupString(int[] nums, int len) {
        long h = 0;
        long aL = 1;
        //the initial number(string)
        for(int i = 0; i < len; i++) {
            h = (h*base + nums[i]) % modulus;
            aL = (aL*base) % modulus; //Similar to the Nth power of 10 in base 10
        }
        HashSet<Long> exist = new HashSet<Long>();
        exist.add(h);

        //move the window
        for(int start = 1; start < nums.length - len + 1; start++) {
            //remove the first digit and multiply by base(26)
            h = (h * base - nums[start - 1] * aL % modulus + modulus) % modulus;
            //add the next digit
            h = (h + nums[start + len - 1]) % modulus;
            
            if(exist.contains(h)) {
                if(maxLen < len) {
                    maxLen = len;
                    StringBuilder sb = new StringBuilder();
                    for(int i = start; i < start + len; i++) {
                        sb.append((char)(nums[i]+'a'));
                    }
                    ans = sb.toString();
                }
                return start;
            }
            exist.add(h);
        }
        return -1;
    }
}
