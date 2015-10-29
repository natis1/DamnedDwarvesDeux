
//package com.zetcode;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
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
	
	private Timer timer;
	private int DELAY = 2000;
	
	private boolean didChangeScreen = false;
	
	//private SystemTray tray;
		
    public ElvenWindowContainment() {
        
    	initBlackUI();
        initUI();
        
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
    
    public void goToMainMenu(){
    	
    	File file = new File("elvenshooterTEMP");
		file.delete();
		
		PrintWriter writer;
		try {
			writer = new PrintWriter("elvenshooterTEMP", "UTF-8");
			
			
			writer.println("Menu");
			
            writer.close();
            
			
		} catch (FileNotFoundException e) {
			System.out.println(
	                "Something is seriously wrong with your system");
			
		} catch (UnsupportedEncodingException e) {
			System.out.println(
	                "You do not have UTF-8? I am seriously amazed");
		}
		
		
    	myBlackScreen.setVisible(false);
    	myBlackScreen.dispose();
    	myGameScreen.setVisible(false);
    	myGameScreen.dispose();
    	myMenuScreen = new JFrame();
        myMenuScreen.add(new ElvenMainMenu(50));
    	
    	
    	myMenuScreen.getContentPane().setBackground(Color.RED);
    	myMenuScreen.setLocation(400, 400);
    	myMenuScreen.setResizable(true);
        
        
    	myMenuScreen.setTitle("Damned Dwarves Deux");
    	myMenuScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        //myBlackScreen.
        
    	myMenuScreen.setVisible(true);
    	
    }
    
    
    private void initUI() {
    	
    	myGameScreen = new JFrame();
    	myGameScreen.getContentPane().setBackground(Color.BLACK);
    	//set this first
    	
    	
    	//Get computer screen size
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();
        
        int screenChangeXBy = 0;
        int screenChangeYBy = 0;
        
        //monitor is less than 16:9
        if (screenWidth < screenHeight * (16.0 / 9.0)){
        	//force height to be a nice guy
        	
        	screenChangeYBy = -(int) ((screenWidth * ( 9.0 / 16.0) - screenHeight) / 2);
        	
        	screenHeight = screenWidth * ( 9.0 / 16.0);
        	
        	
        	
        	
        	//Super wide monitors
        } else if (screenWidth > screenHeight * ( 16.0 / 9.0)) {
        	//force height to be a nice guy
        	
        	screenChangeXBy = -(int) ((screenHeight * ( 16.0 / 9.0) - screenWidth) / 2);
        	
        	screenWidth = screenHeight * ( 16.0 / 9.0);
        	
        	
        	
        }
    	
    	//panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        double universalScaler;
        universalScaler = screenHeight / 1080.0;
        
        
        myGameScreen.add(new ElvenBoard(universalScaler));
        
        //myGameScreen.
        
        myGameScreen.setUndecorated(true);
        myGameScreen.setLocation(screenChangeXBy, screenChangeYBy);

        
        //I sure hope your screen size is an int
        myGameScreen.setVisible(true);
        myGameScreen.setSize((int) screenWidth, (int) screenHeight);
        myGameScreen.setResizable(false);
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
		
	try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = 
            new FileReader("elvenshooterTEMP");

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = 
            new BufferedReader(fileReader);

        
        if (bufferedReader.readLine().charAt(0) == 'D'){
        	
        	goToMainMenu();
        	
        }
        
        bufferedReader.close();         
    }
	catch(FileNotFoundException ex) {
		//swallow that merde
		
	}
    catch(IOException ex) {
        System.out.println(
            "Error reading file because you do not have permissions. Run as Admin to fix.");                  
        // Or we could just do this: 
        // ex.printStackTrace();
    }
		
	
}

}