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

        List<Mine> mineList = new ArrayList<>();

        //gör nya mines... när?
        //ge minePosition random coordinates beroende på väggarnas coordinates och byte ut?
        if (true/*???*/){
            mineList.add(new Mine(new MinePosition(ThreadLocalRandom.current().nextInt(60, 80), ThreadLocalRandom.current().nextInt(1, 40))))
            terminal.setCursorPosition(mineList.get(0).getMinePosition().getX(), mineList.get(0).getMinePosition().getY());
            terminal.putCharacter(Mine.MINE_FIG);
            terminal.flush();
        }

        //move all mines in mineList
        for (Mine m : mineList) {
            m.move();
            terminal.setCursorPosition(m.getMinePosition().getX(), m.getMinePosition().getY());
            terminal.putCharacter(Mine.MINE_FIG);
            terminal.setCursorPosition(m.getPrevMinePosition().getX(), m.getPrevMinePosition().getY());
            terminal.putCharacter(' ');
        }

        //om minePosition x=1 -> försvinner ut till vänster
        if (mineList.get(0).getMinePosition().getX() == 1) {
            terminal.setCursorPosition(mineList.get(0).getMinePosition().getX(), mineList.get(0).getMinePosition().getY());
            terminal.putCharacter(' ');
            mineList.remove(0);
        }



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
