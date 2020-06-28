package algorithms.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/***
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

    Note:
    
    If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
    All airports are represented by three capital letters (IATA code).
    You may assume all tickets form at least one valid itinerary.
    One must use all the tickets once and only once.
    Example 1:
    
    Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
    Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
    Example 2:
    
    Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
    Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
    Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
                 But it is larger in lexical order.
 * @author miche
 *
 */
public class ReconstructItinerary {

    Map<String, PriorityQueue<String>> flight;
    List<String> ans;
    public List<String> findItinerary(List<List<String>> tickets) {
        flight = new HashMap<String, PriorityQueue<String>>();
        ans = new ArrayList<String>();
        
        //construct the graph
        for(List<String> flights : tickets) {
            String departure = flights.get(0);
            String arrival = flights.get(1);
            if(!flight.containsKey(departure)) {
                PriorityQueue<String> dest = new PriorityQueue<String>();
                dest.offer(arrival);
                flight.put(departure, dest);
            } else {
                flight.get(departure).offer(arrival);
            }
        }
        
        //sort the arrival
        //flight.forEach((key, value)->Collections.sort(value));
        
        dfs("JFK");
        return ans;
    }

    public void dfs(String departure) {
        if(flight.containsKey(departure)) {
            PriorityQueue<String> dests = flight.get(departure);
            while(!dests.isEmpty()) {
                String nextStation = dests.poll();
                dfs(nextStation);
            }
        }
        
        ans.add(0, departure);
    }
}
