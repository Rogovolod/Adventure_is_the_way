package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    public final int screenX;
    public final int screenY;

    private int standCounter;

    private final KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.keyHandler = keyHandler;
        screenX = gamePanel.screenWidth / 2 - gamePanel.titleSize / 2;
        screenY = gamePanel.screenHeight / 2 - gamePanel.titleSize / 2;

        solidArea = new Rectangle(8, 16, 30, 28);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knightRedUp3.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knightRedUp2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knightRedUp1.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knightRedDown3.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knightRedDown2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knightRedDown1.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knighRedtleft1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knightLeftRed2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knightLeftRed2.png"))); // need to add 3rd pic
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knighRedtRight1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knightRightRed2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knightRightRed2.png"))); // need to add 3rd pic
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        worldX = gamePanel.titleSize * 23;
        worldY = gamePanel.titleSize * 21;
        speed = gamePanel.WORLD_WIDTH / 600;
        direction = "down";
    }

    public void update() {
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
            } else if (keyHandler.downPressed) {
                direction = "down";
            } else if (keyHandler.rightPressed) {
                direction = "right";
            } else {
                direction = "left";
            }
            // Tile collision
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);
            // Object collision
            int objIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);
            // NPC collision
            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.nPCs);
            interactNPC(npcIndex);
            directionAndCollision();
        } else {
            standCounter++;
            if (standCounter == 20) {
                spriteNumber = 1;
                standCounter = 0;
            }
        }
    }

    private void interactNPC(int index) {
        if (index != 999) {
            gamePanel.gameState = gamePanel.dialogState;
            gamePanel.nPCs.get(index).speak();
        }
    }

    public void pickUpObject(int index) {
        if (index != 999) {

        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = switchDirection();

        int x = screenX;
        int y = screenY;

        if (screenX > worldX) {
            x = worldX;
        }
        if (screenY > worldY) {
            y = worldY;
        }
        int rightOffset = gamePanel.screenWidth - screenX;
        if (rightOffset > gamePanel.WORLD_WIDTH - worldX) {
            x = gamePanel.screenWidth - (gamePanel.WORLD_WIDTH - worldX);
        }
        int bottomOffset = gamePanel.screenHeight - screenY;
        if (bottomOffset > gamePanel.WORLD_HEIGHT - worldY) {
            y = gamePanel.screenHeight - (gamePanel.WORLD_HEIGHT - worldY);
        }

        g2.drawImage(image, x, y, null);

        //Print collision
/*        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);*/


    }

}
