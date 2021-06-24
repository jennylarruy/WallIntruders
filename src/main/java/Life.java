import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Life {

    final static char LIFE_FIG = '♥';
    private int x;
    private int y;
    private int prevX;
    private int prevY;
    public static int numOfLife;

    public Life(int x, int y) {
        this.x = x;
        this.y = y;
        prevX = 1;
        prevY = 1;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static void addLife(ArrayList<Life> lifeList) {
        if (lifeList.size() < 2) {
            Random rand = new Random();
            int yRand = rand.nextInt(20)+10;
            int xRand = rand.nextInt(10)+90;
            lifeList.add(new Life(xRand, yRand));
        }
    }

    public void drawLife(Terminal terminal) throws IOException {
        terminal.setForegroundColor(new TextColor.RGB(255, 0, 0));
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(LIFE_FIG);
        terminal.setCursorPosition(prevX, prevY);
        terminal.putCharacter(' ');
        terminal.flush();
    }

    public void moveLife() {
        prevX = x;
        prevY = y;
        x--;
    }

    public static void removeLife(ArrayList<Life> lifeList, Terminal terminal) throws IOException {
        if (lifeList.get(0).x < 1) {
            terminal.setCursorPosition(lifeList.get(0).x, lifeList.get(0).y);
            terminal.putCharacter(' ');
            lifeList.remove(lifeList.get(0));
        }
    }

    public static boolean hasCollectedLife(Player player, ArrayList<Life> lifeList) {
        for (Life life : lifeList) {
            if (player.getX() == life.x && player.getY() == life.y) {
                lifeList.remove(life);
                addToLife();
                return true;
            }
        }
        return false;
    }

    public static void addToLife() {

        if (numOfLife < 3) {
            numOfLife++;
        }
    }
}



