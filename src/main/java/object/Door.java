package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Door extends SuperObject {

    public Door(int x, int y) {
        try {
            setWorldX(x);
            setWorldY(y);
            setCollision(true);
            setName("Door");
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/items/door2.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
