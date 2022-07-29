package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Key extends SuperObject {

    public Key(int x, int y) {
        try {
            setName("Key");
            setWorldX(x);
            setWorldY(y);
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/items/key.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Key() {
        try {
            setName("Key");
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/items/key.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
