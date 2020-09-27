package algorithms.string;

/***
 * 
 * @author miche
 *
 */
public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0) return true;
        //s = s.toLowerCase();
        int l = 0, r = s.length() - 1;
        while(l < r) {
            while(l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
            while(l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
            if(l < r) {
                char lch = s.charAt(l);
                char rch = s.charAt(r);
                if(lch >= 'A' && lch <= 'Z') lch = (char)(lch-'A'+'a');
                if(rch >= 'A' && rch <= 'Z') rch = (char)(rch-'A'+'a');
                if(lch != rch) return false;
            }
            l++;
            r--;
        }
        return true;
    }

}
