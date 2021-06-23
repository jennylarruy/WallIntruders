import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(new TerminalSize(80, 40));
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        terminal.setForegroundColor(new TextColor.RGB(200, 200, 100));


        Scoreboard scoreboard = new Scoreboard(2,2, terminal);
        Header header = new Header(5, 5);
        header.print(terminal);


        terminal.setForegroundColor(new TextColor.RGB(255, 0, 20));
        Player player = new Player(4, 20, '\u2588');
        terminal.setCursorPosition(player.getX(), player.getY());
        terminal.putCharacter(player.getSymbol());
        terminal.flush();

        ArrayList<Wall> wallList = new ArrayList<>();
        ArrayList<Mine> mineList = new ArrayList<>();
        ArrayList<Coin> coinList = new ArrayList<>();

        boolean continueReadingInput = true;
        int k = 0;
        //Game Loop
        while (continueReadingInput) {
            k++;
            terminal.setForegroundColor(new TextColor.RGB(0, 0, 250));
            terminal.setCursorPosition(2, 10);
            terminal.putCharacter(String.valueOf(Wall.wallScore).charAt(0));
            if (Wall.wallScore > 9) {
                terminal.putCharacter(String.valueOf(Wall.wallScore).charAt(1));
            }
            if (k % 30 == 0) {
                Wall.addWall(wallList);
                int topY = wallList.get(wallList.size() - 1).getyTop();
                int bottomY = wallList.get(wallList.size() - 1).getyBottom();
                Mine.addMine(mineList, topY, bottomY);
                Coin.addCoin(coinList);

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
                terminal.setForegroundColor(new TextColor.RGB(255, 50, 50));
                mine.drawMine(terminal);
            }
            if (mineList.size() != 0) {
                Mine.removeMine(mineList, terminal);
            }
            for (Coin coin : coinList) {
                coin.moveCoin();
                terminal.setForegroundColor(new TextColor.RGB(255, 228, 81));
                coin.drawCoin(terminal);
            }
            if (coinList.size() != 0) {
                Coin.removeCoin(coinList, terminal);
            }

            scoreboard.print(Wall.wallScore, player.lifeList.size());
            terminal.flush();
            Thread.sleep(50 - Wall.wallScore / 2);

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
                terminal.setForegroundColor(new TextColor.RGB(0, 0, 250));
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
            if (Coin.hasCollectedCoin(player, coinList)) {
                terminal.setForegroundColor(new TextColor.RGB(255, 228, 81));
                terminal.setCursorPosition(player.getX(), player.getY());
                terminal.putCharacter(player.getSymbol());
                System.out.println("COIN");
                String str = "1$!";
                terminal.setCursorPosition(10, 10);
                for (int i = 0; i < str.length(); i++) {
                    terminal.putCharacter(str.charAt(i));
                }
                terminal.flush();
                Wall.wallScore++; //annan score?

            }

        }

    }


}
