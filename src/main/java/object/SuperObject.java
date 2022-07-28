package object;

import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SuperObject {

    private BufferedImage image;
    private String name;
    private boolean collision;
    private int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gamePanel) {
        double screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        double screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
        if (worldX + gamePanel.titleSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.titleSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.titleSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.titleSize < gamePanel.player.worldY + gamePanel.player.screenY) {
            g2.drawImage(image, (int)screenX, (int)screenY, gamePanel.titleSize, gamePanel.titleSize, null);
        }
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

}
