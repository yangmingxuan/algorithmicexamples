package algorithms.string;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/***
 * Write a program to count the number of days between two dates.

    The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.
    
     
    
    Example 1:
    
    Input: date1 = "2019-06-29", date2 = "2019-06-30"
    Output: 1
    Example 2:
    
    Input: date1 = "2020-01-15", date2 = "2019-12-31"
    Output: 15
     
    
    Constraints:
    
    The given dates are valid dates between the years 1971 and 2100.
 * @author miche
 *
 */
public class NumberofDaysBetweenTwoDates {

    public int daysBetweenDates(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = sdf.parse(date1, new ParsePosition(0));
        Date dt2 = sdf.parse(date2, new ParsePosition(0));
        long diff = Math.abs(dt1.getTime() - dt2.getTime());
        return (int)(diff/1000/3600/24);
    }

    public int daysBetweenDates2(String date1, String date2) {
        LocalDate dt1 = LocalDate.parse(date1);
        LocalDate dt2 = LocalDate.parse(date2);
        
        return Math.abs((int)(dt1.toEpochDay() - dt2.toEpochDay()));
    }

    public int daysBetweenDates3(String date1, String date2) {
        int d1 = toLongDays(date1);
        int d2 = toLongDays(date2);
        
        return Math.abs(d1-d2);
    }

    public int toLongDays(String date) {
        //String[] ymd = date.split("-");
        int y = Integer.parseInt(date.substring(0, 4)); //Integer.parseInt(ymd[0]);
        int m = Integer.parseInt(date.substring(5, 7)); //Integer.parseInt(ymd[1]);
        int d = Integer.parseInt(date.substring(8, 10));//Integer.parseInt(ymd[2]);
        int total = 0;
        total += 365 * y;
        if (y >= 0) {
            total += (y + 3) / 4 - (y + 99) / 100 + (y + 399) / 400;
        } else {
            total -= y / -4 - y / -100 + y / -400;
        }
        total += ((367 * m - 362) / 12);
        total += d - 1;
        if (m > 2) {
            total--;
            if (!(y % 4 == 0 && y % 100 != 0 || y % 400 == 0)) {
                //if no leaf year
                total--;
            }
        }
        return total;
    }
}
