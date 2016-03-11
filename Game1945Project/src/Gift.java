import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 3/11/2016.
 */
public class Gift extends GameObject {
    private int speed;

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Gift(int x, int y, int speed) {
        setPositionX(x);
        setPositionY(y);
        this.speed = speed;
        try {
            setSprite(ImageIO.read(new File("Resources/gift.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean checkCollision() {
        Rectangle rectGift = new Rectangle((int) getPositionX(), (int) getPositionY(), getSprite().getWidth()
                , getSprite().getHeight());
        Rectangle rectPlaneMoveByKey =
                new Rectangle((int) YourPlaneManager.getInstance().getPlaneMoveByKey().getPositionX()
                        , (int) YourPlaneManager.getInstance().getPlaneMoveByKey().getPositionY()
                        , YourPlaneManager.getInstance().getPlaneMoveByKey().getWidth()
                        , YourPlaneManager.getInstance().getPlaneMoveByKey().getHeight());
        Rectangle rectPlaneMoveByMouse =
                new Rectangle((int) YourPlaneManager.getInstance().getPlaneMoveByMouse().getPositionX()
                        , (int) YourPlaneManager.getInstance().getPlaneMoveByMouse().getPositionY()
                        , YourPlaneManager.getInstance().getPlaneMoveByMouse().getWidth()
                        , YourPlaneManager.getInstance().getPlaneMoveByMouse().getHeight());

        if (rectGift.intersects(rectPlaneMoveByKey)) {
            YourPlaneManager.getInstance().getPlaneMoveByKey().notifyObserver();
            return true;
        } else if (rectGift.intersects(rectPlaneMoveByMouse)) {
            YourPlaneManager.getInstance().getPlaneMoveByMouse().notifyObserver();
            return true;
        } else return false;
    }
    private void move() {
        setPositionY(getPositionY() + getSpeed());
    }
    @Override
    public void update() {
        this.move();
        checkCollision();
        if(checkCollision()) {
            GameWindow.check = 1;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getSprite(), (int)getPositionX(), (int)getPositionY(), null);
    }
}
