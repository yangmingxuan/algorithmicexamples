package algorithms.string;

/***
 * Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.

    If there is no solution for the equation, return "No solution".
    
    If there are infinite solutions for the equation, return "Infinite solutions".
    
    If there is exactly one solution for the equation, we ensure that the value of x is an integer.
    
    Example 1:
    Input: "x+5-3+x=6+x-2"
    Output: "x=2"
    Example 2:
    Input: "x=x"
    Output: "Infinite solutions"
    Example 3:
    Input: "2x=x"
    Output: "x=0"
    Example 4:
    Input: "2x+3x-6x=x+2"
    Output: "x=-1"
    Example 5:
    Input: "x=x+2"
    Output: "No solution"
 * @author miche
 *
 */
public class SolvetheEquation {

    public String solveEquation(String equation) {
        if(equation.isEmpty()) return "No solution";
        int xCount = 0, numSum = 0;
        String[] expressions = equation.split("=");
        //the left of "="
        for(String numorx : expressions[0].split("(?=\\+)|(?=-)")) {
            if(numorx.indexOf('x') >= 0) {
                //if there is x parameter
                xCount += countX(numorx);
            } else {
                //if there is number
                numSum -= Integer.parseInt(numorx);
            }
        }

        //the right of "="
        for(String numorx : expressions[1].split("(?=[-+])")) {
            if(numorx.indexOf('x') >= 0) {
                //if there is x parameter
                xCount -= countX(numorx);
            } else {
                //if there is number
                numSum += Integer.parseInt(numorx);
            }
        }
        
        if(xCount == 0) {
            if(numSum == 0) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        }
        
        return "x="+numSum/xCount;
    }

    public int countX(String xs) {
        String para;
        if(xs.length() > 1 && Character.isDigit(xs.charAt(xs.length()-2))) {
            //the xs is like "2x" or "-7x" or "15x"
            para = xs.replace("x", "");
        } else {
            //the xs is "x" or "-x"
            para = xs.replace("x", "1");
        }
        return Integer.parseInt(para);
    }
}
