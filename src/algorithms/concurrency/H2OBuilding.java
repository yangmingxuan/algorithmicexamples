package algorithms.concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Semaphore;

/***
 * There are two kinds of threads, oxygen and hydrogen. Your goal is to group these threads to form water molecules. There is a barrier where each thread has to wait until a complete molecule can be formed. Hydrogen and oxygen threads will be given releaseHydrogen and releaseOxygen methods respectively, which will allow them to pass the barrier. These threads should pass the barrier in groups of three, and they must be able to immediately bond with each other to form a water molecule. You must guarantee that all the threads from one molecule bond before any other threads from the next molecule do.

    In other words:
    
    If an oxygen thread arrives at the barrier when no hydrogen threads are present, it has to wait for two hydrogen threads.
    If a hydrogen thread arrives at the barrier when no other threads are present, it has to wait for an oxygen thread and another hydrogen thread.
    We don’t have to worry about matching the threads up explicitly; that is, the threads do not necessarily know which other threads they are paired up with. The key is just that threads pass the barrier in complete sets; thus, if we examine the sequence of threads that bond and divide them into groups of three, each group should contain one oxygen and two hydrogen threads.
    
    Write synchronization code for oxygen and hydrogen molecules that enforces these constraints.
    
     
    
    Example 1:
    
    Input: "HOH"
    Output: "HHO"
    Explanation: "HOH" and "OHH" are also valid answers.
    Example 2:
    
    Input: "OOHHHH"
    Output: "HHOHHO"
    Explanation: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" and "OHHOHH" are also valid answers.
     
    
    Constraints:
    
    Total length of input string will be 3n, where 1 ≤ n ≤ 20.
    Total number of H will be 2n in the input string.
    Total number of O will be n in the input string.

 * @author miche
 *
 */
public class H2OBuilding {

    private final Semaphore h = new Semaphore(2);
    private final Semaphore o = new Semaphore(1);

    public H2OBuilding() {
        // TODO Auto-generated constructor stub
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        o.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        h.release(2);
    }

    public static void main(String argv[]) throws NumberFormatException, IOException, InterruptedException {
        PrintHydrogen printHydrogen = new PrintHydrogen();
        PrintOxygen printOxygen = new PrintOxygen();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int count = Integer.parseInt(line);
            
            H2OBuilding h2o = new H2OBuilding();
            
            Thread t1 = new ThreadHydrogen(h2o, printHydrogen, count);
            Thread t2 = new ThreadOxygen(h2o, printOxygen, count);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        }      
    }
}

class PrintHydrogen implements Runnable {
    @Override
    public void run() {
        System.out.print('H');
    }
}
class PrintOxygen implements Runnable {
    @Override
    public void run() {
        System.out.print('O');
    }
}

class ThreadHydrogen extends Thread {
    private H2OBuilding h2o;
    private Runnable runnable;
    private int n;
    public ThreadHydrogen(H2OBuilding h2o, Runnable runnable, int n) {
        this.h2o = h2o;
        this.runnable = runnable;
        this.n = n;
    }

    public void run() {
        try {
            for(int i = 0; i < 2*n; i++) {
                h2o.hydrogen(runnable);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class ThreadOxygen extends Thread {
    private H2OBuilding h2o;
    private Runnable runnable;
    private int n;
    public ThreadOxygen(H2OBuilding h2o, Runnable runnable, int n) {
        this.h2o = h2o;
        this.runnable = runnable;
        this.n = n;
    }

    public void run() {
        try {
            for(int i = 0; i < n; i++) {
                h2o.oxygen(runnable);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
