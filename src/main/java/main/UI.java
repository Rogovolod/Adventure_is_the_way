package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {

    private final GamePanel gamePanel;
    private Graphics2D g2;
    private final Font timesNewRomanPlane40 = new Font("Times new roman", Font.PLAIN, 40);
    private final Font timesNewRomanBold80 = new Font("Times new roman", Font.BOLD, 80);
    private boolean messageOn;
    private String message;
    public String currentDialog;

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

        if (gamePanel.gameState == gamePanel.playState) {
            //todo
        }
        // PAUSE
        if (gamePanel.gameState == gamePanel.pauseState) {
            drawPauseScreen();
        }
        // Dialog state
        if (gamePanel.gameState == gamePanel.dialogState) {
            drawDialogScreen();
        }
    }

    private void drawDialogScreen() {
        // WINDOW
        int x = gamePanel.titleSize * 2;
        int y = gamePanel.titleSize / 2;
        int width = gamePanel.screenWidth - gamePanel.titleSize * 4;
        int height = gamePanel.titleSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28f));
        x += gamePanel.titleSize;
        y += gamePanel.titleSize;

        for (String line : currentDialog.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    private void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
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
