package algorithms.sort;

/***
 * Given an unsorted integer array, find the smallest missing positive integer.

    Example 1:
    
    Input: [1,2,0]
    Output: 3
    Example 2:
    
    Input: [3,4,-1,1]
    Output: 2
    Example 3:
    
    Input: [7,8,9,11,12]
    Output: 1
    Note:
    
    Your algorithm should run in O(n) time and uses constant extra space.
 * @author miche
 *
 */
public class FirstMissingPositive {

    public FirstMissingPositive() {
        // TODO Auto-generated constructor stub
    }

    /***
     * In the perfect case, the positive integer n would be the nth element (that is, the array index is n-1)
     * Line up all positive integers and find the first one that doesn't match
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int len=nums.length, num, idx;
        for(int i = 0; i < len; i ++) {
            num = nums[i];
            idx = i;
            while(num != idx+1 && num > 0 && num <= len) {
                idx = num - 1;
                int tmp = nums[idx];
                nums[idx] = num;
                nums[i] = tmp;
                num = nums[i];
            }
        }
        for(int i = 0; i < len; i++) {
            if(nums[i] != i+1) {
                return i+1;
            }
        }

        return len+1;
    }
}
