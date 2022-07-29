package object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Wall extends SuperObject {

    public Wall(BufferedImage image, int x, int y) {
        setName("Wall");
        setCollision(true);
        setImage(image);
        setWorldX(x);
        setWorldY(y);
    }

    public Wall() {
        try {
            setName("Wall");
            setCollision(true);
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/wall/wall.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
