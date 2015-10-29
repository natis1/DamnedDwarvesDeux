import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ElvenCraft extends ElvenSprite {
	
	private ElvenKeyManager keySetter = new ElvenKeyManager();
	
	
    private float dx;
    private float dy;
    public float accelx;
    public float accely;
    
    private int shieldRegenTimer;
    public int craftShields;
    
    
    ElvenPixAlly pixieHelper;
        
    private int delayShot = 15;
    
    
    public float realx;
    public float realy;
    
    private double universalScaler;
    
    
    private ArrayList<ElvenMissile> Elvenmissiles;
    
    

    public ElvenCraft(int x, int y, double angle, double scaler) {
    	
    	
        super(x, y, 0, 20, "Images/Ghost.gif");
        
        
        
        universalScaler = scaler;
        
        initCraft();
    }
    
    
    

    private void initCraft() {
    	
    	pixieHelper = new ElvenPixAlly(x - 30, y + 80, angle, "Images/elvenFae.png");
    	
        Elvenmissiles = new ArrayList<ElvenMissile>();
        loadImage(); 
        
        realx = x;
        realy = y;
    }

    public void move() {
    	
    	delayShot--;
    	
    	shieldRegenTimer++;
    	if (shieldRegenTimer > 30 && craftShields < 50){
    		craftShields++;
    		shieldRegenTimer = 0;
    	}
    	
    	
    	
    	
    	if (accelx > 3.0 * universalScaler){
    		accelx = (float) (3.0 * universalScaler);
    	} else if (accelx < -3.0 * universalScaler){
    		accelx = (float) (-3.0 * universalScaler);
    	}
    	if (accely > 3.0 * universalScaler){
    		accely = (float) (3.0 * universalScaler);
    	} else if (accely < -3.0 * universalScaler){
    		accely = (float) (-3.0 * universalScaler);
    	}
    	dx = accelx;
    	dy = accely;
    	
        realx += dx;
        realy += dy;
        
        x = (int) realx;
        y = (int) realy;
        
        
        if (x < 0){
        	accelx = -accelx;
        	x = 1;
        } else if (x > (int) (1000)){
        	accelx = -accelx;
        	x = (int) (1000) - 1;
        }
        
        if (y < 0){
        	accely = -accely;
        	y = 1;
        } else if (y > (int) (1080 - getWidth())){
        	accely = -accely;
        	y = (int) (1080) - getWidth();
        }
        
        
        pixieHelper.move(x - 30, y + 80, this.angle);
        
        
    }
    
    public Rectangle getBounds() {
    	getImageDimensions();
        return new Rectangle(x, y, width, height);
    }
    
    
    //In my new game this will be null :)
    public ArrayList<ElvenMissile> getMissiles() {
        return Elvenmissiles;
    }
    

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
        	keySetter.elvenAsciiInput[0] = true;
        }

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
        	keySetter.elvenAsciiInput[2] = true;
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
        	keySetter.elvenAsciiInput[3] = true;
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
        	keySetter.elvenAsciiInput[1] = true;
        }

        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
        	keySetter.elvenAsciiInput[4] = true;
        }
		if (key == KeyEvent.VK_Q) {
			keySetter.elvenAsciiInput[5] = true;
		}
		if (key == KeyEvent.VK_E) {
			keySetter.elvenAsciiInput[6] = true;
		}
        
        
    }
    
    public void isPressed(){
    	if (keySetter.elvenAsciiInput[0]){
    		fire();
    	}
    	if (keySetter.elvenAsciiInput[1]){
    		accely = (float) (accely - 0.25);
    	}
    	if (keySetter.elvenAsciiInput[2]){
    		accelx = (float) (accelx - 0.1);
    	}
    	if (keySetter.elvenAsciiInput[3]){
    		accelx = (float) (accelx + 0.1);
    	}
    	if (keySetter.elvenAsciiInput[4]){
    		accely = (float) (accely + 0.25);
    	}
    	if (keySetter.elvenAsciiInput[5]){
    		this.changeAngle(Math.PI/128.0);
    		realx = x;
    		realy = y;
    	}
    	if (keySetter.elvenAsciiInput[6]){
    		this.changeAngle(-Math.PI/128.0);
    		realx = x;
    		realy = y;
    	}
    	
    	
    }

    public void fire() {
    	
    	if (delayShot < 0){
    		delayShot = 7;
    		Elvenmissiles.add(new ElvenMissile( x + width/2 + (int)(30 * Math.cos(this.angle)), 
    				y + height/2 + (int)(30 * Math.sin(this.angle)), 
    				this.angle, "Images/elvenarrow.gif"));
    		
    		pixieHelper.fire();
    		
    	}
        
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
        	keySetter.elvenAsciiInput[0] = false;
        }

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
        	keySetter.elvenAsciiInput[2] = false;
        	
        	
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
        	keySetter.elvenAsciiInput[3] = false;
        	//dx = dx + accelx;
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
        	keySetter.elvenAsciiInput[1] = false; 
        }
        
		if (key == KeyEvent.VK_Q) {
			keySetter.elvenAsciiInput[5] = false;
		}
		if (key == KeyEvent.VK_E) {
			keySetter.elvenAsciiInput[6] = false;
		}
        
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
        	
        	
        	keySetter.elvenAsciiInput[4] = false;
        }
    }
    

    
}




