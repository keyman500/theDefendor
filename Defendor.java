import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.*;	

public class Defendor{
    DefendorRun running;
    DefendorShoot shooting;

    public Defendor(JFrame window,int x, int y,int dx,int dy){
   this.running = new DefendorRun(window,x,y,dx,dy);
   this.shooting = new DefendorShoot(window,x,y,dx,dy);
    
    }
    public Defendor(JFrame window){
     this.running = new DefendorRun(window);
     this.shooting = new DefendorShoot(window);

    }
  

    public DefendorShoot getShoot(){
        return this.shooting;
    }

    public DefendorRun getRun(){
           return this.running;
    }

	



}