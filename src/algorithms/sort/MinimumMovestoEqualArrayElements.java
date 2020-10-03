package algorithms.sort;

/***
 * Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.

    Example:
    
    Input:
    [1,2,3]
    
    Output:
    3
    
    Explanation:
    Only three moves are needed (remember each move increments two elements):
    
    [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * @author MichelYang
 *
 */
public class MinimumMovestoEqualArrayElements {

    /***
     * Example: [3,1,4] the minimum is 1
     * [3,1,4]==>[3,2,5]==>[3,3,6]==>[4,4,6]==>[5,5,6]==>[6,6,6]
     * Explanation: 从数列中找到最小的数，从数列第一个数开始，第一个数不动，其它的N-1个数开始动diff次(diff=N0-mininum), 
     * 此时第一个数就与minimum相等，接着是第二个数，此时第二个数N1=N1原值+diff，而最小值为minimum+diff, 
     * 第二个数不动，其它的数动diff1次(diff1=(N1+diff)-(minimum+diff)=N1-minimum)，
     * 此时，第一个数，第二个数就与minimum相等，以此类推，直至所有数值都相等
     * Explanation: Find the minimum number from the sequence, starting from the first number in the sequence, the first number does not move, the other N-1 numbers start to move diff times (diff=N0-mininum),
     * At this time, the first number is equal to the minimum, and then the second number. At this time, the second number N1=N1 original value+diff, and the minimum value is minimum+diff,
     * The second number does not move, the other numbers move diff1 times (diff1=(N1+diff)-(minimum+diff)=N1-minimum),
     * At this time, the first number and the second number are equal to the minimum, and so on, until all values are equal
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE, moves = 0;
        for(int num : nums) {
            min = Math.min(min, num);
        }
        for(int num : nums) {
            moves += (num - min);
        }
        return moves;
    }

}
