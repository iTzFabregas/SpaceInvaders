/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spaceinvadersv2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author fabricio
 */
public class SpecialEnemy {
    private int posX, posY, size, dir;
    private Image img;
    private boolean ingame;

    public SpecialEnemy(String url) {
        posX = 10;
        posY = 40;
        size = 40;
        dir = 1;
        img = new Image(url);
        ingame = false;
    }
    
    public void start() {
        ingame = true;
        if (dir == 1) {
            posX = 10;
        } else {
            posX = 1550;
        }
    }
    
    public void draw(GraphicsContext gc) {
        if (ingame) {
            gc.drawImage(img, posX, posY, size, size);
        }
    }
    
    public void destroy() {
        dir = -1*dir;
        ingame = false;
    }
    
    public void update(Map map) {
        if (map.edgeColisionX(this.posX, this.dir, this.size) && ingame) {
            moveX();
        } else {
            destroy();
        }
    }
    
    public void moveX() {
        this.posX += dir*4;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getSize() {
        return size;
    }

    public boolean isIngame() {
        return ingame;
    }
    
    
}
