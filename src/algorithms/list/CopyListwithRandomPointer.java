package algorithms.list;

import java.util.ArrayList;
import java.util.List;

/***
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

    Return a deep copy of the list.
    
    The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
    
    val: an integer representing Node.val
    random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.
     
    
    Example 1:
    
    
    Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
    Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
    Example 2:
    
    
    Input: head = [[1,1],[2,1]]
    Output: [[1,1],[2,1]]
    Example 3:
    
    
    
    Input: head = [[3,null],[3,0],[3,null]]
    Output: [[3,null],[3,0],[3,null]]
    Example 4:
    
    Input: head = []
    Output: []
    Explanation: Given linked list is empty (null pointer), so return null.
     
    
    Constraints:
    
    -10000 <= Node.val <= 10000
    Node.random is null or pointing to a node in the linked list.
    Number of Nodes will not exceed 1000.
 * @author miche
 *
 */
public class CopyListwithRandomPointer {

    public Node copyRandomList(Node head) {
        if(head == null) return null;
        List<Node> nlist = new ArrayList<Node>();
        List<Node> olist = new ArrayList<Node>();
        Node ptr = head;
        while(ptr != null) {
            Node node = new Node(ptr.val);
            Node next = ptr.next;
            nlist.add(node);
            if(nlist.size() > 1) {
                nlist.get(nlist.size() - 2).next = node;
            }
            olist.add(ptr);
            ptr.next = node; //old node's next point to the new node
            ptr = next;
        }
        for(int i = 0; i < olist.size(); i++) {
            nlist.get(i).random = olist.get(i).random == null ? null : olist.get(i).random.next;
        }
        //Restore the original linknode
        for(int i = 1; i < olist.size(); i++) {
            olist.get(i-1).next = olist.get(i);
        }
        olist.get(olist.size()-1).next = null;

        return nlist.get(0);
    }
}
