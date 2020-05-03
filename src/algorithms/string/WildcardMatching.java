package algorithms.string;
import java.util.ArrayList;
import java.util.List;

/***
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

    '?' Matches any single character.
    '*' Matches any sequence of characters (including the empty sequence).
    The matching should cover the entire input string (not partial).
    
    Note:
    
    s could be empty and contains only lowercase letters a-z.
    p could be empty and contains only lowercase letters a-z, and characters like ? or *.
    Example 1:
    
    Input:
    s = "aa"
    p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".
    Example 2:
    
    Input:
    s = "aa"
    p = "*"
    Output: true
    Explanation: '*' matches any sequence.

 * @author miche
 *
 */
public class WildcardMatching {

    public WildcardMatching() {
        // TODO Auto-generated constructor stub
    }

    /***
     * Right in most cases, but Time Limit Exceeded in extreme cases
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        if("*".equals(p)) return true;
        if(p.isEmpty()) return s.isEmpty();
        if(p.charAt(0) == '*') {
            if(s.isEmpty()) {
                return isMatch2(s, p.substring(1));
            } else {
                //find the first regular letter string
                int leftcur = 1, rightcur = 1, questionMarkCount = 0;
                while(leftcur < p.length()) {
                    if(p.charAt(leftcur) == '*') {
                        leftcur++;
                    } else if(p.charAt(leftcur) == '?') {
                        questionMarkCount++;
                        leftcur++;
                    } else {
                        break;
                    }
                }
                if(leftcur == p.length()) {
                    return s.length() >= questionMarkCount;
                }
                rightcur = leftcur + 1;
                while(rightcur < p.length()) {
                    if(p.charAt(rightcur) == '*' || p.charAt(rightcur) == '?') {
                        break;
                    } else {
                        rightcur++;
                    }
                }
                String subRegular = p.substring(leftcur, rightcur);
                //find all of the regular letter string in the s
                List<Integer> allindex = new ArrayList<Integer>();
                int index = s.indexOf(subRegular);
                while(index >= 0) {
                    allindex.add(index);
                    index++;
                    if(index < s.length()) {
                        index = s.indexOf(subRegular, index);
                    } else {
                        break;
                    }
                }

                //if some sub string is true, all is true
                for(int i = 0; i < allindex.size(); i++) {
                    index = allindex.get(i);
                    if(index < questionMarkCount) continue;
                    if(isMatch2(s.substring(index+subRegular.length()), p.substring(rightcur))) {
                        return true;
                    }
                }
                return false;
            }
        } else if(s.isEmpty()) {
            return false;
        } else if(p.charAt(0) == '?' || p.charAt(0) == s.charAt(0)){
            return isMatch2(s.substring(1), p.substring(1));
        }

        return false;
    }

    /***
     * Right in most cases, but Time Limit Exceeded in extreme cases
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch3(String s, String p) {
        if("*".equals(p)) return true;
        if(p.isEmpty()) return s.isEmpty();
        if(p.charAt(0) == '*') {
            if(s.isEmpty() || p.charAt(1) == '*') {
                return isMatch3(s, p.substring(1));
            } else {
                return isMatch3(s, p.substring(1)) || isMatch3(s.substring(1), p);
            }
        } else if(s.isEmpty()) {
            return false;
        } else if(p.charAt(0) == '?' || p.charAt(0) == s.charAt(0)){
            return isMatch3(s.substring(1), p.substring(1));
        }

        return false;
    }

    /***
     * DP means Dynamic Programming. 
     * dp[i][j]，代表一个“状态”，它的含义是s下标小于i的部分和p下标小于j的部分的匹配状况
     * dp[i][j], which represents a "state", which means the match between the part with s subscript less than i and the part with p subscript less than j
     * dp[0][0] = true 表示“空字符串”和“空正则表达式”匹配
     * dp[0][0] = true means that "empty string" and "empty regular expression" match
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        //"empty string" and "empty regular expression" match
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            //当空串碰到*时，是否匹配取决与之前的表达式
            //When the empty string encounters *, the match depends on the previous expression
            if (p.charAt(i) == '*') {
                dp[0][i + 1] = dp[0][i];
            }
        }
        
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j < p.length(); j++) {
                if(p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if(p.charAt(j) == '*') {
                    //Refer to the recursion of isMatch3 function
                    dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1];
                }
            }
        }

        return dp[s.length()][p.length()];
    }
    public static void main(String[] argv) {
        String s = "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb";
        String p = "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb";
        //String s = "ab"; //"ba"; //"mississippi"; //"bbbab"; //"adceb"; // "acdcb";
        //String p = "?*"; //"*a*"; //"m??*ss*?i*pi"; //"*??a?"; //"*a*b"; //"a*c?b";
        WildcardMatching wm = new WildcardMatching();
        System.out.println(wm.isMatch(s, p));
    }
}
