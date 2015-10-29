
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ElvenMainMenu extends JPanel implements ActionListener {
	
	public Stream<String> saveData;
	private int score;
	
	private boolean didNotAttemptBefore = true;
	
	ElvenMainMenu(int myScore) {
		
		readSaveFile(myScore);
		
		JFrame f = new JFrame("Main Menu");
		f.setSize(400, 400);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar jmb = new JMenuBar();

		JMenu jmFile = new JMenu("File");
		JMenuItem jmiOpen = new JMenuItem("Open");
		JMenuItem jmiClose = new JMenuItem("Close");
		JMenuItem jmiSave = new JMenuItem("Save");
		JMenuItem jmiExit = new JMenuItem("Exit");
		jmFile.add(jmiOpen);
		jmFile.add(jmiClose);
		jmFile.add(jmiSave);
		jmFile.addSeparator();
		jmFile.add(jmiExit);
		jmb.add(jmFile);

		JMenu jmOptions = new JMenu("Options");
		JMenu a = new JMenu("Upgrades");
		JMenuItem b = new JMenuItem("B");
		JMenuItem c = new JMenuItem("C");
		JMenuItem d = new JMenuItem("D");
		a.add(b);
		a.add(c);
		a.add(d);
		jmOptions.add(a);

		JMenu e = new JMenu("E");
		e.add(new JMenuItem("F"));
		e.add(new JMenuItem("G"));
		jmOptions.add(e);

		jmb.add(jmOptions);

		JMenu jmHelp = new JMenu("Help");
		JMenuItem jmiAbout = new JMenuItem("About");
		JMenuItem jmiScore = new JMenuItem(Integer.toString(score));
		jmHelp.add(jmiAbout);
		jmHelp.add(jmiScore);
		jmb.add(jmHelp);

		jmiOpen.addActionListener(this);
		jmiClose.addActionListener(this);
		jmiSave.addActionListener(this);
		jmiExit.addActionListener(this);
		b.addActionListener(this);
		c.addActionListener(this);
		d.addActionListener(this);
		jmiAbout.addActionListener(this);

		f.setJMenuBar(jmb);
		f.setVisible(true);
		
		//JLabel scoreText = new JLabel();
		
        //scoreText.setFont(font);
        //scoreText.setText((Integer.toString(score)));
        //scoreText.
        //scoreText.
        //scoreText.drawString((Integer.toString(score)), 25, 50);
        
        
		
	}
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawLine(0, 0, getWidth(), getHeight());
        g.dispose();
        
        Font font = new Font("Courier", Font.BOLD, 15);
        g.setFont(font);
        g.drawString((Integer.toString(score)), 25, 25);
        
        
    }
	
	
	
	public void readSaveFile(int myScore){
		 // The name of the file to open.
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
            	System.out.println(bufferedReader.readLine());
            }
            if (bufferedReader.readLine() != null){
            	score = Integer.parseInt(bufferedReader.readLine());
            }
            score += myScore;
            
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "' Because the file was not found. Recreating... this may lead to lossed data");
            
            PrintWriter writer;
			try {
				writer = new PrintWriter("elvenShooter.txt", "UTF-8");
				
				
				writer.println("Save File. Do not edit you cheater");
	            writer.println(myScore);
	            writer.close();
	            
	            readSaveFile(myScore);
				
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

		
        PrintWriter writer;
		try {
			writer = new PrintWriter("elvenShooter.txt", "UTF-8");
			
			
			writer.println("Save File. Do not edit you cheater");
			//building new score
            writer.println(score);
            writer.close();
            if (didNotAttemptBefore){
            	didNotAttemptBefore = false;
            	readSaveFile(myScore);
            }
            
			
		} catch (FileNotFoundException e) {
			System.out.println(
	                "No idea why I can't make '" + 
	                fileName + "' Something is seriously wrong with your system");
			
		} catch (UnsupportedEncodingException e) {
			System.out.println(
	                "You do not have UTF-8? I am seriously amazed");
		}
        
        
	}
	
	
	public void actionPerformed(ActionEvent ae) {
		String comStr = ae.getActionCommand();
		System.out.println(comStr + " Selected");
		
		repaint();
		
	}
	/*public static void main(String args[]) {
		
		
	}*/
}