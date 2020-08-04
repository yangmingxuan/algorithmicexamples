package algorithms.string;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.

    A valid IP address consists of exactly four integers (each integer is between 0 and 255) separated by single points.
    
    Example:
    
    Input: "25525511135"
    Output: ["255.255.11.135", "255.255.111.35"]
 * @author miche
 *
 */
public class RestoreIPAddresses {

    public List<String> restoreIpAddresses(String s) {
        if(s == null) return null;
        int len = s.length();
        List<String> ans = new ArrayList<String>();
        if(len < 4 || len > 12) return ans;
        int[] idx = new int[3];
        for(int first = 1; first <= 3; first++) {
            idx[0] = first;
            for(int second = first+1; second <= first+3; second++) {
                idx[1] = second;
                for(int third = second+1; third<= second+3; third++) {
                    idx[2] = third;
                    check(idx, len, s, ans);
                }
            }
        }
        return ans;
    }

    public boolean isValid(String seg) {
        if(seg == null || seg.length() < 1 || seg.length() > 3) return false;
        if(seg.charAt(0) == '0' && seg.length() > 1) return false;
        int num;
        try {
            num = Integer.parseInt(seg);
        } catch (NumberFormatException e) {
            return false;
        }
        if(num < 0 || num > 255) return false;
        return true;
    }

    public void check(int[] idx, int len, String s, List<String> ans) {
        if(idx[2] >= len) return;
        boolean isIp = true;
        int pre = 0;
        String seg;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            if(i < 3) {
                seg = s.substring(pre, idx[i]);
                pre = idx[i];
                sb.append(seg);
                sb.append('.');
            } else {
                seg = s.substring(pre);
                sb.append(seg);
            }
            if(!isValid(seg)) {
                isIp = false;
                return;
            }
        }
        if(isIp) ans.add(sb.toString());
    }
}
