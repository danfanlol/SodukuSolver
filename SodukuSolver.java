import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SodukuSolver {
    private int[][] numbers;
    private ArrayList<int[][]> numberHistory = new ArrayList<int[][]>();
    public int[][] getNumbers(){
        return numbers;
    }

    public ArrayList<int[][]> getNumberHistory(){
        return numberHistory;
    }
    public int[][] copyNumbers(){
        int[][] copy = new int[9][9];
        for (int i = 0; i < copy.length;i++){
            for (int j= 0; j < copy[0].length;j++){
                copy[i][j] = numbers[i][j];
            }
        }
        return copy;
    }

    public SodukuSolver(){
        numbers = new int[][]{
                {0,9,8,5,0,6,7,2,3},
                {6,5,7,3,2,9,8,4,0},
                {1,3,2,7,8,4,6,9,5},
                {0,1,4,0,0,7,2,3,0},
                {2,8,5,6,9,3,1,7,4},
                {7,0,3,1,4,2,5,8,9},
                {8,2,9,4,6,1,3,5,7},
                {5,7,6,9,0,8,0,1,2},
                {3,0,1,2,7,5,9,6,0}
        };
        numberHistory.add(copyNumbers());
    }
    public SodukuSolver(String filePath) throws FileNotFoundException {
        numbers = new int[9][9];
        Scanner input = new Scanner(new File(filePath));
        for (int i= 0; i < 9;i++){
            for (int j= 0; j < 9;j++){
               numbers[i][j] = input.nextInt();
            }
        }
        numberHistory.add(copyNumbers());
    }
    public boolean solve(int row, int col) {
        if (row == numbers.length){
            row = 0;
            if (++col == numbers[row].length){
                return true;
            }
        }
        if (numbers[row][col] != 0) {
            return solve(row+1,col);
        }
        for (int k = 1; k <= numbers.length; k++) {
            if (isPossibleDigit(k,row,col)) {
                numbers[row][col] = k;
                numberHistory.add(copyNumbers());
                if (solve(row+1,col) == true) {
                    return true;
                }
            }
        }
        numbers[row][col] = 0;
        numberHistory.add(copyNumbers());
        return false;
    }
    public boolean isPossibleDigit(int val, int row, int col) {
        return !isInRow(val, row) && !isInColumn(val, col) &&
                !isInSquare(val, row, col);
    }
    public boolean isInRow(int val, int row){
        for (int i =0 ; i < numbers[row].length;i++){
            if (val == numbers[row][i]) return true;
        }
        return false;
    }
    public int[][] getSquare(int row, int col){
        int[][] square = new int[3][3];
        int i = ((int)(Math.floor((row/3.0))) )*3;
        int j= ((int)(Math.floor(col/3.0))) * 3;
        for (int x = 0;x < 3; x++){
            for (int y = 0; y < 3;y++){
                square[x][y] = numbers[i+x][j+y];
            }
        }
        return square;
    }
    public boolean isInColumn(int val, int col){
        for (int i=0 ; i < numbers.length;i++){
            if (val == numbers[i][col]) return true;
        }
        return false;
    }
    public boolean isInSquare(int val, int row, int col) {
        int[][] square = getSquare(row, col);
        for(int r = 0; r < square.length; r++)
            for(int c = 0; c < square[r].length; c++)
                if(square[r][c] == val)
                    return true;
        return false;
    }
}
