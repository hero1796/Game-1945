import java.awt.*;

/**
 * Created by Administrator on 3/11/2016.
 */
public class HealthBar extends GameObject {//Thanh mau'
    private int leng;
    Color color;
    private int barType;

    public int getLeng() {
        return leng;
    }
    public void setLeng(int leng) {
        this.leng = leng;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    private HealthBar() {

    }
    public HealthBar(int x, int y, int leng, Color color, int barType) {
        setPositionX(x);
        setPositionY(y);
        this.leng = leng;
        this.color = color;
        this.barType = barType;
    }

    @Override
    public void update() {
        if(barType == 1) {
            this.leng = YourPlaneManager.getInstance().getPlaneMoveByKey().getHealth();
        } else if(barType == 2) {
            this.leng = YourPlaneManager.getInstance().getPlaneMoveByMouse().getHealth();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect((int)getPositionX(), (int)getPositionY(), this.leng, 40);
        g.setColor(this.color);
        g.fillRect((int)getPositionX(), (int)getPositionY(), this.leng, 40);
    }
}
