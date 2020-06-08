package algorithms.struct;

import java.util.LinkedHashSet;

/***
 * 贪吃蛇
 * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

    The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
    
    You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
    
    Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
    
    When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
    
    Example:
    
    Given width = 3, height = 2, and food = [[1,2],[0,1]].
    
    Snake snake = new Snake(width, height, food);
    
    Initially the snake appears at position (0,0) and the food at (1,2).
    
    |S| | |
    | | |F|
    
    snake.move("R"); -> Returns 0
    
    | |S| |
    | | |F|
    
    snake.move("D"); -> Returns 0
    
    | | | |
    | |S|F|
    
    snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
    
    | |F| |
    | |S|S|
    
    snake.move("U"); -> Returns 1
    
    | |F|S|
    | | |S|
    
    snake.move("L"); -> Returns 2 (Snake eats the second food)
    
    | |S|S|
    | | |S|
    
    snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 * @author miche
 *
 */
public class SnakeGame {

    int row;
    int col;
    int width;
    int height;
    int[][] food;
    LinkedHashSet<Integer> snake;
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.row = 0;
        this.col = 0;
        this.width = width;
        this.height = height;
        this.food = food;
        //when comparing if existence(bit itself), LinkedHashSet is faster than LinkedList
        this.snake = new LinkedHashSet<Integer>();
        this.snake.add(0);
    }

    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        if("U".equals(direction)) {
            row--;
        } else if("D".equals(direction)) {
            row++;
        } else if("L".equals(direction)) {
            col--;
        } else if("R".equals(direction)) {
            col++;
        } else {
            return -1;
        }
        
        //if out of bound game over
        if(row < 0 || row >= height || col < 0 || col >= width) {
            return -1;
        }
        
        //if bit itself game over
        int newPosition = row*width + col;
        //needn't compare the tail, because the tail will move to the next step
        int tail = snake.iterator().next();
        if(newPosition != tail && snake.contains(newPosition)) {
            return -1;
        }
        
        //next food
        int[] nextFood = snake.size() <= food.length ? food[snake.size()-1] : null;;
        
        //if eat the food, snake's length increases by 1, food remove
        if(nextFood != null && (row == nextFood[0] && col == nextFood[1])) {
            //food.remove(0);
            snake.add(newPosition);
        } else {
            //snake move to the next, and tail remove
            snake.remove(tail);
            snake.add(newPosition);
        }
        
        return snake.size()-1;
    }

}
