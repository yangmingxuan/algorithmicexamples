package algorithms.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

    Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
    
    Note:
    
    A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
    Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
    Example 1:
    
    Input:
    [[0,1,10], [2,0,5]]
    
    Output:
    2
    
    Explanation:
    Person #0 gave person #1 $10.
    Person #2 gave person #0 $5.
    
    Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
    Example 2:
    
    Input:
    [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
    
    Output:
    1
    
    Explanation:
    Person #0 gave person #1 $10.
    Person #1 gave person #0 $1.
    Person #1 gave person #2 $5.
    Person #2 gave person #0 $5.
    
    Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 * @author miche
 *
 */
public class OptimalAccountBalancing {

    public int minTransfers(int[][] transactions) {
        if(transactions == null || transactions.length == 0) return 0;
        Map<Integer, Integer> creditDebt = new HashMap<Integer, Integer>();
        //everyone's debt situation
        for(int[] tuple : transactions) {
            creditDebt.put(tuple[0], creditDebt.getOrDefault(tuple[0], 0) - tuple[2]);
            creditDebt.put(tuple[1], creditDebt.getOrDefault(tuple[1], 0) + tuple[2]);
        }
        return dfs(new ArrayList<Integer>(creditDebt.values()), 0);
    }

    public int dfs(List<Integer>debts, int index) {
        if(debts.size() == index) return 0;
        if(debts.size() - 2 == index) return debts.get(index) == 0 ? 0 : 1;
        //if someone no claim and no debt, skip
        if(debts.get(index) == 0) return dfs(debts, index+1);
        int ans = Integer.MAX_VALUE;
        for(int i = index+1; i < debts.size(); i++) {
            int debt = debts.get(i);
            if(debt * debts.get(index) >= 0) {
                //there are all positive or all negative or some one is 0
                continue;
            }
            debts.set(i, debt+debts.get(index)); //set the balance(Claims and debts collide) back to list
            ans = Math.min(ans, 1+dfs(debts, index+1));
            debts.set(i, debt); //when recursion return, set the debt back
            if(debt + debts.get(index) == 0) {
                //they're the best matched, stop brute force
                break;
            }
        }
        
        return ans;
    }
    
    public int binarySearch(List<Integer> list, int val) {
        int left = 0, right = list.size()-1;
        while(left <= right) {
            int mid = (left+right) >>> 1; //(l+r)/2
            if(list.get(mid) == val) {
                return mid; //found
            } else if(list.get(mid) < val) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -(left+1); //not found
    }
}
