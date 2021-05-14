import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.*;	
public class BossRun extends Animation{

public BossRun(JFrame window,int x, int y,int dx,int dy){
    super(window,x,y,dx,dy);
    this.loadAnimation("./images/BossWalk/Armature_walk_", 61, 30);
    this.dimension  = window.getSize();
}
    @Override
    public synchronized void update() {
        double fx,fy;

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

           // fx = this.x + this.dx * Math.cos(this.rotate_angle);
           // fy = this.y + this.dy * Math.sin(this.rotate_angle);
           // this.x = (int) fx;
           // this.y =  (int) fy;
        }

      @Override
      public void draw (Graphics2D g2) {		// draw the current frame on the JPanel
        
            
    
        if (active == 0){
		return;}



   BufferedImage image = this.toBufferedImage(getImage());

//BufferedImage image = this.toBufferedImage(getImage());

int imWidth = image.getWidth();
int imHeight = image.getHeight();

double locationX = imWidth / 2;
double locationY = imHeight / 2;

if(this.red){
int [] pixels = new int[imWidth * imHeight];
image.getRGB(0, 0, imWidth, imHeight, pixels, 0, imWidth);


for (int i=0; i<pixels.length; i++) {
    pixels[i] = toRed(pixels[i]);
}

image.setRGB(0, 0, imWidth, imHeight, pixels, 0, imWidth);	
}
AffineTransform tx = AffineTransform.getRotateInstance(this.rotate_angle, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        
        g2.drawImage(op.filter(image, null), x, y,null);
   

    }


public int toRed (int pixel) {

    int alpha, red, green, blue, gray;
  int newPixel;

  alpha = (pixel >> 24) & 255;
  red = (pixel >> 16) & 255;
  green = (pixel >> 8) & 255;
  blue = pixel & 255;
green = 0;
blue = 0;
  newPixel = blue | (green << 8) | (red << 16) | (alpha << 24);
  return newPixel;
}
    
}