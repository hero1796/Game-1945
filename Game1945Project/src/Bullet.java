import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 3/1/2016.
 */
public class Bullet extends GameObject {
    private int speed;
    private int bulletType;
    private int moveType;

    public int getMoveType() {
        return moveType;
    }

    public void setMoveType(int moveType) {
        this.moveType = moveType;
    }
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBulletType() {
        return bulletType;
    }

    public void setBulletType(int bulletType) {
        this.bulletType = bulletType;
    }

    public Bullet(int positionX, int positionY, int speed, int bulletType, int moveType) {
        setPositionX(positionX);
        setPositionY(positionY);
        this.moveType = moveType;
        this.speed = speed;
        this.bulletType = bulletType;

        switch(bulletType) {
            case 1:
                try {
                    setSprite(ImageIO.read(new File("Resources/DAN1.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    setSprite(ImageIO.read(new File("Resources/DAN2.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    //di chuyen cheo'
    private void moveObliquelyLeft() {
        int z = (int) Math.round((double) (this.speed)/(Math.cos(45)));
        setPositionX(getPositionX() - z);
        setPositionY(getPositionY() + z);
    }
    private void moveObliquelyRight() {
        int z = (int) Math.round((double) (this.speed)/(Math.cos(45)));
        setPositionX(getPositionX() + z);
        setPositionY(getPositionY() + z);
    }
    private void moveNomally() {
        setPositionY(getPositionY() - this.speed);
    }
    @Override
    public void update() {
        super.update();
        if(this.moveType == 1) {
            this.moveNomally();
        } else if(this.moveType == 2) {
            this.moveObliquelyLeft();
        } else if(this.moveType == 3) {
            this.moveObliquelyRight();
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(getSprite(), getPositionX(), getPositionY(), null);
    }
}
