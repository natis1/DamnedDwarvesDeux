//Never let a wrong turn into an evil
public class ElvenBackgroundSprite extends ElvenSprite {
	
    

    public ElvenBackgroundSprite(int x, String BGName) {
    	
    	
    	
        super(x, 0, 0, 0, BGName);
        
        
        loadImage();
    }
    
    public void move() {
        
    	x -= 5;
    	
        if (x <= -4000) {
            x=4000;
        }
        
        
    }
    
    
}