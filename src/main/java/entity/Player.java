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
    public int keys = 10;
    private int standCounter;
    private boolean moving;
    private int pixelCounter;

    private final GamePanel gamePanel;
    private final KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        screenX = gamePanel.screenWidth / 2;
        screenY = gamePanel.screenHeight / 2;

        solidArea = new Rectangle(1, 1, 46, 46);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            up1    = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knightRedUp1.png")));
            up2    = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knightRedUp2.png")));
            down1  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knightRedDown1.png")));
            down2  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knightRedDown2.png")));
            left1  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knighRedtleft1.png")));
            left2  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knightLeftRed2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knighRedtRight1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/knight/red/knightRightRed2.png")));
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
        if (!moving) {
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
                moving = true;
                collisionOn = false;
                gamePanel.collisionChecker.checkTile(this);

                int objIndex = gamePanel.collisionChecker.checkObject(this, true);
                pickUpObject(objIndex);
            } else {
                standCounter++;
                if (standCounter == 20) {
                    spriteNumber = 1;
                    standCounter = 0;
                }
            }
        }
        if (moving) {
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
            pixelCounter += speed;
            if (pixelCounter == 48) {
                moving = false;
                pixelCounter = 0;
            }
        }
    }

    public void pickUpObject(int index) {
        if (index != 999) {
            String objName = gamePanel.objects.get(index).getName();
            switch (objName) {
                case "Key" -> {
                    gamePanel.playSE(1);
                    keys++;
                    gamePanel.objects.remove(index);
                    gamePanel.ui.showMessage("You got a key!");
                }
                case "Door" -> {
                    if (keys > 0) {
                        try {
                            if (gamePanel.objects.get(index).isCollision()) {
                                gamePanel.ui.showMessage("You opened the door!");
                                gamePanel.playSE(3);
                                gamePanel.objects.get(index).setImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/items/door3.png"))));
                                gamePanel.objects.get(index).setCollision(false);
                                keys--;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            gamePanel.ui.showMessage("You opened the door!");
                            gamePanel.playSE(3);
                            gamePanel.objects.remove(index);
                            keys--;
                        }
                    } else if (gamePanel.objects.get(index).isCollision()) {
                        gamePanel.ui.showMessage("You need a key!");
                    }
                }
                case "Boots" -> {
                    gamePanel.playSE(2);
                    speed += 2;
                    gamePanel.objects.remove(index);
                    gamePanel.ui.showMessage("Speed up!");
                }
                case "Chest" -> {
                    gamePanel.stopMusic();
                    try {
                        gamePanel.objects.get(index).setImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/items/chest2.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    gamePanel.playSE(4);
                    gamePanel.ui.gameFinished = true;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNumber == 1) image = up1;
                if (spriteNumber == 2) image = up2;
            }
            case "down" -> {
                if (spriteNumber == 1) image = down1;
                if (spriteNumber == 2) image = down2;
            }
            case "left" -> {
                if (spriteNumber == 1) image = left1;
                if (spriteNumber == 2) image = left2;
            }
            case "right" -> {
                if (spriteNumber == 1) image = right1;
                if (spriteNumber == 2) image = right2;
            }
        }
        g2.drawImage(image, screenX, screenY, null);

        //print collision
/*        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);*/


    }

}
