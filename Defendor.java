import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.*;	
import java.awt.geom.Point2D;
import java.awt.Point;

public class Defendor{
    int x;
    int y;
    DefendorRun running;
    DefendorShoot shooting;
    int active_animation;

    public Defendor(JFrame window,int x, int y,int dx,int dy){
   this.running = new DefendorRun(window,x,y,dx,dy);
   this.shooting = new DefendorShoot(window,x,y,dx,dy);
    
    }
    public Defendor(JFrame window){
     this.running = new DefendorRun(window);
     this.shooting = new DefendorShoot(window);

    }
    
    public void setActive(int active_animation){
     this.active_animation = active_animation;
    }

    public DefendorShoot getShoot(){
        return this.shooting;
    }

    public DefendorRun getRun(){
           return this.running;
    }

    public Point getLocation(){
     if(active_animation==0){
         this.x = this.running.getX();
         this.y = this.running.getY();
     }else{
         this.x = this.shooting.getX();
         this.y = this.shooting.getY();
     }
     return new Point(this.x,this.y);
    }

	



}