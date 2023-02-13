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
public class Spaceship {
    private int posX, posY, size;
    private Image img;
    private boolean ingame;


    public Spaceship(int posX, int posY, String img) {
        this.posX = posX;
        this.posY = posY;
        this.size = 80;
        this.img = new Image(img);
        ingame = true;
    }
    
    public void restart() {
        this.posX = 720;
        ingame = true;
    }
    
    public void draw(GraphicsContext gc) {
        if (ingame) {
            gc.drawImage(img, posX, posY, size, size);
        }
    }
    
    public void destroy() {
        ingame = false;
    }
    
    public void move(int dir) {
        this.posX += dir*6;
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
