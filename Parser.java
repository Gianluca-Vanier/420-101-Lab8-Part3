import java.util.Scanner;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two-word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 7.2
 */
import java.util.Scanner;

public class Parser {
    private Scanner reader;

    public Parser() {
        reader = new Scanner(System.in);
    }

    public Command getCommand() {
        System.out.print("> ");
        String input = reader.nextLine().trim().toLowerCase();
        String[] words = input.split(" ");

        if (words.length == 0) {
            return new Command(null, null);
        } else if (words.length == 1) {
            return new Command(words[0], null);
        } else {
            return new Command(words[0], words[1]);
        }
    }

    public void showCommands() {
        System.out.println("Your command words are: move, exit, assist, back, look, details");
    }
}
