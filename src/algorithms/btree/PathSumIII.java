package algorithms.btree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * You are given a binary tree in which each node contains an integer value.

    Find the number of paths that sum to a given value.
    
    The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
    
    The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
    
    Example:
    
    root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
    
          10
         /  \
        5   -3
       / \    \
      3   2   11
     / \   \
    3  -2   1
    
    Return 3. The paths that sum to 8 are:
    
    1.  5 -> 3
    2.  5 -> 2 -> 1
    3. -3 -> 11
 * @author miche
 *
 */
public class PathSumIII {

    int count = 0;
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        List<Integer> eles = new ArrayList<Integer>();
        path(root, sum, eles);
        return count;
    }

    public void path(TreeNode root, int sum, List<Integer> eles) {
        if(root == null) return;
        eles.add(root.val);

        path(root.left, sum, eles);
        path(root.right, sum, eles);
        
        int total = 0;
        for(int i = eles.size() - 1; i >= 0; i--) {
            total += eles.get(i);
            if(total == sum) {
                count++;
            }
        }
        
        eles.remove(eles.size()-1);
    }

    //use map
    public int pathSum2(TreeNode root, int sum) {
        if(root == null) return 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        return dfs(root, sum, 0, map);
    }

    public int dfs(TreeNode root, int sum, int currSum, Map<Integer, Integer>map) {
        if(root == null) return 0;
        int count = 0;
        currSum += root.val;
        if(map.containsKey(currSum - sum)) {
            count += map.get(currSum - sum);
        }
        
        map.put(currSum, map.getOrDefault(currSum, 0)+1);
        count += dfs(root.left, sum, currSum, map);
        count += dfs(root.right, sum, currSum, map);
        
        map.put(currSum, map.get(currSum) - 1);
        
        return count;
    }
}
