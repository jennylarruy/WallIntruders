
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
    private String direction;
    public static int WIDTH = 3;
    public static int wallScore = 0;

    public Wall (int yTop, int yBottom, String direction) {
        this.yTop = yTop;
        this.yBottom = yBottom;
        this.xLeft = 80;
        this.xRight = xLeft + WIDTH;
        this.direction = direction;
    }

    public static void addWall(ArrayList<Wall> wallList) {
        if (wallList.size() < 4) {
            Random rand = new Random();
            int yTopNew;
            int yBottomNew;
            String direction;
            int dir = rand.nextInt(6);
            if (dir == 0) {
                direction = "down";
                yTopNew = -rand.nextInt(20) + 10; // size between 10 and 30
                yBottomNew = 0;
            } else if (dir == 1) {
                direction = "up";
                yTopNew = 40;
                yBottomNew = 40 + rand.nextInt(20) + 10; // size between 10 and 30
            } else {
                direction = "left";
                yTopNew = rand.nextInt(20) + 3; // top corner between 3 and 23
                int gap = rand.nextInt(8) + 6; // width between 6 and 14
                yBottomNew = yTopNew + gap; // bottom corner between 9 and 37
            }
            wallList.add(new Wall(yTopNew, yBottomNew, direction));
        }
    }

    public void printPosition() {
        System.out.println("ytop: "+yTop+" ybottom "+yBottom+" xleft "+xLeft+" xright "+xRight);
    }

    public void drawWall(Terminal terminal) throws IOException {
        if (this.direction.equalsIgnoreCase("left")) {
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
        } else if (this.direction.equalsIgnoreCase("up")) {
            for (int row = -40; row <= 40; row++) {
                if (row >= this.getyTop() && row <= this.getyBottom()) {
                    for (int col = this.getxLeft(); col <= Math.min(80, this.getxRight()); col++) {
                        terminal.setCursorPosition(col, row);
                        terminal.putCharacter('\u2588');
                        terminal.setCursorPosition(col + 1, row + 1);
                        terminal.putCharacter(' ');
                    }
                }
            }
        } else { // direction down
            for (int row = 80; row >= 0; row--) {
                if (row >= this.getyTop() && row <= this.getyBottom()) {
                    for (int col = this.getxLeft(); col <= Math.min(80, this.getxRight()); col++) {
                        terminal.setCursorPosition(col, row);
                        terminal.putCharacter('\u2588');
                        terminal.setCursorPosition(col + 1, row - 1);
                        terminal.putCharacter(' ');
                    }
                }
            }
        }
        terminal.flush();
    }

    public void moveWall() {
        this.xRight -= 1;
        this.xLeft -= 1;
        if (this.direction.equalsIgnoreCase("up") && this.xLeft < 60) {
            this.yBottom -= 1;
            this.yTop -= 1;
        } else if (this.direction.equalsIgnoreCase("down") && this.xLeft < 60) {
            this.yBottom += 1;
            this.yTop += 1;
        }
    }

    public static void removeWall(ArrayList<Wall> wallList) {
        Wall wallOnTheLeft =  wallList.get(0);
        if (wallOnTheLeft.xRight < 0) {
            wallList.remove(wallOnTheLeft);
            wallScore++;
        }
    }

    public static boolean playerHitWall(Player player, ArrayList<Wall> wallList) {
        for (Wall wall : wallList) {
            if (wall.direction.equalsIgnoreCase("left")) {
                if (player.getX() == wall.xLeft && (player.getY() < wall.yTop || player.getY() > wall.yBottom)) {
                    return true;
                }
            } else {
                if (player.getX() == wall.xLeft && (player.getY() > wall.yTop && player.getY() < wall.yBottom)) {
                    return true;
                }
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