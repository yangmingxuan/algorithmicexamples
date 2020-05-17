package algorithms.string;

import java.util.ArrayList;
import java.util.List;

/***
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

    For example, given n = 3, a solution set is:
    
    [
      "((()))",
      "(()())",
      "(())()",
      "()(())",
      "()()()"
    ]
 * @author miche
 *
 */
public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> lret = new ArrayList<String>();
        generateAll("", lret, 0, 0, n);
        return lret;
    }

    public void generateAll(String ans, List<String> lret, int leftCount, int rightCount, int n) {
        if(ans.length() == n*2) {
            lret.add(ans);
            return;
        }
        
        if(leftCount < n) {
            generateAll(ans+"(", lret, leftCount+1, rightCount, n);
        }
        if(rightCount < leftCount) {
            generateAll(ans+")", lret, leftCount, rightCount+1, n);
        }
    }
}
