package algorithms.trie;

/***
 * Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.



    Example 1:
    
    Input: nums = [3,10,5,25,2,8]
    Output: 28
    Explanation: The maximum result is 5 XOR 25 = 28.
    Example 2:
    
    Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
    Output: 127
    
    
    Constraints:
    
    1 <= nums.length <= 2 * 10^5
    0 <= nums[i] <= 2^31 - 1

 * @author miche
 *
 */
public class MaximumXORofTwoNumbersinanArray {

    public int findMaximumXOR(int[] nums) {
        TrieBit root = new TrieBit();
        int ans = 0;
        for(int num : nums) {
            //insert the num(binary mode) in to trie
            TrieBit node = root;
            for(int i = 30; i >= 0; i--) {
                int ithbit = (num >> i) & 1;  //the ith bit of num
                if(node.bits[ithbit] == null) {
                    node.bits[ithbit] = new TrieBit();
                }
                node = node.bits[ithbit];
            }
            
            //find the max xor value
            node = root;
            int curmax = 0;
            for(int i = 30; i >= 0; i--) {
                int ithbit = (num >> i) & 1;  //the ith bit of num
                int wanttoxorbit = 1 - ithbit; //xor the other bit always bigger 0->1 or 1-0
                if(node.bits[wanttoxorbit] == null) {
                    //if not exist other bit, only choose the same bit
                    wanttoxorbit = ithbit;
                }
                curmax = (curmax << 1) | (wanttoxorbit ^ ithbit);
                node = node.bits[wanttoxorbit];
            }
            ans = Math.max(ans, curmax);
        }
        
        return ans;
    }

}

class TrieBit {
    TrieBit[] bits;
    
    public TrieBit() {
        this.bits = new TrieBit[2];
    }
}
