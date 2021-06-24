import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;

public class Bullet {
    private int x;
    private int xPrev;
    private int y;

    public Bullet (int x, int y) {
        this.x = x;
        this.y = y;
        this.xPrev = x - 1;
    }

    public static void move(ArrayList<Bullet> bullets) {
        for (Bullet bullet : bullets) {
            bullet.xPrev = bullet.x;
            bullet.x += 1;
        }
    }

    public static void add(Player player, ArrayList<Bullet> bullets) {
        bullets.add(new Bullet(player.getX(), player.getY()));
    }

    public static void draw(ArrayList<Bullet> bullets, Terminal terminal) throws IOException {
        for (Bullet bullet : bullets) {
            terminal.setCursorPosition(bullet.x, bullet.y);
            terminal.putCharacter('.');
            terminal.setCursorPosition(bullet.xPrev, bullet.y);
            terminal.putCharacter(' ');
            terminal.flush();
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
