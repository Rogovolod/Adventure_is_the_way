package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Boots extends SuperObject {

    public Boots(int x, int y) {
        try {
            setWorldX(x);
            setWorldY(y);
            setName("Boots");
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/items/boots.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
