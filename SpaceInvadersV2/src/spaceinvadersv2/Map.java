/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spaceinvadersv2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author fabricio
 */
public class Map {
    public int CANVA_WIDTH = 1600;
    public int CANVA_HEIGHT = 900;
    
    private int lifes;
    private int points;
    private int level;
    
    private double size;
    private double multp;
    
    Image background;

    public Map() {
        this.lifes = 3;     
        this.points = 0;
        this.level = 1;
        this.background = new Image("file:/home/fabricio/NetBeansProjects/SpaceInvadersV2/src/images/background.jpg");
        this.size = 1;
        this.multp = 0.002;
    }
    
    public void restart() {
        this.lifes = 3;     
        this.points = 0;
        this.level = 1;
    }
    
    public boolean edgeColisionX(int posX, int dir, int size) {
        
        if (dir == -1 && posX <= 0 ) {
            return false;
        }
        if (dir == 1 && posX+size >= CANVA_WIDTH) {
            return false;
        }
        return true;
    }
    
    public boolean edgeColisionY(int posY, int size) {
        if (posY <= 0 || posY+size >= CANVA_HEIGHT) {
            return false;
        }
        return true;
    }
   
    public void drawHUD(GraphicsContext gc, boolean flag) {
        if (!flag) {
            gc.setTextAlign(TextAlignment.LEFT);
            gc.setFont(Font.font(30));
            gc.setFill(Color.WHITE);
            gc.fillText("<< SCORE: " + points + " >>    << LEVEL: " + level + " >>", 10, 40);
            gc.fillText("<< LIFES: " + lifes + " >>", 1330, 40);
        } else {
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setFont(Font.font(60*size));
            gc.setFill(Color.RED);
            gc.fillText("Game Over \nYour Score is: " + points + "\nYour final level is: " + level +"\nENTER para jogar novamente" , 800, 400);
            
            manage_size();
        }
    }
    
    public void manage_size() {
        if (size > 1.1 || size < 1) {
            multp = multp*(-1);
        }
        
        size += multp;
    }
    
    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    
    
}
