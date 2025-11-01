import java.util.Stack;

public class Game {
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> roomHistory;  // Stack to store previous rooms

    public Game() {
        createRooms();
        parser = new Parser();
        roomHistory = new Stack<>();
    }

    private void createRooms() {
        Room outside, theater, pub, lab, office;
    
        // Create rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
    
        // Set exits using Direction enum
        outside.setExit(Direction.EAST, theater);
        outside.setExit(Direction.SOUTH, lab);
        outside.setExit(Direction.WEST, pub);
    
        theater.setExit(Direction.WEST, outside);
        pub.setExit(Direction.EAST, outside);
    
        lab.setExit(Direction.NORTH, outside);
        lab.setExit(Direction.EAST, office);
    
        office.setExit(Direction.WEST, lab);
    
        // Start the game in the "outside" room
        currentRoom = outside;
    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'assist' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
    
        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
    
        // Convert string command to CommandWord enum
        CommandWord commandWord = CommandWord.fromString(command.getCommandWord());
    
        if (commandWord == null) {
            System.out.println("Command not recognized.");
            System.out.println("Your command words are:");
            parser.showCommands();
            return false;
        }
    
        // Switch on the CommandWord enum
        switch (commandWord) {
            case HELP:
                printHelp();  // Help text will be printed
                break;
            case QUESTION_MARK:
                printHelp();  // Same as HELP, '?' will trigger help as well
                break;
            case MOVE:
                goRoom(command);
                break;
            case EXIT:
                wantToQuit = quit(command);
                break;
            case BACK:
                goBack();
                break;
            case LOOK:
                look();
                break;
            case DETAILS:
                CommandWords.commandDetails();
                break;
            default:
                System.out.println("Command not recognized.");
                parser.showCommands();
                break;
        }
        return wantToQuit;
    }

    private void look() {
        System.out.println(currentRoom.getLongDescription());
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }
    
        // Get the direction from the second word in the command
        String directionStr = command.getSecondWord();
        Direction direction = null;
    
        // Convert string direction to Direction enum
        switch (directionStr.toUpperCase()) {
            case "NORTH":
                direction = Direction.NORTH;
                break;
            case "SOUTH":
                direction = Direction.SOUTH;
                break;
            case "EAST":
                direction = Direction.EAST;
                break;
            case "WEST":
                direction = Direction.WEST;
                break;
            default:
                System.out.println("Unknown direction!");
                return;
        }
    
        // Try to move to the next room
        Room nextRoom = currentRoom.getExit(direction);
    
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    private void goBack() {
        if (!roomHistory.isEmpty()) {
            currentRoom = roomHistory.pop();
            System.out.println("You go back to the " + currentRoom.getLongDescription());
        } else {
            System.out.println("You cannot go back. You're at the start!");
        }
    }
}
