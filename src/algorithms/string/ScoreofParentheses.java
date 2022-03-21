package algorithms.string;
/***
 * Given a balanced parentheses string s, return the score of the string.

    The score of a balanced parentheses string is based on the following rule:
    
    "()" has score 1.
    AB has score A + B, where A and B are balanced parentheses strings.
    (A) has score 2 * A, where A is a balanced parentheses string.
     
    
    Example 1:
    
    Input: s = "()"
    Output: 1
    Example 2:
    
    Input: s = "(())"
    Output: 2
    Example 3:
    
    Input: s = "()()"
    Output: 2
     
    
    Constraints:
    
    2 <= s.length <= 50
    s consists of only '(' and ')'.
    s is a balanced parentheses string.
 * @author miche
 *
 */

public class ScoreofParentheses {

    /***
     * Explanation: 其实问题的本质是看最终的()处在第几层，举例说明：如果在第一层()是1--2的0次方，如果是在第二层是2--2^1, 第三层是4--2^2，
     *          所以最终问题是计算各个最核心的()之和
     * In fact, the essence of the problem is to see which layer the final () is in. For example: if the first layer () is 1--2 to the 0th power, if it is in the second layer, it is 2--2^1, The third layer is 4--2^2,
     * So the final problem is to calculate the sum of each core ()
     * @param s
     * @return
     */
    public int scoreOfParentheses(String s) {
        int ans = 0, layer = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                layer++;
            } else {
                layer--;
                if(s.charAt(i-1) == '(') {
                    //the core ()
                    ans += (1 << layer);
                }
            }
        }
        return ans;
    }

}
