/**
 * Created by Administrator on 3/8/2016.
 */
public class YourPlaneManager {
    private static YourPlaneManager outInstance;
    private YourPlane planeMoveByKey;
    private YourPlane planeMoveByMouse;
    private YourPlaneManager() {
        planeMoveByKey = new YourPlane(100, 450, 3, 3, 100);
        planeMoveByMouse = new YourPlane(150, 500, 3, 4, 150);
    }
    public static YourPlaneManager getInstance() {
        if(outInstance == null) {
            outInstance = new YourPlaneManager();
        }
        return outInstance;
    }
    public YourPlane getPlaneMoveByKey() {
        return planeMoveByKey;
    }
    public YourPlane getPlaneMoveByMouse() {
        return planeMoveByMouse;
    }
}
