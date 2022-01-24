package algorithms.array;

/***
 * You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat, and seats[i] = 0 represents that the ith seat is empty (0-indexed).

    There is at least one empty seat, and at least one person sitting.
    
    Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized. 
    
    Return that maximum distance to the closest person.
    
     
    
    Example 1:
    
    
    Input: seats = [1,0,0,0,1,0,1]
    Output: 2
    Explanation: 
    If Alex sits in the second open seat (i.e. seats[2]), then the closest person has distance 2.
    If Alex sits in any other open seat, the closest person has distance 1.
    Thus, the maximum distance to the closest person is 2.
    Example 2:
    
    Input: seats = [1,0,0,0]
    Output: 3
    Explanation: 
    If Alex sits in the last seat (i.e. seats[3]), the closest person is 3 seats away.
    This is the maximum distance possible, so the answer is 3.
    Example 3:
    
    Input: seats = [0,1]
    Output: 1
     
    
    Constraints:
    
    2 <= seats.length <= 2 * 10^4
    seats[i] is 0 or 1.
    At least one seat is empty.
    At least one seat is occupied.
 * @author miche
 *
 */
public class MaximizeDistancetoClosestPerson {

    public int maxDistToClosest(int[] seats) {
        int ans = 1;
        int prev = -1, n = seats.length;
        for(int i = 0; i < n; i++) {
            if(seats[i] == 1) {
                ans = Math.max(ans, (i - prev)/2);
                prev = i;
            }
        }
        //the right empty count
        ans = Math.max(ans, (n - prev - 1));
        
        prev = n;
        for(int i = n-1; i >= 0; i--) {
            if(seats[i] == 1) {
                //ans = Math.max(ans, (prev - i)/2);
                prev = i;
            }
        }
        //the left empty count
        ans = Math.max(ans, prev);
        
        return ans;
    }
    
    public int maxDistToClosest2(int[] seats) {
        int n = seats.length;
        int leftEmpty = 0, rightEmpty = 0;
        int l = 0, r = n - 1;
        
        //the left empty count
        while(l < n && seats[l] == 0) {
            leftEmpty++;
            l++;
        }
        
        //the right empty count
        while(r >= 0 && seats[r] == 0) {
            rightEmpty++;
            r--;
        }
        
        //the maximum empty in the middle
        int prev = l, maxmidEmpty = 0;
        for(int i = l; i <= r; i++) {
            if(seats[i] == 1) {
                maxmidEmpty = Math.max(maxmidEmpty, i - prev);
                prev = i;
            }
        }
        
        return Math.max(Math.max(leftEmpty, rightEmpty), maxmidEmpty/2);
    }

}
