import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 7.2
 */

import java.util.HashMap;

public class Room {
    private String description;
    private HashMap<Direction, Room> exits;

    // Constructor for room description
    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
    }

    // Set exit in a given direction
    public void setExit(Direction direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    // Get the room in a given direction
    public Room getExit(Direction direction) {
        return exits.get(direction);
    }

    // Get the long description of the room
    public String getLongDescription() {
        return "You are " + description;
    }
}

