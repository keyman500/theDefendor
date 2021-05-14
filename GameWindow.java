import javax.swing.*;			// need this for GUI objects
import java.awt.*;			// need this for certain AWT classes
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.image.BufferStrategy;	// need this to implement page flipping
import java.util.ArrayList;


public class GameWindow extends JFrame implements
				Runnable,
				KeyListener,
				MouseListener,
				MouseMotionListener
{
  	private static final int NUM_BUFFERS = 2;	// used for page flipping

	private int pWidth, pHeight;     		// width and height of screen

	private Thread gameThread = null;            	// the thread that controls the game
	private volatile boolean isRunning = false;    	// used to stop the game thread


	private BufferedImage image;			// drawing area for each frame

	private Image quit1Image;			// first image for quit button
	private Image quit2Image;			// second image for quit button

	private boolean finishedOff = false;		// used when the game terminates

	private volatile boolean isOverQuitButton = false;
	private Rectangle quitButtonArea;		// used by the quit button

	private volatile boolean isOverPauseButton = false;
	private Rectangle pauseButtonArea;		// used by the pause 'button'
	private volatile boolean isPaused = false;

	private volatile boolean isOverStopButton = false;
	private Rectangle stopButtonArea;		// used by the stop 'button'
	private volatile boolean isStopped = false;

	private volatile boolean isOverShowAnimButton = false;
	private Rectangle showAnimButtonArea;		// used by the show animation 'button'
	private volatile boolean isAnimShown = false;

	private volatile boolean isOverPauseAnimButton = false;
	private Rectangle pauseAnimButtonArea;		// used by the pause animation 'button'
	private volatile boolean isAnimPaused = false;
   
	private GraphicsDevice device;			// used for full-screen exclusive mode 
	private Graphics gScr;
	private BufferStrategy bufferStrategy;

	private SoundManager soundManager;
	BackgroundManager bgManager;
	TileMapManager tileManager;
	TileMap	tileMap;
	private Image bgImage;
	private Animation runing;
	private Boolean pauserun;

	private Animation shooting;
    private boolean pauseshoot;
	private Fireball f;
	private boolean pausefire;
	ArrayList<Fireball> fireballs;
	private Defendor defendor;
	private Enemy enemy;
	private EnemyManager enemyManager;
	private Background map;

	public GameWindow() {
		super("The Defendor: Full Screen Exclusive Mode");
         
		initFullScreen();
		
		this.fireballs = new ArrayList<Fireball>();
	//	this.enemy =  new Enemy(this,100,100,1,1,this.fireballs);
		this.pauserun = true;
		this.pausefire = true;
		this.pauseshoot = true;
		f = new Fireball(this,100,100,5,5);
		this.defendor = new Defendor(this,100,100,5,5);
        bgImage = loadImage("images/bg4.jpg");
		quit1Image = loadImage("images/Quit1.png");
		quit2Image = loadImage("images/Quit2.png");
		setButtonAreas();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
        //loading animations
		this.defendor.running.start();
		this.map = new Background(this,"./images/bg4.jpg", 10);
		this.defendor.setActive(0);
		this.enemyManager = new EnemyManager(this,fireballs,defendor,6,6);
		soundManager = SoundManager.getInstance();
		image = new BufferedImage (pWidth, pHeight, BufferedImage.TYPE_INT_RGB);

		startGame();

		

	}


	// implementation of Runnable interface

	public void run () {
		try {
			isRunning = true;
			while (isRunning) {
				if (isPaused == false) {
					gameUpdate();
				}
				screenUpdate();
				Thread.sleep (50);
			}
		}
		catch(InterruptedException e) {}

		finishOff();
	}


	/* This method performs some tasks before closing the game.
	   The call to System.exit() should not be necessary; however,
	   it prevents hanging when the game terminates.
	*/

	private void finishOff() { 
    		if (!finishedOff) {
			finishedOff = true;
			restoreScreen();
			System.exit(0);
		}
	}


	/* This method switches off full screen mode. The display
	   mode is also reset if it has been changed.
	*/

	private void restoreScreen() { 
		Window w = device.getFullScreenWindow();
		
		if (w != null)
			w.dispose();
		
		device.setFullScreenWindow(null);
	}


	public void gameUpdate () {
	Dimension dimension = this.getSize();
	int firex,firey;

	if(!this.pauserun){
	this.defendor.running.update();}
	if(!this.pauseshoot){
		this.defendor.shooting.update();}

	if(!this.pausefire){
		for(int i=0;i<fireballs.size();i++){
			firex = fireballs.get(i).getX();
			firey= fireballs.get(i).getY();
			if(firex>(dimension.width+ 20)){
                fireballs.remove(i);
            }else
            if(firey>(dimension.height+20)){
                fireballs.remove(i);
            }else
            if(firey< -20){
				fireballs.remove(i);
            }else
            if(firex<-20){
				fireballs.remove(i);
            }else{
             fireballs.get(i).update();}
		}

		
	}

         
	if(this.defendor.shooting.isfinished() > 1){
		this.pauseshoot = true;
		this.defendor.running.setPosition(this.defendor.shooting.getX(), this.defendor.shooting.getY());
	//	this.defendor.runing.rotate(this.runing.getAngle());
	this.defendor.shooting.reset();
	this.defendor.setActive(0);
	}

	//this.enemy.update();
	this.enemyManager.update();

	}


	private void screenUpdate() { 

		try {
			gScr = bufferStrategy.getDrawGraphics();
			gameRender(gScr);
			gScr.dispose();
			if (!bufferStrategy.contentsLost())
				bufferStrategy.show();
			else
				System.out.println("Contents of buffer lost.");
      
			// Sync the display on some systems.
			// (on Linux, this fixes event queue problems)

			Toolkit.getDefaultToolkit().sync();
		}
		catch (Exception e) { 
			e.printStackTrace();  
			isRunning = false; 
		} 
	}


	public void gameRender (Graphics gScr) {		// draw the game objects
		//gScr.drawImage (bgImage, 0, 0, pWidth, pHeight, null);
		// draw the background image
		this.map.draw((Graphics2D)gScr);

		if(this.defendor.getHealth()<1){
           gameOverMessage((Graphics2D)gScr,false);
		   this.isPaused=  true;
		}
		if(this.enemyManager.isWinGame()){
            gameOverMessage((Graphics2D)gScr, true);
			this.isPaused = true;
		}
		if(!pauseshoot){
			this.defendor.shooting.draw((Graphics2D)gScr);}
		else{
			this.defendor.running.draw((Graphics2D)gScr);}

		if(!this.pausefire){
		for(int i=0;i<fireballs.size();i++){
			fireballs.get(i).draw((Graphics2D)gScr);
		}
		}
	

	//	this.enemy.draw((Graphics2D)gScr);
		this.enemyManager.draw((Graphics2D)gScr);

	//	defender.draw((Graphics2D)gScr);
drawButtons(gScr);			// draw the buttons

gScr.setColor(Color.black);



	
	}


	private void initFullScreen() {				// standard procedure to get into FSEM

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		device = ge.getDefaultScreenDevice();

		setUndecorated(true);	// no menu bar, borders, etc.
		setIgnoreRepaint(true);	// turn off all paint events since doing active rendering
		setResizable(false);	// screen cannot be resized
		
		if (!device.isFullScreenSupported()) {
			System.out.println("Full-screen exclusive mode not supported");
			System.exit(0);
		}

		device.setFullScreenWindow(this); // switch on full-screen exclusive mode

		// we can now adjust the display modes, if we wish

		showCurrentMode();

		pWidth = getBounds().width;
		pHeight = getBounds().height;
		
		System.out.println("Width of window is " + pWidth);
		System.out.println("Height of window is " + pHeight);

		try {
			createBufferStrategy(NUM_BUFFERS);
		}
		catch (Exception e) {
			System.out.println("Error while creating buffer strategy " + e); 
			System.exit(0);
		}

		bufferStrategy = getBufferStrategy();
	}


	// This method provides details about the current display mode.

	private void showCurrentMode() {
		DisplayMode dm = device.getDisplayMode();

		System.out.println("Current Display Mode: (" + 
                           dm.getWidth() + "," + dm.getHeight() + "," +
                           dm.getBitDepth() + "," + dm.getRefreshRate() + ")  " );
  	}


	// Specify screen areas for the buttons and create bounding rectangles

	private void setButtonAreas() {
		
		//  leftOffset is the distance of a button from the left side of the window.
		//  Buttons are placed at the top of the window.

		int leftOffset = (pWidth - (5 * 150) - (4 * 20)) / 2;
		pauseButtonArea = new Rectangle(leftOffset, 60, 150, 40);

		leftOffset = leftOffset + 170;
		stopButtonArea = new Rectangle(leftOffset, 60, 150, 40);

		leftOffset = leftOffset + 170;
		showAnimButtonArea = new Rectangle(leftOffset, 60, 150, 40);

		leftOffset = leftOffset + 170;
		pauseAnimButtonArea = new Rectangle(leftOffset, 60, 150, 40);

		leftOffset = leftOffset + 170;
		int quitLength = quit1Image.getWidth(null);
		int quitHeight = quit1Image.getHeight(null);
		quitButtonArea = new Rectangle(leftOffset, 55, 180, 50);
	}


	public Image loadImage (String fileName) {
		return new ImageIcon(fileName).getImage();
	}


	private void drawButtons (Graphics g) {
		Font oldFont, newFont;

		oldFont = g.getFont();		// save current font to restore when finished
	
		newFont = new Font ("TimesRoman", Font.ITALIC + Font.BOLD, 18);
		g.setFont(newFont);		// set this as font for text on buttons

    		g.setColor(Color.black);	// set outline colour of button

		// draw the pause 'button'

		g.setColor(Color.BLACK);
		g.drawOval(pauseButtonArea.x, pauseButtonArea.y, 
			   pauseButtonArea.width, pauseButtonArea.height);

		if (isOverPauseButton && !isStopped)
			g.setColor(Color.WHITE);
		else
			g.setColor(Color.RED);	

		if (isPaused && !isStopped)
			g.drawString("Paused", pauseButtonArea.x+45, pauseButtonArea.y+25);
		else
			g.drawString("Pause", pauseButtonArea.x+55, pauseButtonArea.y+25);

		// draw the stop 'button'

		g.setColor(Color.BLACK);
		g.drawOval(stopButtonArea.x, stopButtonArea.y, 
			   stopButtonArea.width, stopButtonArea.height);

		if (isOverStopButton && !isStopped)
			g.setColor(Color.WHITE);
		else
			g.setColor(Color.RED);

		if (isStopped)
			g.drawString("Stopped", stopButtonArea.x+40, stopButtonArea.y+25);
		else
			g.drawString("kills "+this.enemyManager.getDefeated(), stopButtonArea.x+40, stopButtonArea.y+25);

		// draw the show animation 'button'

		g.setColor(Color.BLACK);
		g.drawOval(showAnimButtonArea.x, showAnimButtonArea.y, 
			   showAnimButtonArea.width, showAnimButtonArea.height);

		if (isOverShowAnimButton && !isPaused && !isStopped)
			g.setColor(Color.WHITE);
		else
			g.setColor(Color.RED);
      		g.drawString("Round "+this.enemyManager.getLevel(), showAnimButtonArea.x+40, showAnimButtonArea.y+25);

		// draw the pause anim 'button'

		g.setColor(Color.BLACK);
		g.drawOval(pauseAnimButtonArea.x, pauseAnimButtonArea.y, 
			   pauseAnimButtonArea.width, pauseAnimButtonArea.height);

		if (isOverPauseAnimButton && isAnimShown && !isPaused && !isStopped)
			g.setColor(Color.WHITE);
		else
			g.setColor(Color.RED);

		if (isAnimShown && isAnimPaused && !isStopped)
			g.drawString("Anim Paused", pauseAnimButtonArea.x+30, pauseAnimButtonArea.y+25);
		else
			g.drawString("Health "+this.defendor.getHealth(), pauseAnimButtonArea.x+35, pauseAnimButtonArea.y+25);

			
		// draw the quit button (an actual image that changes when the mouse moves over it)

		if (isOverQuitButton)
		   g.drawImage(quit1Image, quitButtonArea.x, quitButtonArea.y, 180, 50, null);
		    	       //quitButtonArea.width, quitButtonArea.height, null);
				
		else
		   g.drawImage(quit2Image, quitButtonArea.x, quitButtonArea.y, 180, 50, null);
		    	       //quitButtonArea.width, quitButtonArea.height, null);

		g.setFont(oldFont);		// reset font

	}


	private void startGame() { 
		if (gameThread == null) {
			gameThread = new Thread(this);
			gameThread.start();			

		}
	}


	// displays a message to the screen when the user stops the game

	private void gameOverMessage(Graphics g,boolean win) {
		String msg ="";
		Font font = new Font("SansSerif", Font.BOLD, 24);
		FontMetrics metrics = this.getFontMetrics(font);

        if(win)
		    msg = "You Win! Thanks for playing!";
		else
		    msg = "Game over you lose! Thanks for playing!";

		int x = (pWidth - metrics.stringWidth(msg)) / 2; 
		int y = (pHeight - metrics.getHeight()) / 2;

		g.setColor(Color.BLUE);
		g.setFont(font);
		g.drawString(msg, x, y);

	}


	// implementation of methods in KeyListener interface

	public void keyPressed (KeyEvent e) {
		map.moveUp();

		if (isPaused)
			return;

		int keyCode = e.getKeyCode();

		if ((keyCode == KeyEvent.VK_ESCAPE) || (keyCode == KeyEvent.VK_Q) ||
             	   (keyCode == KeyEvent.VK_END)) {
           		isRunning = false;		// user can quit anytime by pressing
			return;				//  one of these keys (ESC, Q, END)			
         	}
		else
		if (keyCode == KeyEvent.VK_A) {
		
			if(!this.pauseshoot){
				this.defendor.shooting.moveLeft();
			}else{
			this.pauserun = false;
			this.defendor.running.moveLeft();
		     System.out.println("going left bro");
		}
           
			//defender.moveLeft();
		}
		else
		if (keyCode == KeyEvent.VK_D) {
			if(!this.pauseshoot){
				this.defendor.shooting.moveRight();
			}else{
			this.pauserun = false;
			this.defendor.running.moveRight();}
		}
		else
		if (keyCode == KeyEvent.VK_W) {
			//defender.moveUp();
			if(!this.pauseshoot){
				this.defendor.shooting.moveUp();
			}else{
			this.pauserun = false;
			this.defendor.running.moveUp();}
		}
		else
		if (keyCode == KeyEvent.VK_S) {
			//defender.moveDown();
			if(!this.pauseshoot){
				this.defendor.shooting.moveDown();
			}else{
			this.pauserun = false;
			this.defendor.running.moveDown();}
		}

	}


	public void keyReleased (KeyEvent e) {
     this.pauserun = true;
	 this.defendor.running.stopSound();
	 this.defendor.shooting.stopSound();

	}


	public void keyTyped (KeyEvent e) {

	}


	// implement methods of MouseListener interface

	public void mouseClicked(MouseEvent e) {
     
			

			
			
			

	}


	public void mouseEntered(MouseEvent e) {

	}


	public void mouseExited(MouseEvent e) {

	}


	public void mousePressed(MouseEvent e) {
		
		testMousePress(e.getX(), e.getY());
		//this.enemy.stab();
		//this.enemy.doThrust();
		//this.enemy.doHit();

		int offx,offy;
		double theta;

		this.pauserun = true;
		this.defendor.shooting.start();
		this.defendor.setActive(1);
			if(this.pauseshoot==true){
				this.defendor.shooting.setPosition(this.defendor.running.getX(),this.defendor.running.getY());
				this.defendor.shooting.rotate(this.defendor.running.getAngle());
			}
			this.pauseshoot = false; 
			theta = this.defendor.shooting.getAngle();
			offx =  (int) (120 * Math.cos(theta) - 5 * Math.sin(theta));
			offy =  (int) (120 * Math.sin(theta) + 5 * Math.cos(theta));
			//this.f = new Fireball(this,offx,offy,40,40);
			this.f = new Fireball(this,this.defendor.shooting.getX()+offx,this.defendor.shooting.getY() + offy,60,60);
			//this.f = new Fireball(this,this.defendor.shooting.getX()+150,this.defendor.shooting.getY() + 40,40,40);
			fireballs.add(this.f);
			this.f.start();
			this.f.rotate(this.defendor.shooting.getAngle());
	    	//this.f.setPosition(this.shooting.getX()+150, this.shooting.getY() + 40);
			this.pausefire = false;
			this.soundManager.playSound("fireball", false);
	}


	public void mouseReleased(MouseEvent e) {

	}



	// implement methods of MouseMotionListener interface

	public void mouseDragged(MouseEvent e) {		
	        double angle = 0;
			double dx = e.getX() - this.defendor.running.getX();
			double dy = e.getY() - this.defendor.running.getY();
            angle = Math.atan2(dy, dx);
	
			this.defendor.shooting.rotate(angle);
		
			this.defendor.running.rotate(angle);
		

	}	


	public void mouseMoved(MouseEvent e) {
		//get angle for if you shooting here should be done so the angle of the char not off
		double angle = 0;
		testMouseMove(e.getX(), e.getY()); 
		double dx = e.getX() - this.defendor.running.getX();
		double dy = e.getY() - this.defendor.running.getY();
		angle = Math.atan2(dy, dx);
		this.defendor.shooting.rotate(angle);
		this.defendor.running.rotate(angle);
	
	}


	/* This method handles mouse clicks on one of the buttons
	   (Pause, Stop, Start Anim, Pause Anim, and Quit).
	*/

	private void testMousePress(int x, int y) {

		if (isStopped && !isOverQuitButton) 	// don't do anything if game stopped
			return;

		//if (isOverStopButton) {			// mouse click on Stop button
		//	isStopped = true;
		//	isPaused = false;
		//}
		//else
		if (isOverPauseButton) {		// mouse click on Pause button
			isPaused = !isPaused;     	// toggle pausing
		}
		else 
		if (isOverShowAnimButton && !isPaused) {// mouse click on Start Anim button
			isAnimShown = true;
		 	isAnimPaused = false;

		}
		else
		if (isOverPauseAnimButton) {		// mouse click on Pause Anim button
			if (isAnimPaused) {
				isAnimPaused = false;
				
			}
			else {
				isAnimPaused = true;	// toggle pausing
				
			}
		}
		else if (isOverQuitButton) {		// mouse click on Quit button
			isRunning = false;		// set running to false to terminate
		}
  	}


	/* This method checks to see if the mouse is currently moving over one of
	   the buttons (Pause, Stop, Show Anim, Pause Anim, and Quit). It sets a
	   boolean value which will cause the button to be displayed accordingly.
	*/

	private void testMouseMove(int x, int y) { 
		if (isRunning) {
			isOverPauseButton = pauseButtonArea.contains(x,y) ? true : false;
			isOverStopButton = stopButtonArea.contains(x,y) ? true : false;
			isOverShowAnimButton = showAnimButtonArea.contains(x,y) ? true : false;
			isOverPauseAnimButton = pauseAnimButtonArea.contains(x,y) ? true : false;
			isOverQuitButton = quitButtonArea.contains(x,y) ? true : false;
		}
	}

}