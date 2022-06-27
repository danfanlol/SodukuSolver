import processing.core.PApplet;
import processing.core.PImage;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class SodukuProcessing extends PApplet{
    public static void main(String[] args){ PApplet.main("SodukuProcessing");}
    public void settings(){
        size(450,450);
    }
    SodukuSolver s;
    ArrayList<int[][]> numberHistory;
    int index = 0;
    public void setup(){
        background(255);
        textSize(40);
        textAlign(CENTER);
        fill(0,0,255);
        try {
            s = new SodukuSolver("scores.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        drawPuzzle(s.getNumbers());
        s.solve(0,0);
        numberHistory = s.getNumberHistory();
    }


    public void draw(){
        if (!paused && index < numberHistory.size()){
            drawPuzzle(numberHistory.get(index));
            index += 1;
        }
    }
    public void drawPuzzle(int[][] numbers){
        background(255);
        drawGridLines();
        drawNumbers(numbers);
    }
    public void drawGridLines(){
        fill(0);
        int numLines = 9;
        for (int i=0 ; i <= numLines;i++){
            if (i % 3 == 0)
                strokeWeight(3);
            else
                strokeWeight(1);
            line(i*width/(numLines*(float)1.0),0,i*width/(numLines*(float)1.0),height);
            line(0,i*height/numLines,width,i*height/numLines);
        }
    }
    public void drawNumbers(int[][] numbers){
        for (int i =0 ; i < numbers.length;i++){
            for (int j= 0; j < numbers[0].length;j++){

                if (numbers[i][j] != 0){
                    text("" + numbers[i][j],(j+1)*height/ numbers.length-25,(i+1)*width/numbers.length-10);
                }
            }
        }
    }
    boolean paused = true;
    public void mousePressed(){
        paused = false;
    }
}
