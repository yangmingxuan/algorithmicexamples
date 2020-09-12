package algorithms.array;

/***
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.

    However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
    
    Return the least number of units of times that the CPU will take to finish all the given tasks.
    
     
    
    Example 1:
    
    Input: tasks = ["A","A","A","B","B","B"], n = 2
    Output: 8
    Explanation: 
    A -> B -> idle -> A -> B -> idle -> A -> B
    There is at least 2 units of time between any two same tasks.
    Example 2:
    
    Input: tasks = ["A","A","A","B","B","B"], n = 0
    Output: 6
    Explanation: On this case any permutation of size 6 would work since n = 0.
    ["A","A","A","B","B","B"]
    ["A","B","A","B","A","B"]
    ["B","B","B","A","A","A"]
    ...
    And so on.
    Example 3:
    
    Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
    Output: 16
    Explanation: 
    One possible solution is
    A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
     
    
    Constraints:
    
    1 <= task.length <= 104
    tasks[i] is upper-case English letter.
    The integer n is in the range [0, 100].
 * @author miche
 *
 */
public class TaskScheduler {

    /***
     * Solution: 问题可转化为最大重复任务数量的计算，相同的任务之间的间隔为n, 那么最大任务数量需要的长度为(N-1)*(n+1)+最多重复的任务数量
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int maxnum = 0;
        int[] frequencies = new int[26];
        for(char ch : tasks) {
            frequencies[ch-'A']++;
            maxnum = Math.max(maxnum, frequencies[ch-'A']);
        }
        
        int maxRepeatTaskCount = 0;
        for(int num : frequencies) {
            if(num == maxnum) {
                maxRepeatTaskCount++;
            }
        }

        return Math.max(tasks.length, (maxnum-1)*(n+1)+maxRepeatTaskCount);
    }

}
