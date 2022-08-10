package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class NPC_Pig extends Entity {

    public NPC_Pig(GamePanel gamePanel, int x, int y) {
        super(gamePanel, x, y);
        direction = "down";
        speed = 2;
        getPigImage();
    }

    public void getPigImage() {
        try {
            up1    = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/pig/pig1up.png")));
            up2    = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/pig/pig2up.png")));
            down1  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/pig/pig1d.png")));
            down2  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/pig/pig2d.png")));
            left1  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/pig/pig1left.png")));
            left2  = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/pig/pig2left.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/pig/pig1right.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/pig/pig2right.png")));
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

}
