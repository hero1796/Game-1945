
public class Main {
    public static void main(String args[]) {
        GameWindow game = new GameWindow();
        Thread thread = new Thread(game);
        thread.start();
    }
}

/*- Thiết kế class PlaneAbstract
- Tạo viên đạn sao cho phù hợp vs cả địch cả ta.
        - Thiết kế thêm 2 con EnemyPlane nữa
        + 1 con thì di chuyển theo hình Elip, bắn đạn đôi
        + 1 con di chuyển như bình thường, nhưng bắn đạn chéo tẽ làm 3.
        - Giảm kích thước con EnemyPlane xuống.*/