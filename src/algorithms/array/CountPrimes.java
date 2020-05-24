package algorithms.array;

/***
 * Count the number of prime numbers less than a non-negative number, n.

    Example:
    
    Input: 10
    Output: 4
    Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * @author miche
 *
 */
public class CountPrimes {

    public int countPrimes(int n) {
        if(n < 2) return 0;
        boolean[] noprime = new boolean[n];
        int ans = 0;
        for(int i = 2; i < n; i++) {
            if(noprime[i]) continue;
            ans++;
            for(long j = (long)i*i; j < n; j+=i) {
                noprime[(int)j] = true;
            }
        }
        
        return ans;
    }

}
