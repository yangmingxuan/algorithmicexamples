package algorithms.array;

public class LargestRectangleinHistogram {

    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0) return 0;
        int n = heights.length;
        int[] left_i = new int[n]; // index of the first bar the left that is lower than current
        int[] right_i = new int[n];// index of the first bar the right that is lower than current
        left_i[0] = -1;
        right_i[n - 1] = n;
        int res = 0;
        for (int i = 1; i < n; i++) {
            int tmp = i - 1;
            while (tmp >= 0 && heights[tmp] >= heights[i]){
                tmp = left_i[tmp];
            }
            left_i[i] = tmp;
        }
        for (int i = n - 2; i >= 0; i--) {
            int tmp = i + 1;
            while (tmp < n && heights[tmp] >= heights[i]){
                tmp = right_i[tmp];
            }
            right_i[i] = tmp;
        }
        for (int i = 0; i < n; i++){
            res = Math.max(res, (right_i[i] - left_i[i] - 1) * heights[i]);
        }

        return res;
    }

    public static void main(String[] argv) {
        int[] heights = {2,1,5,6,2,3};
        LargestRectangleinHistogram lrh = new LargestRectangleinHistogram();
        int max = lrh.largestRectangleArea(heights);
        System.out.print(max);
    }
}
