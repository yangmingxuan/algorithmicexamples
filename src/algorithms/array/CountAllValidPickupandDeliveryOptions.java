package algorithms.array;
/***
 * Given n orders, each order consist in pickup and delivery services. 

    Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i). 
    
    Since the answer may be too large, return it modulo 10^9 + 7.
    
     
    
    Example 1:
    
    Input: n = 1
    Output: 1
    Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup 1.
    Example 2:
    
    Input: n = 2
    Output: 6
    Explanation: All possible orders: 
    (P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1) and (P2,D2,P1,D1).
    This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery 2.
    Example 3:
    
    Input: n = 3
    Output: 90
     
    
    Constraints:
    
    1 <= n <= 500
    Hint: Use the permutation and combination theory to add one (P, D) pair each time until n pairs.
 * @author miche
 *
 */

public class CountAllValidPickupandDeliveryOptions {

    /***
     * Explanation: 如果不考虑Dn必须在Pn之后的话，那么有n对PD的排列组合数量为(2n)的阶乘，当PnDn成对出现，Dn出现在Pn前的概率为1/2，所以要剔除这种情况
     *  If it is not considered that Dn must be after Pn, then the number of n pairs of PD permutations and combinations is the factorial of (2n). 
     *  When PnDn appears in pairs, the probability of Dn appearing before Pn is 1/2, so this situation should be eliminated.
     * @param n
     * @return
     */
    public int countOrders(int n) {
        int MOD = 1_000_000_007;
        long ans = 1;
        int pairn = 2*n;
        for(int i = 1; i <= pairn; i++) {
            ans *= i;
            if(i % 2 == 0) {
                ans /= 2;
            }
            ans %= MOD;
        }
        return (int)ans;
    }

}
