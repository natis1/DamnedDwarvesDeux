import java.util.ArrayList;
import java.util.Dictionary;


public class DwarvenSpecies {

    public boolean[] keysPressed = new boolean[5];

    protected int fitness;




    static Dictionary GENETIC_CODE;

    private int[][] relativeEnemyLocations;

    private int nearestEnemyRelativeYPosition;
    private int nearestEnemyHealth;
    private int pixTimer;



    public DwarvenSpecies(Dictionary genes){
        GENETIC_CODE = genes;


    }

    public void setGameVariablesAndRun(int[][] relativeEnemyLocations, ArrayList<Integer> variables){
        this.relativeEnemyLocations = relativeEnemyLocations;
        nearestEnemyRelativeYPosition = variables.get(0);
        nearestEnemyHealth = variables.get(1);
        pixTimer = variables.get(2);




        runProgram();
    }


    public void runProgram(){
        for (int i = 0; i < 5; i++){
            keysPressed[i] = false;
        }
        int shouldGoLeft = (GENETIC_CODE.get(left_nearestEnemyYBenefit))




    }

    private void pushLeft(){
        keysPressed[0] = true;




    }
    private void pushRight(){
        keysPressed[1] = true;

    }
    private void pushUp(){
        keysPressed[2] = true;

    }
    private void pushDown(){
        keysPressed[3] = true;


    }

    private void shoot(){
        keysPressed[4] = true;

    }

}
