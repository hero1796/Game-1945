import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Administrator on 3/1/2016.
 */
public class EnemyPlane extends Plane implements Observer {
    private Vector<Bullet> vectorB = new Vector<Bullet>();
    private int shotType;
    private int moveTye;
    public int count = 0;

    public int getMoveTye() {
        return moveTye;
    }
    public void setMoveTye(int moveTye) {
        this.moveTye = moveTye;
    }
    public int getShotType() {
        return shotType;
    }
    public void setShotType(int shotType) {
        this.shotType = shotType;
    }
    public int getWidth() {
        return getSprite().getWidth();
    }
    public int getHeight() {
        return getSprite().getHeight();
    }

    public EnemyPlane(int positionX, int positionY, int speed, int planeType, int shotType, int moveTye) {
        setPositionX(positionX);
        setPositionY(positionY);
        setSpeed(speed);
        setPlaneType(planeType);
        this.moveTye = moveTye;
        this.shotType = shotType;
        switch (planeType) {
            case 1:
                try {
                    setSprite(ImageIO.read(new File("Resources/PLANE1.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    setSprite(ImageIO.read(new File("Resources/PLANE2.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    setSprite(ImageIO.read(new File("Resources/PLANE3.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    setSprite(ImageIO.read(new File("Resources/PLANE4.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    private void move() {
        if (moveTye == 1) {
            setPositionX(getPositionX() + getSpeed());
            if (getPositionX() >= 347) {
                setSpeed(-getSpeed());
            }
            if (getPositionX() <= 0) {
                setSpeed(-getSpeed());
            }
        } else if (moveTye == 2) {
            setPositionX(getPositionX() + getSpeed());
            if (getPositionX() >= 347) {
                setSpeed(-getSpeed());
            }
            if (getPositionX() <= 0) {
                setSpeed(-getSpeed());
            }

            setPositionY(getPositionY() + Math.sin(getPositionX() * Math.PI / 140));
        }
    }

    @Override
    public void update() {
        count++;
        if (count == 120) {
            this.shot();
            count = 0;
        }
        for (Bullet b : vectorB) {
            b.update();
        }
        this.move();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getSprite(), (int) getPositionX(), (int) getPositionY(), null);
        for (Bullet b : vectorB) {
            b.draw(g);
        }
    }

    public void shot() {
        if (this.shotType == 1) {
            Bullet bullet = new Bullet((int) getPositionX() + 20, (int) getPositionY() + 23, -7, 2, 1);
            vectorB.add(bullet);
        } else if (this.shotType == 2) {
            Bullet bullet = new Bullet((int) getPositionX() + 20, (int) getPositionY() + 23, -7, 2, 1);
            Bullet bullet2 = new Bullet((int) getPositionX() + 20, (int) getPositionY() + 60, -7, 2, 1);
            vectorB.add(bullet);
            vectorB.add(bullet2);
        } else if (this.shotType == 3) {
            Bullet bullet = new Bullet((int) getPositionX() + 20, (int) getPositionY() + 23, -7, 2, 1);
            Bullet bullet2 = new Bullet((int) getPositionX() + 20, (int) getPositionY() + 23, 2, 2, 2);
            Bullet bullet3 = new Bullet((int) getPositionX() + 20, (int) getPositionY() + 23, 2, 2, 3);
            vectorB.add(bullet);
            vectorB.add(bullet2);
            vectorB.add(bullet3);
        }
    }

    @Override
    public void update(String messenger) {
        if(messenger.equals("Bo may an duoc qua roi !")) {
            System.out.println("Thang planeMoveByKey an duoc qua cmnr :(");
            //EnemyPlaneManage.getInstance().getVectorEP().clear();
        }
    }
}
