package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Rock extends SuperObject {

    public Rock() {
        try {
            setName("Rock5");
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/tiles/rocks/rock5.png"))));
            setCollision(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
