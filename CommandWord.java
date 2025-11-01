/**
 * Representations for all the valid command words for the game.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 7.0
 */
public enum CommandWord {
    MOVE("go"),
    EXIT("quit"),
    HELP("help"),
    BACK("back"),
    LOOK("look"),
    DETAILS("details"),
    QUESTION_MARK("?"),
    UNKNOWN("unknown"); // Added UNKNOWN
    
    
    private String command;

    CommandWord(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static CommandWord fromString(String command) {
        for (CommandWord c : CommandWord.values()) {
            if (c.getCommand().equalsIgnoreCase(command)) {
                return c;
            }
        }
        return null; // Command not recognized
    }
}