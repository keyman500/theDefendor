import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.*;	

public class DefendorRun extends Animation{

    public DefendorRun(JFrame window,int x, int y,int dx,int dy){
        super(window,x,y,dx,dy);
        loadrun();
    
    }
    public DefendorRun(JFrame window){
        super(window);
        loadrun();

    }

    public void loadrun(){
		Image frame = null;
		String filename = "./images/run/Armature_newAnimation_";
         String framename="";
       for(int i=0;i<51;i++){
		framename = filename + i + ".png";
        frame = loadImage(framename);
		this.addFrame(frame,20);
	   }



}

}