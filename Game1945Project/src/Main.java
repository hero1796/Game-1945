
public class Main {
    public static void main(String args[]) {
        GameWindow game = new GameWindow();
        Thread thread = new Thread(game);
        thread.start();
    }
}
//bat va cham cua 2 hinh chu nhat dan do YourPlane ban ra va EnemyPlane neu cham thi lam mat di