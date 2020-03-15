package algorithms.array;

/***
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

    Each element in the array represents your maximum jump length at that position.
    
    Your goal is to reach the last index in the minimum number of jumps.
    
    Example:
    
    Input: [2,3,1,1,4]
    Output: 2
    Explanation: The minimum number of jumps to reach the last index is 2.
        Jump 1 step from index 0 to 1, then 3 steps to the last index.
    Note:
    
    You can assume that you can always reach the last index.
 * @author miche
 *
 */
public class JumpGameII {

    public JumpGameII() {
        // TODO Auto-generated constructor stub
    }

    public int jump(int[] nums) {
        int jumps = 0, step = 0, pos = 0;
        for(int i = 0; i < nums.length-1; i++) {
            step = Math.max(nums[i]+i, step);
            if(pos == i) {
                jumps++;
                pos = step;
            }
        }

        return jumps;
    }

    /***
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.

        Each element in the array represents your maximum jump length at that position.
        
        Determine if you are able to reach the last index.
        
        Example 1:
        
        Input: [2,3,1,1,4]
        Output: true
        Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
        Example 2:
        
        Input: [3,2,1,0,4]
        Output: false
        Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int lastPosition = nums.length - 1;
        for(int i = nums.length-1; i >= 0; i--) {
            if(nums[i]+i >= lastPosition) {
                lastPosition = i;
            }
        }
        return lastPosition == 0;
    }

    public static void main(String[] argv) {
        int[] nums = {2,3,1,1,4};
        JumpGameII jg = new JumpGameII();
        jg.canJump(nums);
    }
}
