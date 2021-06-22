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
            if (ticker % 20 == 0) {
                Wall.addWall(wallList);
            }
            for (Wall wall : wallList) {
                wall.moveWall();
                wall.printPosition();
                for (int row = 0; row <= 40; row++) {
                    if (row < wall.getyTop() && row > wall.getyBottom()) {
                        for (int col = wall.getxLeft(); col < 80; col++) { //Math.min(80, wall.getxRight())
                            System.out.println("hey");
                            terminal.setCursorPosition(col, row);
                            terminal.putCharacter('o');
                        }
                    }
                }

            }
//            Wall.removeWall(wallList);
            terminal.flush();
            Thread.sleep(100);


        }
    }
}
