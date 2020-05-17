package algorithms.array;

import java.util.LinkedList;
import java.util.Queue;

/***
 * On an N x N board, the numbers from 1 to N*N are written boustrophedonically starting from the bottom left of the board, and alternating direction each row.  For example, for a 6 x 6 board, the numbers are written as follows:

    {36, 35, 34, 33, 32, 31,
     25, 26, 27, 28, 29, 30,
     24, 23, 22, 21, 20, 19,
     13, 14, 15, 16, 17, 18,
     12, 11, 10, 9 , 8 , 7 ,
     1 , 2 , 3 , 4 , 5 , 6 }

    You start on square 1 of the board (which is always in the last row and first column).  Each move, starting from square x, consists of the following:
    
    You choose a destination square S with number x+1, x+2, x+3, x+4, x+5, or x+6, provided this number is <= N*N.
    (This choice simulates the result of a standard 6-sided die roll: ie., there are always at most 6 destinations, regardless of the size of the board.)
    If S has a snake or ladder, you move to the destination of that snake or ladder.  Otherwise, you move to S.
    A board square on row r and column c has a "snake or ladder" if board[r][c] != -1.  The destination of that snake or ladder is board[r][c].
    
    Note that you only take a snake or ladder at most once per move: if the destination to a snake or ladder is the start of another snake or ladder, you do not continue moving.  (For example, if the board is `[[4,-1],[-1,3]]`, and on the first move your destination square is `2`, then you finish your first move at `3`, because you do not continue moving to `4`.)
    
    Return the least number of moves required to reach square N*N.  If it is not possible, return -1.
    
    Example 1:
    
    Input: [
    [-1,-1,-1,-1,-1,-1],
    [-1,-1,-1,-1,-1,-1],
    [-1,-1,-1,-1,-1,-1],
    [-1,35,-1,-1,13,-1],
    [-1,-1,-1,-1,-1,-1],
    [-1,15,-1,-1,-1,-1]]
    Output: 4
    Explanation: 
    At the beginning, you start at square 1 [at row 5, column 0].
    You decide to move to square 2, and must take the ladder to square 15.
    You then decide to move to square 17 (row 3, column 5), and must take the snake to square 13.
    You then decide to move to square 14, and must take the ladder to square 35.
    You then decide to move to square 36, ending the game.
    It can be shown that you need at least 4 moves to reach the N*N-th square, so the answer is 4.
    Note:
    
    2 <= board.length = board[0].length <= 20
    board[i][j] is between 1 and N*N or is equal to -1.
    The board square with number 1 has no snake or ladder.
    The board square with number N*N has no snake or ladder.
 * @author miche
 *
 */
public class SnakesandLadders {

    /*
     * num from 1 to N*N, 1 is board[N-1][0], N*N is board[0][N-1], N+1 is board[N-2][N-1];
     * ==>
     * row = N - (num-1)/N - 1;
     * col = (num-1)/N % 2 == 0 ? (num-1)%N : N - (num-1)%N - 1
     * or 
     * col = (N-row) % 2 == 0 ? N - (num-1) % N - 1 : (num-1) % N
     */
    
    public int snakesAndLadders(int[][] board) {
        int N = board.length;
        int min = N * N, moves = 0;
        boolean[] visited = new boolean[N*N];
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        visited[0] = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int oldNum = queue.poll();
                if(oldNum == N*N) {
                    min = Math.min(min, moves);
                }
                //Roll the dice
                for(int dice = 1; dice <= 6; dice++) {
                    int num = oldNum + dice;
                    if(num > N*N) {
                        break;
                    }
                    if(!visited[num-1]) {
                        visited[num-1] = true;
                        int row = N - (num-1)/N - 1;
                        int col = (num-1)/N % 2 == 0 ? (num-1)%N : N - (num-1)%N - 1 ;
                        if(board[row][col] == -1) {
                            queue.offer(num);
                        } else {
                            queue.offer(board[row][col]);
                        }
                    }
                }
            } //end for once poll last queue
            moves++;
        }
        
        return min == N*N ? -1 : min;
    }

}
