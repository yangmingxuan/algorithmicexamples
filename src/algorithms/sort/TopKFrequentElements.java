package algorithms.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import javafx.util.Pair;

/***
 * Given a non-empty array of integers, return the k most frequent elements.

    Example 1:
    
    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]
    Example 2:
    
    Input: nums = [1], k = 1
    Output: [1]
    Note:
    
    You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
    Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
    It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
    You can return the answer in any order.
 * @author miche
 *
 */
public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        for(int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((a, b) -> count.get(a) - count.get(b));
        for(Map.Entry<Integer, Integer>keys : count.entrySet()) {
            queue.offer(keys.getKey());
            if(queue.size() > k) {
                queue.poll();
            }
        }
        return queue.stream().mapToInt(i->i).toArray();
    }

    public int[] topKFrequent2(int[] nums, int k) {
        Arrays.parallelSort(nums);
        int count = 1;
        int prevNum = nums[0];
        PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<Pair<Integer, Integer>>((a, b) -> b.getValue() - a.getValue());
        for(int i = 1; i < nums.length; i++) {
            if(prevNum == nums[i]) {
                count++;
            } else {
                queue.offer(new Pair<Integer, Integer>(prevNum, count));
                count = 1;
                prevNum = nums[i];
            }
        }
        queue.offer(new Pair<Integer, Integer>(prevNum, count));
        int i = 0;
        int[] ans = new int[k];
        while(i < k) {
            ans[i++] = queue.poll().getKey();
        }
        return ans;
    }
}
