package cmput301t4.gameswap.Models;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import cmput301t4.gameswap.Exceptions.DateFormatException;

/**
 * Stores the Items that the current user owns
 */
public class Inventory {
    private ArrayList<Item> inventory = new ArrayList<Item>();

    /**
     * Adds an Item to the current users Inventory
     *
     * @param item The Item to be added to the Inventory
     */
    public void add(Item item) {
        inventory.add(item);
    }

    /**
     * Removes an Item from the current users Inventory based on its position in the list
     *
     * @param position The position of the item
     */
    public void del(int position) {
        inventory.remove(inventory.get(position));
    }

    /**
     * Changes an Item at a position to another Item
     *
     * @param item The new Item
     * @param position The position of the old Item
     */
    public void replace(Item item, int position){
        inventory.set(position, item);
    }

    /**
     * Simple getter to retrieve an Item based on its position
     *
     * @param index The position of the Item
     * @return The Item at the position
     */
    public Item getItem(int index){
        return inventory.get(index);
    }

    /**
     * Generic getter to retrieve all Items in the current users Inventory
     *
     * @return A Collection of Items that the current user owns
     */
    //public Collection<? extends Item> getItems() {
    public ArrayList<Item> getItems(){
        return inventory;
    }

    /**
     * Check if the current user owns an Item
     *
     * @param trader The Item to be checked
     * @return A boolean that is true if the current user has that Item
     */
    public boolean hasItem(Item item) {
        return inventory.contains(item);
    }

    /**
     * Empties the Inventory of the current user
     */
    public void clearInventory(){
        inventory.clear();
    }

    /**
     * Checks if the current user has any Items :(
     *
     * @return A boolean that is true if the current user has no Items
     */
    public boolean isEmpty(){
        return inventory.isEmpty();
    }

    /**
     * Checks how many Items the current user has
     *
     * @return The number of Items in the current users Inventory
     */
    public int size(){          //gets size of array of items
        return inventory.size();
    }

    /**
     * Delete several Items from the current users Inventory based on the positions of these Items
     *
     * @param delList A Collection of Item position to be removed
     */
    public void bulkdel(ArrayList<Integer> delList){
        /*count is used to take into account all items in arraylist
        move down 1 slot each time an item is deleted from arraylist
        */
        int count = 0;
        for (int i = 0; i < delList.size(); i++){
            del(delList.get(i)-count);
            count++;
        }
    }

    /**
     * Creates a new Item based on inputs and replaces the Item at the given position
     *
     * @param Name The name of the Item
     * @param ReleaseDate The date it was released in the form "dd-MM-yyyy"
     * @param isPrivate A boolean for if the Item will be shown to everyone
     * @param Quaility The quality of the Item from 1-5
     * @param Platform The platform the Item is intended for
     * @param Description A description of the Item
     * @param index The position of the Item to be replaced by the new Item
     */
    public void editItem(String Name, String ReleaseDate, boolean isPrivate, Integer Quality, Integer Platform, String Description, int index){
        Item edited_item = new Item(Name, ReleaseDate, isPrivate, Quality, Platform, Description);
        replace(edited_item, index);
    }

    //EditItem with a picture (Not Yet Implemented)
    /*
    public void editItem(String Name, String ReleaseDate, boolean isPrivate, Integer Quaility, Integer Platform, String Description, int index){
        Item edited_item = new Item(Name, ReleaseDate,isPrivate,Quaility,Platform,Description);
        replace(edited_item, index);
    }//end EditItem
    */

    /**
     * Converts a Date object into a String in the form "dd-MM-yyyy"
     *
     * @param date The Date to be displayed as a String
     * @return A String in the form "dd-MM-yyyy"
     * @throws DateFormatException
     */
    public String dateToString(Date date) throws DateFormatException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }
}