
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class YourPlane extends Plane implements Subject {
    private int direction;
    private Vector<Bullet> vectorB = new Vector<Bullet>();
    private Vector<Observer> vectorO = new Vector<Observer>();

    public void setDirection(int direction) {
        this.direction = direction;
    }
    public int getDirection() {
        return direction;
    }
    public int getWidth() {
        return getSprite().getWidth();
    }
    public int getHeight() {
        return getSprite().getHeight();
    }

    public YourPlane(int positionX, int positionY, int speed, int planeType, int health) {
        setPositionX(positionX);
        setPositionY(positionY);
        setSpeed(speed);
        setPlaneType(planeType);
        setHealth(health);
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
        Bullet bullet = new Bullet((int)getPositionX() + 29, (int)getPositionY(), 10, 1, 1);
        vectorB.add(bullet);
    }

    @Override
    public void update() {
        for(Bullet b : vectorB) {
            b.update();
        }
        this.moveByKey();
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(getSprite(),(int) getPositionX(),(int) getPositionY(), null);
        for(Bullet b : vectorB) {
            b.draw(g);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        vectorO.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        vectorO.remove(observer);
    }

    @Override
    public void notifyObserver() {
        if(true) {
            for(Observer o : vectorO) {
                o.update("Bo may an duoc qua roi !");
            }
        }
    }
}

