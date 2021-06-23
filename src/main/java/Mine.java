import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Mine {

    final static char MINE_FIG = 'o';
    private int x;
    private int y;
    private int prevX;
    private int prevY;

    public Mine(int x, int y) {
        this.x = x;
        this.y = y;
        prevX = 1;
        prevY = 1;
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

    public int getPrevx() {
        return prevX;
    }

    public void setPrevx(int prevx) {
        this.prevX = prevx;
    }

    public int getPrevy() {
        return prevY;
    }

    public void setPrevy(int prevy) {
        this.prevY = prevy;
    }

    public static void addMine(ArrayList<Mine> mineList, int topY, int bottomY) {
        if (mineList.size() < 3) {
            Random rand = new Random();
            int yRand = rand.nextInt(bottomY-topY) + topY;
            mineList.add(new Mine(80, yRand));
        }
    }

    public void drawMine(Terminal terminal) throws IOException {
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(Mine.MINE_FIG);
        terminal.setCursorPosition(prevX, prevY);
        terminal.putCharacter(' ');
        terminal.flush();
    }

    public void moveMine() {
        prevX = x;
        prevY = y;
        x--;
    }

    public static void removeMine(ArrayList<Mine> mineList, Terminal terminal) throws IOException {
        if (mineList.get(0).x < 1) {
            terminal.setCursorPosition(mineList.get(0).x, mineList.get(0).y);
            terminal.putCharacter(' ');
            mineList.remove(mineList.get(0));
        }
    }

    public static boolean hasHitMine(Player player, ArrayList<Mine> mineList) {
        for (Mine mine : mineList) {
            if (player.getX() == mine.x && player.getY() == mine.y) {
                return true;
            }
        }
        return false;
    }
}


