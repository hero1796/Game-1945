
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class GameWindow extends Frame implements Runnable {
    //start
    Graphics seconds;
    Image image;
    BufferedImage background;
    Cursor blankCursor = null;
    YourPlane keyPlane, mousePlane;
    Vector<EnemyPlane> vectorEP = new Vector<EnemyPlane>();
    //bien bat cac su kien cua chuot.
    //int s = 0;
    //constructor
    public GameWindow(){
        //hide cursor
        blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().createImage("blank.png"), new Point(0, 0), "blankCursor");
        //tao tieu de cho cua so
        this.setTitle("50k/1 Shot");
        //tao kich co cua so
        this.setSize(400,640);
        //thiet lap xem cua so co hien thi hay khong
        this.setVisible(true);
        //thiet lap nhan phim X de thoat
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        //khoi tao may bay
        initPlane();
        //load anh tu file Resources
        try{
            background = ImageIO.read(new File("Resources/Background.png"));
        } catch(IOException e){
            e.printStackTrace();
        }

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //s = 1;
                if(e.getModifiers()==InputEvent.BUTTON1_MASK){
                    mousePlane.shot();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
                //s = 0;
            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //if(s == 1) {
                    setCursor(blankCursor);
                    mousePlane.moveByMouse(e.getX(), e.getY());
                //}
            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W){
                    keyPlane.setDirection(5);
                } else if(e.getKeyCode() == KeyEvent.VK_S) {
                    keyPlane.setDirection(2);
                } else if(e.getKeyCode() == KeyEvent.VK_D) {
                    keyPlane.setDirection(3);
                } else if(e.getKeyCode() == KeyEvent.VK_A) {
                    keyPlane.setDirection(1);
                } else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    keyPlane.shot();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyPlane.setDirection(0);
            }
        });
    }

    private void initPlane() {
        keyPlane = new YourPlane(100, 450, 3, 3);
        mousePlane = new YourPlane(150, 500, 3, 4);
        vectorEP.add(new EnemyPlane(100, 200, 2, 1, 1));
        vectorEP.add(new EnemyPlane(150, 300, 2, 1, 2));
        vectorEP.add(new EnemyPlane(200, 70, 1, 1, 3));
    }

    @Override
    public void update(Graphics g) {
        if(image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            seconds = image.getGraphics();
        }
        seconds.setColor(getBackground());
        seconds.fillRect(0, 0, getWidth(), getHeight());
        seconds.setColor(getForeground());
        paint(seconds);
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(background, 0, 0, null);
        keyPlane.draw(g);
        mousePlane.draw(g);
        for(EnemyPlane e : vectorEP) {
            e.draw(g);
        }
    }
    //Game loop
    @Override
    public void run() {
        while(true) {
            keyPlane.update();
            mousePlane.update();
            for(EnemyPlane e : vectorEP) {
                e.update();
            }
            repaint();
            try {
                Thread.sleep(17);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

