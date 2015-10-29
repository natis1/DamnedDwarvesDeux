//package com.zetcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;


public class ElvenBoard extends JPanel implements ActionListener {
	
	//private KeyboardAnimation animation2

    private final int ICRAFT_X = 200;
    private final int ICRAFT_Y = 500;
    
    private double universalScaler;
    
    
    private int health = 100;
    
    
    private boolean didCreateTheMainMenu = false;
    
    
    private boolean didChangeColor = false;
    private boolean is4K;
    public boolean isGameOver = false;
    private int score = 0;
    

    //This caps the game framerate, which means that the game doesn't use delta-T
    //For calculating movement. I think this is fine if we set the cap at something like 144hz (~7)
    //Smooth enough for most monitors even if eyes can still see past it.


    //Hopefully all monitors support G-sync in the future :)
    private int DELAY = 10;
    private Timer timer;
    private ElvenCraft craft;
    private ArrayList<ElvenEnemy> Elvenenemies;
    public int spawnTimer = 0;
    
    private String bgImageString;


    public Random universalRandomNumberGenerator;

                
    private ElvenGameOverScreen myGameOverScreen;
    private ElvenBackgroundSprite backgroundSprite1;
    private ElvenBackgroundSprite backgroundSprite2;
    
    
    
    
    public ElvenBoard(double scaler) {
    	universalScaler = scaler;
        initBoard();
        
        
    }

