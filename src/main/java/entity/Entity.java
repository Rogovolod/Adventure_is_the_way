package entity;

import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;

public class Entity {

    public final GamePanel gamePanel;
    public int worldX, worldY;
    public int speed;
    protected BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction;
    protected int spriteCounter = 0;
    protected int spriteNumber = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn;
    public int actionLockCounter;
    public CopyOnWriteArrayList<String> dialogues = new CopyOnWriteArrayList<>();
    int dialogIndex;

    public Entity(GamePanel gamePanel, int worldX, int worldY) {
        this.gamePanel = gamePanel;
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    void setAction() {}
    void speak(){}

    public void update() {
        setAction();
        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkPlayer(this);
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
        if (worldX + gamePanel.titleSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.titleSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.titleSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.titleSize < gamePanel.player.worldY + gamePanel.player.screenY) {
            BufferedImage image = switchDirection();
            directionAndCollision();

            g2.drawImage(image, screenX, screenY, gamePanel.titleSize, gamePanel.titleSize, null);
        }
    }

    void directionAndCollision() {
        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "right" -> worldX += speed;
                case "left" -> worldX -= speed;
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNumber == 1) spriteNumber = 2;
                else if (spriteNumber == 2) spriteNumber = 1;
                spriteCounter = 0;
            }
        }
    }

    BufferedImage switchDirection() {
        switch (direction) {
            case "up" -> {
                if (spriteNumber == 1) return up1;
                if (spriteNumber == 2) return up2;
            }
            case "down" -> {
                if (spriteNumber == 1) return down1;
                if (spriteNumber == 2) return down2;
            }
            case "left" -> {
                if (spriteNumber == 1) return left1;
                if (spriteNumber == 2) return left2;
            }
            case "right" -> {
                if (spriteNumber == 1) return right1;
                if (spriteNumber == 2) return right2;
            }
        }
        return down1;
    }

}
