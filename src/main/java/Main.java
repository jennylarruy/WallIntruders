import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) throws Exception {

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(new TerminalSize(80, 40));
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

/*
        player= new player
        wall = new wall

        while (true) {

            walls.move
            walls.create

            mine.get(i).move();
            mine.create

            check if key pressed => move player

            check collision
        }


         - skapa spelare
         - skapa först vägg längst till höger

        LOOP:
        - skapa vägg regelbundent, slumpmässigt placering
        - flytta väggar, loopa genom lista med väggobjekt (typ max 3 samtidigt?)

        Flytta spelare vid knapptryckning
        - check kollision med vägg

         */


    }


}
