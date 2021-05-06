import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.*;	

public class DefendorShoot extends Animation{

    public DefendorShoot(JFrame window,int x, int y,int dx,int dy){
        super(window,x,y,dx,dy);
        load_shoot();
    
    }
    public DefendorShoot(JFrame window){
        super(window);
        load_shoot();

    }

    public void load_shoot(){
		Image frame = null;
		String filename = "./images/hipFire/Armature_hipFire_";
         String framename="";
       for(int i=0;i<51;i++){
		framename = filename + i + ".png";
        frame = loadImage(framename);
		this.addFrame(frame,15);
	   }

	}



}