package algorithms.array;

/***
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


    The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
    
    Example:
    
    Input: [0,1,0,2,1,0,1,3,2,1,2,1]
    Output: 6
 * @author miche
 *
 */
public class TrappingRainWater {

    public TrappingRainWater() {
        // TODO Auto-generated constructor stub
    }

    public int trap(int[] height) {
        int leftBorder = 0, rightBorder = height.length - 1;
        int leftCursor = leftBorder, rightCursor = rightBorder;
        int totalWater = 0;
        while(leftCursor < rightCursor) {
            if(height[leftBorder] < height[rightBorder]) {
                if(height[leftBorder] > height[leftCursor]) {
                    totalWater += height[leftBorder] - height[leftCursor];
                }
                leftCursor ++;
                if(height[leftBorder] <= height[leftCursor]) {
                    leftBorder = leftCursor;
                }
            } else {
                if(height[rightBorder] > height[rightCursor]) {
                    totalWater += height[rightBorder] - height[rightCursor];
                }
                rightCursor --;
                if(height[rightBorder] <= height[rightCursor]) {
                    rightBorder = rightCursor;
                }
            }
        }

        return totalWater;
    }
}
