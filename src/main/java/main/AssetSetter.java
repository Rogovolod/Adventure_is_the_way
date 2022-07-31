package main;

import entity.NPC_Pig;
import object.Rock;
import object.Wall;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class AssetSetter {

    private final GamePanel gamePanel;

    private BufferedImage wallUpLeftCorner;
    private BufferedImage wallUp;
    private BufferedImage wallUpRightCorner;
    private BufferedImage wallLeft;
    private BufferedImage wallRight;
    private BufferedImage wallDownLeftCorner;
    private BufferedImage wallDown;
    private BufferedImage wallDownRightCorner;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        initImages();
    }

    private void initImages() {
        try {
            wallUpLeftCorner = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/wall/wallUpLeftCorner.png")));
            wallUp = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/wall/wallUp.png")));
            wallUpRightCorner = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/wall/wallUpRightCorner.png")));
            wallLeft = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/wall/wallLeft.png")));
            wallRight = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/wall/wallRight.png")));
            wallDownLeftCorner = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/wall/wallDownLeftCorner.png")));
            wallDown = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/wall/wallDown.png")));
            wallDownRightCorner = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/wall/wallDownRightCorner.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setObject() {
        gamePanel.objects.add(new Rock(25 * gamePanel.titleSize, 19 * gamePanel.titleSize));

        gamePanel.objects.add(new Wall(wallUpLeftCorner, 8 * gamePanel.titleSize, 7 * gamePanel.titleSize));
        gamePanel.objects.add(new Wall(wallUp, 9 * gamePanel.titleSize, 7 * gamePanel.titleSize));
        gamePanel.objects.add(new Wall(wallUp, 10 * gamePanel.titleSize, 7 * gamePanel.titleSize));
        gamePanel.objects.add(new Wall(wallUp, 11 * gamePanel.titleSize, 7 * gamePanel.titleSize));
        gamePanel.objects.add(new Wall(wallUpRightCorner, 12 * gamePanel.titleSize, 7 * gamePanel.titleSize));
        gamePanel.objects.add(new Wall(wallLeft, 8 * gamePanel.titleSize, 8 * gamePanel.titleSize));
        gamePanel.objects.add(new Wall(wallRight, 12 * gamePanel.titleSize, 8 * gamePanel.titleSize));
        gamePanel.objects.add(new Wall(wallLeft, 8 * gamePanel.titleSize, 9 * gamePanel.titleSize));
        gamePanel.objects.add(new Wall(wallRight, 12 * gamePanel.titleSize, 9 * gamePanel.titleSize));
        gamePanel.objects.add(new Wall(wallLeft, 8 * gamePanel.titleSize, 10 * gamePanel.titleSize));
        gamePanel.objects.add(new Wall(wallRight, 12 * gamePanel.titleSize, 10 * gamePanel.titleSize));
        gamePanel.objects.add(new Wall(wallLeft, 8 * gamePanel.titleSize, 11 * gamePanel.titleSize));
        gamePanel.objects.add(new Wall(wallRight, 12 * gamePanel.titleSize, 11 * gamePanel.titleSize));
        gamePanel.objects.add(new Wall(wallDownLeftCorner, 8 * gamePanel.titleSize, 12 * gamePanel.titleSize));
        gamePanel.objects.add(new Wall(wallDown, 9 * gamePanel.titleSize, 12 * gamePanel.titleSize));
        gamePanel.objects.add(new Wall(wallDown, 11 * gamePanel.titleSize, 12 * gamePanel.titleSize));
        gamePanel.objects.add(new Wall(wallDownRightCorner, 12 * gamePanel.titleSize, 12 * gamePanel.titleSize));
    }

    public void setNPC() {
        gamePanel.nPCs.add(new NPC_Pig(gamePanel, gamePanel.titleSize * 21, gamePanel.titleSize * 21));
    }

}
