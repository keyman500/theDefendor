import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.*;	
public class EnemyRun extends Animation{

public EnemyRun(JFrame window,int x, int y,int dx,int dy){
    super(window,x,y,dx,dy);
    this.loadAnimation("./images/Enemyrun/armature_run_", 21, 30);
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

      



    
}