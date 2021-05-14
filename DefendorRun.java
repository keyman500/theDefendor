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

public class DefendorRun extends Animation{

    public DefendorRun(JFrame window,int x, int y,int dx,int dy){
        super(window,x,y,dx,dy);
      //  loadrun();
      this.loadAnimation("./images/run/Armature_newAnimation_", 51,20);
    
    }
    public DefendorRun(JFrame window){
        super(window);
       // loadrun();
        this.loadAnimation("./images/run/Armature_newAnimation_", 51,20);

    }
    /*

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
*/

@Override
public Rectangle2D.Double getBoundingRectangle() {
    return new Rectangle2D.Double (x+(this.getImageWidth()/2)-100, y+(this.getImageHeight()/2)-120, 270 ,245);
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
    
    g2.drawImage(op.filter(image, null), x, y,null);
    g2.draw(this.getBoundingRectangle());


}*/
}