
//package com.zetcode;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.Timer;



public class ElvenWindowContainment implements ActionListener {
	
	
	//private ElvenKeyManager keySetter = new ElvenKeyManager();
	private JFrame myBlackScreen;
	private JFrame myGameScreen;
	private JFrame myMenuScreen;
	
	
	private boolean isRunning = false;
	
	private boolean didInit = false;
	
	private Timer timer;
	private int DELAY = 2000;
	
	
	private int pseudoVSync;
	
	//private SystemTray tray;
		
    public ElvenWindowContainment() {
    	
    	initUI();
        
    	if (ElvenMain.ElvenXResolution == 0){
    		initBlackUI();
    		myGameScreen.toFront();
    	}
    	
    	
    	
        
        timer = new Timer(DELAY, this);
        timer.start();
        
        
    }
    
    private void initBlackUI() {
    	
    	//Get computer screen size
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();
        
        
        
		
    	myBlackScreen = new JFrame();
        
        myBlackScreen.setUndecorated(true);
        myBlackScreen.getContentPane().setBackground(Color.BLACK);
        myBlackScreen.setLocation(-0, -0);

        
        myBlackScreen.setSize((int) screenWidth, (int) screenHeight);
        myBlackScreen.setResizable(false);
        
        
        myBlackScreen.setTitle("Damned Dwarves Deux");
        //setLocationRelativeTo(null);
        myBlackScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        //myBlackScreen.
        
        myBlackScreen.setVisible(true);
        
        
    }
    
    public void goBackToGame(){
    	
    	ElvenMain.ElvenGameState = 1;
    	
    	
    	initUI();
        
    	if (ElvenMain.ElvenXResolution == 0){
    		initBlackUI();
    		myGameScreen.toFront();
    	}
        
        //myMenuScreen.setVisible(false);
    	//myMenuScreen.dispose();
    }
    
