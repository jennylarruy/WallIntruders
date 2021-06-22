import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(new TerminalSize(80,40));
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        ArrayList<Wall> wallList = new ArrayList<>();
        int ticker = 0;
        while (true) {
            ticker++;
            if (ticker % 30 == 0) {
                Wall.addWall(wallList);
            }
            for (Wall wall : wallList) {
                wall.moveWall();
                wall.drawWall(terminal);
            }
            if (wallList.size() != 0) {
                Wall.removeWall(wallList);
            }
            terminal.flush();
            Thread.sleep(100);


        }
    }
}
