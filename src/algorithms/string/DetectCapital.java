package algorithms.string;

/***
 * We define the usage of capitals in a word to be right when one of the following cases holds:

    All letters in this word are capitals, like "USA".
    All letters in this word are not capitals, like "leetcode".
    Only the first letter in this word is capital, like "Google".
    Given a string word, return true if the usage of capitals in it is right.
    
     
    
    Example 1:
    
    Input: word = "USA"
    Output: true
    Example 2:
    
    Input: word = "FlaG"
    Output: false
     
    
    Constraints:
    
    1 <= word.length <= 100
    word consists of lowercase and uppercase English letters.
 * @author miche
 *
 */
public class DetectCapital {

    public boolean detectCapitalUse(String word) {
        if(word.length() == 1) return true;
        char[] cs = word.toCharArray();
        if(Character.isUpperCase(cs[0]) && Character.isUpperCase(cs[1])) {
            //case 1 must all uppercase
            for(int i = 2; i < cs.length; i++) {
                if(!Character.isUpperCase(cs[i])) {
                    return false;
                }
            }
        } else {
            //case 2, 3 from index 1 to the end must all lowwercase
            for(int i = 1; i < cs.length; i++) {
                if(!Character.isLowerCase(cs[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean detectCapitalUse2(String word) {
        return word.matches("[A-Z]*|.[a-z]*");
    }
}
