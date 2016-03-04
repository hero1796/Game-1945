
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class YourPlane extends Plane {
    private int direction;
    private Vector<Bullet> vectorB = new Vector<Bullet>();

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public YourPlane(int positionX, int positionY, int speed, int planeType) {
        setPositionX(positionX);
        setPositionY(positionY);
        setSpeed(speed);
        setPlaneType(planeType);
        switch(planeType) {
            case 1:
                try {
                    setSprite(ImageIO.read(new File("Resources/PLANE1.png")));
                } catch(IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    setSprite(ImageIO.read(new File("Resources/PLANE2.png")));
                } catch(IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    setSprite(ImageIO.read(new File("Resources/PLANE3.png")));
                } catch(IOException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    setSprite(ImageIO.read(new File("Resources/PLANE4.png")));
                } catch(IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void moveByMouse(int x, int y) {
        setPositionX(x);
        setPositionY(y);
         /* PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            int x = (int) b.getX();
            int y = (int) b.getY();
            setPositionX(x);
            setPositionY(y); */

    }

    private void moveByKey() {
        if(this.direction == 1) {
            setPositionX(getPositionX() - getSpeed());
        } else if(this.direction == 3) {
            setPositionX(getPositionX() + getSpeed());
        } else if(this.direction == 2) {
            setPositionY(getPositionY() + getSpeed());
        } else if(this.direction == 5) {
            setPositionY(getPositionY() - getSpeed());
        }
    }
    @Override
    public void shot() {
        super.shot();
        Bullet bullet = new Bullet(getPositionX() + 29, getPositionY(), 10, 1, 1);
        vectorB.add(bullet);
    }

    @Override
    public void update() {
        super.update();
        for(Bullet b : vectorB) {
            b.update();
        }
        this.moveByKey();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(getSprite(), getPositionX(), getPositionY(), null);
        for(Bullet b : vectorB) {
            b.draw(g);
        }
    }
}

