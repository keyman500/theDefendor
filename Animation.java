import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
    The Animation class manages a series of images (frames) and
    the amount of time to display each frame.
*/

public class Animation {

    private static int XSIZE = 150;		// width of image for animation
    private static int YSIZE = 125;		// height of image for animation

    private int dx = 10;
    private int dy = 10;

    private int x;
    private int y;

    private Dimension dimension;

    private JFrame window;			// JFrame on which animation is being displayed
    private ArrayList<AnimFrame> frames;	// collection of frames for animation
    private int currFrameIndex;			// current frame being displayed
    private long animTime;			// time that the animation has run for already
    private long startTime;			// start time of the animation or time since last update
    private long totalDuration;			// total duration of the animation
 
    private int active;

    private SoundManager soundManager;		// reference to SoundManager to play clip


    /**
        Creates a new, empty Animation.
    */

    public Animation(JFrame window) {
	this.window = window;
        frames = new ArrayList<AnimFrame>();	// animation is a collection of frames        	totalDuration = 0;
	active = 0;				// keeps track of how many animations have completed
	soundManager = SoundManager.getInstance();	
						// get reference to Singleton instance of SoundManager
    }


    /**
        Adds an image to the animation with the specified
        duration (time to display the image).
    */

    public synchronized void addFrame(Image image, long duration) {
        totalDuration += duration;
        frames.add(new AnimFrame(image, totalDuration));
    }


    /**
        Starts this animation over from the beginning.
    */

    public synchronized void start() {
	x = 100;
	y = 300;

	active = 1;				// 1 indicates first animation sequence
        animTime = 0;				// reset time animation has run for, to zero
        currFrameIndex = 0;			// reset current frame to first fram,e
	startTime = System.currentTimeMillis();	// reset start time to current time
	playSound();				// start playing sound clip
    }


    /**
        Updates this animation's current image (frame), if
        neccesary.
    */

    public synchronized void update() {

	if (active == 0)
	   return;

        long currTime = System.currentTimeMillis();
						// find the current time
	long elapsedTime = currTime - startTime;	
						// find how much time has elapsed since last update
	startTime = currTime;			// set start time to current time

        if (frames.size() > 1) {
            animTime += elapsedTime;		// add elapsed time to time animation has run for
            if (animTime >= totalDuration) {			
		active = active + 1;
		if (active > 2) {		// allow two animation sequences only
			active = 0;
			stopSound();
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
	if (active == 0)
		return;

        g2.drawImage(getImage(), x, y, XSIZE, YSIZE, null);
    }


    public int getNumFrames() {			// find out how many frames in animation
	return frames.size();
    }


    private AnimFrame getFrame(int i) {		// returns ith frame in the collection
        return frames.get(i);
    }


    private class AnimFrame {			// inner class for the frames of the animation

        Image image;
        long endTime;

        public AnimFrame(Image image, long endTime) {
            this.image = image;
            this.endTime = endTime;
        }
    }


    public void playSound() {
	soundManager.playSound("birdSound", true);
    }


    public void stopSound() {
	soundManager.stopSound("birdSound");
    }
    
    public void moveLeft () {
        Dimension dimension;
  
        if (!window.isVisible ()) return;
  
  
        dimension = window.getSize();
  
        if ((x - dx) > 0)
              x = x - dx;
  
        // check if x is outside the left side of the tile map
     }
  
  
     public void moveRight () {
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
  
        if (!window.isVisible ()) return;
  
        y = y - dy;
     }
  
  
     public void moveDown () {
  
        if (!window.isVisible ()) return;
  
        y = y + dy;
     }
}
