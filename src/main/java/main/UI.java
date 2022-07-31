package main;

import object.Key;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    private final GamePanel gamePanel;
    private Graphics2D g2;
    private final Font timesNewRomanPlane40 = new Font("Times new roman", Font.PLAIN, 40);
    private final Font timesNewRomanBold80 = new Font("Times new roman", Font.BOLD, 80);
    private boolean messageOn;
    private String message;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(timesNewRomanPlane40);
        g2.setColor(Color.white);
        if (!gamePanel.isPause) {
            //todo
        }
        if (gamePanel.isPause) {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        final int x = getXForCenteredText(text);
        final int y = gamePanel.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public int getXForCenteredText(String text) {
        final int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gamePanel.screenWidth / 2 - length / 2;
    }

}
