import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Scoreboard {

    public Scoreboard(int x, int y, int score, int lives, Terminal terminal) throws IOException {
        TextGraphics tg = terminal.newTextGraphics();
        tg.setBackgroundColor(TextColor.ANSI.WHITE);
        tg.setForegroundColor(TextColor.ANSI.BLACK);
        tg.putString(x,y,"LIVES: " + lives);
        tg.putString(x,y + 1,"SCORE: " + score);
    }

    // Scoreboard scoreboard = new Scoreboard(2,2, Wall.wallScore, player.lifeList.size(), terminal);
    //in main
}
