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
        validCommands.put("go", CommandWord.MOVE);
        validCommands.put("quit", CommandWord.EXIT);
        validCommands.put("help", CommandWord.HELP);
        validCommands.put("?", CommandWord.HELP);  // '?' should map to HELP
        validCommands.put("back", CommandWord.BACK);
        validCommands.put("look", CommandWord.LOOK);
        validCommands.put("details", CommandWord.DETAILS);
    }

    /**
     * Find the CommandWord associated with a command string.
     * @param commandWord The word to look up (as a string).
     * @return The CommandWord corresponding to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if (command != null) {
            return command;
        } else {
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
        for (String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }

    public static void commandDetails() {
        System.out.println("Here are the details about each command:");
        System.out.println("GO - Move in a specified direction (e.g., 'go north').");
        System.out.println("QUIT - Exit the game.");
        System.out.println("HELP - Display a help message.");
        System.out.println("BACK - Go back to the previous room.");
        System.out.println("LOOK - Look around the current room and get a description.");
        System.out.println("DETAILS - Show detailed information about each command.");
        System.out.println("?");
    }
}

