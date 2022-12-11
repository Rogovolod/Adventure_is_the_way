package main;

import entity.Entity;
import entity.NPC_Magician;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean checkDrawTime;


    private GamePanel gamePanel;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gamePanel.gameState == gamePanel.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_P) {
                gamePanel.gameState = gamePanel.pauseState;
            }
        } else if (gamePanel.gameState == gamePanel.pauseState) {
            if (code == KeyEvent.VK_P) {
                gamePanel.gameState = gamePanel.playState;
            }
        } else if (gamePanel.gameState == gamePanel.dialogState) {
            if (code == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.playState;
            }
        }


        //Debug
        if (code == KeyEvent.VK_T) {
            checkDrawTime = !checkDrawTime;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
