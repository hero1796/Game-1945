import java.util.Vector;

/**
 * Created by Administrator on 3/8/2016.
 */
public class EnemyPlaneManage {
    private static EnemyPlaneManage ourInstance = new EnemyPlaneManage();//tao ra truoc khi goi getIntance
    private Vector<EnemyPlane> vectorEP;
    private EnemyPlaneManage() {
        vectorEP = new Vector<EnemyPlane>();
    }

    public static EnemyPlaneManage getInstance() {
        return ourInstance;
    }

    public Vector<EnemyPlane> getVectorEP() {
        return vectorEP;
    }
}