    private void initBoard() {
    	
    	PrintWriter writer;
		try {
			writer = new PrintWriter("elvenshooterTEMP", "UTF-8");
			
			
			writer.println("Still Alive");
			
            writer.close();
            
			
		} catch (FileNotFoundException e) {
			System.out.println(
	                "Something is seriously wrong with your system");
			
		} catch (UnsupportedEncodingException e) {
			System.out.println(
	                "You do not have UTF-8? I am seriously amazed");
		}

    	
    	
    	if (universalScaler <= 1.0001){
    		bgImageString = "Images/background2.png";
    		is4K = false;
    	} else {
    		bgImageString = "Images/background4.png";
    		is4K = true;
    	}
    	
    	
    	myGameOverScreen = new ElvenGameOverScreen();
    	
    	Elvenenemies = new ArrayList<ElvenEnemy>();
    	
    	backgroundSprite1 = new ElvenBackgroundSprite(0, bgImageString);
    	backgroundSprite2 = new ElvenBackgroundSprite(4000 , bgImageString);
    	
    	//ElvenBackgroundSprites
    	
        //Real Seed means completely deterministic. No Entropy. I hate Entropy
    	universalRandomNumberGenerator = new Random(2048);
    	
        addKeyListener(new TAdapter());
        setFocusable(true);
        
        
        
        setDoubleBuffered(true);
        
        double angle = 0;
        //String picName = "Images/Ghost.gif";
        

        craft = new ElvenCraft(ICRAFT_X, ICRAFT_Y, angle, universalScaler);
        
        

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }


/** This function draws all of the sprites using graphics2D libraries
All drawing must be called from the board
You cannot call doDrawing from other classes, to add a sprite to
the drawing queue, create the class inside the board.

Yes I know it is an oversite, whatever.

*/
    private void doDrawing(Graphics g) {
    	
    	Graphics2D g2d = (Graphics2D) g;
    	
    	if (!isGameOver){
    		
    		
    		g2d.scale(universalScaler, universalScaler);
    		
    		//Draw BG
        	g2d.drawImage(backgroundSprite1.getImage(), backgroundSprite1.getX(),
        			backgroundSprite1.getY(), this);
        	
        	g2d.drawImage(backgroundSprite2.getImage(), backgroundSprite2.getX(),
        			backgroundSprite2.getY(), this);
        	
        	
        	//Draw player health
        	Font font = new Font("Serif", Font.BOLD, 15);
            g.setFont(font);
            g.setColor(Color.YELLOW);
        	g.drawString((Integer.toString(health)), 25, 25);
        	
        	
        	//Font font = new Font("Serif", Font.BOLD, 15);
        	//font already set
            g.setFont(font);
            g.setColor(Color.BLUE);
        	g.drawString((Integer.toString(craft.craftShields)), 70, 25);
        	
        	
            g.setFont(font);
            g.setColor(Color.RED);
        	g.drawString((Integer.toString(score)), 25, 50);
        	
        	
            
            g2d.drawImage(craft.getImage(), craft.getX(),
                    craft.getY(), this);

            ArrayList<ElvenMissile> ms = craft.getMissiles();
            
            ArrayList<ElvenEnemy> es = Elvenenemies;
            
            
            
            g2d.drawImage(craft.pixieHelper.getImage(), craft.pixieHelper.getX(),
            		craft.pixieHelper.getY(), this);
            
            
            
            
            if (health <= 0){
            	
            	
            	isGameOver = true;
            }
            
            

            for (Object m1 : ms) {
                ElvenMissile m = (ElvenMissile) m1;
            	if (m.vis){
                    g2d.drawImage(m.getImage(), m.getX(),
                            m.getY(), this);
            	}

            }
            
            ms = craft.pixieHelper.getMissiles();
            
            
            
            for (Object m1 : ms) {
                ElvenMissile m = (ElvenMissile) m1;
                
                if (m.vis){
                    g2d.drawImage(m.getImage(), m.getX(),
                            m.getY(), this);
                }
            }
            
            for (Object e1 : es){
            	ElvenEnemy e = (ElvenEnemy) e1;
            	if (e.vis){
                	g2d.drawImage(e.getImage(), e.getX(),
                			e.getY(), this);
            	}

            	
            	ArrayList<ElvenEnemyMissile> eem = e.elvenEnemyMissiles;
            	
            	
            	//Draw all missiles
            	for (Object ee1 : eem){
            		ElvenEnemyMissile em = (ElvenEnemyMissile) ee1;
            		if (em.vis){
                		g2d.drawImage(em.getImage(), em.getX(),
                    			em.getY(), this);
            		}

            	}
            	
            	
            	
            }
    	} else {
    		//Draw game over screen
    		g2d.drawImage(myGameOverScreen.getImage(), myGameOverScreen.getX(),
        			myGameOverScreen.getY(), this);
    		
    		
    		Font font = new Font("Courier New", Font.BOLD, 60);
            g.setFont(font);
            g.setColor(Color.BLUE);
        	g.drawString((Integer.toString(score)), 10, 690);
        	if (!didCreateTheMainMenu){
        		DELAY = 2000;
        		
        		File file = new File("elvenshooterTEMP");
        		file.delete();
        		try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// Seems ok
					e1.printStackTrace();
				}
        		
            	PrintWriter writer;
        		try {
        			writer = new PrintWriter("elvenshooterTEMP", "UTF-8");
        			
        			
        			writer.println("Dead");
        			
                    writer.close();
                    
        			
        		} catch (FileNotFoundException e) {
        			System.out.println(
        	                "Something is seriously wrong with your system");
        			
        		} catch (UnsupportedEncodingException e) {
        			System.out.println(
        	                "You do not have UTF-8? I am seriously amazed");
        		}
        		
        		        		
        		didChangeColor = true;
        		didCreateTheMainMenu = true;
        		
    	    	
    	    	
        		
        	}
        	
    	}
    	
    	
    	
        
        
        
    }

    public void checkCollisions() {

        Rectangle r3 = craft.getBounds();

        for (ElvenEnemy ee : Elvenenemies) {
        	
        	ArrayList<ElvenEnemyMissile> eem = ee.elvenEnemyMissiles;
        	for (Object ee1 : eem){
        		ElvenEnemyMissile em = (ElvenEnemyMissile) ee1;
        		Rectangle r4 = em.getBounds();
        		if (r3.intersects(r4)){
        			if (craft.craftShields > 3){
                		craft.craftShields -= 3;
                	} else if (craft.craftShields > 0) {
                		health -= (3-craft.craftShields);
                		craft.craftShields = 0;
                	} else {
                		health -= 3;
                	}
        			
        			em.setVisible(false);
        		}
        		
        		
        	}
        	
        	
            Rectangle r2 = ee.getBounds();

            if (r3.intersects(r2)) {
            	
            	if (ee.vis){
            		if (ee.elvenEnemyType == -1){
                    	if (craft.craftShields > 14){
                    		craft.craftShields -= 15;
                    	} else if (craft.craftShields > 0) {
                    		health -= (15-craft.craftShields);
                    		craft.craftShields = 0;
                    	} else {
                    		health -= 15;
                    	}
                	} else if (ee.elvenEnemyType == 0){
                    	if (craft.craftShields > 29){
                    		craft.craftShields -= 30;
                    	} else if (craft.craftShields > 0) {
                    		health -= (30-craft.craftShields);
                    		craft.craftShields = 0;
                    	} else {
                    		health -= 30;
                    	}
                	} else if (ee.elvenEnemyType == 1){
                    	if (craft.craftShields > 49){
                    		craft.craftShields -= 50;
                    	} else if (craft.craftShields > 0) {
                    		health -= (50-craft.craftShields);
                    		craft.craftShields = 0;
                    	} else {
                    		health -= 50;
                    	}
                	} else if (ee.elvenEnemyType == 2){
                    	if (craft.craftShields > 49){
                    		craft.craftShields -= 50;
                    	} else if (craft.craftShields > 0) {
                    		health -= (50-craft.craftShields);
                    		craft.craftShields = 0;
                    	} else {
                    		health -= 50;
                    	}
                	}
            	} else {
            		//Your revenge will continue, Kalista.
            	}
            	
            	

            	
            	
            	ee.vis = false;
            	
            }
        }


        
        
        
        
        ArrayList<ElvenMissile> ms = craft.getMissiles();

        for (ElvenMissile m : ms) {

            Rectangle r1 = m.getBounds();

            for (ElvenEnemy ee : Elvenenemies) {

            	if (ee.vis){
            		Rectangle r2 = ee.getBounds();

                    if (r1.intersects(r2)) {
                    	
                        m.setVisible(false);
                        
                        score += ee.hit();
                        
                        //score++;
                        
                    }
            	} else {
            		//You are safe dear voidling. For now at least.
            	}
                
            }
        }
        
        ms = craft.pixieHelper.getMissiles();

        for (ElvenMissile m : ms) {

            Rectangle r1 = m.getBounds();

            for (ElvenEnemy ee : Elvenenemies) {

                Rectangle r2 = ee.getBounds();

                if (r1.intersects(r2)) {
                	
                	if (ee.vis){
                        m.setVisible(false);
                        ee.hit();
                	} else {
                		//The fae cannot find its target. Let's kill someone else instead.
                	}

                }
            }
        }
        
        
    }
    
    
    public void actionPerformed(ActionEvent e) {
    	
    	if (!isGameOver){
    		updateMissiles();
        	updateEnemyMissiles();
        	updateCraft();
        	checkCollisions();
    	}
    	
    	
        repaint();
    }
    
    private void updateEnemyMissiles() {
    	
    	for (ElvenEnemy ee : Elvenenemies) {
    		
        	
            	ArrayList<ElvenEnemyMissile> eem = ee.elvenEnemyMissiles;
            	for (Object ee1 : eem){
            		

            		
            		ElvenEnemyMissile em = (ElvenEnemyMissile) ee1;
            		
                		if (ee.elvenEnemyType != 1){
                			em.move();
                		} else {
                			em.moveToLocation(craft.x, craft.y);
                		}
                    
            		

            		
            		
            	}
        	}

    	
    	
    }
    

    private void updateMissiles() {
    	
    	
    	//Where stuff dies
        ArrayList<ElvenEnemy> es = Elvenenemies;
        
        for (int i = 0; i < es.size(); i++) {
            ElvenEnemy e = (ElvenEnemy) es.get(i);
            e.move();
            
            
            if (!e.isVisible()) {
            	e.missileGarbageCollector--;
            	if (e.missileGarbageCollector <= 0){
            		es.remove(i);
            	}
            }
            
        }
        ArrayList<ElvenMissile> ms = craft.getMissiles();
        
        
        for (int i = 0; i < ms.size(); i++) {

            ElvenMissile m = (ElvenMissile) ms.get(i);

            if (m.isVisible()) {

                m.move();
            } else {

                ms.remove(i);
            }
        }
        ms = craft.pixieHelper.getMissiles();
        
        
        boolean enemiesOnScreen = false;
        
        for (int i = 0; i < ms.size(); i++) {
        	
            ElvenMissile m = (ElvenMissile) ms.get(i);
            if (m.isVisible()) {
            	
            	for (int x = 0; x < es.size(); x++) {
                    ElvenEnemy e = (ElvenEnemy) es.get(x);
                    if (e.vis && e.x > craft.pixieHelper.x + 150 && e.elvenEnemyType != 2) {
                    	
                    	//Heat seeking missiles
                    	m.moveToLocation(e.x + e.width/2, e.y + e.height/2);
                    	enemiesOnScreen = true;
                    	break;
                    }
            	
                
            } if (!enemiesOnScreen){
            	m.move();
            }
        } else {
        		
        		ms.remove(i);
        	}
            
        }
        
    }
    
    

    private void updateCraft() {
    	
    	backgroundSprite1.move();
    	backgroundSprite2.move();
    	
        	spawnTimer++;
        	if (spawnTimer > 60){
        		
        		spawnTimer = 0;
                    long elvenActionToTake = universalRandomNumberGenerator.nextLong();
                    
                    
        		
                    //All the enemy code is in the enemy class, where it BELONGS.
        		Elvenenemies.add(new ElvenEnemy(elvenActionToTake, universalScaler));
        	}
        	
        	
        	craft.isPressed();
            craft.move();
            //craft.moveEnemy();
    	

    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
}