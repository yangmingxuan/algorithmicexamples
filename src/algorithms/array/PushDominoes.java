package algorithms.array;
/***
 * There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.

    After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
    
    When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
    
    For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
    
    You are given a string dominoes representing the initial state where:
    
    dominoes[i] = 'L', if the ith domino has been pushed to the left,
    dominoes[i] = 'R', if the ith domino has been pushed to the right, and
    dominoes[i] = '.', if the ith domino has not been pushed.
    Return a string representing the final state.
    
     
    
    Example 1:
    
    Input: dominoes = "RR.L"
    Output: "RR.L"
    Explanation: The first domino expends no additional force on the second domino.
    Example 2:
    
    
    Input: dominoes = ".L.R...LR..L.."
    Output: "LL.RR.LLRRLL.."
     
    
    Constraints:
    
    n == dominoes.length
    1 <= n <= 105
    dominoes[i] is either 'L', 'R', or '.'.
 * @author miche
 *
 */
public class PushDominoes {

    /***
     * Explanation: 从左往右标上右向的正值，再从右往左标上左向的负值，最终为正值的倒向右，负值倒向左，0为不动。
     * From left to right, mark the positive value to the right, and then mark the negative value to the left from right to left, and finally the positive value is reversed to the right, the negative value is reversed to the left, and 0 means no movement.
     * @param dominoes
     * @return
     */
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        int[] directions = new int[n];
        
        int direction = 0;
        //from left to right
        for(int i = 0; i < n; i++) {
            if(dominoes.charAt(i) == 'R') direction = n;
            else if(dominoes.charAt(i) == 'L') direction = 0;
            else direction = Math.max(--direction, 0);
            directions[i] += direction;
        }
        
        direction = 0;
        //from right to left
        for(int i = n-1; i >= 0; i--) {
            if(dominoes.charAt(i) == 'L') direction = n;
            else if(dominoes.charAt(i) == 'R') direction = 0;
            else direction = Math.max(--direction, 0);
            directions[i] -= direction;
        }
        
        StringBuilder ans = new StringBuilder();
        for(int d : directions) {
            ans.append(d > 0 ? 'R' : d < 0 ? 'L' : '.');
        }
        
        return ans.toString();
    }

    public String pushDominoes2(String dominoes) {
        int buffCount = 0;
        boolean isinright = false;
        StringBuilder ans = new StringBuilder();
        
        for(char ch : dominoes.toCharArray()) {
            if(ch == '.') {
                buffCount++;
            } else if(ch == 'R') {
                if(isinright) {
                    for(int i = 0; i < buffCount; i++) ans.append('R');
                } else {
                    for(int i = 0; i < buffCount; i++) ans.append('.');
                    isinright = true;
                }
                buffCount = 1;
            } else { //ch == 'L'
                buffCount++;
                if(isinright) {
                    int s = buffCount / 2;
                    int y = buffCount % 2;
                    for(int i = 0; i < s; i++) ans.append('R');
                    if(y == 1) ans.append('.');
                    for(int i = 0; i < s; i++) ans.append('L');
                    isinright = false;
                } else {
                    for(int i = 0; i < buffCount; i++) ans.append('L');
                }
                buffCount = 0;
            }
        }
        
        if(buffCount > 0) {
            if(isinright) {
                for(int i = 0; i < buffCount; i++) ans.append('R');
            } else {
                for(int i = 0; i < buffCount; i++) ans.append('.');
            }
        }
        
        return ans.toString();
    }
}
