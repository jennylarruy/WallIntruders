import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Scoreboard {
    private int x;
    private int y;
    Terminal terminal;

    public Scoreboard(int x, int y, Terminal terminal) throws IOException {

        this.x = x;
        this.y = y;
        this.terminal = terminal;

    }

    public void print(int score, int lives) throws IOException {
        TextGraphics tg = terminal.newTextGraphics();
        tg.setBackgroundColor(TextColor.ANSI.WHITE);
        tg.setForegroundColor(TextColor.ANSI.BLACK);
        tg.putString(x,y,"LIVES: " + lives);
        tg.putString(x,y + 1,"SCORE: " + score);
    }
}
