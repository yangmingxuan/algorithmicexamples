package algorithms.string;

/***
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

    You have the following 3 operations permitted on a word:
    
    Insert a character
    Delete a character
    Replace a character
    Example 1:
    
    Input: word1 = "horse", word2 = "ros"
    Output: 3
    Explanation: 
    horse -> rorse (replace 'h' with 'r')
    rorse -> rose (remove 'r')
    rose -> ros (remove 'e')
    Example 2:
    
    Input: word1 = "intention", word2 = "execution"
    Output: 5
    Explanation: 
    intention -> inention (remove 't')
    inention -> enention (replace 'i' with 'e')
    enention -> exention (replace 'n' with 'x')
    exention -> exection (replace 'n' with 'c')
    exection -> execution (insert 'u')
 * @author miche
 *
 */
public class EditDistance {

    /***
     * Dynamic Programming
     * dp[i][j] 表示word1下标小于i的部分与word2下标小于j的部分需要转换的次数
     * dp[i][j] Represents the number of times the word1 subscript is less than i and the word2 subscript is less than j
     * dp[0][0] = 0 表示两个字符串长度都为0时，次数为0
     * dp[0][0] = 0 When the length of both strings is 0, the number of times is 0
     * dp[0][j] dp[i][0] 表示某一字符串长度为0时，次数取决于另外一个字符串的长度
     * dp[0][j] dp[i][0] When the length of a certain string is 0, the number of times depends on the length of another string
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        if(word1.isEmpty() && word2.isEmpty()) {
            return 0;
        }
        if(word1.isEmpty()) {
            return word2.length();
        }
        if(word2.isEmpty()) {
            return word1.length();
        }
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int i = 1; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for(int j = 1; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        for(int i = 0; i < word1.length(); i++) {
            for(int j = 0; j < word2.length(); j++) {
                if(word1.charAt(i) == word2.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j];
                } else {
                    dp[i+1][j+1] = Math.min(dp[i][j], Math.min(dp[i+1][j], dp[i][j+1])) + 1;
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }

}
