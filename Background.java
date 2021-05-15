import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class Background {
  	private Image bgImage;
  	private int bgImageWidth;      		// width of the background (>= panel Width)

	private JFrame window;
	private Dimension dimension;

 	private int bgX;
	private int backgroundX;
	private int backgroundX2;
	private int bgDX;			// size of the background move (in pixels)

	private int backgroundY;
	private int backgroundY2;
	private int bgY;
	private int bgImageHeight;
	


  public Background(JFrame window, String imageFile, int bgDX) {
    
	this.window = window;
    	this.bgImage = loadImage(imageFile);
    	bgImageWidth = bgImage.getWidth(null);	// get width of the background
       //  bgY =0;
		
	System.out.println ("bgImageWidth = " + bgImageWidth);

	dimension = window.getSize();
	bgImageHeight = (int) dimension.getHeight();
//bgImageHeight = bgImage.getHeight(null);
	if (bgImageWidth < dimension.width)
      		System.out.println("Background width < panel width");

    	this.bgDX = bgDX;

  }


  public void moveRight() {

	if (bgX == 0) {
		backgroundX = 0;
		backgroundX2 = bgImageWidth;			
	}

	bgX = bgX - bgDX;

	backgroundX = backgroundX - bgDX;
	backgroundX2 = backgroundX2 - bgDX;

	String mess = "Right: bgX=" + bgX + " bgX1=" + backgroundX + " bgX2=" + backgroundX2;
	//System.out.println (mess);

	if ((bgX + bgImageWidth) % bgImageWidth == 0) {
		System.out.println ("Background change: bgX = " + bgX); 
		backgroundX = 0;
		backgroundX2 = bgImageWidth;
	}

  }


  public void moveLeft() {

	if (bgX == 0) {
		backgroundX = bgImageWidth * -1;
		backgroundX2 = 0;			
	}

	bgX = bgX + bgDX;
				
	backgroundX = backgroundX + bgDX;	
	backgroundX2 = backgroundX2 + bgDX;

	String mess = "Left: bgX=" + bgX + " bgX1=" + backgroundX + " bgX2=" + backgroundX2;
	//System.out.println (mess);

	if ((bgX + bgImageWidth) % bgImageWidth == 0) {
		System.out.println ("Background change: bgX = " + bgX); 
		backgroundX = bgImageWidth * -1;
		backgroundX2 = 0;
	}			
   }

   
   public void moveUp() {

	if (bgY == 0) {
		backgroundY = 0;
		backgroundY2 = bgImageHeight;			
	}

	bgY = bgY - 10;
	backgroundY = backgroundY - 1;
	backgroundY2 = backgroundY2 - 1;

	if ((bgY + (bgImageHeight*2)) % (bgImageHeight*2) == 0) {
		System.out.println ("Background change: bgY = " + bgY); 
	    backgroundY = 0;
		backgroundY2 = bgImageHeight;
	}

  }


  public void draw (Graphics2D g2) {
	  
	g2.drawImage(bgImage, 0, backgroundY,(int)dimension.getWidth(),bgImageHeight, null);
	g2.drawImage(bgImage, 0, backgroundY2,(int)dimension.getWidth(),bgImageHeight,null);
//	g2.drawImage(bgImage, 0, backgroundY2 * -1,(int)dimension.getWidth(),bgImageHeight,null);
  }


  public Image loadImage (String fileName) {
	return new ImageIcon(fileName).getImage();
  }

}
