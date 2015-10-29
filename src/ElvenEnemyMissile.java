//Never cower from difficulty; difficult soil harbors strong roots.


import java.awt.Rectangle;

//package com.zetcode;

public class ElvenEnemyMissile extends ElvenSprite {

    private final int BOARD_WIDTH = -50;
    private final int MISSILE_SPEED = 6;
    //private double angle;
    

    public ElvenEnemyMissile(int x, int y, double angle, String image_file) {
    	
    	
    	
        super(x, y, angle, 10, image_file);
        
        //x += (int)10*Math.cos(angle);
        //y += (int)10*Math.sin(angle);
        
        
        this.angle = angle;
        
        loadImage();
    }
    
    
    
    
    
    
    public void moveToLocation(int xLoc, int yLoc) {
        
    	//this.angle = angle
    	
    	
    	//Uses math and magic IDK
    	int xTheTriangle = xLoc - x;
    	int yTheTriangle = yLoc - y;
    	if ( xTheTriangle < -30 && (yTheTriangle > 15 || yTheTriangle < -15)){
    		
		//Go where life can exist
		//With the exception of areas where arctan is not useful
        	double angle = Math.atan((double) yTheTriangle/((double) xTheTriangle));
		
        	this.angle = angle + Math.PI;
        	
            x += Math.cos(this.angle) * MISSILE_SPEED;
            y += Math.sin(this.angle) * MISSILE_SPEED /2;
            
            //x -= 4;

    	} else {
    		move();
    	}
    	
    }
    

    public void move() {
        
    	x -= MISSILE_SPEED;
        if (x < BOARD_WIDTH) {
            vis = false;
        }
    }
    
    public Rectangle getBounds() {
    	
    	getImageDimensions();
        return new Rectangle(x, y, width, height);
        
        
    }

    
    
    
}