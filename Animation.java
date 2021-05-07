import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.*;	


/**
    The Animation class manages a series of images (frames) and
    the amount of time to display each frame.
*/

public class Animation {

    protected  int XSIZE = 150;		// width of image for animation
    protected  int YSIZE = 125;		// height of image for animation

    protected int dx = 10;
    protected int dy = 10;

    protected int x;
    protected int y;

    protected Dimension dimension;

    protected JFrame window;			// JFrame on which animation is being displayed
    protected ArrayList<AnimFrame> frames;	// collection of frames for animation
    protected int currFrameIndex;			// current frame being displayed
    protected long animTime;			// time that the animation has run for already
    protected long startTime;			// start time of the animation or time since last update
    protected long totalDuration;			// total duration of the animation
 
    protected int active;

    protected SoundManager soundManager;		// reference to SoundManager to play clip


    /**
        Creates a new, empty Animation.
    */
     protected boolean infinite;
     protected double rotate_angle;
     protected boolean pause;
     protected BufferedImage image1;
   AffineTransform identity = new AffineTransform();

    public Animation(JFrame window,int x, int y,int dx,int dy) {
    this.pause = false;
    this.rotate_angle = 0;
    this.infinite = true;
	this.window = window;
        frames = new ArrayList<AnimFrame>();	// animation is a collection of frames        	totalDuration = 0;
	active = 0;				// keeps track of how many animations have completed
	soundManager = SoundManager.getInstance();	
						// get reference to Singleton instance of SoundManager
     this.x = x;
     this.y = y;
     this.dx = dx;
     this.dy = dy;
    }

    public Animation(JFrame window) {
        this.pause = false;
        this.infinite = true;
        this.window = window;
            frames = new ArrayList<AnimFrame>();	// animation is a collection of frames        	totalDuration = 0;
        active = 0;				// keeps track of how many animations have completed
        soundManager = SoundManager.getInstance();	
                            // get reference to Singleton instance of SoundManager
        }

        public Animation(JFrame window,int x, int y,int dx,int dy,boolean infinite) {
            this.pause = false;
            this.window = window;
                frames = new ArrayList<AnimFrame>();	// animation is a collection of frames        	totalDuration = 0;
            active = 0;				// keeps track of how many animations have completed
            soundManager = SoundManager.getInstance();	
                                // get reference to Singleton instance of SoundManager
             this.x = x;
             this.y = y;
             this.dx = dx;
             this.dy = dy;
             this.infinite = infinite;
            }




    /**
        Adds an image to the animation with the specified
        duration (time to display the image).
    */
    public void loadAnimation(String filename,int frames,int frametime){
        ArrayList<AnimFrame> framer  =new ArrayList<AnimFrame>();
        long total = 0;
		Image frame = null;
         String framename="";
       for(int i=0;i<frames;i++){
        total += frametime;
		framename = filename + i + ".png";
        frame = loadImage(framename);
        framer.add(new AnimFrame(frame,total));
		this.addFrame(frame,frametime);
    }
    this.frames = framer;
	   }

    public synchronized void addFrame(Image image, long duration) {
        totalDuration += duration;
        frames.add(new AnimFrame(image, totalDuration));
    }


    /**
        Starts this animation over from the beginning.
    */

    public synchronized void start() {
        System.out.println("starting");

	active = 1;				// 1 indicates first animation sequence
        animTime = 0;				// reset time animation has run for, to zero
        currFrameIndex = 0;			// reset current frame to first fram,e
	startTime = System.currentTimeMillis();	// reset start time to current time
	//playSound();				// start playing sound clip
    }


    /**
        Updates this animation's current image (frame), if
        neccesary.
    */

    public synchronized void update() {


	if (active == 0)
	   return;

       if(!this.pause){

        long currTime = System.currentTimeMillis();
						// find the current time
	long elapsedTime = currTime - startTime;	
						// find how much time has elapsed since last update
	startTime = currTime;			// set start time to current time

        if (frames.size() > 1) {
            animTime += elapsedTime;		// add elapsed time to time animation has run for
            if (animTime >= totalDuration) {			
		active = active + 1;
		if (active > 1&&this.infinite==false) {		// allow two animation sequences only
			active = 0;
			//stopSound();
			return;
		}
						// if the time animation has run for > total duration
                animTime = animTime % totalDuration;	
						//    reset time animation has run for
                currFrameIndex = 0;		//    reset current frame to first frame
            }

            while (animTime > getFrame(currFrameIndex).endTime) {
                currFrameIndex++;		// set frame corresponding to time animation has run for
            }
        }

        dimension = window.getSize();

    }
    }


