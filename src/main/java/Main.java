import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(new TerminalSize(80,40));
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        Player player = new Player(4, 20, '\u2588');
        terminal.setCursorPosition(player.getX(), player.getY());
        terminal.putCharacter(player.getSymbol());
        terminal.flush();

        ArrayList<Wall> wallList = new ArrayList<>();
        int ticker = 0;

        boolean continueReadingInput = true;

        //Game Loop
        while (continueReadingInput) {
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


            KeyStroke keyStroke;
            int k = 0;

            do {
                k++;

                Thread.sleep(5);
                keyStroke = terminal.pollInput();

            } while (keyStroke == null);

            KeyType type = keyStroke.getKeyType();
            Character c = keyStroke.getCharacter();

            player.move(type);
            player.print(terminal);
            if (Wall.playerHitWall(player, wallList)) {
                System.out.println("HIT");
            }
            terminal.flush();

            //Exit the program
            if (c == Character.valueOf('q')) {
                continueReadingInput = false;
                terminal.close();
                System.out.println("quit");
            }
        }

        }


    }
