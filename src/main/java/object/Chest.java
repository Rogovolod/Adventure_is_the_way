package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Chest extends SuperObject {

    public Chest() {
        setName("Chest");
        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/items/chest.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
