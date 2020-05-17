package algorithms.array;

import java.util.HashMap;
import java.util.Map;

/***
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.

    Each day, whether the cell is occupied or vacant changes according to the following rules:
    
    If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
    Otherwise, it becomes vacant.
    (Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)
    
    We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
    
    Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)
    
     
    
    Example 1:
    
    Input: cells = [0,1,0,1,1,0,0,1], N = 7
    Output: [0,0,1,1,0,0,0,0]
    Explanation: 
    The following table summarizes the state of the prison on each day:
    Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
    Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
    Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
    Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
    Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
    Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
    Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
    Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
    
    Example 2:
    
    Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
    Output: [0,0,1,1,1,1,1,0]
     
    
    Note:
    
    cells.length == 8
    cells[i] is in {0, 1}
    1 <= N <= 10^9
 * @author miche
 *
 */
public class PrisonCellsAfterNDays {

    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int state = 0;
        for(int i = 0; i < 8; i++) {
            if(cells[i] == 1) {
                state ^= 1 << i;
            }
        }
        int oldState = state, newState = 0;
        for(int i = 0; i < N; i++) {
            if(map.containsKey(oldState)) {
                newState = map.get(oldState);
            } else {
                newState = nextDay(oldState);
                map.put(oldState, newState);
            }
            oldState = newState;
        }
 
        int[] ans = new int[8];
        for(int i = 0; i < 8; i++) {
            if(((newState >> i) & 1) > 0) {
                ans[i] = 1;
            }
        }
        return ans;
    }

    public int[] nextDay(int[] oldCells) {
        int[] newCells = new int[8];
        newCells[0] = 0;
        newCells[7] = 0;
        for(int i = 1; i <= 6; i++) {
            newCells[i] = ~(oldCells[i-1]^oldCells[i+1]);
        }
        return newCells;
    }

    public int[] prisonAfterNDays2(int[] cells, int N) {
        Map<Integer, int[]> map = new HashMap<Integer, int[]>();
        int state = 0;
        for(int i = 0; i < 8; i++) {
            state = state << 1;
            state += cells[i];
        }
        int oldState = state, newState = 0, round = 0;
        for(; N > 0; N--) {
            if(map.containsKey(oldState)) {
                round = (map.get(oldState))[1] - N;
                N %= round;
                if(N > 0) {
                    newState = (map.get(oldState))[0];
                    (map.get(oldState))[1] = N;
                }
            } else {
                newState = nextDay(oldState);
                int[] result = new int[2];
                result[0] = newState;
                result[1] = N;
                map.put(oldState, result);
            }
            oldState = newState;
        }
 
        int[] ans = new int[8];
        for(int i = 0; i < 8; i++) {
            ans[8-1-i] = newState & 1;
            newState = newState >> 1;
        }
        return ans;
    }
    
    public int nextDay(int state) {
        /*
        int ans = 0;
        for(int i = 1; i <= 6; i++) {
            if(((state >> (i-1)) & 1) == ((state >> (i+1)) & 1)) {
                ans ^= 1 << i;
            }
        }
        */
        return (~((state << 1)^(state >> 1))) & 126;
    }

    public static void main(String[] argv) {
        int[] cells = {1,0,0,0,1,0,0,1};
        int N = 99; //1000000000;
        PrisonCellsAfterNDays ncad = new PrisonCellsAfterNDays();
        int[] newCells = ncad.prisonAfterNDays2(cells, N);
        for(int i = 0; i < newCells.length; i++) {
            System.out.print(newCells[i] + " ");
        }
    }
}
