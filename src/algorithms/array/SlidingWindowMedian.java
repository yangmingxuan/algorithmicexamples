package algorithms.array;

import java.util.ArrayList;
import java.util.List;

/***
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

    Examples:
    [2,3,4] , the median is 3
    
    [2,3], the median is (2 + 3) / 2 = 2.5
    
    Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
    
    For example,
    Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
    
    Window position                Median
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       1
     1 [3  -1  -3] 5  3  6  7       -1
     1  3 [-1  -3  5] 3  6  7       -1
     1  3  -1 [-3  5  3] 6  7       3
     1  3  -1  -3 [5  3  6] 7       5
     1  3  -1  -3  5 [3  6  7]      6
    Therefore, return the median sliding window as [1,-1,-1,3,5,6].
    
    Note:
    You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
    Answers within 10^-5 of the actual value will be accepted as correct.
 * @author miche
 *
 */
public class SlidingWindowMedian {

    /***
     * use a sorted list, when moving the window, insert the new number, remove the old number,
     * and get the median number of the sorted list
     * @param nums
     * @param k
     * @return
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k > nums.length) return null;
        double[] ans = new double[nums.length-k+1];
        List<Double> list = new ArrayList<Double>();
        for(int i = 0; i < nums.length; i++) {
            insert((double)nums[i], list);
            if(i >= k - 1) {
                ans[i-k+1] = (list.get(k/2) + list.get((k-1)/2)) /2;
                remove((double)nums[i-k+1], list);
            }
        }
        return ans;
    }

    public void insert(double num, List<Double> list) {
        int l = 0, r = list.size();
        while(l < r) {
            int mid = (l+r) / 2;
            if(list.get(mid) < num) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        list.add(l, num);
    }

    public void remove(double num, List<Double> list) {
        int l = 0, r = list.size();
        while(l < r) {
            int mid = (l+r) / 2;
            if(list.get(mid) < num) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        list.remove(l);
    }
}
