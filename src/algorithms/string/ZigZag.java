package algorithms.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

    P   A   H   N
    A P L S I I G
    Y   I   R
    And then read line by line: "PAHNAPLSIIGYIR"
    
    Write the code that will take a string and make this conversion given a number of rows:
    
    string convert(string s, int numRows);
    Example 1:
    
    Input: s = "PAYPALISHIRING", numRows = 3
    Output: "PAHNAPLSIIGYIR"
    Example 2:
    
    Input: s = "PAYPALISHIRING", numRows = 4
    Output: "PINALSIGYAHRPI"
    Explanation:
    
    P     I    N
    A   L S  I G
    Y A   H R
    P     I
 * @author miche
 *
 */
public class ZigZag {

    public ZigZag() {
        // TODO Auto-generated constructor stub
    }

    /***
     * Runtime: 5 ms, faster than 76.88% of Java online submissions for ZigZag Conversion.
     * Memory Usage: 38.5 MB, less than 84.04% of Java online submissions for ZigZag Conversion.
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if(s == null || s.length() < 2 || numRows == 1) {
            return s;
        }
        StringBuilder[] sbtmp = new StringBuilder[numRows];
        for(int i = 0; i < numRows; i ++) {
            sbtmp[i] = new StringBuilder();
        }
        int j = 0;
        for(int i = 0; i < s.length(); i ++) {
            if(j < numRows) {
                sbtmp[j].append(s.charAt(i));
            }
            if(j >= numRows && j < (2 * numRows - 2)) {
                sbtmp[2*numRows-j-2].append(s.charAt(i));
            }
            j++;
            if(j >= (2*numRows-2)) {
                j = 0;
            }
        }
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < numRows; i ++) {
            ret.append(sbtmp[i]);
        }
        return ret.toString();
    }

    /***
     * Runtime: 4 ms, faster than 80.84% of Java online submissions for ZigZag Conversion.
     * Memory Usage: 37.2 MB, less than 100.00% of Java online submissions for ZigZag Conversion.
     * @param s
     * @param numRows
     * @return
     */
    public String convert2(String s, int numRows) {
        if(s == null || s.length() < 2 || numRows == 1) {
            return s;
        }
        StringBuilder[] sbtmp = new StringBuilder[numRows];
        for(int i = 0; i < numRows; i ++) {
            sbtmp[i] = new StringBuilder();
        }
        int j = 0;
        boolean downcount = false;
        char[] sarr = s.toCharArray();
        for(char c : sarr) {
            sbtmp[j].append(c);
            if(j == 0 || j == numRows - 1) {
                downcount = !downcount;
            }
            j += downcount ? 1 : -1;
        }
        StringBuilder ret = new StringBuilder();
        for(StringBuilder tmp : sbtmp) {
            ret.append(tmp);
        }
        return ret.toString();
    }

    public static void main(String argv[]) throws IOException {
        ZigZag zigzag = new ZigZag();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int n = 4;
        while ((line = in.readLine()) != null) {
            System.out.print(zigzag.convert2(line, n));
        }
    }
}
