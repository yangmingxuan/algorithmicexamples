package algorithms.concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;

/***
 * 
 * @author miche
 * The same instance of FooBar will be passed to two different threads. Thread A will call foo() while thread B will call bar(). Modify the given program to output "foobar" n times.

    Example 1:
    
    Input: n = 1
    Output: "foobar"
    Explanation: There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar(). "foobar" is being output 1 time.
    Example 2:
    
    Input: n = 2
    Output: "foobarfoobar"
    Explanation: "foobar" is being output 2 times.
 */

class FooBar {
    private int n;
    public final static ReentrantLock reenLock = new ReentrantLock();
    public final static Condition conditionFoo = reenLock.newCondition();
    public final static Condition conditionBar = reenLock.newCondition();
    public final static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            reenLock.lock();
            while(map.get("key") != null) {
                conditionFoo.await();
            }
            try {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                map.put("key","true");
                conditionBar.signalAll();
            } finally {
                reenLock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        //Thread.sleep(1);
        for (int i = 0; i < n; i++) {
            reenLock.lock();
            while(map.get("key") == null) {
                conditionBar.await();
            }
            try {
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                map.remove("key");
                conditionFoo.signalAll();
            } finally {
                reenLock.unlock();
            }
        }
    }

}

/***
class FooBar {
    private int n;
    private final Semaphore semaphoreFoo = new Semaphore(1);
    private final Semaphore semaphoreBar = new Semaphore(1);
    

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            semaphoreFoo.acquire(1);
            printFoo.run();
            semaphoreBar.release(1);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            semaphoreBar.acquire(1);
            printBar.run();
            semaphoreFoo.release(1);
        }
    }
}
*/

public class FooBarAlternately {
    public static void main(String[] args) throws NumberFormatException, IOException, InterruptedException {
        PrintFoo printFoo = new PrintFoo();
        PrintBar printBar = new PrintBar();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int count = Integer.parseInt(line);
            
            FooBar foobar = new FooBar(count);
            
            Thread t1 = new ThreadRunFoo(foobar, printFoo);
            Thread t2 = new ThreadRunBar(foobar, printBar);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        }      
    }
}

class PrintFoo implements Runnable {

    @Override
    public void run() {
        System.out.print("foo");
    }
    
}
class PrintBar implements Runnable {

    @Override
    public void run() {
        System.out.print("bar");
    }
    
}

class ThreadRunFoo extends Thread {
    private FooBar foobar;
    private Runnable runnable;
    public ThreadRunFoo(FooBar foobar, Runnable runnable) {
        this.foobar = foobar;
        this.runnable = runnable;
    }
    
    public void run() {
        try {
            foobar.foo(runnable);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
class ThreadRunBar extends Thread {
    private FooBar foobar;
    private Runnable runnable;
    public ThreadRunBar(FooBar foobar, Runnable runnable) {
        this.foobar = foobar;
        this.runnable = runnable;
    }
    
    public void run() {
        try {
            foobar.bar(runnable);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

