package algorithms.concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/***
 * Suppose you are given the following code:

    class ZeroEvenOdd {
      public ZeroEvenOdd(int n) { ... }      // constructor
      public void zero(printNumber) { ... }  // only output 0's
      public void even(printNumber) { ... }  // only output even numbers
      public void odd(printNumber) { ... }   // only output odd numbers
    }
    The same instance of ZeroEvenOdd will be passed to three different threads:
    
    Thread A will call zero() which should only output 0's.
    Thread B will call even() which should only ouput even numbers.
    Thread C will call odd() which should only output odd numbers.
    Each of the threads is given a printNumber method to output an integer. Modify the given program to output the series 010203040506... where the length of the series must be 2n.
    
     
    
    Example 1:
    
    Input: n = 2
    Output: "0102"
    Explanation: There are three threads being fired asynchronously. One of them calls zero(), the other calls even(), and the last one calls odd(). "0102" is the correct output.
    Example 2:
    
    Input: n = 5
    Output: "0102030405"
 * @author miche
 *
 */
public class ZeroEvenOdd {

    private int n;
    private final static ReentrantLock reenLock = new ReentrantLock();
    private final static Condition conditionZero = reenLock.newCondition();
    private final static Condition conditionEven = reenLock.newCondition();
    private final static Condition conditionOdd = reenLock.newCondition();
    private final static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
    private static volatile int count = 0;

    public ZeroEvenOdd(int n) {
        this.n = n;
        count = 0;
        map.clear();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while(count < n) {
            reenLock.lock();
            while(map.get("ODD") != null || map.get("EVEN") != null) {
                conditionZero.await();
            }
            try {
                printNumber.accept(0);
                count++;
                map.put("count", count);
                if(count % 2 == 0) {
                    map.put("EVEN", count);
                } else {
                    map.put("ODD", count);
                }
                conditionOdd.signalAll();
                conditionEven.signalAll();
                
                
            } finally {
                reenLock.unlock();
            }
        }
        if(n % 2 == 0) {
            map.put("EVEN", n);
            map.put("ODD", n+1);
        } else {
            map.put("ODD", n);
            map.put("EVEN", n+1);
        }
        map.put("count", n+1);
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        boolean isprint = false;
        while(map.get("count") == null || map.get("count").intValue() <= n) {
            reenLock.lock();
            while(map.get("EVEN") == null) {
                conditionEven.await();
            }
            try {
                if(map.get("EVEN").intValue() % 2 == 0 && map.get("EVEN").intValue() <= n) {
                    printNumber.accept(map.get("EVEN").intValue());
                    if(map.get("EVEN").intValue() == n) {
                        isprint = true;
                    }
                }
                    map.remove("EVEN");
                    conditionZero.signalAll();
                    conditionOdd.signalAll();
            } finally {
                reenLock.unlock();
            }
        }
        if(!isprint && map.get("EVEN") != null && map.get("EVEN").intValue() % 2 == 0 && map.get("EVEN").intValue() == n) {
            printNumber.accept(map.get("EVEN").intValue());
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        boolean isprint = false;
        while(map.get("count") == null || map.get("count").intValue() <= n) {
            reenLock.lock();
            while(map.get("ODD") == null) {
                conditionOdd.await();
            }
            try {
                if(map.get("ODD").intValue() % 2 != 0 && map.get("ODD").intValue() <= n) {
                    printNumber.accept(map.get("ODD").intValue());
                    if(map.get("ODD").intValue() == n) {
                        isprint = true;
                    }
                }
                    map.remove("ODD");
                    conditionZero.signalAll();
                    conditionEven.signalAll();
            } finally {
                reenLock.unlock();
            }
        }
        if(!isprint && map.get("ODD") != null && map.get("ODD").intValue() % 2 != 0 && map.get("ODD").intValue() == n) {
            printNumber.accept(map.get("ODD").intValue());
        }
    }

    /***
     * Runtime: 5 ms, faster than 47.23% of Java online submissions for Print Zero Even Odd.
       Memory Usage: 35.7 MB, less than 100.00% of Java online submissions for Print Zero Even Odd.
     * @param argv
     * @throws NumberFormatException
     * @throws IOException
     */
    public static void main(String argv[]) throws NumberFormatException, IOException {
        IntConsumer printNumber = new IntConsumer();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = in.readLine()) != null) {
            int number = Integer.parseInt(line);
            ZeroEvenOdd thread012 = new ZeroEvenOdd(number);
            Thread t0 = new Thread() {
                public void run(){
                    try {
                        thread012.zero(printNumber);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            Thread t1 = new Thread() {
                public void run(){
                    try {
                        thread012.odd(printNumber);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            Thread t2 = new Thread() {
                public void run(){
                    try {
                        thread012.even(printNumber);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            t0.start();
            t1.start();
            t2.start();
            try {
                t0.join();
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
       }
    }
}

class IntConsumer {
    public void accept(int x) {
        System.out.print(x);
    }
}
