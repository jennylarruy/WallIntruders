import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

        boolean continueReadingInput = true;
        int k = 0;
        //Game Loop
        while (continueReadingInput) {
            k++;
            if (k % 30 == 0) {
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
            Thread.sleep(50);

            KeyStroke keyStroke = terminal.pollInput();

            if (keyStroke != null) {
                KeyType type = keyStroke.getKeyType();
                Character c = keyStroke.getCharacter();
                if (c == Character.valueOf('q')) {
                    continueReadingInput = false;
                    terminal.close();
                    System.out.println("quit");
                }
                player.move(type);
                player.print(terminal);
                terminal.flush();
            }

            if (Wall.playerHitWall(player, wallList)) {
                System.out.println("HIT");
                String str = "YOU HIT A WALL!";
                terminal.setCursorPosition(10, 10);
                for (int i = 0; i < str.length(); i++) {
                    terminal.putCharacter(str.charAt(i));
                }
                terminal.flush();
                Thread.sleep(2000);
                continueReadingInput = false;
                terminal.close();

            }

        }

        }


    }
