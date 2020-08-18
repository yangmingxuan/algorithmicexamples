package algorithms.string;

import java.util.ArrayList;
import java.util.List;

/***
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

    You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
    
    Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
    
    For the last line of text, it should be left justified and no extra space is inserted between words.
    
    Note:
    
    A word is defined as a character sequence consisting of non-space characters only.
    Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
    The input array words contains at least one word.
    Example 1:
    
    Input:
    words = ["This", "is", "an", "example", "of", "text", "justification."]
    maxWidth = 16
    Output:
    [
       "This    is    an",
       "example  of text",
       "justification.  "
    ]
    Example 2:
    
    Input:
    words = ["What","must","be","acknowledgment","shall","be"]
    maxWidth = 16
    Output:
    [
      "What   must   be",
      "acknowledgment  ",
      "shall be        "
    ]
    Explanation: Note that the last line is "shall be    " instead of "shall     be",
                 because the last line must be left-justified instead of fully-justified.
                 Note that the second line is also left-justified becase it contains only one word.
    Example 3:
    
    Input:
    words = ["Science","is","what","we","understand","well","enough","to","explain",
             "to","a","computer.","Art","is","everything","else","we","do"]
    maxWidth = 20
    Output:
    [
      "Science  is  what we",
      "understand      well",
      "enough to explain to",
      "a  computer.  Art is",
      "everything  else  we",
      "do                  "
    ]
 * @author miche
 *
 */
public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<String>();
        if(words == null || words.length == 0) return result;
        List<String> rowWords = new ArrayList<String>();
        int rowLength = 0;
        for(int i = 0; i < words.length; i++) {
            if(rowLength == 0) {
                rowLength += words[i].length();
                rowWords.add(words[i]);
            } else {
                rowLength += (1 + words[i].length());
                rowWords.add(" " + words[i]);
            }
            if(i == words.length-1 || rowLength + 1 + words[i+1].length() > maxWidth) {
                //split the row
                boolean alignleft = false;
                if(i == words.length-1) {
                    alignleft = true;
                }
                organizeRow(result, rowWords, maxWidth - rowLength, alignleft);
                rowLength = 0;
                rowWords = new ArrayList<String>();
            }
        }
        return result;
    }

    public void organizeRow(List<String> result, List<String> rowWords, int addSpaceCount, boolean alignleft) {
        StringBuilder sb = new StringBuilder();
        if(rowWords.size() == 1 || alignleft) {
            for(String word : rowWords) {
                sb.append(word);
            }
            for(int i = 0; i < addSpaceCount; i++) {
                sb.append(' ');
            }
            result.add(sb.toString());
            return;
        }
        
        //N words have N-1 splits
        int atleast = addSpaceCount / (rowWords.size()-1);
        int remainder = addSpaceCount % (rowWords.size()-1);
        for(int i = 0; i < rowWords.size() - 1; i++) {
            sb.append(rowWords.get(i));
            for(int j = 0; j < (i < remainder ? atleast+1 : atleast); j++) {
                sb.append(' ');
            }
        }
        sb.append(rowWords.get(rowWords.size() - 1));
        result.add(sb.toString());
    }
}
