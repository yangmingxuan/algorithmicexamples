package algorithms.string;

import java.util.Arrays;
import java.util.Comparator;

/***
 * Given a list of non negative integers, arrange them such that they form the largest number.

    Example 1:
    
    Input: [10,2]
    Output: "210"
    Example 2:
    
    Input: [3,30,34,5,9]
    Output: "9534330"
    Note: The result may be very large, so you need to return a string instead of an integer.
 * @author miche
 *
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        if(nums == null || nums.length == 0) return "";
        String[] strNums = new String[nums.length];
        for(int i = 0; i < nums.length; i++) {
            strNums[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strNums, (a,b)->(b+a).compareTo((a+b)));
        if(strNums[0].charAt(0) == '0') return "0";
        StringBuilder sb = new StringBuilder();
        for(String strnum : strNums) {
            sb.append(strnum);
        }
        return sb.toString();
    }

    class MergeIntegerComparator implements Comparator<Integer> {

        @Override
        //equals String (a,b)->(b+a).compareTo((a+b))
        public int compare(Integer a, Integer b) {
            long longa = a * 10, longb = b * 10;
            int x = a, y = b;
            while((x /= 10) > 0) longb *= 10;
            while((y /= 10) > 0) longa *= 10;
            if(longa+b < longb+a) return 1;  //desc
            else if(longa+b == longb+a) return 0;
            else return -1;
        }
        
    }
    
    public String largestNumber2(int[] nums) {
        if(nums == null || nums.length == 0) return "";
        Integer[] INums = new Integer[nums.length];
        for(int i = 0; i < nums.length; i++) {
            INums[i] = nums[i];
        }
        Arrays.sort(INums, new MergeIntegerComparator());
        if(INums[0] == 0) return "0";
        StringBuilder sb = new StringBuilder();
        for(Integer num : INums) {
            sb.append(num);
        }
        return sb.toString();
    }
}
