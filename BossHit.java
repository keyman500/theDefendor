import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.*;	
public class BossHit extends Animation{

public BossHit(JFrame window,int x, int y,int dx,int dy){
    super(window,x,y,dx,dy);
    this.loadAnimation("./images/BossHit/Armature_takeAimGun_", 41, 30);
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

     /*
        @Override
        public void draw (Graphics2D g2) {		// draw the current frame on the JPanel
          
              
      
          if (active == 0){
          return;}
  
  
  
     BufferedImage image = this.toBufferedImage(getImage());
  
  //BufferedImage image = this.toBufferedImage(getImage());
  
  
  double locationX = image.getWidth() / 2;
  double locationY = image.getHeight() / 2;
  
  AffineTransform tx = AffineTransform.getRotateInstance(this.rotate_angle, locationX, locationY);
          AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
          
          g2.drawImage(op.filter(image, null), x, y,this.XSIZE,this.YSIZE,null);
     
  
      }*/


    
}