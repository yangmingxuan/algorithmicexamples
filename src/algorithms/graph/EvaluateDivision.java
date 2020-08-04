package algorithms.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.util.Pair;

/***
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

    Example:
    Given a / b = 2.0, b / c = 3.0.
    queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
    return [6.0, 0.5, -1.0, 1.0, -1.0 ].
    
    The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
    
    According to the example above:
    
    equations = [ ["a", "b"], ["b", "c"] ],
    values = [2.0, 3.0],
    queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
     
    
    The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 * @author miche
 *
 */
public class EvaluateDivision {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //Map<String, List<Pair<String, Double>>> graph = new HashMap<String, List<Pair<String, Double>>>();
        Map<String, Map<String, Double>> graph = new HashMap<String, Map<String, Double>>();
        //construct the graph
        for(int i = 0; i < values.length; i++) {
            String s = equations.get(i).get(0);
            String t = equations.get(i).get(1);
            //graph.putIfAbsent(s, new ArrayList<Pair<String, Double>>());
            //graph.putIfAbsent(t, new ArrayList<Pair<String, Double>>());
            //graph.get(s).add(new Pair<String, Double>(t, values[i]));
            //graph.get(t).add(new Pair<String, Double>(s, 1/values[i]));
            graph.putIfAbsent(s, new HashMap<String, Double>());
            graph.putIfAbsent(t, new HashMap<String, Double>());
            graph.get(s).put(t, values[i]);
            graph.get(t).put(s, 1/values[i]);
        }
        
        double[] ans = new double[queries.size()];
        for(int i = 0; i < ans.length; i++) {
            ans[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), graph, new HashSet<String>());
        }
        
        return ans;
    }

    public double dfs(String s, String t, Map<String, Map<String, Double>> graph, Set<String> seen) {
        if(seen.contains(s) || !graph.containsKey(s)) return -1;
        if(s.equals(t)) return 1;

        if(graph.get(s).containsKey(t)) {
            return graph.get(s).get(t);
        }
        seen.add(s);

        for(Map.Entry<String, Double> neighbor : graph.get(s).entrySet()) {
            double result = dfs(neighbor.getKey(), t, graph, seen);
            if(result != -1) {
                return result * neighbor.getValue();
            }
        }
        return -1;
    }
}
