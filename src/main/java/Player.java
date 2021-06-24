import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Player {

    private int x;
    private int y;
    private final char PLAYER_FIG;
    private int previousX;
    private  int previousY;

    public Player (int x, int y, char symbol) {
        this.x = x;
        this.y = y;
        this.PLAYER_FIG = symbol;
        this.previousX = x;
        this.previousY = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getPLAYER_FIG() {
        return PLAYER_FIG;
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
        terminal.setForegroundColor(new TextColor.RGB(0, 0, 250));
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(PLAYER_FIG);

        terminal.setCursorPosition(previousX, previousY);
        terminal.putCharacter(' ');
    }

    public boolean hitBoundary() {
        for(int i = 0; i <= 80; i++) {
            if(x == -1 || x == 81 || y == - 1 || y == 41) {
                return true;
            }
        }
        return false;
    }
}
