/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spaceinvadersv2;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author fabricio
 */
public class GameUpdate {

    // VARIAVEIS DO JOGO
    private GraphicsContext gc;
    public int CANVA_WIDTH = 1600;
    public int CANVA_HEIGHT = 900;

    private long time;
    private boolean gameover;
    int num_enemys;
    int difficulty;

    private Spaceship player;
    private Shot playerShot;
    private Shot enemyShot;
    private Map map;
    private Enemy[][] enemys;
    private Wall[] wall;
    private SpecialEnemy s_enemy;

    /** Construtor do Game Update
     * 
     * @param gc obejto necessário para desenhar na tela
     */
    public GameUpdate(GraphicsContext gc) {
        this.gc = gc;
        num_enemys = 55;
        time = 0;
        difficulty = 1;
        gameover = false;
    }

    /** Inicializa todas os objetos necessários para o jogo
     * 
     * @param canva tela do jogo
     */
    public void initialize() {
        this.player = new Spaceship(720, 800, "file:/home/fabricio/NetBeansProjects/SpaceInvadersV2/src/images/spaceship.png");
        this.playerShot = new Shot("file:/home/fabricio/NetBeansProjects/SpaceInvadersV2/src/images/playershot.png");
        this.enemyShot = new Shot("file:/home/fabricio/NetBeansProjects/SpaceInvadersV2/src/images/enemyshot.png");
        this.s_enemy = new SpecialEnemy("file:/home/fabricio/NetBeansProjects/SpaceInvadersV2/src/images/s_enemy.png");
        this.map = new Map();
        this.wall = new Wall[20];
        int num = 0;
        for (int i = 0; i < 4; i++) {
            wall[num++] = new Wall(100 + 400*i, 600);
            wall[num++] = new Wall(100 + 400*i, 560);
            wall[num++] = new Wall(160 + 400*i, 560);
            wall[num++] = new Wall(220 + 400*i, 560);
            wall[num++] = new Wall(220 + 400*i, 600);
        }

        enemys = new Enemy[5][11];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++) {
                if (i < 2) {
                    enemys[i][j] = new Enemy(j * 65, i * 65 + 80, 3, "file:/home/fabricio/NetBeansProjects/SpaceInvadersV2/src/images/enemy2");
                } else if (i < 4) {
                    enemys[i][j] = new Enemy(j * 65, i * 65 + 80, 2, "file:/home/fabricio/NetBeansProjects/SpaceInvadersV2/src/images/enemy1");
                } else {
                    enemys[i][j] = new Enemy(j * 65, i * 65 + 80, 1, "file:/home/fabricio/NetBeansProjects/SpaceInvadersV2/src/images/enemy0");
                }
            }
        }
    }
    
    /** Função que restaura a vida da barreiras e dos inimgigos, alem de resetar a posicao dos inimigos
     * 
     */
    public void restart() {
        for (int i = 0; i < 20; i++) {
            wall[i].restart();
        }
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++) {
                if (i < 2) {
                    enemys[i][j].restart(j * 65, i * 65 + 80);
                } else if (i < 4) {
                    enemys[i][j].restart(j * 65, i * 65 + 80);
                } else {
                    enemys[i][j].restart(j * 65, i * 65 + 80);
                }
            }   
        }
    }

    /** Função que inicia o jogo
     * 
     */
    public void start() {
        this.draw();
    }
    
    /** Função que pega as teclas pressionadas pelo jogador e faz uma ação no jogo
     * 
     * @param input lista com as teclas pressionadas
     */
    public void UserInput(ArrayList<String> input) {
        if(input.contains("D") && map.edgeColisionX(player.getPosX(), 1, player.getSize())){
            player.move(1);
        }
        if (input.contains("A") && map.edgeColisionX(player.getPosX(), -1, player.getSize())) {
            player.move(-1);
        }
        if (input.contains("SPACE") && !playerShot.isIngame() && player.isIngame()) {
            playerShot.start(player.getPosX(), player.getPosY()-player.getSize());
        }
        // SE O JOGO TIVER ACABO É POSSÍVEL JOGAR NOVAMENTE
        if (input.contains("ENTER") && gameover) {
            this.restart();
            player.restart();
            map.restart();
            num_enemys = 55;
            time = 0;
            difficulty = 1;
            gameover = false;
        }
    }

    /** Função principal do jogo, nela ocorre todas as atualizações necessárias durante o jogo: 
     * Movimentação, tiro, spawn de objetos, verificação colisao entre outras
     * 
     */
    public void update() {
        time++;

        // TODOS INIMIGOS MORTOS
        if (num_enemys == 0) {
            num_enemys = 55;
            difficulty++;
            map.setLevel(map.getLevel() + 1);
            restart();
        }
        
        // VOCE PERDEU TODAS AS SUAS VIDAS
        if (map.getLifes() == 0) {
            gameover = true;
            player.destroy();
        }
        
        // INIMIGOS CHEGARAM NA LINHA DO PLAYER
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++) {
                if (enemys[i][j].getPosY() >= 800 && enemys[i][j].isIngame()) {
                    gameover = true;
                    player.destroy();
                }
            }
        }
        
        
        // COLISAO TIRO COM INIMIGO
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++) {
                if (playerShot.shotColision(enemys[i][j].getPosX(), enemys[i][j].getPosY(), enemys[i][j].getSize(), enemys[i][j].getSize()) && enemys[i][j].isIngame()) {
                    num_enemys--;
                    enemys[i][j].destroy();
                    playerShot.destroy();
                    map.setPoints(map.getPoints()+enemys[i][j].getNivel());
                    
                }
            }
        }
        
        // COLISAO TIRO COM PLAYER
        if (enemyShot.isIngame()) {
            if (enemyShot.shotColision(player.getPosX(), player.getPosY(), player.getSize(), player.getSize())) {
                map.setLifes(map.getLifes()-1);
                enemyShot.destroy();
            }
        }
        
        //COLISAO TIRO COM INIMIGO ESPECIAL
        if (playerShot.isIngame()) {
            if (playerShot.shotColision(s_enemy.getPosX(), s_enemy.getPosY(), s_enemy.getSize(),s_enemy.getSize()) && s_enemy.isIngame()) {
                playerShot.destroy();
                map.setPoints(map.getPoints()+10);
                s_enemy.destroy();
            }
        }
        
        // COLISAO COM BARREIRA
        if (enemyShot.isIngame()) {
            for (int i = 0; i < 20; i++) {
                if (enemyShot.shotColision(wall[i].getPosX(), wall[i].getPosY(), wall[i].getSizeW(), wall[i].getSizeH()) && wall[i].isIngame()) {
                    enemyShot.destroy();
                    wall[i].destroy();
                }
             }
        }
        if (playerShot.isIngame()) {
            for (int i = 0; i < 20; i++) {
                if (playerShot.shotColision(wall[i].getPosX(), wall[i].getPosY(), wall[i].getSizeW(), wall[i].getSizeH()) && wall[i].isIngame()) {
                    playerShot.destroy();
                    wall[i].destroy();
                }
             }
        }
        
        // MOVIMENTAÇAO DOS INIMIGOS
        if (time % 2 == 0) {
            updateEnemys();
        } 
        
        
        // MOVIMENTAÇÃO INIMIGO ESPECIAL
        if (s_enemy.isIngame()) {
            s_enemy.update(map);
        } else if (time % 1000 == 0) {
            s_enemy.start();
        }
        
        // MOVIMENTAÇAO TIRO DO PLAYER
        if (playerShot.isIngame()) {
            if (map.edgeColisionY(playerShot.getPosY(), playerShot.getSize())) {
                if (time % 3 == 0) {
                    playerShot.update(-1);
                }
            } else {
                playerShot.destroy();
            }
        }
        
        // MOVIMENTAÇAO TIRO DO INIMIGO
        Random gerador = new Random();
        if (enemyShot.isIngame()) {
            if (map.edgeColisionY(enemyShot.getPosY(), enemyShot.getSize())) {
                if (time % 2 == 0) {
                    enemyShot.update(1);
                }
            } else {
                enemyShot.destroy();
            }
        } else if (time % 20 == 0) {
            while (true) {                
                int y = gerador.nextInt(10);
                boolean flag = false;
                for (int x = 4; x >= 0; x--) {
                    if (enemys[x][y].isIngame()) {
                        enemyShot.start(enemys[x][y].getPosX(), enemys[x][y].getPosY());
                        flag = true;
                        break;                        
                    }
                }
                if (flag) {
                    break;
                }
            }
        }
    }
    
    /** Função que atualiza a movimentação e colisão dos inimigos
     * 
     */
    public void updateEnemys() {
        boolean flag = false;
        for (int i = 10; i >= 0; i--) {
            for (int j = 4; j >= 0; j--) {
                if (enemys[j][i].update(map)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++) {
                if (flag) {
                    enemys[i][j].moveY();
                } else {
                    if (time % 15 == 0) {
                        enemys[i][j].changeImage();
                    }
                    enemys[i][j].moveX(difficulty);
                    
                }
            }
        }
    }

    /** Função que desenha os objetos do jogo na tela
     * 
     */
    public void draw() {
        gc.clearRect(0, 0, CANVA_WIDTH, CANVA_HEIGHT);
        // BACKGROUND
        gc.drawImage(map.background, 0, 0, CANVA_WIDTH, CANVA_HEIGHT);
        
        // BARREIRA
        for (int i = 0; i < 20; i++) {
            wall[i].draw(gc);
        }
        
        // PLAYER
        player.draw(gc);
        
        // INIMIGOS
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++) {
                enemys[i][j].draw(gc);
            }
        }
        
        // INIMIGO ESPECIAL
        s_enemy.draw(gc);
        
        // TIROS
        playerShot.draw(gc);
        enemyShot.draw(gc);
        
        // HUD
        map.drawHUD(gc, gameover);
    }
    
    /** Verifica se ja acabou o jogo
     * 
     * @return boolean se o jogo acabou
     */
    public boolean isGameover() {
        return gameover;
    }
}
