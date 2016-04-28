import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;


public class DwarvenSpecies {

    public boolean[] keysPressed = new boolean[5];

    protected int fitness;




    private HashMap GENETIC_CODE;

    private int[][] relativeEnemyLocations;

    private int nearestEnemyRelativeYPosition;
    private int nearestEnemyHealth;
    private int pixTimer;



    public DwarvenSpecies(HashMap genes){
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
        int shouldGoLeft = (int)(((double) GENETIC_CODE.get("left_nearestEnemyY") * (double) nearestEnemyRelativeYPosition )
                + ((double) GENETIC_CODE.get("left_nearestEnemyHealth") * (double) nearestEnemyHealth)
                + ((double) GENETIC_CODE.get("left_pixTimer") * (double) pixTimer));
        if (shouldGoLeft > 0) pushLeft();

        int shouldGoUp = (int)(((double) GENETIC_CODE.get("up_nearestEnemyY") * (double) nearestEnemyRelativeYPosition )
                + ((double) GENETIC_CODE.get("up_nearestEnemyHealth") * (double) nearestEnemyHealth)
                + ((double) GENETIC_CODE.get("up_pixTimer") * (double) pixTimer));
        if (shouldGoUp > 0) pushUp();

        int shouldGoRight = (int)(((double) GENETIC_CODE.get("right_nearestEnemyY") * (double) nearestEnemyRelativeYPosition )
                + ((double) GENETIC_CODE.get("right_nearestEnemyHealth") * (double) nearestEnemyHealth)
                + ((double) GENETIC_CODE.get("right_pixTimer") * (double) pixTimer));
        if (shouldGoRight > 0) pushRight();

        int shouldGoDown = (int)(((double) GENETIC_CODE.get("down_nearestEnemyY") * (double) nearestEnemyRelativeYPosition )
                + ((double) GENETIC_CODE.get("down_nearestEnemyHealth") * (double) nearestEnemyHealth)
                + ((double) GENETIC_CODE.get("down_pixTimer") * (double) pixTimer));
        if (shouldGoDown > 0) pushDown();

        int shouldShoot = (int)(((double) GENETIC_CODE.get("shoot_nearestEnemyY") * (double) nearestEnemyRelativeYPosition )
                + ((double) GENETIC_CODE.get("shoot_nearestEnemyHealth") * (double) nearestEnemyHealth)
                + ((double) GENETIC_CODE.get("shoot_pixTimer") * (double) pixTimer));
        if (shouldShoot > 0) shoot();


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
