package algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/***
 * We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].

    A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.  (The websites in a 3-sequence are not necessarily distinct.)
    
    Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.
    
     
    
    Example 1:
    
    Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
    Output: ["home","about","career"]
    Explanation: 
    The tuples in this example are:
    ["joe", 1, "home"]
    ["joe", 2, "about"]
    ["joe", 3, "career"]
    ["james", 4, "home"]
    ["james", 5, "cart"]
    ["james", 6, "maps"]
    ["james", 7, "home"]
    ["mary", 8, "home"]
    ["mary", 9, "about"]
    ["mary", 10, "career"]
    The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
    The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
    The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
    The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
    The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
     
    
    Note:
    
    3 <= N = username.length = timestamp.length = website.length <= 50
    1 <= username[i].length <= 10
    0 <= timestamp[i] <= 10^9
    1 <= website[i].length <= 10
    Both username[i] and website[i] contain only lowercase characters.
    It is guaranteed that there is at least one user who visited at least 3 websites.
    No user visits two websites at the same time.
 * @author miche
 *
 */
public class AnalyzeUserWebsiteVisitPattern {

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<String> lret = new ArrayList<String>();
        int len = timestamp.length;
        Tuple[] tuples = new Tuple[len];
        for(int i = 0; i < len; i++) {
            tuples[i] = new Tuple(username[i], timestamp[i], website[i]);
        }
        Arrays.sort(tuples, (a,b)->a.username.equals(b.username)?a.timestamp-b.timestamp:a.username.compareTo(b.username));
        String max = "";
        int maxcount = 0;
        Map<String, Integer> visited = new HashMap<String, Integer>();
        visited.put(max, maxcount);
        StringBuilder sbEachUserWebsite = new StringBuilder();
        for(int i = 0; i < len; i++) {
            sbEachUserWebsite.append(tuples[i].website + "#");
            if(i == len - 1 || !(tuples[i].username.equals(tuples[i+1].username))) {
                //analysis the 3-sequence
                HashSet<String> userWeb = analysisUserWeb(sbEachUserWebsite.deleteCharAt(sbEachUserWebsite.length()-1).toString());
                for(String sequence : userWeb) {
                    int count = visited.getOrDefault(sequence, 0) + 1;
                    if(count > maxcount || count == maxcount && sequence.compareTo(max) < 0) {
                        maxcount = count;
                        max = sequence;
                    }
                    visited.put(sequence, count);
                }
                sbEachUserWebsite = new StringBuilder();
            }
        }
        
        String[] webs = max.split("#");
        for(String web : webs) {
            lret.add(web);
        }
        
        return lret;
    }

    public HashSet<String> analysisUserWeb(String strWebs) {
        HashSet<String> set = new HashSet<String>();
        String[] webs = strWebs.split("#");
        for(int i = 0; i < webs.length; i++) {
            for(int j = i+1; j < webs.length; j++) {
                for(int k = j+1; k < webs.length; k++) {
                    set.add(webs[i]+"#"+webs[j]+"#"+webs[k]);
                }
            }
        }
        return set;
    }
    
    class Tuple {
        String username;
        int timestamp;
        String website;
        Tuple(String username, int timestamp, String website) {
            this.username = username;
            this.timestamp = timestamp;
            this.website = website;
        }
    }
}
