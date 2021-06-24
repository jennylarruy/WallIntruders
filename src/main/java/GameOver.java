import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;

public class GameOver {

    private int x;
    private int y;
    private ArrayList<String> gameOverStrings = new ArrayList<>();

    public GameOver(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void print(Terminal terminal) throws IOException {

        gameOverStrings.add("  _______      ___      .___  ___.  _______  ");
        gameOverStrings.add(" /  _____|    /   \\     |   \\/   | |   ____|  ");
        gameOverStrings.add("|  |  __     /  ^  \\    |  \\  /  | |  |__     ");
        gameOverStrings.add("|  | |_ |   /  /_\\  \\   |  |\\/|  | |   __|    ");
        gameOverStrings.add("|  |__| |  /  _____  \\  |  |  |  | |  |____   ");
        gameOverStrings.add(" \\______| /__/     \\__\\ |__|  |__| |_______|  ");
        gameOverStrings.add("");
        gameOverStrings.add("  ______   ____    ____  _______ .______      ");
        gameOverStrings.add(" /  __  \\  \\   \\  /   / |   ____||   _  \\     ");
        gameOverStrings.add("|  |  |  |  \\   \\/   /  |  |__   |  |_)  |    ");
        gameOverStrings.add("|  |  |  |   \\      /   |   __|  |      /     ");
        gameOverStrings.add("|  `--'  |    \\    /    |  |____ |  |\\  \\----.");
        gameOverStrings.add(" \\______/      \\__/     |_______|| _| `._____|");

        int j = 0;
        for (String str : gameOverStrings) {
            terminal.setCursorPosition(x, y + j);
            for (int i = 0; i < str.length(); i++) {
                terminal.putCharacter(str.charAt(i));
            }
            j++;
        }
    }
}