    public void goToMainMenu(int score){
    	
		
    	if (ElvenMain.ElvenXResolution == 0){
    		myBlackScreen.setVisible(false);
        	myBlackScreen.dispose();
    	}

		myGameScreen.setVisible(false);
		myGameScreen.dispose();

    	/*
    		myMenuScreen = new JFrame();
            myMenuScreen.add(new ElvenMainMenu(score));
        	
        	
        	myMenuScreen.getContentPane().setBackground(Color.RED);
        	myMenuScreen.setLocation(400, 400);
        	myMenuScreen.setResizable(true);
            
            
        	myMenuScreen.setTitle("Damned Dwarves Deux");
        	myMenuScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	
		*/
		new ElvenMainMenu(score);

        
        
        
        //myBlackScreen.
        
    	
    	
    }
    
    
    private void initUI() {
    	
    	isRunning = true;
    	if (!didInit){
    		
    		
    		
    		
    		
    		
    		String fileName = "elvenShooter.txt";
            
            

            // This will reference one line at a time
            //String line = null;

            try {
                // FileReader reads text files in the default encoding.
                FileReader fileReader = 
                    new FileReader(fileName);

                // Always wrap FileReader in BufferedReader.
                BufferedReader bufferedReader = 
                    new BufferedReader(fileReader);

                
                if (bufferedReader.readLine() != null){
                	System.out.print(bufferedReader.readLine());
                	ElvenMain.ElvenGameDifficulty = Integer.parseInt(bufferedReader.readLine());
                	ElvenMain.ElvenSoulsStolen = Integer.parseInt(bufferedReader.readLine());
                	
                	ElvenMain.ElvenUpgradeMovement = (bufferedReader.readLine().charAt(0));
                	ElvenMain.ElvenUpgradeFirerate = (bufferedReader.readLine().charAt(0));
                	ElvenMain.ElvenUpgradeTracking = (bufferedReader.readLine().charAt(0));
                	
                	ElvenMain.ElvenXResolution = Integer.parseInt(bufferedReader.readLine());
                	System.out.println((bufferedReader.readLine()));
                	ElvenMain.ElvenYResolution = Integer.parseInt(bufferedReader.readLine());
                	ElvenMain.ElvenFramerate = Integer.parseInt(bufferedReader.readLine());
                	
                	if (ElvenMain.ElvenFramerate == 0){
                		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                	    GraphicsDevice[] gs = ge.getScreenDevices();

                	    for (int i = 0; i < gs.length; i++) {
                	      DisplayMode dm = gs[i].getDisplayMode();

                	       pseudoVSync = dm.getRefreshRate();
                	      if (pseudoVSync == DisplayMode.REFRESH_RATE_UNKNOWN) {
                	        System.out.println("Unknown HZ, using 60 because you are probably in a VM or something"); //I love VMs, might add an override
                	        //if the person runs it from the cmdline with the --hz option.
                	        pseudoVSync = 60;
                	      }
                	    }
                	} else {
                		pseudoVSync = ElvenMain.ElvenFramerate;//use user set fps
                	}
                	
                	
                	
                }
                
                
                bufferedReader.close();         
            }
            catch(FileNotFoundException ex) {
                System.out.println(
                    "Unable to open file '" + 
                    fileName + "' Because the file was not found. Recreating... this may lead to lossed data");
                
                PrintWriter writer;
    			try {
    				writer = new PrintWriter("elvenShooter.txt", "UTF-8");
    				int zero = 0;
    				char pleaseBeZero = (char) zero;//If this doesn't work IDK what will
    				
    				writer.println("Save File. Do not edit you cheater");
    	            writer.println("0");
    	            writer.println("0");
    	            writer.println("0");//Top ones are ints, bottom ones are nulls.
    	            writer.println(pleaseBeZero);//0
    	            writer.println(pleaseBeZero);//0
    	            writer.println(pleaseBeZero);//0
    	            writer.println("0");// res X
    	            writer.println("x"); // by sign, does nothing
    	            writer.println("0");//y res
    	            writer.println("0");//framerate
    	            writer.close();
    	            
    	            
    				
    			} catch (FileNotFoundException e) {
    				System.out.println(
    		                "No idea why I can't make '" + 
    		                fileName + "' Something is seriously wrong with your system");
    				
    			} catch (UnsupportedEncodingException e) {
    				System.out.println(
    		                "You do not have UTF-8? I am seriously amazed");
    			}
                
                
                
            }
            catch(IOException ex) {
                System.out.println(
                    "Error reading file '" 
                    + fileName + "' Because you do not have permissions. Run as Admin to fix.");                  
                // Or we could just do this: 
                // ex.printStackTrace();
            }
    		
    	}
    	
    	if (ElvenMain.ElvenFramerate != 0){
    		pseudoVSync = ElvenMain.ElvenFramerate;
    	} 
    	
    	
    	myGameScreen = new JFrame();
    	myGameScreen.getContentPane().setBackground(Color.BLACK);
    	//set this first
    	
    	
    	//Get computer screen size
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	double screenWidth;
    	double screenHeight;
    	
    	int screenChangeXBy = 0;
        int screenChangeYBy = 0;
        double universalScaler = 1;
    	
    	if (ElvenMain.ElvenXResolution == 0){
    		screenWidth = screenSize.getWidth();
            screenHeight = screenSize.getHeight();
            
            myGameScreen.setUndecorated(true);
            myGameScreen.setResizable(false); //fullscreen
            
            if (screenWidth < screenHeight * (16.0 / 9.0)){
            	//force height to be a nice guy
            	
            	screenChangeYBy = -(int) ((screenWidth * ( 9.0 / 16.0) - screenHeight) / 2);
            	
            	screenHeight = screenWidth * ( 9.0 / 16.0);
            	universalScaler = screenHeight / 1080.0;
            	//Super wide monitors
            } else if (screenWidth > screenHeight * ( 16.0 / 9.0)) {
            	//force height to be a nice guy
            	
            	screenChangeXBy = -(int) ((screenHeight * ( 16.0 / 9.0) - screenWidth) / 2);
            	
            	screenWidth = screenHeight * ( 16.0 / 9.0);

            	universalScaler = screenHeight / 1080.0;

            }


    	} else {
    		screenHeight = ElvenMain.ElvenYResolution;
    		screenWidth = screenHeight * 16.0 / 9.0;
    		universalScaler = screenHeight / 1080.0;
    		myGameScreen.setUndecorated(false);
            myGameScreen.setResizable(false); //windowed

    	}




        //monitor is less than 16:9


    	//panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        
        
        
        
        myGameScreen.add(new ElvenBoard(universalScaler, pseudoVSync));
        
        //myGameScreen.
        
        
        myGameScreen.setLocation(screenChangeXBy, screenChangeYBy);

        
        //I sure hope your screen size is an int
        myGameScreen.setVisible(true);
        myGameScreen.setSize((int) screenWidth, (int) screenHeight);
        
        
        myGameScreen.setBackground(Color.black);
        
        myGameScreen.setTitle("Damned Dwarves Deux");
        //setLocationRelativeTo(null);
        
        //Wait. This means you can't possibly close it without taskMGR
        //OP. Or maybe not, but the school will probably hate me for it.
        //FYI This ain't my damn fault you put a virus on the school computers and are too apathetic
        //to even fix it.
        myGameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        /*JFrame frame = new JFrame("TitleLessJFrame");
        frame.getContentPane().add(new JLabel(" What up"));
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setVisible(true);*/
        
        /*GraphicsDevice myDevice = null;
        Window myWindow = Elvenboard;

        try {
            myDevice.setFullScreenWindow(myWindow);
        } finally {
            myDevice.setFullScreenWindow(null);
        }*/
        myGameScreen.toFront();
        
    }

    
    public static void main(String[] args) {
        
    	
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                ElvenWindowContainment ex = new ElvenWindowContainment();
                ex.myBlackScreen.setVisible(true);
                ex.myGameScreen.setVisible(true);
            }
        });
    }


@Override
public void actionPerformed(ActionEvent e) {
		
	if (ElvenMain.ElvenGameState > 999){
		
		goToMainMenu(ElvenMain.ElvenGameState - 1000);
		goBackToGame();

	}
		
	
}

}