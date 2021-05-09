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
import java.awt.geom.Point2D;
import java.awt.Point;
import javax.swing.*;
import java.util.Random;
public class EnemyManager{
    int level;
    ArrayList<Enemy> enemies;
    JFrame window;
    ArrayList<Fireball> fireballs;
    Defendor defendor;
    int defeated;
    Random random;
    int dy;
    int dx;
    boolean enemywait;
    Dimension dimension;
    boolean reposition;
public EnemyManager(JFrame window,ArrayList<Fireball> fireballs, Defendor defendor,int dy,int dx){
enemies = new ArrayList<Enemy>();
this.fireballs = fireballs;
this.window = window;
level =0;
this.defendor = defendor;
this.random = new Random();
this.dy = dy;
this.dx = dx;
this.enemywait = false;
this.dimension = window.getSize();
this.reposition = false;
createEnemies(3);
}

public void draw(Graphics2D g2){
    for(int i =0;i<enemies.size();i++){
        enemies.get(i).draw(g2);
    }
}

public void update(){

    Point p = defendor.getLocation();
    double dx,dy,angle;
    int x1 = (int) p.getX();
    int y1 = (int) p.getY();
    int repositinx=0,repositiny=0;

    for(int i =0;i<enemies.size();i++){
        Enemy e = enemies.get(i);
        if(random.nextInt(20)==0){
            e.doThrust();
        }
        if(e.getHits()>20){
            enemies.remove(i);
        }
        else{
        dx = x1 - e.getX();
        dy = y1 - e.getY();
        angle = Math.atan2(dy, dx);
        e.rotate(angle);
        //loop for collision
        for(int l=0;l<enemies.size();l++){
        
        while(e.getBoundingRectangle().intersects(enemies.get(l).getBoundingRectangle())&&i!=l){
           repositinx = random.nextInt((int)dimension.getWidth());
           repositiny = random.nextInt((int)dimension.getHeight());
           e.doTeleport();
           e.setPosition(repositinx, repositiny);
        }
    
    }
    //if(reposition){
     
    //    reposition = false;
   // }
    //end loop for collision
           
           e.update();
        //   e.unsetCollide();
    }


}

}

public void createEnemies(int n){
    
for(int i=0;i<n;i++){
    addEnemy();
}
}

public void addEnemy(){
    int width = (int) dimension.getWidth();
    int height = (int) dimension.getHeight();

  int  x = random.nextInt(width);
  int  y = random.nextInt(height);
    Enemy e = new Enemy(this.window, x, y, dx, dy, fireballs);

    if(!enemies.isEmpty()){
        for(int k=0;k<enemies.size();k++){
            while(enemies.get(k).getBoundingRectangle().intersects(e.getBoundingRectangle())){            
                  x = random.nextInt(width);
                  y = random.nextInt(height);
                  e.setPosition(x, y);
            }
        }
    }
     enemies.add(e);
}
}