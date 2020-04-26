package algorithms.struct;

import java.util.PriorityQueue;

/***
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

    For example,
    [2,3,4], the median is 3
    
    [2,3], the median is (2 + 3) / 2 = 2.5
    
    Design a data structure that supports the following two operations:
    
    void addNum(int num) - Add a integer number from the data stream to the data structure.
    double findMedian() - Return the median of all elements so far.
     
    
    Example:
    
    addNum(1)
    addNum(2)
    findMedian() -> 1.5
    addNum(3) 
    findMedian() -> 2
     
    
    Follow up:
    
    If all integer numbers from the stream are between 0 and 100, how would you optimize it?
    If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * @author miche
 *
 */
public class MedianFinder {

    private PriorityQueue<Integer> minPQ;
    private PriorityQueue<Integer> maxPQ;
    public MedianFinder() {
        minPQ = new PriorityQueue<Integer>();
        maxPQ = new PriorityQueue<Integer>((a, b) -> b-a);
    }

    public void addNum(int num) {
        if((minPQ.isEmpty() || minPQ.size() == 1) && maxPQ.isEmpty()) {
            minPQ.offer(num);
        } else if(num > minPQ.peek()) {
            minPQ.offer(num);
        } else if(num < maxPQ.peek()) {
            maxPQ.offer(num);
        } else {
            minPQ.offer(num);
        }
        
        if(minPQ.size() - maxPQ.size() > 1) {
            maxPQ.offer(minPQ.poll());
        } else if(maxPQ.size() - minPQ.size() > 1) {
            minPQ.offer(maxPQ.poll());
        }
    }

    public double findMedian() {
        if(minPQ.isEmpty()) {
            return 0.0;
        }
        if((minPQ.size()+maxPQ.size()) % 2 == 0) {
            return (double)(minPQ.peek() + maxPQ.peek()) / 2;
        }
        
        return minPQ.size() > maxPQ.size() ? minPQ.peek() : maxPQ.peek();
    }

}
