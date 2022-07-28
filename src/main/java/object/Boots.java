package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Boots extends SuperObject {

    public Boots() {
        setName("Boots");
        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/items/boots.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
