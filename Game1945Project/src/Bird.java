import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 3/6/2016.
 */
public class Bird extends GameObject {
    private int moveType;
    private int elip =0;

    private int xVelocity = 1;
    private int yVelocity = 1;
    private static final int RIGHT_WALL = 370;
    private static final int LEFT_WALL = 20;
    private static final int DOWN_WALL = 370;
    private static final int UP_WALL = 20;
    public Bird(int x, int y, int moveType) {
        setPositionX(x);
        setPositionY(y);
        this.moveType = moveType;
        try {
            setSprite(ImageIO.read(new File("Resources/bird.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void move() {
        if(moveType == 1) {
            elip++;
            setPositionX(getPositionX() + (double) (15 * Math.sin(elip * 0.09)));
            setPositionY(getPositionY() + (double) (4 * Math.cos(elip * 0.09)));
        } else if(moveType == 2) {
            elip++;
            setPositionX(getPositionX() + (double) (4 * Math.sin(elip * 0.09)));
            setPositionY(getPositionY() + (double) (4 * Math.cos(elip * 0.09)));
        } else if(moveType == 3) {
            setPositionX(getPositionX() + xVelocity);
            setPositionY(getPositionY() + yVelocity);

            if (getPositionX() >= RIGHT_WALL)
            {
                setPositionX( RIGHT_WALL);

                randomDirection();
            }

            if (getPositionY() <= UP_WALL)
            {
                 setPositionY(UP_WALL);

                randomDirection();
            }

            if (getPositionX() <= LEFT_WALL)
            {
                setPositionX(LEFT_WALL);

                randomDirection();
            }
            if (getPositionY() >= DOWN_WALL)
            {
               setPositionY( DOWN_WALL);

                randomDirection();
            }
        }
    }
    private void randomDirection()
    {
        double speed = 2.0;
        double direction = Math.random() * 2 * Math.PI;
        xVelocity = (int) (speed * Math.cos(direction));
        yVelocity = (int) (speed * Math.sin(direction));

    }
    @Override
    public void update() {
        this.move();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getSprite(), (int)getPositionX(),(int) getPositionY(), null);
    }
}
