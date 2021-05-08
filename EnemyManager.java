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
public EnemyManager(JFrame window,ArrayList<Fireball> fireballs, Defendor defendor){
enemies = new ArrayList<Enemy>();
this.fireballs = fireballs;
this.window = window;
level =0;
this.defendor = defendor;
this.random = new Random();
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

    for(int i =0;i<enemies.size();i++){
        dx = x1 - enemies.get(i).getX();
        dy = y1 - enemies.get(i).getY();
        angle = Math.atan2(dy, dx);
        enemies.get(i).rotate(angle);
        enemies.get(i).update();
    }

}

public void createEnemies(int n,int x,int y,int dx,int dy){
    
    Dimension dimension = window.getSize();
    int width = (int) dimension.getWidth();
    int height = (int) dimension.getHeight();

    x = random.nextInt(width);
    y = random.nextInt(height);
    if(!enemies.isEmpty()){
        for(int k=0;k<enemies.size();k++){
            
        }
    }

for(int i=0;i<n;i++){
    enemies.add(new Enemy(this.window, x, y, dx, dy, fireballs));
}
}


}