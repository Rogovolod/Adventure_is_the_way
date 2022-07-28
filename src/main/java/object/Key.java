package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Key extends SuperObject {

    public Key() {
        setName("Key");
        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/items/key.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
