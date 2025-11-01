import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 7.2
 */

public class CommandWords
{
    // A mapping between a command string and the CommandWord
    // associated with it.
    private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();
        validCommands.put("move", CommandWord.MOVE);
        validCommands.put("assist", CommandWord.ASSIST);
        validCommands.put("?", CommandWord.ASSIST);
        validCommands.put("exit", CommandWord.EXIT);
    }

    /**
     * Find the CommandWord associated with a command string.
     * @param commandWord The word to look up (as a string).
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll() 
    {
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
    
        public static void commandDetails() {
        System.out.println("Here are the details about each command:");
        System.out.println("MOVE - Move in a specified direction (e.g., 'move north').");
        System.out.println("EXIT - Exit the game.");
        System.out.println("ASSIST - Show a list of commands and a brief help message.");
        System.out.println("BACK - Go back to the previous room.");
        System.out.println("LOOK - Look around the current room and get a description.");
        System.out.println("DETAILS - Show detailed information about each command.");
    }
}

