import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
public class Fireball extends Animation{

public Fireball(JFrame window,int x, int y,int dx,int dy){
    super(window,x,y,dx,dy);
    loadfire();
    this.dimension  = window.getSize();

}
	public void loadfire(){
		Image frame = null;
		String filename = "./images/imgs/img_";
         String framename="";
         Image check =  loadImage(filename + 0 + ".png");
         BufferedImage d = toBufferedImage(check);
         this.imageheight = d.getWidth();
         this.imagewidth= d.getHeight();
       for(int i=0;i<40;i++){
		framename = filename + i + ".png";
        frame = loadImage(framename);
		this.addFrame(frame,10);}
	   }
    
  

  
    @Override
    public synchronized void update() {
        double fx,fy,drag;
         drag =.1;

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
            
            fx = this.x + dx2 * Math.cos(this.rotate_angle);
            fy = this.y + dy2 * Math.sin(this.rotate_angle);
            //Variables potentially impacting air resistance (if implemented)
          //  var p = 1.2; //air density in kg/m^3
           //  var mass = 3; //mass in kg
           // var dragC = 0.5; //drag coefficient
           // var faceArea = 0.1; //square meters facing into the wind in given direction. Would be the cross-sectional area for a simple object like a sphere
            this.x = (int) fx;
            this.y =  (int) fy;
            //this.dy = this.dx = (int) (Math.sqrt(.01/(.5 * .00009)));

            //using the formuna Velcity = Math.sqrt(drag/ 1/2 * mass)
          
           // dy = dx = (int) (Math.sqrt(drag/(.5 * .00009)));
            
            //drag -= .001;
            dy2 = dy2 - drag;
            dx2 = dx2 - drag;

            System.out.println("speed = "+dy2);
        }

        @Override
        public void draw (Graphics2D g2) {		// draw the current frame on the JPanel
            if (active == 0){
                return;}

        BufferedImage image = this.toBufferedImage(getImage());
        double locationX = image.getWidth() / 2;
        double locationY = image.getHeight() / 2;
        this.imagewidth = (int) locationX;
        this.imageheight = (int) locationX;
        AffineTransform tx = AffineTransform.getRotateInstance(this.rotate_angle, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
                
                g2.drawImage(op.filter(image, null), x, y,200,200,null);
             //   g2.draw(getBoundingRectangle());
                //getBoundingRectangle().
            }


        @Override
        public Rectangle2D.Double getBoundingRectangle() {
            return new Rectangle2D.Double (x+(this.imagewidth/5)+10, y+(this.imageheight/5)-10, 20 ,20);
         }
        


    
}