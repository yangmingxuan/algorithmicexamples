package algorithms.array;
/***
 * Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.

You can use each character in text at most once. Return the maximum number of instances that can be formed.

 

Example 1:



Input: text = "nlaebolko"
Output: 1
Example 2:



Input: text = "loonbalxballpoon"
Output: 2
Example 3:

Input: text = "leetcode"
Output: 0
 

Constraints:

1 <= text.length <= 104
text consists of lower case English letters only.
 * @author miche
 *
 */
public class MaximumNumberofBalloons {

    public int maxNumberOfBalloons(String text) {
        int bcount = 0, acount = 0, lcount = 0, ocount = 0, ncount = 0;
        
        char[] tcs = text.toCharArray();
        for(char ch: tcs) {
            switch(ch) {
            case 'b':
                bcount++;
                break;
            case 'a':
                acount++;
                break;
            case 'l':
                lcount++;
                break;
            case 'o':
                ocount++;
                break;
            case 'n':
                ncount++;
                break;
                
            }
        }
        
        lcount /= 2;
        ocount /= 2;
        
        return Math.min(bcount, Math.min(acount, Math.min(lcount, Math.min(ocount, ncount))));
    }

}
