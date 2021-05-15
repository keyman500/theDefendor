import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.*;	
public class Boss {
BossHit hit;
BossRun walk;
int x;
int y;
int dx;
int dy;
int curr_animation;
int hits;
double rotate_angle;
GameAnimation curr;
private boolean collide;
ArrayList<Fireball> fireballs;
SoundManager soundManager;
public Boss(JFrame window,int x, int y,int dx,int dy,ArrayList<Fireball> fireballs){
hit = new BossHit(window, x, y, dx, dy);
walk  = new BossRun(window,x,y,dx,dy);
this.x = x;
this.y = y;
this.rotate_angle =0;
this.dx = dx;
this.dy = dy;
this.curr_animation =0;
curr = walk;
this.fireballs = fireballs;
this.collide = false;
this.soundManager = SoundManager.getInstance();
curr.start();
}

    public void update() {

      double fx,fy;
      if(!fireballs.isEmpty()){
        for(int i=0;i<fireballs.size();i++){
            if(fireballs.get(i).getBoundingRectangle().intersects(this.getBoundingRectangle())){
             hits++;
             this.curr.setRed();
            fireballs.remove(i);
            this.soundManager.playSound("Rgethit",false);
            System.out.println("hits: "+hits);
           }
       }
    }

    if(hit.isfinished()>1){
        curr = walk;
    }

   

    if(!this.collide){

    fx = this.x + this.dx * Math.cos(this.rotate_angle);
    fy = this.y + this.dy * Math.sin(this.rotate_angle);
   this.x = (int) fx;
    this.y =  (int) fy;
 
      }
        this.curr.setPosition(this.x, this.y);
        this.curr.update();
        

          
        }

    public void draw(Graphics2D g2){

    this.curr.draw(g2);

this.curr.unsetRed();
    }

 
     public Rectangle2D.Double getBoundingRectangle() {
         return new Rectangle2D.Double (x+(curr.getImageWidth()/2)-75, y+(curr.getImageHeight()/2)-62, 150 ,125);
      }

       public void doRun(){
        curr = walk;
        curr.start();
       }

       public void doHit(){
        if(curr != this.hit){
            this.curr = this.hit;
            curr.start();
            }
       }

      
       public GameAnimation getCurrent(){

        return this.curr;

       }
       public void setPosition(int x,int y){
           this.x =x;
           this.y =y;
       }
       public int getX(){
           return this.x;
       }

       public int getY(){
           return this.y;
       }
       public void rotate(double angle){
           this.curr.rotate(angle);
           this.rotate_angle = angle;
       }

       public void setCollide(){
          this.collide = true;
       }

       public void unsetCollide(){
           this.collide = false;
       }   
       public  int  getHits(){
           return this.hits;
       }
       public boolean isHit(){
           if(this.curr==this.hit)
               return true;
           return false;
       }
}