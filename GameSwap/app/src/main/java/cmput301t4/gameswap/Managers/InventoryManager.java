package cmput301t4.gameswap.Managers;

import android.location.Location;

import java.util.ArrayList;

import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.Item;


/**
 * InventoryManager handles all the function related to the inventory
 * @author  Kynan Ly, Preyanshu Kumar, Rupehra Chouhan , Daniel Ren, Blake Sakaluk,
 * @version Part 4
 */
public class InventoryManager {

    /**Singleton Code */
    static public Inventory getInstance(){
        return UserManager.getInventory();
    }

    /**
     * This adds the items to the inventory
     * @param Name item name
     * @param ReleaseDate item release date
     * @param isPrivate item public/private status
     * @param Quaility item quality
     * @param Platform item platform
     * @param Description item description
     */
    static public void addItem(String Name, String ReleaseDate, boolean isPrivate, Integer Quaility, Integer Platform, String Description){
        getInstance().add(new Item(Name, ReleaseDate, isPrivate,Quaility,Platform,Description));
    }

    /**
     * This deletes the item from inventory
     * @param position index of item in the inventory
     */
    static public void delItem(int position){
        getInstance().del(position);
    }

    /**
     * Gets the item at position
     * @param position index of item in the inventory
     * @return item
     */
    static public Item getItem(int position){
        return getInstance().getItem(position);
    }

    /**
     * Gets all items in the inventory
     * @return all items in the inventory
     */
    static public ArrayList<Item> getItems() { return getInstance().getItems(); }

    /**
     * Gets the name of all the items in inventory
     * @return item names
     */
    static public ArrayList<String> getItemsNames(){
        return getInstance().getItemsNames();
    }

    /**
     * Replaces the item at position
     * @param item item to replace with
     * @param position index of where this item should be placed
     */
    static public void replaceItem(Item item, int position){
        getInstance().replace(item, position);
    }

    /**
     * @param item item which we are looking for in the inventory
     * @return true if the item exists in the inventory, returns false otherwise
     */
    public boolean hasItem(Item item){
        return getInstance().hasItem(item);
    }

    /**
     * This clears the inventory
     */
    public void clearInventory(){
        getInstance().clearInventory();
    }

    /**
     * This deletes all the items
     * @param delList entire list of items
     */
    public void bulkDel(ArrayList<Integer> delList){
        getInstance().bulkdel(delList);
    }

    /**
     * Sets the items at position
     * @param itemsPosition
     */
    public void setItems(int itemsPosition) {
        getInstance().setItemArrayPosition(itemsPosition);
    }


    /**
     * This edits the item
     * @param Name item name
     * @param ReleaseDate item release date
     * @param isPrivate item public/private status
     * @param Quaility item quality
     * @param Platform item platform
     * @param Description item description
     * @param index index of the item in inventory
     */
    public void editItem(String Name, String ReleaseDate, boolean isPrivate, Integer Quaility, Integer Platform, String Description, int index){
        getInstance().editItem(Name, ReleaseDate, isPrivate, Quaility, Platform, Description, index);
    }

    /**
     * Sets the location of the item at position
     * @param position index of the item in inventory
     * @param location location of the item
     */
    public static void setItemLocation(int position, Location location) {
        getItem(position).setLocation(location);
    }

    /**
     * Gets item location
     * @param position
     * @return item location
     */
    public static Location getItemLocation(int position) {
        return getItem(position).getLocation();
    }

    //edit Item with the addition of an Image (Not yet implmeneted)
    /*
    public void editItem(String Name, String ReleaseDate, boolean isPrivate, Integer Quaility, Integer Platform, String Description, int index){
        getInstance().editItem(Name,ReleaseDate,isPrivate, Quaility, Platform, Description, index);
    }//end editItem
    */

}
