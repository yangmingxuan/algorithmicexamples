package algorithms.array;

/***
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

    Example 1:
    
    Input: [1,3,4,2,2]
    Output: 2
    Example 2:
    
    Input: [3,1,3,4,2]
    Output: 3
    Note:
    
    You must not modify the array (assume the array is read only).
    You must use only constant, O(1) extra space.
    Your runtime complexity should be less than O(n^2).
    There is only one duplicate number in the array, but it could be repeated more than once.
 * @author miche
 *
 */
public class FindtheDuplicateNumber {

    /***
     * Space O(1)  Time O(n^2 / 2)
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        for(int i = 0; i < len-1; i++) {
            for(int j = i+1; j < len; j++) {
                if(nums[j] == nums[i]) {
                    return nums[j];
                }
            }
        }
        return 0;
    }

    /**
     * Space O(n)  Time O(n) Does not meet the requirements of the question
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        int len = nums.length;
        int[] counts = new int[len];
        for(int i = 0; i < len; i++) {
            counts[nums[i] - 1] += 1;
            if(counts[nums[i] - 1] > 1) {
                return nums[i];
            }
        }
        return 0;
    }

    /***
     * just like LinkedListCycleII
     * @param nums
     * @return
     */
    public int findDuplicate3(int[] nums) {
        int slow = nums[0], fast = nums[0];
        //find the intersection of two runner
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);

        slow = nums[0];
        //find the entrance of the cycle
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
}
