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
public class Shot {
    private int posX, posY, size;
    private Image img;
    private boolean ingame;

    public Shot(String img) {
        this.posX = 0;
        this.posY = 0;
        this.size = 40;
        this.img = new Image(img);
        ingame = false;
        
    }
    
    public void draw(GraphicsContext gc) {
        if (ingame) {
            gc.drawImage(img, posX, posY, size, size);
        }
    }
    
    public void start(int x, int y) {
        this.posX = x;
        this.posY = y;
        ingame = true;
    }
    
    public void destroy() {
        ingame = false;
    }
    
    public void update(int dir) {
        this.posY += dir*20;
    }
    
    public boolean shotColision(int ObjX, int ObjY, int ObjSizeW, int ObjSizeH) {
        
        if (!ingame) {
            return false;
        }
        
        if (posX+(size/2) <= ObjX+ObjSizeW && posX+(size/2) >= ObjX) {
            if (posY+(size/2) >= ObjY+(ObjSizeH/4) && posY+(size/2) <= ObjY+(3*ObjSizeH/4)) {
                return true;
            }   
        }
        return false;
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
