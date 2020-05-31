package algorithms.string;

/***
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

    Example 1:
    
    Input: 123
    Output: "One Hundred Twenty Three"
    Example 2:
    
    Input: 12345
    Output: "Twelve Thousand Three Hundred Forty Five"
    Example 3:
    
    Input: 1234567
    Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
    Example 4:
    
    Input: 1234567891
    Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 * @author miche
 *
 */
public class IntegertoEnglishWords {

    public String numberToWords(int num) {
        if(num == 0) return "Zero";
        StringBuilder sb = new StringBuilder();
        int y = num / 1000000000;
        num %= 1000000000;
        if(y > 0) {
            sb.append(three(y));
            sb.append(" Billion");
            if(num > 0)
                sb.append(' ');
        }
        int w = num / 1000000;
        num %= 1000000;
        if(w > 0) {
            sb.append(three(w));
            sb.append(" Million");
            if(num > 0)
                sb.append(' ');
        }
        int q = num / 1000;
        num %= 1000;
        if(q > 0) {
            sb.append(three(q));
            sb.append(" Thousand");
            if(num > 0)
                sb.append(' ');
        }
        if(num > 0) {
            sb.append(three(num));
        }
        return sb.toString();
    }

    public String one(int num) {
        switch(num) {
        case 0:
            return "";
        case 1:
            return "One";
        case 2:
            return "Two";
        case 3:
            return "Three";
        case 4:
            return "Four";
        case 5:
            return "Five";
        case 6:
            return "Six";
        case 7:
            return "Seven";
        case 8:
            return "Eight";
        case 9:
            return "Nine";
        }
        return "";
    }

    public String ten(int num) {
        switch(num) {
        case 1:
            return "Ten";
        case 2:
            return "Twenty";
        case 3:
            return "Thirty";
        case 4:
            return "Forty";
        case 5:
            return "Fifty";
        case 6:
            return "Sixty";
        case 7:
            return "Seventy";
        case 8:
            return "Eighty";
        case 9:
            return "Ninety";
        }
        return "";
    }

    public String tenTotwenty(int num) {
        switch(num) {
        case 11:
            return "Eleven";
        case 12:
            return "Twelve";
        case 13:
            return "Thirteen";
        case 14:
            return "Fourteen";
        case 15:
            return "Fifteen";
        case 16:
            return "Sixteen";
        case 17:
            return "Seventeen";
        case 18:
            return "Eighteen";
        case 19:
            return "Nineteen";
        }
        return "";
    }

    public String two(int num) {
        if(num > 10 && num < 20) {
            return tenTotwenty(num);
        }
        int s = num / 10;
        int g = num % 10;
        StringBuilder sb = new StringBuilder();
        if(s > 0) {
            sb.append(ten(s));
            if(g > 0)
                sb.append(' ');
        }
        if(g > 0) {
            sb.append(one(g));
        }
        return sb.toString();
    }

    public String three(int num) {
        StringBuilder sb = new StringBuilder();
        int b = num / 100;
        int s = num % 100;
        if(b > 0) {
            sb.append(one(b));
            sb.append(" Hundred");
            if(s > 0)
                sb.append(' ');
        }
        if(s > 0) {
            sb.append(two(s));
        }
        return sb.toString();
    }
}
