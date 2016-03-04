import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Administrator on 3/1/2016.
 */
public abstract class GameObject {
    private int positionX;
    private int positionY;
    private BufferedImage sprite;

    public void update() {

    }

    public void draw(Graphics g) {

    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }
}
