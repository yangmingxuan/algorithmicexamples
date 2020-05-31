package algorithms.string;

import java.util.Stack;

/***
 * Implement a basic calculator to evaluate a simple expression string.

    The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
    
    Example 1:
    
    Input: "3+2*2"
    Output: 7
    Example 2:
    
    Input: " 3/2 "
    Output: 1
    Example 3:
    
    Input: " 3+5 / 2 "
    Output: 5
    Note:
    
    You may assume that the given expression is always valid.
    Do not use the eval built-in library function.
 * @author miche
 *
 */
public class BasicCalculatorII {

    public int calculate(String s) {
        if(s.isEmpty()) return 0;
        Stack<Character> operator = new Stack<Character>();
        Stack<Integer> digit = new Stack<Integer>();
        int sum = 0;
        char[] chs = s.toCharArray();
        for(char ch : chs) {
            if(ch == ' ') continue;
            if(ch >= '0' && ch <= '9') {
                sum = sum * 10 + (ch - '0');
            } else {
                //operator
                if(operator.isEmpty()) {
                    digit.add(sum);
                    operator.add(ch);
                    sum = 0;
                } else {
                    if(comparePrevious(ch, operator.peek()) > 0) {
                        //can not calculate firstly
                        digit.add(sum);
                        operator.add(ch);
                        sum = 0;
                    } else {
                        //calculate
                        while(!operator.isEmpty() && comparePrevious(ch, operator.peek()) <= 0) {
                            int firstnumber = digit.pop();
                            char lastOper = operator.pop();
                            sum = cal(firstnumber, lastOper, sum);
                        }
                        digit.add(sum);
                        operator.add(ch);
                        sum = 0;
                    }
                }
            }
        }
        //calculate last time
        while(!operator.isEmpty()) {
            int firstnumber = digit.pop();
            char lastOper = operator.pop();
            sum = cal(firstnumber, lastOper, sum);
        }
        return sum;
    }

    public int comparePrevious(char oper1, char oper2) {
        switch(oper1) {
        case '+':
            if(oper2 == '*' || oper2 == '/') {
                return -1;
            } else {
                return 0;
            }
        case '-':
            if(oper2 == '*' || oper2 == '/') {
                return -1;
            } else {
                return 0;
            }
        case '*':
            if(oper2 == '*' || oper2 == '/') {
                return 0;
            } else {
                return 1;
            }
        case '/':
            if(oper2 == '*' || oper2 == '/') {
                return 0;
            } else {
                return 1;
            }
        }
        return 0;
    }

    public int cal(int num1, char operator, int num2) {
        switch(operator) {
        case '+':
            return num1 + num2;
        case '-':
            return num1 - num2;
        case '*':
            return num1 * num2;
        case '/':
            return num1 / num2;
        }
        return 0;
    }
}
