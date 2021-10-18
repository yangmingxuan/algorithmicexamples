package algorithms.array;

/***
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.

    Find the maximum profit you can achieve. You may complete at most two transactions.
    
    Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
    
     
    
    Example 1:
    
    Input: prices = [3,3,5,0,0,3,1,4]
    Output: 6
    Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
    Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
    Example 2:
    
    Input: prices = [1,2,3,4,5]
    Output: 4
    Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
    Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
    Example 3:
    
    Input: prices = [7,6,4,3,1]
    Output: 0
    Explanation: In this case, no transaction is done, i.e. max profit = 0.
    Example 4:
    
    Input: prices = [1]
    Output: 0
     
    
    Constraints:
    
    1 <= prices.length <= 10^5
    0 <= prices[i] <= 10^5
 * @author miche
 *
 */
public class BestTimetoBuyandSellStockIII {

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE, maxProfie = 0;
        int n = prices.length;
        int[] profit = new int[n];
        //Find largest profit one could make selling on any given day
        for(int i = 0; i < n; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfie = Math.max(maxProfie, prices[i] - minPrice);
            
            profit[i] = maxProfie; //record the max profit in the ith day
        }

        int futureMaxPrice = prices[n-1];
        //Find largest profit on could make if stock be bought on a specific day and sold in the future 
        for(int i = n-1; i >= 0; i--) {
            futureMaxPrice = Math.max(futureMaxPrice, prices[i]);
            profit[i] = Math.max(profit[i], profit[i]+(futureMaxPrice-prices[i]));
            maxProfie = Math.max(maxProfie, profit[i]);
        }
        return maxProfie;
    }

    public int maxProfit2(int[] prices) {
        int t1min = prices[0], t2min = prices[0];
        int t1profit = 0, t2profit = 0;
        for(int price : prices) {
            //the maximum profit if only one transaction is allowed
            t1min = Math.min(t1min, price);
            t1profit = Math.max(t1profit, price-t1min);
            
            //reinvest the gained profit in the second transaction
            t2min = Math.min(t2min, price-t1profit);
            t2profit = Math.max(t2profit, price-t2min);
        }
        return t2profit;
    }
}
