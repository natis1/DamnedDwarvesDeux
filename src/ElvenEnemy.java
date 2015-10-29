import java.awt.Rectangle;
import java.util.ArrayList;
//Act with wisdom, but act.






//Hel yes, this code looks awesome. I cannot even explain why.

public class ElvenEnemy extends ElvenSprite {




    //This will be determined by the RNG to allow for multiple kinds of enemies
    //All enemies controlled by this class so that they can be in one array though.
    protected int elvenEnemyType;

    private int elvenEnemySpeed;
    
    
    public double universalScaler;
    
    
    //Missile data
    private int nextEnemyMissile;
    public ArrayList<ElvenEnemyMissile> elvenEnemyMissiles;
    
    //Missile garbage collector
    //Will self nuke in t-50.
    public int missileGarbageCollector = 200;
    
    private float realx;
    
    //to be used when enemies move better
    private float realy;
    
    

    public ElvenEnemy(long elvenActionToTake, double scaler) {
    	




        super(2000, 0, -Math.PI/2, 50, "Images/elvenelf1.png");
        universalScaler = scaler;
        
        initEnemy(elvenActionToTake);
    }

        //Eh good enough
	private final int BOARD_WIDTH = -200;
    
	private int EnemyHP;
    
    
    private void initEnemy(long elvenActionToTake) {
    	
    	elvenEnemyMissiles = new ArrayList<ElvenEnemyMissile>();
    	nextEnemyMissile = 50;
    	
    	
        //So many possibilites. We need one for every y co-ord and every elvenEnemyType
        //One day this will take advantage of the size of the screen, but I am a bad programmer
    	int realElvenAction = (int) elvenActionToTake % 4320;

        //0 - ? possible
        elvenEnemyType = realElvenAction / 1080;
        elvenEnemySpeed = 10;
        
        if (elvenEnemyType < 0){
        	elvenEnemyType = 0 - elvenEnemyType;
        }
        
        if (elvenEnemyType == 0){
        	this.image_file = "Images/e1.png";
        	elvenEnemySpeed = 8;
        	
        	
        	
        } else if (elvenEnemyType == 1){
        	this.image_file = "Images/e2.png";
        	elvenEnemySpeed = 5;
        } else if (elvenEnemyType == 2){
        	this.image_file = "Images/asteroid.png";
        	EnemyHP = 50000;
        	elvenEnemySpeed = 5;
        } else {
        	elvenEnemyType = -1;
        }

        //Get the y value
        realElvenAction = realElvenAction % 1080;
        if (realElvenAction < 0){
        	realElvenAction = 0 - realElvenAction;
        }
        
        
        realElvenAction = realElvenAction - 100;
        
        if (realElvenAction < 0){
        	realElvenAction = 0;
        }
        	realElvenAction = (int) (realElvenAction * universalScaler);

        //Y should be determined by this point
        //DONE
    	y = realElvenAction;
    	
    	realy = y;
    	realx = x;
    	
    	
    	EnemyHP = 5;
    	loadImage(); 
    }
    
    public int hit(){
    	EnemyHP--;
        if (EnemyHP < 1) {
        	
        	
    		if (elvenEnemyType == 0){
    			vis = false;
    			return 50;
    		}
    		if (elvenEnemyType == 1){
    			vis = false;
    			return 100;
    		}
    		if (elvenEnemyType == -1){
    			vis = false;
    			return 300;
    		}
            
    		
        } else {
        	return 1;
        }
		return 0;
    }


    public void move() {
    	
    	if (vis){
    		if (nextEnemyMissile <= 0){
        		if (elvenEnemyType == 0){
        			
        			elvenEnemyMissiles.add(new ElvenEnemyMissile( x, 
            				y + height/2, this.angle, "Images/ElvenEnemyBullet0.png"));
        			
        			//Reset based on enemy type
        			nextEnemyMissile = 70;
        		}
        		if (elvenEnemyType == 1){
        			
        			elvenEnemyMissiles.add(new ElvenEnemyMissile( x, 
            				y + height/2, this.angle, "Images/ElvenEnemyBullet0.png"));
        			
        			//Reset based on enemy type
        			nextEnemyMissile = 30;
        		}
        		if (elvenEnemyType == -1){
        			
        			elvenEnemyMissiles.add(new ElvenEnemyMissile( x, 
            				y + height/2, this.angle, "Images/ElvenEnemyBullet0.png"));
        			
        			//Reset based on enemy type
        			nextEnemyMissile = 120;
        		}
        		
        		
        	}
    		
    		
        	nextEnemyMissile--;
        	
            
            realx -= elvenEnemySpeed;
            if (realx < BOARD_WIDTH) {
                vis = false;
            }
            
            
            //approximation of real location at any frame
            x = (int) realx;
            y = (int) realy;
            
    	}
        
    	
    	
    	
    	
        for (int i = 0; i < elvenEnemyMissiles.size(); i++) {
            ElvenEnemyMissile e = (ElvenEnemyMissile) elvenEnemyMissiles.get(i);
            if (!e.isVisible()) {
            	elvenEnemyMissiles.remove(i);
            }
        }
    	
    	
    	
    	

    }
    
    public Rectangle getBounds() {
    	
    	getImageDimensions();
        return new Rectangle(x, y, width, height);
    }
    
    
    
}




