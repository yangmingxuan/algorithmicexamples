package algorithms.array;

/***
 * A rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) are the coordinates of its bottom-left corner, and (x2, y2) are the coordinates of its top-right corner.

    Two rectangles overlap if the area of their intersection is positive.  To be clear, two rectangles that only touch at the corner or edges do not overlap.
    
    Given two (axis-aligned) rectangles, return whether they overlap.
    
    Example 1:
    
    Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
    Output: true
    Example 2:
    
    Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
    Output: false
    Notes:
    
    Both rectangles rec1 and rec2 are lists of 4 integers.
    All coordinates in rectangles will be between -10^9 and 10^9.
 * @author miche
 *
 */
public class RectangleOverlap {

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int[] left;
        int[] right;
        if(rec1[0] < rec2[0]) {
            left = rec1;
            right = rec2;
        } else {
            left = rec2;
            right = rec1;
        }
        if(right[1] >= left[1]) {
            if(left[2] > right[0] && left[3] > right[1]) {
                return true;
            } else {
                return false;
            }
        } else {
            if(left[2] > right[0] && right[3] > left[1]) {
                return true;
            } else {
                return false;
            }
        }
    }

    //if all left or all right or all top or all bottom is not overlap
    public boolean isRectangleOverlap2(int[] rec1, int[] rec2) {
        return !(  rec1[2] <= rec2[0] //all left
                || rec1[0] >= rec2[2] //all right
                || rec1[3] <= rec2[1] //all bottom
                || rec1[1] >= rec2[3] //all top
                );
    }
}