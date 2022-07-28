package main;

import entity.Entity;

public class CollisionChecker {

    private final GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftColumn = entityLeftWorldX / gamePanel.titleSize;
        int entityRightColumn = entityRightWorldX / gamePanel.titleSize;
        int entityTopRow = entityTopWorldY / gamePanel.titleSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.titleSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.titleSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightColumn][entityTopRow];
                if (gamePanel.tileManager.tile[tileNum1].isCollision() ||
                        gamePanel.tileManager.tile[tileNum2].isCollision()) {
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.titleSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftColumn][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightColumn][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].isCollision() ||
                        gamePanel.tileManager.tile[tileNum2].isCollision()) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entityRightColumn = (entityRightWorldX + entity.speed) / gamePanel.titleSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightColumn][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].isCollision() ||
                        gamePanel.tileManager.tile[tileNum2].isCollision()) {
                    entity.collisionOn = true;
                }
            }
            case "left" -> {
                entityLeftColumn = (entityLeftWorldX - entity.speed) / gamePanel.titleSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityLeftColumn][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].isCollision() ||
                        gamePanel.tileManager.tile[tileNum2].isCollision()) {
                    entity.collisionOn = true;
                }
            }
        }
    }

    public int checkObject(Entity entity, boolean isPlayer) {
        int index = 999;
        for (int i = 0; i < gamePanel.objects.length; i++) {
            if(gamePanel.objects[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gamePanel.objects[i].solidArea.x = gamePanel.objects[i].getWorldX() + gamePanel.objects[i].solidArea.x;
                gamePanel.objects[i].solidArea.y = gamePanel.objects[i].getWorldY() + gamePanel.objects[i].solidArea.y;

                switch (entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gamePanel.objects[i].solidArea)) {
                            if(gamePanel.objects[i].isCollision()) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                    }
                    case "down" -> {
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gamePanel.objects[i].solidArea)) {
                            if(gamePanel.objects[i].isCollision()) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                    }
                    case "left" -> {
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gamePanel.objects[i].solidArea)) {
                            if(gamePanel.objects[i].isCollision()) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                    }
                    case "right" -> {
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gamePanel.objects[i].solidArea)) {
                            if(gamePanel.objects[i].isCollision()) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gamePanel.objects[i].solidArea.x = gamePanel.objects[i].solidAreaDefaultX;
                gamePanel.objects[i].solidArea.y = gamePanel.objects[i].solidAreaDefaultY;

            }
        }
        return index;
    }

}
