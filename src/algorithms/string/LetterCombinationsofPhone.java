package algorithms.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

    A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
    
    
    
    Example:
    
    Input: "23"
    Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    Note:
    
    Although the above answer is in lexicographical order, your answer could be in any order you want.
 * @author miche
 *
 */
public class LetterCombinationsofPhone {

    public LetterCombinationsofPhone() {
        // TODO Auto-generated constructor stub
    }

    private static List<String> list = new ArrayList<String>();

    /***
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Letter Combinations of a Phone Number.
     * Memory Usage: 38.7 MB, less than 6.16% of Java online submissions for Letter Combinations of a Phone Number.

     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        list.clear();
        if(digits == null || digits.isEmpty()) {
            return list;
        }
        Map<Character, String> phoneLetter = new HashMap<Character, String>();
        phoneLetter.put('2', "abc");
        phoneLetter.put('3', "def");
        phoneLetter.put('4', "ghi");
        phoneLetter.put('5', "jkl");
        phoneLetter.put('6', "mno");
        phoneLetter.put('7', "pqrs");
        phoneLetter.put('8', "tuv");
        phoneLetter.put('9', "wxyz");
        StringBuilder sbLetter = new StringBuilder();
        RecursionLetter(sbLetter, digits, phoneLetter);
        return list;
    }

    public void RecursionLetter(StringBuilder sbLetter, String leftDigits, Map<Character, String> phoneLetter) {
        if(leftDigits.isEmpty()) {
            list.add(sbLetter.toString());
            return;
        }
        String letters = phoneLetter.get(leftDigits.charAt(0));
        if(letters == null) {
            RecursionLetter(sbLetter, leftDigits.substring(1), phoneLetter);
        } else {
            for(char c: letters.toCharArray()) {
                sbLetter.append(c);
                RecursionLetter(sbLetter, leftDigits.substring(1), phoneLetter);
                sbLetter.deleteCharAt(sbLetter.length()-1);
            }
        }
    }
}