    /**
        Gets this Animation's current image. Returns null if this
        animation has no images.
    */

    public synchronized Image getImage() {
        if (frames.size() == 0) {
            return null;
        }
        else {
            return getFrame(currFrameIndex).image;
        }
    }


    public void draw (Graphics2D g2) {		// draw the current frame on the JPanel
        
            
    
        if (active == 0){
		return;}



   BufferedImage image = this.toBufferedImage(getImage());

//BufferedImage image = this.toBufferedImage(getImage());


double locationX = image.getWidth() / 2;
double locationY = image.getHeight() / 2;

AffineTransform tx = AffineTransform.getRotateInstance(this.rotate_angle, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        
        g2.drawImage(op.filter(image, null), x, y,null);
   

    }
    

    public void pauseAnimation(){
        this.pause = true;
    }

    public void unpauseAnimation(){
        this.pause = false;
    }

    public int getNumFrames() {			// find out how many frames in animation
	return frames.size();
    }


    protected AnimFrame getFrame(int i) {		// returns ith frame in the collection
        return frames.get(i);
    }


    protected class AnimFrame {			// inner class for the frames of the animation

        Image image;
        long endTime;

        public AnimFrame(Image image, long endTime) {
            this.image = image;
            this.endTime = endTime;
        }
    }


    public void playSound() {
	soundManager.playSound("walk", true);
    }

    public void setsize(int xsize,int ysize){
        this.XSIZE = xsize;
        this.YSIZE = ysize;
    }

    public void stopSound() {
	soundManager.stopSound("walk");
    }
    
    public void moveLeft () {
        playSound();
        Dimension dimension;
  
        if (!window.isVisible ()) return;
  
  
        dimension = window.getSize();
  
        if ((x - dx) > 0)
              x = x - dx;
  
        // check if x is outside the left side of the tile map
     }
  
  
     public void moveRight () {
         playSound();
        Dimension dimension;
  
        if (!window.isVisible ()) return;

  
        dimension = window.getSize();
  
       // int tileMapWidth = tileMap.getWidthPixels();
  
        //int playerWidth = playerImage.getWidth(null);
  
        //if ((x + DX + playerWidth) <= tileMapWidth) {
  
        //int xTile = tileMap.pixelsToTiles(x + DX + playerWidth);
        //int yTile = tileMap.pixelsToTiles(y) - 1;
  
         //   String mess = "Coordinates in TileMap: (" + xTile + "," + yTile + ")";
        //System.out.println (mess);
  
        //if (tileMap.getTile(xTile, yTile) == null)
            x = x + dx;
        //}
  
        // check if x is outside the right side of the tile map.
  
     }
  
  
     public void moveUp () {
  playSound();
        if (!window.isVisible ()) return;
  
        y = y - dy;
     }

     

     public void reset(){
         this.active = 0;
     }
  
     public void moveDown () {
     playSound();
        if (!window.isVisible ()) return;
  
        y = y + dy;
     }
     public void setPosition(int x,int y){
         this.x = x;
         this.y = y;
     }
    public int getX(){
        return this.x;
    } 
    public int getY(){
        return this.y;
    }

    public int isfinished(){
        return this.active;
    }
     
    public void rotate(double angle){
      this.rotate_angle = angle;

    }
    public double getAngle(){
        return this.rotate_angle;
    }


    public static BufferedImage toBufferedImage(Image img){
    if (img instanceof BufferedImage)
    {
        return (BufferedImage) img;
    }
    // Create a buffered image with transparency
    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

    // Draw the image on to the buffered image
    Graphics2D bGr = bimage.createGraphics();
    bGr.drawImage(img, 0, 0, null);
    bGr.dispose();

    // Return the buffered image
    return bimage;
}

public Image loadImage (String fileName) {
    return new ImageIcon(fileName).getImage();
}

}
