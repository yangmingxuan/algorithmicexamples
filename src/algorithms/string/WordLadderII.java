package algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javafx.util.Pair;

/***
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

    Only one letter can be changed at a time
    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
    Note:
    
    Return an empty list if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.
    Example 1:
    
    Input:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]
    
    Output:
    [
      ["hit","hot","dot","dog","cog"],
      ["hit","hot","lot","log","cog"]
    ]
    Example 2:
    
    Input:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]
    
    Output: []
    
    Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 * @author miche
 *
 */
public class WordLadderII {

    String beginWord;
    String endWord;
    List<List<String>> lret;
    Map<String, List<String>> originWords;
    Map<String, List<String>> deformWords;
    Map<String, Integer> dist; //save the distance to beginWord and record if be visited
    int min = Integer.MAX_VALUE;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        lret = new ArrayList<List<String>>();
        if(wordList == null || wordList.isEmpty()) return lret;
        originWords = new HashMap<String, List<String>>();
        deformWords = new HashMap<String, List<String>>();
        dist = new HashMap<String, Integer>();
        this.beginWord = beginWord;
        this.endWord = endWord;
        
        //add the wordList to Map
        for(String word : wordList) {
            //get all the one letter deform
            for(int i = 0; i < word.length(); i++) {
                String deformWord = word.substring(0, i) + "#" + word.substring(i+1);
                List<String> oTod = originWords.getOrDefault(word, new ArrayList<String>());
                oTod.add(deformWord);
                originWords.put(word, oTod);
                List<String> dToo = deformWords.getOrDefault(deformWord, new ArrayList<String>());
                dToo.add(word);
                deformWords.put(deformWord, dToo);
            }
        }
        //endWord not in the wordList
        if(!originWords.containsKey(endWord)) return lret;

        //add the beginWord to Map
        if(!originWords.containsKey(beginWord)) {
            for(int i = 0; i < beginWord.length(); i++) {
                String deformWord = beginWord.substring(0, i) + "#" + beginWord.substring(i+1);
                List<String> oTod = originWords.getOrDefault(beginWord, new ArrayList<String>());
                oTod.add(deformWord);
                originWords.put(beginWord, oTod);
                List<String> dToo = deformWords.getOrDefault(deformWord, new ArrayList<String>());
                dToo.add(beginWord);
                deformWords.put(deformWord, dToo);
            }
        }
        //Use Map<String, Set<String>> as Adjacent List to store directed graph vertices and their neighbors.
        Map<String, Set<String>> graph = new HashMap<String, Set<String>>(); 

        /*** do with BFS queue */
        Queue<String> words = new LinkedList<String>();
        words.offer(beginWord);
        dist.put(beginWord, 0);
        int step = 0;
        boolean bFound = false;
        
        while(!words.isEmpty()) {
            int size = words.size();
            for(int i = 0; i < size; i++) {
                String originWord = words.poll();
                if(endWord.equals(originWord)) {
                    bFound = true;
                    break; //do NOT need to continue for current Level
                }
                List<String> oTod = originWords.get(originWord);
                for(String deformWord : oTod) {
                    List<String> dToo = deformWords.get(deformWord);
                    for(String newWord : dToo) {
                        if(!dist.containsKey(newWord)) {
                            //the newWord have not been visited
                            words.offer(newWord);
                            dist.put(newWord, dist.get(originWord)+1);
                            //add edge to directed graph -- build graph
                            Set<String> neighbors = graph.getOrDefault(originWord, new HashSet<String>());
                            neighbors.add(newWord);
                            graph.put(originWord, neighbors);
                        } else if(dist.get(newWord) == dist.get(originWord) + 1) {
                            //different paths which have the SAME shortest distance from beginWord
                            //keep all of them (add edge to graph)
                            Set<String> neighbors = graph.getOrDefault(originWord, new HashSet<String>());
                            neighbors.add(newWord);
                            graph.put(originWord, neighbors);
                        }
                    }
                }
            }
            step++;
            if(bFound == true) { //reach to endWord -- do NOT need to continue.
                break;
            }
        }
        
        if(!bFound) {
            return lret;
        }
        //do DFS
        dfs(graph, beginWord, endWord, lret, new ArrayList<String>(Arrays.asList(beginWord)));

        return lret;
    }

    public void dfs(Map<String, Set<String>> graph, String start, String end, List<List<String>> res, List<String> tmp) {
        if(start.equals(end)) {
            //find the endWord
            res.add(new ArrayList<String>(tmp));
            return;
        }
        
        //get all neighbors
        Set<String> neighbors = graph.get(start);
        if(neighbors != null) {
            for(String neighbor : neighbors) {
                tmp.add(neighbor);
                dfs(graph, neighbor, end, res, tmp);
                tmp.remove(tmp.size()-1); //backtrack
            }
        }
    }


    public static void main(String[] argv) {
        String beginWord = "cet";
        String endWord = "ism";
        String[] words = {"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"};
        //String[] words = {"ted","tex","red","tax","tad","den","rex","pee"};
        List<String> wordList = Arrays.asList(words);
        WordLadderII wl2 = new WordLadderII();
        List<List<String>> ans = wl2.findLadders(beginWord, endWord, wordList);
        System.out.print(ans.toString());
    }
}
