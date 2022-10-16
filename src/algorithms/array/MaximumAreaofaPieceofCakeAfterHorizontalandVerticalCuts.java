package algorithms.array;

import java.util.Arrays;

/****
 * You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:

    horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
    verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
    Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a large number, return this modulo 10^9 + 7.
    
     
    
    Example 1:
    
    
    Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
    Output: 4 
    Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
    Example 2:
    
    
    Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
    Output: 6
    Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.
    Example 3:
    
    Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
    Output: 9
     
    
    Constraints:
    
    2 <= h, w <= 10^9
    1 <= horizontalCuts.length <= min(h - 1, 10^5)
    1 <= verticalCuts.length <= min(w - 1, 10^5)
    1 <= horizontalCuts[i] < h
    1 <= verticalCuts[i] < w
    All the elements in horizontalCuts are distinct.
    All the elements in verticalCuts are distinct.
    
 * @author miche
 *
 */
public class MaximumAreaofaPieceofCakeAfterHorizontalandVerticalCuts {

    /***
     * Sort the arrays, then compute the maximum difference between two consecutive elements for horizontal cuts and vertical cuts.
     * The answer is the product of these maximum values in horizontal cuts and vertical cuts.
     * @param h
     * @param w
     * @param horizontalCuts
     * @param verticalCuts
     * @return
     */
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int maxHdistance = horizontalCuts[0];
        for(int i = 1; i < horizontalCuts.length; i++) {
            maxHdistance = Math.max(maxHdistance, horizontalCuts[i]-horizontalCuts[i-1]);
        }
        maxHdistance = Math.max(maxHdistance, h-horizontalCuts[horizontalCuts.length-1]);
        int maxVdistance = verticalCuts[0];
        for(int i = 1; i < verticalCuts.length; i++) {
            maxVdistance = Math.max(maxVdistance, verticalCuts[i]-verticalCuts[i-1]);
        }
        maxVdistance = Math.max(maxVdistance, w-verticalCuts[verticalCuts.length-1]);
        
        return (int)((long)maxHdistance * (long)maxVdistance % 1000000007);
    }

}
