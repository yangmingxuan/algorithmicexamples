package algorithms.string;

import java.util.Stack;

/***
 * Implement a basic calculator to evaluate a simple expression string.

    The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
    
    The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.
    
    You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].
    
    Some examples:
    
    "1 + 1" = 2
    " 6-4 / 2 " = 4
    "2*(5+5*2)/3+(6/2+8)" = 21
    "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
     
    
    Note: Do not use the eval built-in library function.
 * @author miche
 *
 */
public class BasicCalculatorIII {

    public int calculate(String s) {
        if(s.isEmpty()) return 0;
        int sum = 0;
        Stack<Character> op = new Stack<Character>();
        Stack<Integer> digit = new Stack<Integer>();
        char[] chs = s.toCharArray();
        for(char ch : chs) {
            if(ch == ' ') continue;
            if(ch >= '0' && ch <= '9') {
                sum = sum * 10 + (ch - '0');
            } else {
                //operator
                if(ch == '(') {
                    op.add(ch);
                } else if(op.isEmpty()) {
                    digit.add(sum);
                    op.add(ch);
                    sum = 0;
                } else {
                    if(priOp(op.peek(), ch) == 0) {
                        //a match ()
                        op.pop();
                    } else if(priOp(op.peek(), ch) < 0) {
                        //can not calculate firstly
                        digit.add(sum);
                        op.add(ch);
                        sum = 0;
                    } else {
                        //calculate
                        while(!op.isEmpty() && priOp(op.peek(), ch) > 0) {
                            int firstnum = digit.pop();
                            char lastOp = op.pop();
                            sum = cal(firstnum, lastOp, sum);
                        }
                        if(!op.isEmpty() && priOp(op.peek(), ch) == 0) {
                            op.pop();
                        } else {
                            digit.add(sum);
                            op.add(ch);
                            sum = 0;
                        }
                    }
                }
            }
        }
        //calculate last item
        while(!op.isEmpty()) {
            int firstnum = digit.pop();
            char lastOp = op.pop();
            sum = cal(firstnum, lastOp, sum);
        }
        return sum;
    }

    /***
     * if op1=( and op2=) return 0
     * if op1 takes precedence over op2 return 1 else return -1
     * @param op1
     * @param op2
     * @return
     */
    public int priOp(char op1, char op2) {
        switch(op1) {
        case '(':
            if(op2 == ')') {
                return 0;
            } else {
                return -1;
            }
        case '+':
        case '-':
            if(op2 == '+' || op2 == '-' || op2 == ')') {
                return 1;
            } else {
                return -1;
            }
        case '*':
        case '/':
            if(op2 == '(') {
                return -1;
            } else {
                return 1;
            }
        //case ')':
            //error
        }
        return -2;
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
            return num1 / num2; //if num2 == 0 error
        }
        return 0;
    }
}
