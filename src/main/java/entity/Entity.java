package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    protected int spriteCounter = 0;
    protected int spriteNumber = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn;

}
