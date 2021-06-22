import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Main {

    public static void main(String[] args) throws Exception {

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(new TerminalSize(80,40));
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);


        /*

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
