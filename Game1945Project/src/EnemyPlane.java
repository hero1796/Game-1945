import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Administrator on 3/1/2016.
 */
public class EnemyPlane extends Plane {
    private Vector<Bullet> vectorB = new Vector<Bullet>();
    private Vector<Bullet> vectorB2 = new Vector<Bullet>();
    private Vector<Bullet> vectorB3 = new Vector<Bullet>();
    private int shotType;
    private int count = 0;

    public int getShotType() {
        return shotType;
    }

    public void setShotType(int shotType) {
        this.shotType = shotType;
    }
    public EnemyPlane(int positionX, int positionY, int speed, int planeType, int shotType) {
        setPositionX(positionX);
        setPositionY(positionY);
        setSpeed(speed);
        setPlaneType(planeType);
        this.shotType = shotType;
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

    private void move() {
        setPositionX(getPositionX() + getSpeed());
        if(getPositionX() >=347) {
            setSpeed(-getSpeed());
        }
        if(getPositionX() <=0) {
            setSpeed(-getSpeed());
        }
    }

    @Override
    public void update() {
        count++;
        super.update();
        if(count >= 60) {
            this.shot();
            count = 0;
        }
        if(this.shotType == 3) {
            for (Bullet b : vectorB) {
                b.update();
            }
            for (Bullet b : vectorB2) {
                b.update();
            }
            for (Bullet b : vectorB3) {
                b.update();
            }
        } else if(this.shotType == 1) {
            for (Bullet b : vectorB) {
                b.update();
            }
        } else if(this.shotType == 2) {
            for (Bullet b : vectorB) {
                b.update();
            }
            for (Bullet b : vectorB2) {
                b.update();
            }
        }
        this.move();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(getSprite(), getPositionX(), getPositionY(), null);
        if(this.shotType == 1) {
            for(Bullet b : vectorB) {
                b.draw(g);
            }
        } else if(this.shotType == 2) {
            for(Bullet b : vectorB) {
                b.draw(g);
            }
            for (Bullet b : vectorB2) {
                b.draw(g);
            }
        } else if(this.shotType == 3) {
            for(Bullet b : vectorB) {
                b.draw(g);
            }
            for (Bullet b : vectorB2) {
                b.draw(g);
            }
            for (Bullet b : vectorB3) {
                b.draw(g);
            }
        }

    }

    @Override
    public void shot() {
        super.shot();
        if(this.shotType == 1) {
            Bullet bullet = new Bullet(getPositionX() + 20, getPositionY() + 23, -3, 2, 1);
            vectorB.add(bullet);
        } else if(this.shotType == 2) {
            Bullet bullet = new Bullet(getPositionX() + 20, getPositionY() + 23, -4, 2, 1);
            vectorB.add(bullet);
            Bullet bullet2 = new Bullet(getPositionX() + 20, getPositionY() + 60, -4, 2, 1);
            vectorB2.add(bullet2);
        } else if(this.shotType == 3) {
            Bullet bullet = new Bullet(getPositionX() + 20, getPositionY() + 23, -3, 2, 1);
            vectorB.add(bullet);
            Bullet bullet2 = new Bullet(getPositionX() + 20, getPositionY() + 23, 3, 2, 2);
            vectorB2.add(bullet2);
            Bullet bullet3 = new Bullet(getPositionX() + 20, getPositionY() + 23, 3, 2, 3);
            vectorB.add(bullet3);
        }
    }
}
