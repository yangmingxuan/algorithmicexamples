package algorithms.array;
/***
 * In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith domino. (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

    We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.
    
    Return the minimum number of rotations so that all the values in tops are the same, or all the values in bottoms are the same.
    
    If it cannot be done, return -1.
    
     
    
    Example 1:
    tops = [2,1,2,4,2,2]
    bottoms = [5,2,6,2,3,2]
    
    rotate the 2nd and 4th(swap tops[1]bottoms[1] and tops[3]bottoms[3):
    tops = [2,2,2,2,2,2]
    bottoms = [5,1,6,4,3,2]
    
    
    Input: tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
    Output: 2
    Explanation: 
    The first figure represents the dominoes as given by tops and bottoms: before we do any rotations.
    If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
    Example 2:
    
    Input: tops = [3,5,1,2,3], bottoms = [3,6,3,3,4]
    Output: -1
    Explanation: 
    In this case, it is not possible to rotate the dominoes to make one row of values equal.
     
    
    Constraints:
    
    2 <= tops.length <= 2 * 10^4
    bottoms.length == tops.length
    1 <= tops[i], bottoms[i] <= 6
 * @author miche
 *
 */
public class MinimumDominoRotationsForEqualRow {

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int ans = tops.length+1;
        int[] topCount = new int[6];
        int[] bottomCount = new int[6];
        int[] sameCount = new int[6];
        
        //count the tops value
        for(int val : tops) {
            topCount[val-1]++;
        }
        //count the bottom value
        for(int i = 0; i < bottoms.length; i++) {
            bottomCount[bottoms[i]-1]++;
            if(bottoms[i] == tops[i]) { //Swapping values is meaningless if the values are the same
                sameCount[bottoms[i]-1]++;
            }
        }
        
        for(int i = 0; i < 6; i++) {
            if(topCount[i]+bottomCount[i]-sameCount[i] >= tops.length) {
                ans = Math.min(ans, Math.min(topCount[i], bottomCount[i])-sameCount[i]);
            }
        }
        
        return ans==tops.length+1?-1:ans;
    }

}
