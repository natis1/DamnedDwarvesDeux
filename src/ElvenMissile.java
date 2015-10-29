import java.awt.Rectangle;

//package com.zetcode;

public class ElvenMissile extends ElvenSprite {

	
	
	//Should be 2000* resolution scale
    private final int BOARD_WIDTH = 2000;
    private final int MISSILE_SPEED = 7;
    private double angle;
    

    public ElvenMissile(int x, int y, double angle, String image_file) {
    	
    	
    	
        super(x, y, angle, 10, image_file);
        x += (int)10*Math.cos(angle);
        y += (int)10*Math.sin(angle);
        
        
        this.angle = angle;
        
        loadImage();
    }
    
    /*private void initMissile() {
        
        loadImage("Images/elvenarrow.gif");  
        getImageDimensions();
    }*/

    
    public void moveToLocation(int xLoc, int yLoc) {
        
    	//this.angle = angle
    	
    	
    	//Uses math and magic IDK
    	int xTheTriangle = xLoc - x;
    	int yTheTriangle = yLoc - y;
    	
    	
    	if ( xTheTriangle > 30 && (yTheTriangle > 15 || yTheTriangle < -15)){
    		
        	double angle = Math.atan((double) yTheTriangle/((double) xTheTriangle));
        	this.angle = angle;
        	
            x += Math.cos(this.angle) * MISSILE_SPEED;
            y += Math.sin(this.angle) * MISSILE_SPEED;

    	} else {
    		move();
    	}
    	
    	
    	
    }
    

    public void move() {
        
    	//this.angle = angle
        x += Math.cos(this.angle) * MISSILE_SPEED;
        y += Math.sin(this.angle) * MISSILE_SPEED;
        
        if (x > BOARD_WIDTH) {
            vis = false;
        }
    }
    
    public Rectangle getBounds() {
    	
    	getImageDimensions();
        return new Rectangle(x, y, width, height);
        
        
    }

    
    
    
}


