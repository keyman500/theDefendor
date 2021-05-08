import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player {

   private static final int XSIZE = 150;	// width of the player's sprite
   private static final int YSIZE = 150;	// height of the player's sprite			

   private static final int DX = 12;	// amount of X pixels to move in one keystroke
   private static final int DY = 50;	// amount of Y pixels to move in one keystroke

   private JFrame window;		// reference to the JFrame on which player is drawn
   private TileMap tileMap;

   private int x;			// x-position of player's sprite
   private int y;			// y-position of player's sprite

   Graphics2D g2;
   private Dimension dimension;

   private Image playerImage, playerLeftImage, playerRightImage;

   public Player (JFrame window, TileMap t) {
      this.window = window;
      tileMap = t;			// tile map on which the player's sprite is displayed

      playerLeftImage = loadImage("images/Armature_shoot_002.png");
      playerRightImage = loadImage("images/Armature_shoot_002.png");

      playerImage = playerRightImage;

      x = (int) ((window.getWidth() - playerRightImage.getWidth(null)) / 2);
      y = 380;

   }


   public void draw (Graphics2D g2) {
	g2.drawImage (playerImage, x, y, XSIZE, YSIZE, null);
   }


   public Rectangle2D.Double getBoundingRectangle() {
      return new Rectangle2D.Double (x, y, XSIZE, YSIZE);
   }


   public Image loadImage (String fileName) {
      return new ImageIcon(fileName).getImage();
   }


   public void moveLeft () {
      Dimension dimension;

      if (!window.isVisible ()) return;

      playerImage = playerLeftImage;

      dimension = window.getSize();

      if ((x - DX) > 0)
      	  x = x - DX;

      // check if x is outside the left side of the tile map
   }


   public void moveRight () {
      Dimension dimension;

      if (!window.isVisible ()) return;

      playerImage = playerRightImage;

      dimension = window.getSize();

     // int tileMapWidth = tileMap.getWidthPixels();

      //int playerWidth = playerImage.getWidth(null);

      //if ((x + DX + playerWidth) <= tileMapWidth) {

	  //int xTile = tileMap.pixelsToTiles(x + DX + playerWidth);
	  //int yTile = tileMap.pixelsToTiles(y) - 1;

       //   String mess = "Coordinates in TileMap: (" + xTile + "," + yTile + ")";
	  //System.out.println (mess);

	  //if (tileMap.getTile(xTile, yTile) == null)
	  	x = x + DX;
      //}

      // check if x is outside the right side of the tile map.

   }


   public void moveUp () {

      if (!window.isVisible ()) return;

      y = y - DY;
   }


   public void moveDown () {

      if (!window.isVisible ()) return;

      y = y + DY;
   }


   public int getX() {
      return x;
   }


   public void setX(int x) {
      this.x = x;
   }


   public int getY() {
      return y;
   }


   public void setY(int y) {
      this.y = y;
   }


   public Image getImage(){
      return playerImage;
   }

}