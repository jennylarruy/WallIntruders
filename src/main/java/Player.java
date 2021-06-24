import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;

public class Player {

    private int x;
    private int y;
    private char symbol;
    private int previousX;
    private  int previousY;
    public ArrayList<Integer> lifeList = new ArrayList<>();

    public Player (int x, int y, char symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
        this.previousX = x;
        this.previousY = y;

        for (int i = 0; i < 3; i++) {
            lifeList.add(1);
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

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getPreviousX() {
        return previousX;
    }

    public void setPreviousX(int previousX) {
        this.previousX = previousX;
    }

    public int getPreviousY() {
        return previousY;
    }

    public void setPreviousY(int previousY) {
        this.previousY = previousY;
    }

    public void move(KeyType keyType){
        switch (keyType) {
            case ArrowUp : {
                previousX = x;
                previousY = y;
                y -= 1;
            } break;
            case ArrowDown : {
                previousX = x;
                previousY = y;
                y += 1;
            } break;
            case ArrowRight : {
                previousX = x;
                previousY = y;
                x += 1;
            } break;
            case ArrowLeft : {
                previousX = x;
                previousY = y;
                x -= 1;
            } break;
        }
    }

    public boolean print(Terminal terminal) throws IOException, InterruptedException {
        terminal.setForegroundColor(new TextColor.RGB(0, 0, 250));
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(symbol);

        terminal.setCursorPosition(previousX, previousY);
        terminal.putCharacter(' ');
        return true;
    }

    public boolean hitBoundary() {
        for(int i = 0; i <= 80; i++) {
            if(x == -1 || x == 81 || y == - 1 || y == 41) {
                return true;
            }
        }
        return false;
    }

    public void removeLife() {
       lifeList.remove(0);
    }

    public void addLife() {
        lifeList.add(1);
    }
}
