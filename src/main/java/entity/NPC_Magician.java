package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class NPC_Magician extends Entity {

    public NPC_Magician(GamePanel gamePanel, int x, int y) {
        super(gamePanel, x, y);
        direction = "down";
        speed = 1;
        getMagicianImage();
        setDialog();
    }

    public void getMagicianImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianback1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianback2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianback3.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magician1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magician2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magician3.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianleft1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianleft2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianleft3.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianright1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianright2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/NPC/magician/magicianright3.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setDialog() {
        dialogues.add("Greeting, mortal.");
        dialogues.add("Are you have come to my island \nto find the treasure?");
        dialogues.add("I used to be a great wizard but now...\nI am a powerful lich. So, I'm too old for\n adventure.");
        dialogues.add("Good luck, mortal. Try to not die.");
    }

    public void setAction() {
        Random random = new Random();
        actionLockCounter++;
        if (actionLockCounter == 100) {
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
