package spaceinvadersv2;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class SpaceInvadersV2 extends Application  {
    
    public int CANVA_WIDTH = 1600;
    public int CANVA_HEIGHT = 900;
    
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        
        // INICIALIZAÇÃO DA TELA
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Canvas canvas = new Canvas( CANVA_WIDTH, CANVA_HEIGHT);
        root.getChildren().add(canvas);        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        stage.show();
        
        // INICIALIZAÇAO DO GAME MANAGER
        GameUpdate game = new GameUpdate(gc);
        game.initialize();

        // INICILIAZAÇAO PARA PEGAR O INPUT DO TECLADO
        ArrayList<String> keys = UserInput(scene);
        
        // INICIALIZA O MENU
        Menu menu = new Menu(gc);
        
        // GAME LOOP
        game.start();
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );
        
        KeyFrame kf = new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
            if (menu.isInmenu()) {
                menu.draw();
                if (keys.contains("ENTER")) {
                    menu.setInmenu(false);
                }
                
            } else { 
                game.UserInput(keys); 
                game.update();
                game.draw();
            }
        });
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }
    
    public static ArrayList<String> UserInput(Scene scene) {
        ArrayList<String> keys = new ArrayList<String>();
        scene.setOnKeyPressed((KeyEvent e) -> {
            String code = e.getCode().toString();
            if ( !keys.contains(code) ) {
                keys.add(code);
            }
        });
        
        scene.setOnKeyReleased((KeyEvent e) -> {
            String code = e.getCode().toString();
            keys.remove( code );
        });
        return keys;
    }

}