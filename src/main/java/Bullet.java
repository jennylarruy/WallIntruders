import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;

public class Bullet {
    private int x;
    private int xPrev;
    private int y;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        this.xPrev = x - 1;
    }

    public static void move(ArrayList<Bullet> bullets) {
        for (Bullet bullet : bullets) {
            bullet.xPrev = bullet.x;
            bullet.x += 1;
        }
        try {
            if (bullets.get(0).x > 80) {
                bullets.remove(0);
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    public static void add(Player player, ArrayList<Bullet> bullets) {
        if (bullets.size() < 5) {
            bullets.add(new Bullet(player.getX(), player.getY()));
        }
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

    public boolean hasShotSomething(Terminal terminal, ArrayList<Mine> mineList, ArrayList<Coin> coinList, ArrayList<Life> lifeList, ArrayList<Wall> wallList) throws IOException {
        for (Mine mine : mineList) {
            if (x == mine.getX() && y == mine.getY()) {
                terminal.setCursorPosition(x, y);
                terminal.putCharacter(' ');
                terminal.flush();
                mineList.remove(mine);
                return true;
            }
        }
        for (Coin coin : coinList) {
            if (x == coin.getX() && y == coin.getY()) {
                terminal.setCursorPosition(x, y);
                terminal.putCharacter(' ');
                terminal.flush();
                coinList.remove(coin);
                return true;
            }
        }
        for (Life life : lifeList) {
            if (x == life.getX() && y == life.getY()) {
                terminal.setCursorPosition(x, y);
                terminal.putCharacter(' ');
                terminal.flush();
                lifeList.add(life);
                return true;
            }
        }
        for (Wall wall : wallList) {
            if (wall.getDirection().equalsIgnoreCase("left")) {
                if (x >= wall.getxLeft() - 1 && (y <= wall.getyTop() || y >= wall.getyBottom())) {
                    terminal.setCursorPosition(x, y);
                    terminal.putCharacter(' ');
                    terminal.flush();
                    return true;
                }
            }
            else {
                if (x >= wall.getxLeft() - 1 && (y >= wall.getyTop() && y <= wall.getyBottom())) {
                    terminal.setCursorPosition(x, y);
                    terminal.putCharacter(' ');
                    terminal.flush();
                    return true;
                }
            }
        }
        return false;
    }
}
