package algorithms.string;

import java.util.TreeMap;
import java.util.Map;

/***
 * Given a chemical formula (given as a string), return the count of each atom.

    An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
    
    1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.
    
    Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.
    
    A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.
    
    Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.
    
    Example 1:
    Input: 
    formula = "H2O"
    Output: "H2O"
    Explanation: 
    The count of elements are {'H': 2, 'O': 1}.
    Example 2:
    Input: 
    formula = "Mg(OH)2"
    Output: "H2MgO2"
    Explanation: 
    The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
    Example 3:
    Input: 
    formula = "K4(ON(SO3)2)2"
    Output: "K4N2O14S4"
    Explanation: 
    The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
    Note:
    
    All atom names consist of lowercase letters, except for the first character which is uppercase.
    The length of formula will be in the range [1, 1000].
    formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.
 * @author miche
 *
 */
public class NumberofAtoms {

    int index = 0;
    public String countOfAtoms(String formula) {
        if(formula == null || formula.isEmpty()) return formula;

        TreeMap<String, Integer> map = new TreeMap<String, Integer>();  //key order by letter
        StringBuilder element = new StringBuilder();
        int num = 0;
        while(index < formula.length()) {
            if(formula.charAt(index) >= 'A' && formula.charAt(index) <= 'Z') {
                //a new element
                if(element.length() != 0) {
                    //there is a last element
                    if(num == 0) num = 1;
                    map.put(element.toString(), map.getOrDefault(element.toString(), 0) + num);
                }
                element = new StringBuilder();
                num = 0;
                element.append(formula.charAt(index));
                index++;
            } else if(formula.charAt(index) >= 'a' && formula.charAt(index) <= 'z') {
                //element
                element.append(formula.charAt(index));
                index++;
            } else if(Character.isDigit(formula.charAt(index))) {
                num = num * 10 + (formula.charAt(index) - '0');
                index++;
            } else if(formula.charAt(index) == '(') {
                mergeMap(map, getParentheseMap(formula));
            }
        }
        if(element.length() != 0) {
            //the last element
            if(num == 0) num = 1;
            map.put(element.toString(), map.getOrDefault(element.toString(), 0) + num);
        }

        StringBuilder ans = new StringBuilder();
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            ans.append(entry.getKey());
            if(entry.getValue() > 1) {
                ans.append(entry.getValue());
            }
        }
        return ans.toString();
    }

    public TreeMap<String, Integer> getDigitMap(int num, TreeMap<String, Integer> map) {
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            map.put(entry.getKey(), num*entry.getValue());
        }
        return map;
    }

    public void mergeMap(TreeMap<String, Integer> map1, TreeMap<String, Integer> map2) {
        for(Map.Entry<String, Integer> entry : map2.entrySet()) {
            map1.put(entry.getKey(), map1.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
    }

    public TreeMap<String, Integer> getParentheseMap(String formula) {
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        index++;  //first char is '(
        StringBuilder element = new StringBuilder();
        int num = 0;
        while(index < formula.length() && formula.charAt(index) != ')') {
            if(formula.charAt(index) >= 'A' && formula.charAt(index) <= 'Z') {
                //a new element
                if(element.length() != 0) {
                    //there is a last element
                    if(num == 0) num = 1;
                    map.put(element.toString(), map.getOrDefault(element.toString(), 0) + num);
                }
                element = new StringBuilder();
                num = 0;
                element.append(formula.charAt(index));
                index++;
            } else if(formula.charAt(index) >= 'a' && formula.charAt(index) <= 'z') {
                //element
                element.append(formula.charAt(index));
                index++;
            } else if(Character.isDigit(formula.charAt(index))) {
                num = num * 10 + (formula.charAt(index) - '0');
                index++;
            } else if(formula.charAt(index) == '(') {
                mergeMap(map, getParentheseMap(formula));
            }
        }
        if(element.length() != 0) {
            //the last element
            if(num == 0) num = 1;
            map.put(element.toString(), map.getOrDefault(element.toString(), 0) + num);
        }
        if(index < formula.length()) {
            index++;    //the char is ')'
        }

        num = 0;
        while(index < formula.length() && Character.isDigit(formula.charAt(index))) {
            num = num * 10 + (formula.charAt(index) - '0');
            index++;
        }
        if(num > 1) return getDigitMap(num, map);
        return map;
    }
}
