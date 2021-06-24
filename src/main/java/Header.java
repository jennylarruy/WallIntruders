import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;

public class Header {

    private final int x;
    private final int y;
    private final ArrayList<String> headerStrings = new ArrayList<>();

    public Header(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void print(Terminal terminal) throws IOException {

        headerStrings.add("                                       [=====>");
        headerStrings.add("                                       [  (    _____");
        headerStrings.add("                                        \\__\\,-'//   `--._");
        headerStrings.add("                                         [_/~||,-----.\\@_\\\\___");
        headerStrings.add("   _____                                 [_) |||()()()   ~[|||>");
        headerStrings.add("  / ___/ ____   ____ _ _____ ___         [_\\_||`-----'   //");
        headerStrings.add("  \\__ \\ / __ \\ / __ `// ___// _ \\       /  /`-.\\___,--'==(-");
        headerStrings.add(" ___/ // /_/ // /_/ // /__ /  __/      [  (");
        headerStrings.add("/____// .___/ \\__,_/ \\___/ \\___/       [=====>");
        headerStrings.add("    _/_/        __                    __                     ");
        headerStrings.add("   /  _/____   / /_ _____ __  __ ____/ /___   _____ _____    ");
        headerStrings.add("   / / / __ \\ / __// ___// / / // __  // _ \\ / ___// ___/  ");
        headerStrings.add(" _/ / / / / // /_ / /   / /_/ // /_/ //  __// /   (__  )     ");
        headerStrings.add("/___//_/ /_/ \\__//_/    \\__,_/ \\__,_/ \\___//_/   /____/  ");


        int j = 0;
        for (String str : headerStrings) {
            terminal.setCursorPosition(x, y + j);
            for (int i = 0; i < str.length(); i++) {
                terminal.putCharacter(str.charAt(i));
            }
            j++;
        }
    }

    public void remove(Terminal terminal, int k) throws IOException {
        if (k == 40) {
            terminal.setForegroundColor(new TextColor.RGB(0, 0, 0));
            for (int x=1; x<70; x++){
                for (int y=1; y<40; y++){
                    terminal.setCursorPosition(x,y);
                    terminal.putCharacter(' ');
                }
            }
            terminal.flush();
        }
    }
}