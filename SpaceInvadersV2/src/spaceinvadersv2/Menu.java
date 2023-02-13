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
public class Menu {

    private boolean inmenu;
    private Image logo;
    private Image background;
    private GraphicsContext gc;
    
    private double size;
    private double multp;
    private long time;
    
    public Menu(GraphicsContext gc) {
        inmenu = true;
        logo = new Image("file:/home/fabricio/NetBeansProjects/SpaceInvadersV2/src/images/logo.png");
        background = new Image("file:/home/fabricio/NetBeansProjects/SpaceInvadersV2/src/images/background.jpg");
        this.gc = gc;
        size = 1;
        multp = 0.001;
        time = 0;
    }
    
    public void draw() {
        time++;
        
        gc.drawImage(background, 0, 0, 1600, 900);
        gc.drawImage(logo, 495 + 495*(1 - size)/2, 75 + 75*(1 - size)/2, 600*size, 250*size);
        
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFill(Color.YELLOW);
        gc.setFont(Font.font(70));
        gc.fillText("Instruções", 800, 500);
        gc.setFont(Font.font(30));
        gc.fillText("* Use as teclas A & D para se movimentar *\n\n* Use o SPACE para atirar *", 800, 600);
        
        gc.setFont(Font.font(40*size));
        gc.setFill(Color.RED);
        gc.fillText("* Aperte ENTER para começar a jogar *", 800, 800);
     
        manage_size();
    }

    public void manage_size() {
        if (size > 1.1 || size < 1) {
            multp = multp*(-1);
        }
        
        size += multp;
    }
    
    public boolean isInmenu() {
        return inmenu;
    }

    public void setInmenu(boolean inmenu) {
        this.inmenu = inmenu;
    }
    
}
