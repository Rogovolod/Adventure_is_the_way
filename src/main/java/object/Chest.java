package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Chest extends SuperObject {

    public Chest(int x, int y) {
        try {
            setWorldX(x);
            setWorldY(y);
            setName("Chest");
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/items/chest.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
