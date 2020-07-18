package algorithms.array;

/***
 * A conveyor belt has packages that must be shipped from one port to another within D days.

    The i-th package on the conveyor belt has a weight of weights[i].  Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
    
    Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.
    
     
    
    Example 1:
    
    Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
    Output: 15
    Explanation: 
    A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
    1st day: 1, 2, 3, 4, 5
    2nd day: 6, 7
    3rd day: 8
    4th day: 9
    5th day: 10
    
    Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed. 
    Example 2:
    
    Input: weights = [3,2,2,4,1,4], D = 3
    Output: 6
    Explanation: 
    A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
    1st day: 3, 2
    2nd day: 2, 4
    3rd day: 1, 4
    Example 3:
    
    Input: weights = [1,2,3,1,1], D = 4
    Output: 3
    Explanation: 
    1st day: 1
    2nd day: 2
    3rd day: 3
    4th day: 1, 1
     
    
    Constraints:
    
    1 <= D <= weights.length <= 50000
    1 <= weights[i] <= 500
 * @author miche
 *
 */
public class CapacityToShipPackagesWithinDDays {

    public int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0, ans = -1;
        //the minimum capacity must be heaver(or equal) the maximum package
        for(int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }
        
        while(left <= right) {
            int mid = (left + right) >>> 1;
            if(isValid(weights, D, mid)) {
                ans = mid;
                right = mid - 1; //find the better capacity
            } else {
                left = mid + 1;
            }
        }
        
        return ans;
    }

    /***
     * count how many days are required when the capacity , and if the days more than D, that the capacity is not suitable
     * @param weights
     * @param D
     * @param capacity
     * @return
     */
    public boolean isValid(int[] weights, int D, int capacity) {
        int days = 1;
        int weightsum = 0;
        for(int i = 0; i < weights.length; i++) {
            weightsum += weights[i];
            
            if(weightsum > capacity) {
                //if the ship can not carry, need the next day to ship
                days++;
                weightsum = weights[i];
            }
            
            if(days > D) {
                return false;
            }
        }
        return true;
    }
}
