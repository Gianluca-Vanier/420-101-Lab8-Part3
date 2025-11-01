/**
 * Representations for all the valid command words for the game.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 7.0
 */
public enum CommandWord {
    MOVE, EXIT, ASSIST, BACK, LOOK, DETAILS, UNKNOWN;

    // Converts a string command to CommandWord enum
    public static CommandWord fromString(String command) {
        try {
            return CommandWord.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;  // Return null if the command doesn't match an enum value
        }
    }
}