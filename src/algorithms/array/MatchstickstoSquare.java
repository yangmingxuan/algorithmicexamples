package algorithms.array;

import java.util.Arrays;

/***
 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

    Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.
    
    Example 1:
    Input: [1,1,2,2,2]
    Output: true
    
    Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
    Example 2:
    Input: [3,3,3,3,4]
    Output: false
    
    Explanation: You cannot find a way to form a square with all the matchsticks.
    Note:
    The length sum of the given matchsticks is in the range of 0 to 10^9.
    The length of the given matchstick array will not exceed 15.
 * @author miche
 *
 */
public class MatchstickstoSquare {

    Integer[] typeNums;
    int sideLength;
    int[] side;
    
    public boolean makesquare(int[] nums) {
        if(nums == null || nums.length < 4) return false;
        int allLength = 0;
        for(int num : nums) {
            allLength += num;
        }
        
        if(allLength % 4 != 0) {
            //can not construct a square
            return false;
        }
        sideLength = allLength / 4;
        side = new int[4];
        typeNums = new Integer[nums.length];
        for(int i = 0; i < nums.length; i++) {
            typeNums[i] = nums[i];
        }
        Arrays.sort(typeNums, (a,b)->b-a);
        
        //if(typeNums[0] > sideLength) return false;
        boolean[] visited = new boolean[nums.length];
        return dfs2(visited, sideLength, 0);
        //return dfs(0);
    }

    public boolean dfs(int index) {
        //if to the end of arrays
        if(index == typeNums.length) {
            return side[0] == side[1] && side[1] == side[2] && side[2] == side[3];
        }
        
        int stickLength = typeNums[index];
        
        for(int i = 0; i < 4; i++) {
            if(side[i] + stickLength <= sideLength) {
                side[i] += stickLength;
                if(dfs(index+1)) {
                    return true;
                }
                side[i] -= stickLength; //reset the side
            }
        }

        return false;
    }

    public boolean dfs2(boolean[] visited, int remain, int sideidx) {
        if(sideidx == 4) return true;
        
        for(int i = 0; i < typeNums.length; i++) {
            if(visited[i]) continue; //used
            if(typeNums[i] > remain) return false; //cannot used to be linked to a stick
            int tmp = typeNums[i];
            visited[i] = true;
            
            //if remain-tmp == 0, construct the next side
            boolean ans = dfs2(visited, remain-tmp==0 ? sideLength : remain-tmp, remain-tmp==0 ? sideidx+1 : sideidx);
            
            visited[i] = false; //reset
            if(ans) return true;
            while (i+1 < typeNums.length && typeNums[i+1] == typeNums[i]) i++;
        }
        
        return false;
    }
}
