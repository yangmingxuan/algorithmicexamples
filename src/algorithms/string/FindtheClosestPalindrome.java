package algorithms.string;

/***
 * Given an integer n, find the closest integer (not including itself), which is a palindrome.

    The 'closest' is defined as absolute difference minimized between two integers.
    
    Example 1:
    Input: "123"
    Output: "121"
    Note:
    The input n is a positive integer represented by string, whose length will not exceed 18.
    If there is a tie, return the smaller one as answer.
 * @author miche
 *
 */
public class FindtheClosestPalindrome {

    public String nearestPalindromic(String n) {
        long adj = (long)Math.pow(10, n.length()/2);  //use to get the first half of the value
        long origin = Long.parseLong(n);
        long palindrome1 = palindrome(origin);
        long diff1 = Math.abs(palindrome1 - origin);
        if(diff1 == 0) diff1 = Long.MAX_VALUE;  //not including itself
        long larger = palindrome((origin/adj)*adj+adj+1);//palindrome((origin/adj+1)*adj);
        long diff2 = Math.abs(larger - origin);
        long smaller =  palindrome((origin/adj)*adj-1); //palindrome((origin/adj-1)*adj);
        long diff3 = Math.abs(origin - smaller);
        
        //If there is a tie, return the smaller one as answer.
        if(diff3 <= diff1 && diff3 <= diff2) {
            return String.valueOf(smaller);
        } else if(diff1 <= diff3 && diff1 <= diff2) {
            return String.valueOf(palindrome1);
        } else {
            return String.valueOf(larger);
        }
    }

    /***
     * get the palindrome integer
     * @param n
     * @return
     */
    public long palindrome(long n) {
        char[] arr = String.valueOf(n).toCharArray();
        int l = 0, r = arr.length - 1;
        while(l < r) {
            arr[r--] = arr[l++];
        }
        return Long.parseLong(new String(arr));
    }
}
