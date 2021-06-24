import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Coin {

    final static char COIN_FIG = '$';
    private int x;
    private int y;
    private int prevX;
    private int prevY;

    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
        prevX = 1;
        prevY = 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static void addCoin(ArrayList<Coin> coinList) {
        if (coinList.size() < 2) {
            Random rand = new Random();
            int yRand = rand.nextInt(20)+10;
            int xRand = rand.nextInt(10)+90;
            coinList.add(new Coin(xRand, yRand));
        }
    }

    public void drawCoin(Terminal terminal) throws IOException {
        terminal.setForegroundColor(new TextColor.RGB(255, 228, 81));
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(COIN_FIG);
        terminal.setCursorPosition(prevX, prevY);
        terminal.putCharacter(' ');
        terminal.flush();
    }

    public void moveCoin() {
        prevX = x;
        prevY = y;
        x--;
    }

    public static void removeCoin(ArrayList<Coin> coinList, Terminal terminal) throws IOException {
        if (coinList.get(0).x < 1) {
            terminal.setCursorPosition(coinList.get(0).x, coinList.get(0).y);
            terminal.putCharacter(' ');
            coinList.remove(coinList.get(0));
        }
    }

    public static boolean hasCollectedCoin(Player player, ArrayList<Coin> coinList) {
        for (Coin coin : coinList) {
            if (player.getX() == coin.x && player.getY() == coin.y) {
                coinList.remove(coin);
                return true;
            }
        }
        return false;
    }
}



