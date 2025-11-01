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

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);
        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

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

        if(command.isUnknown()) {
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
        switch(commandWord) {
            case BACK:
                goBack();
                break;
            case ASSIST:
                printHelp();
                break;
            case MOVE:
                goRoom(command);
                break;
            case LOOK:
                look();
                break;
            case EXIT:
                wantToQuit = quit(command);
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
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            roomHistory.push(currentRoom);  // Save the current room before moving
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
