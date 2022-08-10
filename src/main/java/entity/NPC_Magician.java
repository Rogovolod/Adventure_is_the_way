package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class NPC_Magician extends Entity {

    private int standCounter;

    public NPC_Magician(GamePanel gamePanel, int x, int y) {
        super(gamePanel, x, y);
        direction = "down";
        speed = 2;
        getMagicianImage();
    }

    public void getMagicianImage() {
        try {
            up1    = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianback1.png")));
            up2    = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianback2.png")));
            up3    = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianback3.png")));
            down1  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magician1.png")));
            down2  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magician3.png")));
            down3  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magician2.png")));
            left1  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianleft1.png")));
            left2  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianleft2.png")));
            left3  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianleft3.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianright1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianright2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianright3.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {
        Random random = new Random();
        actionLockCounter++;
        if (actionLockCounter == 120) {
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    @Override
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
                else if (spriteNumber == 2) spriteNumber = 3;
                else if (spriteNumber == 3) spriteNumber = 2;
                spriteCounter = 0;
            }
        } else if(collisionOn){
            standCounter++;
            if (standCounter == 20) {
                spriteNumber = 1;
                standCounter = 0;
            }
        }
    }

    @Override
    BufferedImage switchDirection() {
        switch (direction) {
            case "up" -> {
                if (spriteNumber == 1) return up1;
                if (spriteNumber == 2) return up2;
                if (spriteNumber == 3) return up3;
            }
            case "down" -> {
                if (spriteNumber == 1) return down1;
                if (spriteNumber == 2) return down2;
                if (spriteNumber == 3) return down3;
            }
            case "left" -> {
                if (spriteNumber == 1) return left1;
                if (spriteNumber == 2) return left2;
                if (spriteNumber == 3) return left3;
            }
            case "right" -> {
                if (spriteNumber == 1) return right1;
                if (spriteNumber == 2) return right2;
                if (spriteNumber == 3) return right3;
            }
        }
        return down1;
    }

}
