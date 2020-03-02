package algorithms.string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/***
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

    Symbol       Value
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000
    For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
    
    Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
    
    I can be placed before V (5) and X (10) to make 4 and 9. 
    X can be placed before L (50) and C (100) to make 40 and 90. 
    C can be placed before D (500) and M (1000) to make 400 and 900.
    Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
    
    Example 1:
    
    Input: 3
    Output: "III"
    Example 2:
    
    Input: 4
    Output: "IV"
    Example 3:
    
    Input: 9
    Output: "IX"
    Example 4:
    
    Input: 58
    Output: "LVIII"
    Explanation: L = 50, V = 5, III = 3.
    Example 5:
    
    Input: 1994
    Output: "MCMXCIV"
    Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 * @author miche
 *
 */
public class IntegerToRoma {

    public IntegerToRoma() {
        // TODO Auto-generated constructor stub
    }

    /***
     * Runtime: 5 ms, faster than 35.49% of Java online submissions for Integer to Roman.
     * Memory Usage: 44.1 MB, less than 5.00% of Java online submissions for Integer to Roman.
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        String fiveM[] = {"V", "L", "D"};
        String tenM[] = {"I", "X", "C", "M"};
        StringBuilder ret = new StringBuilder();
        int m = 0, y = 0, t = num;
        while(t > 0) {
            y = t % 10;
            switch (y) {
            case 0:
                ret.insert(0, "");
                break;
            case 1:
                ret.insert(0, tenM[m]);
                break;
            case 2:
                ret.insert(0, tenM[m]).insert(0, tenM[m]);
                break;
            case 3:
                ret.insert(0, tenM[m]).insert(0, tenM[m]).insert(0, tenM[m]);
                break;
            case 4:
                ret.insert(0, fiveM[m]).insert(0, tenM[m]);
                break;
            case 5:
                ret.insert(0, fiveM[m]);
                break;
            case 6:
                ret.insert(0, tenM[m]).insert(0, fiveM[m]);
                break;
            case 7:
                ret.insert(0, tenM[m]).insert(0, tenM[m]).insert(0, fiveM[m]);
                break;
            case 8:
                ret.insert(0, tenM[m]).insert(0, tenM[m]).insert(0, tenM[m]).insert(0, fiveM[m]);
                break;
            case 9:
                ret.insert(0, tenM[m+1]).insert(0, tenM[m]);
                break;
            }
            m += 1;
            t /= 10;
        }
        
        return ret.toString();
    }

    /***
     * Runtime: 5 ms, faster than 37.72% of Java online submissions for Integer to Roman.
     * Memory Usage: 45.1 MB, less than 5.00% of Java online submissions for Integer to Roman.
     * @param num
     * @return
     */
    public String intToRoman2(int num) {
        String fiveM[] = {"V", "L", "D"};
        String tenM[] = {"I", "X", "C", "M"};
        StringBuilder ret = new StringBuilder();
        int m = 0, y = 0, t = num;
        while(t > 0) {
            y = t % 10;
            if(y == 4){
                ret.insert(0, fiveM[m]).insert(0, tenM[m]);
            } else if(y == 9) {
                ret.insert(0, tenM[m+1]).insert(0, tenM[m]);
            } else if(y < 4) {
                for(int i = 0; i < y; i ++) {
                    ret.insert(0, tenM[m]);
                }
            } else {
                for(int i = 5; i < y; i ++) {
                    ret.insert(0, tenM[m]);
                }
                ret.insert(0, fiveM[m]);
            }
            
            m += 1;
            t /= 10;
        }
        
        return ret.toString();
    }

    /***
     * Runtime: 8 ms, faster than 18.88% of Java online submissions for Integer to Roman.
     * Memory Usage: 47.2 MB, less than 5.00% of Java online submissions for Integer to Roman.
     * @param num
     * @return
     */
    public String intToRoman3(int num) {
        //Stack stack;
        Deque<StringBuilder> deque = new ArrayDeque<StringBuilder>();
        String fiveM[] = {"V", "L", "D"};
        String tenM[] = {"I", "X", "C", "M"};
        StringBuilder ret = new StringBuilder();
        int m = 0, y = 0, t = num;
        while(t > 0) {
            y = t % 10;
            StringBuilder digit = new StringBuilder();
            if(y == 4){
                digit.append(tenM[m]).append(fiveM[m]);
            } else if(y == 9) {
                digit.append(tenM[m]).append(tenM[m+1]);
            } else if(y < 4) {
                for(int i = 0; i < y; i ++) {
                    digit.append(tenM[m]);
                }
            } else {
                digit.append(fiveM[m]);
                for(int i = 5; i < y; i ++) {
                    digit.append(tenM[m]);
                }
            }
            deque.addFirst(digit);
            m += 1;
            t /= 10;
        }
        while(!deque.isEmpty()) {
            ret.append(deque.removeFirst());
        }
        
        return ret.toString();
    }

    /***
     * Runtime: 6 ms, faster than 50.18% of Java online submissions for Roman to Integer.
     * Memory Usage: 45.4 MB, less than 5.48% of Java online submissions for Roman to Integer.
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        char roma[] = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int val[] = {1000, 500, 100, 50, 10, 5, 1};
        Map<Character, Integer> romaval = new HashMap<Character, Integer>();
        for(int i = 0; i < val.length; i++) {
            romaval.put(roma[i], val[i]);
        }
        int ret = 0, pre = 0, cur = 0;
        char sarry[] = s.toCharArray();
        for(char c: sarry) {
            cur = romaval.get(c);
            if(pre < cur) {
                ret = ret + cur - 2 * pre;
            } else {
                ret += cur;
            }
            pre = cur;
        }
        return ret;
    }

    private int getValue(char c) {
        switch(c) {
        case 'M': return 1000;
        case 'D': return 500;
        case 'C': return 100;
        case 'L': return 50;
        case 'X': return 10;
        case 'V': return 5;
        case 'I': return 1;
        default:
                return 0;
        }
    }

    /***
     * Runtime: 4 ms, faster than 72.91% of Java online submissions for Roman to Integer.
     * Memory Usage: 44.8 MB, less than 5.48% of Java online submissions for Roman to Integer.
     * @param s
     * @return
     */
    public int romanToInt2(String s) {
        int ret = 0, pre = 0, cur = 0;
        char sarry[] = s.toCharArray();
        for(char c: sarry) {
            cur = getValue(c);
            if(pre < cur) {
                ret = ret + cur - 2 * pre;
            } else {
                ret += cur;
            }
            pre = cur;
        }
        return ret;
    }

    /***
     * Runtime: 3 ms, faster than 99.99% of Java online submissions for Roman to Integer.
     * Memory Usage: 45.2 MB, less than 5.48% of Java online submissions for Roman to Integer.
     * @param s
     * @return
     */
    public int romanToInt3(String s) {
        int ret = 0, pre = 0, cur = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cur = getValue(c);
            if(pre < cur) {
                ret = ret + cur - 2 * pre;
            } else {
                ret += cur;
            }
            pre = cur;
        }
        return ret;
    }

}
