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
public class Enemy {
    private int posX, posY, size, dir, nivel;
    private boolean ingame;
    private Image img0, img1;
    private int num_img;

    public Enemy(int posX, int posY, int nivel, String img) {
        this.posX = posX;
        this.posY = posY;
        this.nivel = nivel;
        this.img0 = new Image(img + "_0.png");
        this.img1 = new Image(img + "_1.png");
        num_img = 1;
        size = 40;
        dir = 1; // DIREITA
        ingame = true;
    }
    
    public void restart(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.ingame = true;
    }
    
    public void draw(GraphicsContext gc) {
        if (ingame) {
            if (num_img == 1) {
                gc.drawImage(img1, posX, posY, size, size);
            } else {
                gc.drawImage(img0, posX, posY, size, size);
            }
        }
    }
    
    public boolean update(Map map) {
        if (!map.edgeColisionX(this.posX, this.dir, this.size) && ingame) {
            return true; // necessario mover o Y
        }
        return false;
    }
    
    public void moveX(int difficulty) {
        this.posX += dir*difficulty*2;
    }
    
    public void moveY() {
        this.posY += 20;
        dir = (-1*dir);
    }
    
    public void destroy() {
        ingame = false;
        
    }
    
    public void changeImage() {
        num_img = -1*num_img;
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

    public int getDir() {
        return dir;
    }

    public int getNivel() {
        return nivel;
    }

    public boolean isIngame() {
        return ingame;
    }
    
    
}
