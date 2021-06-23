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
            int yRand = rand.nextInt(40 - bottomY) + topY;
            mineList.add(new Mine(80, yRand));

            System.out.println("new mine at x: 80  y: " + yRand);
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
        x++;
    }

    public static void removeMine(ArrayList<Mine> mineList) {
        if (mineList.get(0).x <= 1) {
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

//----MAIN--------------------------------------------------------
/*    ArrayList<Mine> mineList = new ArrayList<>();

    int ticker = 0;
        while (true) {
        ticker++;
        if (ticker % 30 == 0) {
            Wall.addWall(wallList);
            int topY = wallList.get(wallList.size() - 1).getyTop;
            int bottomY = wallList.get(wallList.size() - 1).getyBottom;
            Mine.addMine(mineList, topY, bottomY);     //Jenny
        }

        for (Wall wall : wallList) {
            wall.moveWall();
            wall.drawWall(terminal);
        }
        for (Mine mine : mineList) {    //Jenny
            mine.moveMine();            //Jenny
            mine.drawMine(terminal);    //Jenny
        }                               //Jenny
        if (wallList.size() != 0) {
            Wall.removeWall(wallList);
        }
        if (mineList.size() != 0) {     //Jenny
            Mine.removeMine(addMine();  //Jenny
        }                               //Jenny
        terminal.flush();
        Thread.sleep(100);
    }

 */

