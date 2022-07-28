package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Door extends SuperObject {

    public Door() {
        setCollision(true);
        setName("Door");
        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/items/door2.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
