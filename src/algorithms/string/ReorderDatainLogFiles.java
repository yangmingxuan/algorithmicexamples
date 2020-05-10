package algorithms.string;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import javafx.util.Pair;

/***
 * You have an array of logs.  Each log is a space delimited string of words.

    For each log, the first word in each log is an alphanumeric identifier.  Then, either:
    
    Each word after the identifier will consist only of lowercase letters, or;
    Each word after the identifier will consist only of digits.
    We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
    
    Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
    
    Return the final order of the logs.
    
     
    
    Example 1:
    
    Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
    Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
     
    
    Constraints:
    
    0 <= logs.length <= 100
    3 <= logs[i].length <= 100
    logs[i] is guaranteed to have an identifier, and a word after the identifier.
 * @author miche
 *
 */
public class ReorderDatainLogFiles {

    public String[] reorderLogFiles(String[] logs) {
        String[] ans = new String[logs.length];
        if(logs == null || logs.length == 0) return ans;
        PriorityQueue<Pair<String, String>> letterlog = new PriorityQueue<Pair<String, String>>((a,b)->a.getValue().compareTo(b.getValue()));
        List<String> digitlog = new ArrayList<String>();
        for(int i = 0; i < logs.length; i++) {
            String log = logs[i];
            int idx = log.indexOf(' ');
            String iden = log.substring(0, idx);
            String word = log.substring(idx+1).trim();
            if(word.charAt(0) >= '0' && word.charAt(0) <= '9') {
                digitlog.add(log);
            } else {
                Pair<String, String> letterPair = new Pair<String, String>(log, word+iden);
                letterlog.add(letterPair);
            }
        }
        int t = 0;
        while(!letterlog.isEmpty()) {
            ans[t++] = letterlog.poll().getKey();
        }
        for(int i = 0; i < digitlog.size(); i++) {
            ans[t++] = digitlog.get(i);
        }

        return ans;
    }

}
