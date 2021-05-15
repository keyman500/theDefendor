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
      this.loadAnimation("./images/run/Armature_newAnimation_", 51,20);
    
    }
    public DefendorRun(JFrame window){
        super(window);
        this.loadAnimation("./images/run/Armature_newAnimation_", 51,20);

    }
 

@Override
public Rectangle2D.Double getBoundingRectangle() {
    return new Rectangle2D.Double (x+(this.getImageWidth()/2)-100, y+(this.getImageHeight()/2)-120, 270 ,245);
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