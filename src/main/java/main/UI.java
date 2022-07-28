package main;

import object.Key;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    private final GamePanel gamePanel;
    private final Font arialPlane40 = new Font("Times new roman", Font.PLAIN, 40);
    private final Font arialBold80 = new Font("Arial", Font.BOLD, 80);
    private final BufferedImage keyImage;
    private boolean messageOn;
    private String message;
    private int messageCounter;
    public boolean gameFinished;

    private double playTime;
    private DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        Key key = new Key();
        keyImage = key.getImage();
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2) {

        if (gameFinished) {
            g2.setFont(arialPlane40);
            g2.setColor(Color.WHITE);
            String text;
            int textLength;
            int x, y;

            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 - (gamePanel.titleSize * 3);
            g2.drawString(text, x, y);

            text = "Your time is: " + decimalFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 + (gamePanel.titleSize * 4);
            g2.drawString(text, x, y);

            g2.setFont(arialBold80);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 + (gamePanel.titleSize * 2);
            g2.drawString(text, x, y);
            gamePanel.gameThread = null;

        } else {
            g2.setFont(arialPlane40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gamePanel.titleSize / 2, gamePanel.titleSize / 2, gamePanel.titleSize, gamePanel.titleSize, null);
            g2.drawString("x " + gamePanel.player.keys, 74, 65);

            playTime += 1d / 60d;
            g2.drawString("Time: " + decimalFormat. format(playTime), gamePanel.titleSize * 11, 65);

            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString(message, gamePanel.titleSize / 2, gamePanel.titleSize * 5);
                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

}
