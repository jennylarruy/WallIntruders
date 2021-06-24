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

    public static boolean hasShotSomething(Terminal terminal, ArrayList<Bullet> bulletList, ArrayList<Mine> mineList, ArrayList<Coin> coinList, ArrayList<Life> lifeList) throws IOException {
        for (Bullet bullet : bulletList) {
            for (Mine mine : mineList) {
                if (bullet.x == mine.getX() && bullet.y == mine.getY()) {
                    terminal.setCursorPosition(bullet.x, bullet.y);
                    terminal.putCharacter(' ');
                    terminal.flush();
                    mineList.remove(mine);
                    bulletList.remove(bullet);
                    return true;
                }
            }
            for (Coin coin : coinList) {
                if (bullet.x == coin.getX() && bullet.y == coin.getY()) {
                    terminal.setCursorPosition(bullet.x, bullet.y);
                    terminal.putCharacter(' ');
                    terminal.flush();
                    coinList.remove(coin);
                    bulletList.remove(bullet);
                    return true;
                }
            }
            for (Life life : lifeList) {
                if (bullet.x == life.getX() && bullet.y == life.getY()) {
                    terminal.setCursorPosition(bullet.x, bullet.y);
                    terminal.putCharacter(' ');
                    terminal.flush();
                    lifeList.remove(life);
                    bulletList.remove(bullet);
                    return true;
                }
            }
        }
        return false;
    }
}
