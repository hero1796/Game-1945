
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class GameWindow extends Frame implements Runnable {
    Graphics seconds;
    Image image;
    Cursor blankCursor = null;

    BufferedImage background;
    Vector<Bird> vectorBird = new Vector<Bird>();
    Vector<Island> vectorIsland = new Vector<Island>();
    Gift gift;
    HealthBar planeKeyHealthBar, getPlaneMouseHealthBar;
    public static int check = 0;

    public GameWindow(){
        blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().createImage("blank.png"), new Point(0, 0)
                , "blankCursor");

        this.setTitle("Ban may bay");
        this.setSize(400,640);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        init();

        try{
            background = ImageIO.read(new File("Resources/Background.png"));
        } catch(IOException e){
            e.printStackTrace();
        }

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getModifiers()==InputEvent.BUTTON1_MASK){
                    YourPlaneManager.getInstance().getPlaneMoveByMouse().shot();
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

            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                    setCursor(blankCursor);
                    YourPlaneManager.getInstance().getPlaneMoveByMouse().moveByMouse(e.getX(), e.getY());
            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W){
                    YourPlaneManager.getInstance().getPlaneMoveByKey().setDirection(5);
                } else if(e.getKeyCode() == KeyEvent.VK_S) {
                    YourPlaneManager.getInstance().getPlaneMoveByKey().setDirection(2);
                } else if(e.getKeyCode() == KeyEvent.VK_D) {
                    YourPlaneManager.getInstance().getPlaneMoveByKey().setDirection(3);
                } else if(e.getKeyCode() == KeyEvent.VK_A) {
                    YourPlaneManager.getInstance().getPlaneMoveByKey().setDirection(1);
                } else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    YourPlaneManager.getInstance().getPlaneMoveByKey().shot();
                } else if(e.getKeyCode() == KeyEvent.VK_Q) {
                    YourPlaneManager.getInstance().getPlaneMoveByKey().notifyObserver();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                YourPlaneManager.getInstance().getPlaneMoveByKey().setDirection(0);
            }
        });
    }

    private void init() {
        EnemyPlaneManage.getInstance().getVectorEP().add(new EnemyPlane(100, 200, 3, 1, 1,1));
        EnemyPlaneManage.getInstance().getVectorEP().add(new EnemyPlane(150, 300, 3, 1, 2,1));
        EnemyPlaneManage.getInstance().getVectorEP().add(new EnemyPlane(200, 70, 3, 1, 3,1));

        EnemyPlaneManage.getInstance().getVectorEP().add(new EnemyPlane(0,30 , 3, 2, 1,2));
        EnemyPlaneManage.getInstance().getVectorEP().add(new EnemyPlane(30,70 , 3, 2, 1,2));
        EnemyPlaneManage.getInstance().getVectorEP().add(new EnemyPlane(60,110 , 3, 2, 1,2));
        EnemyPlaneManage.getInstance().getVectorEP().add(new EnemyPlane(90,150 , 3, 2, 1,2));
        EnemyPlaneManage.getInstance().getVectorEP().add(new EnemyPlane(120,190 , 3, 2, 1,2));

        vectorBird.add(new Bird(20,200,1));
        vectorBird.add(new Bird(270,200,3));

        vectorIsland.add(new Island(200,240,1));
        vectorIsland.add(new Island(100,320,2));

        gift = new Gift(100, 0, 1);

        planeKeyHealthBar = new HealthBar(0, 560, YourPlaneManager.getInstance().getPlaneMoveByKey().getHealth(), Color.red, 1);
        getPlaneMouseHealthBar = new HealthBar(0, 600, YourPlaneManager.getInstance().getPlaneMoveByMouse().getHealth(), Color.green, 2);

        for(EnemyPlane e : EnemyPlaneManage.getInstance().getVectorEP()) {
            YourPlaneManager.getInstance().getPlaneMoveByKey().addObserver(e);
        }
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

        for(Island i : vectorIsland) {
            i.draw(g);
        }

        YourPlaneManager.getInstance().getPlaneMoveByKey().draw(g);
        YourPlaneManager.getInstance().getPlaneMoveByMouse().draw(g);

        planeKeyHealthBar.draw(g);
        getPlaneMouseHealthBar.draw(g);

        for(EnemyPlane e : EnemyPlaneManage.getInstance().getVectorEP()) {
            e.draw(g);
        }

        for(Bird b : vectorBird) {
            b.draw(g);
        }

        gift.draw(g);
    }

    @Override
    public void run() {
        while(true) {
            for(Island i : vectorIsland) {
                i.update();
            }

            YourPlaneManager.getInstance().getPlaneMoveByKey().update();
            YourPlaneManager.getInstance().getPlaneMoveByMouse().update();

            planeKeyHealthBar.update();
            getPlaneMouseHealthBar.update();

            for(EnemyPlane e : EnemyPlaneManage.getInstance().getVectorEP()) {
                e.update();
            }

            if(GameWindow.check == 1) {
                EnemyPlaneManage.getInstance().getVectorEP().clear();
            }

            for(Bird b : vectorBird) {
                b.update();
            }
            gift.update();
            repaint();
            try {
                Thread.sleep(17);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

