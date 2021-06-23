import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
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

        terminal.setForegroundColor(new TextColor.RGB(255, 0, 20));
        Player player = new Player(4, 20, '\u2588');
        terminal.setCursorPosition(player.getX(), player.getY());
        terminal.putCharacter(player.getSymbol());
        terminal.flush();

//        Screen screen = new TerminalScreen(terminal);


        ArrayList<Wall> wallList = new ArrayList<>();
        ArrayList<Mine> mineList = new ArrayList<>();

        boolean continueReadingInput = true;
        int k = 0;
        //Game Loop
        while (continueReadingInput) {
            k++;
            if (k % 30 == 0) {
                Wall.addWall(wallList);
                int topY = wallList.get(wallList.size() - 1).getyTop();
                int bottomY = wallList.get(wallList.size() - 1).getyBottom();
                Mine.addMine(mineList, topY, bottomY);
            }
            for (Wall wall : wallList) {
                wall.moveWall();
                terminal.setForegroundColor(new TextColor.RGB(10, 255, 20));
                wall.drawWall(terminal);
            }
            if (wallList.size() != 0) {
                Wall.removeWall(wallList);
            }
            for (Mine mine : mineList) {
                mine.moveMine();
                mine.drawMine(terminal);
            }
            if (mineList.size() != 0) {
                Mine.removeMine(mineList, terminal);
            }
            terminal.flush();
            Thread.sleep(50 - Wall.wallScore/2);

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
                terminal.setForegroundColor(new TextColor.RGB(255, 0, 20));
                player.print(terminal);
                terminal.flush();
            }

            if (Wall.playerHitWall(player, wallList)) {
                System.out.println("HIT");
                String str = "YOU HIT A WALL!";
                terminal.setForegroundColor(new TextColor.RGB(0, 0, 250));
                terminal.setCursorPosition(20, 10);
                for (int i = 0; i < str.length(); i++) {
                    terminal.putCharacter(str.charAt(i));
                }
                terminal.flush();
                Thread.sleep(2000);
                continueReadingInput = false;
                terminal.close();

            }
            if (Mine.hasHitMine(player, mineList)) {
                System.out.println("HIT");
                String str = "YOU HIT A MINE!";
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
