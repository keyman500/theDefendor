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
public class Enemy {
EnemyRun run;
EnemyThrust thrust;
EnemyHit hit;
int x;
int y;
int dx;
int dy;
int curr_animation;
int hits;
double rotate_angle;
GameAnimation curr;
ArrayList<Fireball> fireballs;
public Enemy(JFrame window,int x, int y,int dx,int dy,ArrayList<Fireball> fireballs){
run = new EnemyRun(window,x,y,dx,dy);
thrust = new EnemyThrust(window, x, y, dx, dy);
hit = new EnemyHit(window, x, y, dx, dy);
this.x = x;
this.y = y;
this.rotate_angle =0;
this.dx = dx;
this.dy = dy;
this.curr_animation =0;
curr = run;
this.fireballs = fireballs;
curr.start();
}

    public void update() {
        /*
        if(curr_animation==0){
            this.run.update();
        }else
        if(curr_animation==1){
            this.thrust.update();
        }else 
        if(curr_animation==2){
            this.hit.update();
        }*/
      double fx,fy;
      if(!fireballs.isEmpty()){
        for(int i=0;i<fireballs.size();i++){
            if(fireballs.get(i).getBoundingRectangle().intersects(this.getBoundingRectangle())){
             hits++;
            this.doHit();
            fireballs.remove(i);
            System.out.println("hits: "+hits);
           }
       }
    }

    if(hit.isfinished()>1){
        curr = run;
    }

    if(thrust.isfinished()){
        curr = run;
    }

    fx = this.x + this.dx * Math.cos(this.rotate_angle);
    fy = this.y + this.dy * Math.sin(this.rotate_angle);
    this.x = (int) fx;
    this.y =  (int) fy;
 

        this.curr.setPosition(this.x, this.y);
        this.curr.update();
        
       

    
        

          
        }

    public void draw(Graphics2D g2){
    /*
        if(curr_animation==0){
            this.run.draw(g2);
        }else
        if(curr_animation==1){
            this.thrust.draw(g2);
        }else 
        if(curr_animation==2){
            this.hit.draw(g2);
        }*/

    this.curr.draw(g2);

    }

 
    public Rectangle2D.Double getBoundingRectangle() {
        return new Rectangle2D.Double (x, y, 300, 300);
     }


       public void doThrust(){

         /*
          if(curr_animation==0){
            EnemyRun curr = (EnemyRun) getCurrent();
        }else
        if(curr_animation==1){
            EnemyThrust curr = (EnemyRun) getCurrent();
        }else 
        if(curr_animation==2){
            EnemyHit curr = (EnemyRun) getCurrent();
        }*/
        this.curr = this.thrust;
        curr.start();
          

        


       }

       public void doRun(){
        curr = run;
        curr.start();

       }
       public void doHit(){
           curr = hit;
           curr.start();
       }
      
       public GameAnimation getCurrent(){
      /*
        if(curr_animation==0){
            return this.run;
        }else
        if(curr_animation==1){
            return this.thrust;
        }else 
        if(curr_animation==2){
            return this.hit;
        }*/
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
      
    


    
}