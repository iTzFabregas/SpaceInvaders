
package spaceinvadersv2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author fabricio
 */
public class Wall {
    private int posX, posY, sizeW, sizeH, lifes;
    Image images[];
    private boolean ingame;

    public Wall(int x, int y) {
        posX = x;
        posY = y;
        sizeH = 40;
        sizeW = 60;
        this.lifes = 5;
        images = new Image[this.lifes];
        ingame = true;
        for (int i = 0; i < this.lifes; i++) {
            images[i] = new Image("file:/home/fabricio/NetBeansProjects/SpaceInvadersV2/src/images/wall" + i + ".png");
        }
    }
     
    public void restart() {
        this.lifes = 5;
        ingame = true;
    }
    
        public void destroy() {
        lifes--;
        if (lifes == 0) {
            ingame = false;
        }
    }
    
    public void draw(GraphicsContext gc) {
        if (ingame) {
            gc.drawImage(images[lifes-1], posX, posY, sizeW, sizeH);
        }
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getSizeW() {
        return sizeW;
    }
    
    public int getSizeH() {
        return sizeH;
    }

    public boolean isIngame() {
        return ingame;
    }
    
}
