package algorithms.array;

/***
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

    Note: You may not slant the container and n is at least 2.

    Example:
    
    Input: [1,8,6,2,5,4,8,3,7]
    Output: 49
 * @author miche
 *
 */
public class ContainerWithMostWater {

    public ContainerWithMostWater() {
        // TODO Auto-generated constructor stub
    }

    /***
     * Runtime: 205 ms, faster than 18.51% of Java online submissions for Container With Most Water.
     * Memory Usage: 40.5 MB, less than 77.56% of Java online submissions for Container With Most Water.
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        int len = height.length;
        int maxarea = 0;
        for(int i = 0; i < len -1; i++) {
            for(int j = i+1; j < len; j++) {
                int h = Math.min(height[i], height[j]);
                int tmp = h*(j-i);
                if(tmp > maxarea) maxarea = tmp;
            }
        }
        return maxarea;
    }

    /***
     * Runtime: 2 ms, faster than 95.16% of Java online submissions for Container With Most Water.
     * Memory Usage: 40.1 MB, less than 93.59% of Java online submissions for Container With Most Water.
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while(l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if(height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return maxarea;
    }
}
