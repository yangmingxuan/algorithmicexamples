package algorithms.btree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javafx.util.Pair;

/***
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

    If two nodes are in the same row and column, the order should be from left to right.
    
    Examples 1:
    
    Input: [3,9,20,null,null,15,7]
    
       3
      /\
     /  \
     9  20
        /\
       /  \
      15   7 
    
    Output:
    
    [
      [9],
      [3,15],
      [20],
      [7]
    ]
    Examples 2:
    
    Input: [3,9,8,4,0,1,7]
    
         3
        /\
       /  \
       9   8
      /\  /\
     /  \/  \
     4  01   7 
    
    Output:
    
    [
      [4],
      [9],
      [3,0,1],
      [8],
      [7]
    ]
    Examples 3:
    
    Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
    
         3
        /\
       /  \
       9   8
      /\  /\
     /  \/  \
     4  01   7
        /\
       /  \
       5   2
    
    Output:
    
    [
      [4],
      [9,5],
      [3,0,1],
      [8,2],
      [7]
    ]
    
 like the NO. 987 Vertical Order Traversal of a Binary Tree
 just from dfs to bfs
 * @author miche
 *
 */
public class BinaryTreeVerticalOrderTraversal {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> lret = new ArrayList<List<Integer>>();
        if(root == null) return lret;
        List<Pair<TreeNode, Integer>> loc = new ArrayList<Pair<TreeNode, Integer>>();
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<Pair<TreeNode, Integer>>();
        queue.offer(new Pair<TreeNode, Integer>(root, 0));
        while(!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            loc.add(pair);
            TreeNode node = pair.getKey();
            int vertical = pair.getValue();
            if(node.left != null) queue.offer(new Pair<TreeNode, Integer>(node.left, vertical-1));
            if(node.right != null) queue.offer(new Pair<TreeNode, Integer>(node.right, vertical+1));
        }
        Collections.sort(loc, (a,b)->a.getValue()-b.getValue());
        int prex = loc.get(0).getValue();
        lret.add(new ArrayList<Integer>());
        for(Pair<TreeNode, Integer> pair : loc) {
            if(prex != pair.getValue()) {
                prex = pair.getValue();
                lret.add(new ArrayList<Integer>());
            }
            lret.get(lret.size()-1).add(pair.getKey().val);
        }

        return lret;
    }

    public List<List<Integer>> verticalOrder2(TreeNode root) {
        List<List<Integer>> lret = new ArrayList<List<Integer>>();
        if(root == null) return lret;
        Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<Pair<TreeNode, Integer>>();
        queue.offer(new Pair<TreeNode, Integer>(root, 0));
        while(!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            int vertical = pair.getValue();
            if(!map.containsKey(vertical)) {
                map.put(vertical, new ArrayList<Integer>());
            }
            map.get(vertical).add(node.val);
            if(node.left != null) queue.offer(new Pair<TreeNode, Integer>(node.left, vertical-1));
            if(node.right != null) queue.offer(new Pair<TreeNode, Integer>(node.right, vertical+1));
        }

        List<Integer> keys = new ArrayList<Integer>(map.keySet());
        Collections.sort(keys);
        for(Integer key : keys) {
            lret.add(map.get(key));
        }
        return lret;
    }

    public List<List<Integer>> verticalOrder3(TreeNode root) {
        List<List<Integer>> lret = new ArrayList<List<Integer>>();
        if(root == null) return lret;
        int leftMin = 0;
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<Pair<TreeNode, Integer>>();
        queue.offer(new Pair<TreeNode, Integer>(root, 0));
        while(!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            int vertical = pair.getValue();
            if(vertical < leftMin) {
                leftMin = vertical;
                lret.add(0, new ArrayList<Integer>());
            }
            if(vertical - leftMin >= lret.size()) {
                lret.add(new ArrayList<Integer>());
            }
            lret.get(vertical - leftMin).add(node.val); //good
            
            if(node.left != null) queue.offer(new Pair<TreeNode, Integer>(node.left, vertical-1));
            if(node.right != null) queue.offer(new Pair<TreeNode, Integer>(node.right, vertical+1));
        }
        return lret;
    }
}
