package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    //Screen settings
    private final int ORIGINAL_TITLE_SIZE = 48; // 48x48 title

    public int titleSize = ORIGINAL_TITLE_SIZE; // 48 * 48
    public int maxScreenColumns = 16;
    public int maxScreenRows = 12;
    public int screenWidth = titleSize * maxScreenColumns; // 768 pixels
    public int screenHeight = titleSize * maxScreenRows; // 576 pixels

    //WORLD SETTINGS
    public final int MAX_WORLD_COLUMNS = 50;
    public final int MAX_WORLD_ROWS = 50;
    public final int WORLD_WIDTH = titleSize * MAX_WORLD_COLUMNS;
    public final int WORLD_HEIGHT = titleSize * MAX_WORLD_ROWS;

    //SYSTEM
    public final TileManager tileManager = new TileManager(this);
    private final KeyHandler keyHandler = new KeyHandler(this);
    public final CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public final UI ui = new UI(this);
    private Sound music = new Sound();
    private Sound se = new Sound();
    public Thread gameThread;


    //ENTITY
    public final Player player = new Player(this, keyHandler);
    public ArrayList<SuperObject> objects = new ArrayList<>();


    // Frame per second
    private final int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setUpGame() {
        assetSetter.setObject();
        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000D / FPS; // 0.01666 second
        double delta = 0;
        double currentTime;
        double lastTime = System.nanoTime();
        int timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer > 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void zoomInOut(int zoom) {
        int oldWorldWide = titleSize * MAX_WORLD_COLUMNS; // 2400
        titleSize += zoom;
        int newWorldWide = titleSize * MAX_WORLD_COLUMNS; // 2350
        double multiple = (double) newWorldWide / oldWorldWide;
        player.worldX = (int) (player.worldX * multiple);
        player.worldY = (int) (player.worldY * multiple);
        player.speed = newWorldWide / 600;

    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        long drawStart = System.nanoTime(); // DEBUG

        tileManager.draw(g2); // Tile

        //OBJECTS
        for (SuperObject object : objects) {
            if (object != null) {
                object.draw(g2, this);
            }
        }

        player.draw(g2); // Player
        ui.draw(g2); // main.UI

        // DEBUG
        if (keyHandler.checkDrawTime) {
            long passedDrawTime = System.nanoTime() - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passedDrawTime, 10, 400);
            System.out.println(passedDrawTime);
        }

        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }

}
