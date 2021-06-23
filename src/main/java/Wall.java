import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Wall {
    private int xLeft;
    private int xRight;
    private int yTop;
    private int yBottom;
    public static int WIDTH = 5;


    public Wall (int yTop, int yBottom) {
        this.yTop = yTop;
        this.yBottom = yBottom;
        this.xLeft = 80;
        this.xRight = 80 + WIDTH;
    }

    public static void addWall(ArrayList<Wall> wallList) {
        if (wallList.size() < 3) {
            Random rand = new Random();
            int yTopNew = rand.nextInt(15) + 5; // top corner between 5 and 20
            int gap = rand.nextInt(5) + 10; // width between 10 and 15
            int yBottomNew = yTopNew + gap; // bottom corner between 15 (5 + 10) and 35 (20 + 15)
            wallList.add(new Wall(yTopNew, yBottomNew));
//            System.out.println("new wall at ytop "+yTopNew+" ybottom "+yBottomNew);
        }
    }

    public void printPosition() {
        System.out.println("ytop: "+yTop+" ybottom "+yBottom+" xleft "+xLeft+" xright "+xRight);
    }

    public void drawWall(Terminal terminal) throws IOException {
        for (int row = 0; row <= 40; row++) {
            if (row < this.getyTop() || row > this.getyBottom()) {
                for (int col = this.getxLeft(); col < Math.min(80, this.getxRight()); col++) {
                    terminal.setCursorPosition(col, row);
                    terminal.putCharacter('\u2588');
                    terminal.setCursorPosition(col + 1, row);
                    terminal.putCharacter(' ');
                }
            }
        }
    }

    public void moveWall() {
        this.xRight -= 1;
        this.xLeft -= 1;
    }

    public static void removeWall(ArrayList<Wall> wallList) {
        Wall wallOnTheLeft =  wallList.get(0);
        if (wallOnTheLeft.xRight < 1) {
            wallList.remove(wallOnTheLeft);
        }
    }

    public static boolean playerHitWall(Player player, ArrayList<Wall> wallList) {
        for (Wall wall : wallList) {
            if (player.getX() == wall.xLeft && (player.getY() < wall.yTop || player.getY() > wall.yBottom)) {
                return true;
            }
        }
        return false;
    }

    public int getxLeft() {
        return xLeft;
    }

    public void setxLeft(int xLeft) {
        this.xLeft = xLeft;
    }

    public int getxRight() {
        return xRight;
    }

    public void setxRight(int xRight) {
        this.xRight = xRight;
    }

    public int getyTop() {
        return yTop;
    }

    public void setyTop(int yTop) {
        this.yTop = yTop;
    }

    public int getyBottom() {
        return yBottom;
    }

    public void setyBottom(int yBottom) {
        this.yBottom = yBottom;
    }
}
