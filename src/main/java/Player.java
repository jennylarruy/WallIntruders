import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Player {

    private int x;
    private int y;
    private char symbol;
    private int previousX;
    private  int previousY;

    public Player (int x, int y, char symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
        this.previousX = x;
        this.previousY = y;
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

    public void print(Terminal terminal) throws IOException {

        terminal.setCursorPosition(x, y);
        terminal.putCharacter(symbol);

        terminal.setCursorPosition(previousX, previousY);
        terminal.putCharacter(' ');

    }
}
