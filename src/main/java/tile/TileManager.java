package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
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
/*            setupImage(0, "grass1");
            setupImage(1, "wall", true);
            setupImage(2, "water", true);
            setupImage(3, "earth1");
            setupImage(4, "treeC", true);
            setupImage(5, "sand1");*/
        setupImage(0, "grass0");
        setupImage(1, "grass0");
        setupImage(2, "grass0");
        setupImage(3, "grass0");
        setupImage(4, "grass0");
        setupImage(5, "grass0");
        setupImage(6, "grass0");
        setupImage(7, "grass0");
        setupImage(8, "grass0");
        setupImage(9, "grass0");

        setupImage(10 , "grass2", false);
        setupImage(11 , "grass1", false);
        setupImage(12 , "waternew7", true);
        setupImage(13 , "waternew14", true);
        setupImage(14 , "waternew1", true);
        setupImage(15 , "waternew2", true);
        setupImage(16 , "waternew3", true);
        setupImage(17 , "waternew6", true);
        setupImage(18 , "waternew8", true);
        setupImage(19 , "waternew11", true);
        setupImage(20 , "waternew12", true);
        setupImage(21 , "waternew13", true);
        setupImage(22 , "waternew4", true);
        setupImage(23 , "waternew5", true);
        setupImage(24 , "waternew9", true);
        setupImage(25 , "waternew10", false);
        setupImage(26 , "sendnew7", false);
        setupImage(27 , "sendnew1", false);
        setupImage(28 , "sendnew2", false);
        setupImage(29 , "sendnew3", false);
        setupImage(30 , "sendnew6", false);
        setupImage(31 , "sendnew8", false);
        setupImage(32 , "sendnew11", false);
        setupImage(33 , "sendnew12", false);
        setupImage(34 , "sendnew13", false);
        setupImage(35 , "sendnew4", false);
        setupImage(36 , "sendnew5", false);
        setupImage(37 , "sendnew9", false);
        setupImage(38 , "sendnew10", false);
        setupImage(39 , "earth1", false);
        setupImage(40 , "wall", true);
        setupImage(41 , "treeN1", true);
        setupImage(42 , "grass3", false);
        setupImage(43 , "treeN2", true);
        setupImage(44 , "rock5", true);


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

    public void setupImage(int index, String imageName) {
        try {
            tile[index] = new Tile();
            tile[index].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/" + imageName + ".png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < gamePanel.MAX_WORLD_ROWS; i++) {
            for (int j = 0; j < gamePanel.MAX_WORLD_COLUMNS; j++) {
                int worldX = j * gamePanel.titleSize;
                int worldY = i * gamePanel.titleSize;
                double screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
                double screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
                if (worldX + gamePanel.titleSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - gamePanel.titleSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.titleSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.titleSize < gamePanel.player.worldY + gamePanel.player.screenY) {
                    g2.drawImage(tile[mapTileNumber[j][i]].getImage(), (int)screenX, (int)screenY, null);
                }
            }
        }
    }

}
