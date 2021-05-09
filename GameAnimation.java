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

public interface GameAnimation{

    public void loadAnimation(String filename,int frames,int frametime);
    public  void addFrame(Image image, long duration);
    public  void start();
    public void update();
    public  Image getImage();
    public void draw (Graphics2D g2);
    public void pauseAnimation();
    public void unpauseAnimation();
    public int getNumFrames();
    public AnimFrame getFrame(int i);
    public void playSound();
    public void setsize(int xsize,int ysize);
    public void stopSound();
    public void moveLeft();
    public class AnimFrame {			
        Image image;
        long endTime;

        public AnimFrame(Image image, long endTime) {
            this.image = image;
            this.endTime = endTime;
        }
    }
    public void moveRight();
    public void moveUp();
    public void reset();
    public void moveDown ();
    public void setPosition(int x,int y);
    public int getX();
    public int getY();
    public int isfinished();
    public void rotate(double angle);
    public double getAngle();
    public Rectangle2D.Double getBoundingRectangle();
    public static BufferedImage toBufferedImage(Image img){
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    
        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
    
        // Return the buffered image
        return bimage;
    }
    public Image loadImage (String fileName);
    public int getImageWidth();
    public int getImageHeight();

}