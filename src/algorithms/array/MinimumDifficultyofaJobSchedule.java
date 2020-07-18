package algorithms.array;

/***
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job, you have to finish all the jobs j where 0 <= j < i).

    You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done in that day.
    
    Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].
    
    Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
    
     
    
    Example 1:
    
    
    Input: jobDifficulty = [6,5,4,3,2,1], d = 2
    Output: 7
    Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
    Second day you can finish the last job, total difficulty = 1.
    The difficulty of the schedule = 6 + 1 = 7 
    Example 2:
    
    Input: jobDifficulty = [9,9,9], d = 4
    Output: -1
    Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
    Example 3:
    
    Input: jobDifficulty = [1,1,1], d = 3
    Output: 3
    Explanation: The schedule is one job per day. total difficulty will be 3.
    Example 4:
    
    Input: jobDifficulty = [7,1,7,1,7,1], d = 3
    Output: 15
    Example 5:
    
    Input: jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
    Output: 843
 * @author miche
 *
 */
public class MinimumDifficultyofaJobSchedule {

    int[][] dp;
    public int minDifficulty(int[] jobDifficulty, int d) {
        if(d > jobDifficulty.length) return -1;
        //dp[i][j] represents the minimum difficulty
        //and the minimum difficulty required to start the ith task when j days remain
        //dp[i][j]表示还剩余j天时，第i项任务开始需要的最小难度和
        dp = new int[jobDifficulty.length][d+1];
        return findMinDfs(jobDifficulty, 0, d);
    }

    public int findMinDfs(int[] difficulty, int startJobPointer, int numDaysLeft) {
        if(dp[startJobPointer][numDaysLeft] > 0) return dp[startJobPointer][numDaysLeft];
        if(numDaysLeft == 1) {
            // If only 1 day is present, then the
            // minimum difficulty can't be smaller than the maximum of all days.
            //如果只剩1天，那么最后一天的难度就是剩余最大难度的工作难度为本日的难度值
            int max = 0;
            for(int i = startJobPointer; i < difficulty.length; i++) {
                max = Math.max(max, difficulty[i]);
            }
            dp[startJobPointer][numDaysLeft] = max;
            return dp[startJobPointer][numDaysLeft];
        }
        
        int max = 0, ans = Integer.MAX_VALUE;
        // From Start pointer you can go max upto (total diff - numDaysLeft) since every 
        // day has to get some task to do.
        //每次多一个工作，递归得出所需要的最小值。 (如果最终剩余N天，所余的任务不能小于N)
        for(int i = startJobPointer; i <= difficulty.length - numDaysLeft; i++) {
            max = Math.max(max, difficulty[i]);
            ans = Math.min(ans, max+findMinDfs(difficulty, i+1, numDaysLeft-1));
        }
        
        dp[startJobPointer][numDaysLeft] = ans;
        return dp[startJobPointer][numDaysLeft];
    }

    public int minDifficulty2(int[] jobDifficulty, int d) {
        int n=jobDifficulty.length;
        if(d > n){
            return -1;
        }
        //dp[i][j] represents the minimum difficulty
        //and the minimum difficulty required to start the ith task when j days remain
        //dp[i][j]表示还剩余j天时，第i项任务开始需要的最小难度和
        int dp[][]=new int[n][d+1];
        // If only 1 day is present, then the
        // minimum difficulty can't be smaller than the maximum of all days.
        //如果只剩1天，那么最后一天的难度就是剩余最大难度的工作难度为本日的难度值
        dp[n-1][1]=jobDifficulty[n-1];
        for(int i=n-2;i>=0;i--){
            dp[i][1]=Math.max(dp[i+1][1],jobDifficulty[i]);
        }

        for(int j=2;j<=d;j++){
            for(int i=0;i<=n-j;i++){
                dp[i][j]=jobDifficulty[i]+dp[i+1][j-1];
                int max=jobDifficulty[i];
                for(int k=i+1;k<=n-j;k++){
                    // From Start pointer you can go max upto (total diff - numDaysLeft) since every 
                    // day has to get some task to do.
                    //当剩余j天时，所能分配的工作可能由i到n-j，那么最小值就是dp的值，循环计算得出这个最小值
                    max=Math.max(max,jobDifficulty[k]);
                    dp[i][j]=Math.min(dp[i][j],dp[k+1][j-1]+max);
                }
            }
        }
        return dp[0][d];
    }

}
