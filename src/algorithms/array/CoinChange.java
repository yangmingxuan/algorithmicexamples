package algorithms.array;

import java.util.Arrays;

/***
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

    Example 1:
    
    Input: coins = [1, 2, 5], amount = 11
    Output: 3 
    Explanation: 11 = 5 + 5 + 1
    Example 2:
    
    Input: coins = [2], amount = 3
    Output: -1
    Note:
    You may assume that you have an infinite number of each kind of coin.
 * @author miche
 *
 */
public class CoinChange {

    public int coinChange1(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++) {
            for(int j = 0; j < coins.length; j++) {
                if(i >= coins[j]) {
                    dp[i] = Math.min(dp[i], 1+dp[i-coins[j]]);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount];
        return coinChange(coins, amount, dp);
    }
    
    public int coinChange(int[] coins, int amount, int[] dp) {
        if(amount < 0) return -1;
        if(amount == 0) return 0;
        if(dp[amount-1] != 0) return dp[amount-1];
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < coins.length; i++) {
            int res = coinChange(coins, amount-coins[i], dp);
            if(res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        dp[amount-1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return dp[amount-1];
    }

    private int ans;
    public int coinChange2(int[] coins, int amount) {
        if(amount < 0) return -1;
        ans = Integer.MAX_VALUE;
        Arrays.sort(coins);
        dfs(coins, amount, 0, coins.length-1);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    public void dfs(int[] coins, int amount, int count, int level) {
        if(level < 0) return;
        for(int i = amount/coins[level]; i >= 0; i--) {
            int balance = amount - coins[level]*i;
            int newCount = count + i;
            if(balance > 0 && newCount + 1 < ans) {
                dfs(coins, balance, newCount, level-1);
            } else {
                if(balance == 0) {
                    ans = Math.min(ans, newCount);
                }
                break;
            }
        }
    }
}
