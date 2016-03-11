import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 3/6/2016.
 */
public class Island extends GameObject {

    private int islandType;
    public int getIslandType() {
        return islandType;
    }

    public void setIslandType(int islandType) {
        this.islandType = islandType;
    }

    public Island(int x,int y, int islandType) {
        setPositionX(x);
        setPositionY(y);
        this.islandType = islandType;
        switch (islandType) {
            case 1:
            try {
                setSprite(ImageIO.read(new File("Resources/island.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
                break;
            case 2:
                try {
                    setSprite(ImageIO.read(new File("Resources/tropical-volcano.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getSprite(), (int) getPositionX(), (int)getPositionY(), null);
    }
}
