package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    private final GamePanel gamePanel;
    public final Tile[] tile;
    public final int[][] mapTileNumber;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[50];
        mapTileNumber = new int[gamePanel.MAX_WORLD_COLUMNS][gamePanel.MAX_WORLD_ROWS];
        getTileImage();
        loadMap("/maps/world02.csv");
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            for (int i = 0; i < gamePanel.MAX_WORLD_ROWS; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < gamePanel.MAX_WORLD_COLUMNS; j++) {
                    mapTileNumber[j][i] = Integer.parseInt(line[j]);
                }
            }
            is.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTileImage() {
        setupImage(0, "grass/grass0", false);
        setupImage(1, "grass/grass0", false);
        setupImage(2, "grass/grass0", false);
        setupImage(3, "grass/grass0", false);
        setupImage(4, "grass/grass0", false);
        setupImage(5, "grass/grass0", false);
        setupImage(6, "grass/grass0", false);
        setupImage(7, "grass/grass0", false);
        setupImage(8, "grass/grass0", false);
        setupImage(9, "grass/grass0", false);

        setupImage(10, "grass/grass2", false);
        setupImage(11, "grass/grass1", false);
        setupImage(42, "grass/grass3", false);
        setupImage(12, "water/waternew7", true);
        setupImage(13, "water/waternew14", true);
        setupImage(14, "water/waternew1", true);
        setupImage(15, "water/waternew2", true);
        setupImage(16, "water/waternew3", true);
        setupImage(17, "water/waternew6", true);
        setupImage(18, "water/waternew8", true);
        setupImage(19, "water/waternew11", true);
        setupImage(20, "water/waternew12", true);
        setupImage(21, "water/waternew13", true);
        setupImage(22, "water/waternew4", true);
        setupImage(23, "water/waternew5", true);
        setupImage(24, "water/waternew9", true);
        setupImage(25, "water/waternew10", false);
        setupImage(26, "trail/sandnew7", false);
        setupImage(27, "trail/sandnew1", false);
        setupImage(28, "trail/sandnew2", false);
        setupImage(29, "trail/sandnew3", false);
        setupImage(30, "trail/sandnew6", false);
        setupImage(31, "trail/sandnew8", false);
        setupImage(32, "trail/sandnew11", false);
        setupImage(33, "trail/sandnew12", false);
        setupImage(34, "trail/sandnew13", false);
        setupImage(35, "trail/sandnew4", false);
        setupImage(36, "trail/sandnew5", false);
        setupImage(37, "trail/sandnew9", false);
        setupImage(38, "trail/sandnew10", false);
        setupImage(39, "earth1", false);
        setupImage(40, "wall/wall", true);
        setupImage(41, "trees/treeN1", true);
        setupImage(43, "trees/treeN2", true);
        setupImage(44, "rocks/rock5", true);


    }

    public void setupImage(int index, String imageName, boolean collision) {
        try {
            tile[index] = new Tile();
            tile[index].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/" + imageName + ".png"))));
            tile[index].setCollision(collision);
//            tile[index].setImage(utilityTools.scaleImage(tile[index].getImage(), gamePanel.titleSize, gamePanel.titleSize)); example scale image
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < gamePanel.MAX_WORLD_ROWS; i++) {
            for (int j = 0; j < gamePanel.MAX_WORLD_COLUMNS; j++) {
                int worldX = j * gamePanel.titleSize;
                int worldY = i * gamePanel.titleSize;
                int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
                int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
                // Stop camera at the edge
                if (gamePanel.player.screenX > gamePanel.player.worldX) {
                    screenX = worldX;
                }
                if (gamePanel.player.screenY > gamePanel.player.worldY) {
                    screenY = worldY;
                }
                int rightOffset = gamePanel.screenWidth - gamePanel.player.screenX;
                if (rightOffset > gamePanel.WORLD_WIDTH - gamePanel.player.worldX) {
                    screenX = gamePanel.screenWidth - (gamePanel.WORLD_WIDTH - worldX);
                }
                int bottomOffset = gamePanel.screenHeight - gamePanel.player.screenY;
                if (bottomOffset > gamePanel.WORLD_HEIGHT - gamePanel.player.worldY) {
                    screenY = gamePanel.screenHeight - (gamePanel.WORLD_HEIGHT - worldY);
                }

                if (worldX + gamePanel.titleSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - gamePanel.titleSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.titleSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.titleSize < gamePanel.player.worldY + gamePanel.player.screenY) {
                    g2.drawImage(tile[mapTileNumber[j][i]].getImage(), screenX, screenY, null);
                }
                else if (gamePanel.player.screenX > gamePanel.player.worldX ||
                        gamePanel.player.screenY > gamePanel.player.worldY ||
                        rightOffset > gamePanel.WORLD_WIDTH - gamePanel.player.worldX ||
                        bottomOffset > gamePanel.WORLD_HEIGHT - gamePanel.player.worldY) {
                    g2.drawImage(tile[mapTileNumber[j][i]].getImage(), screenX, screenY, null);
                }
            }
        }
    }

}
