package cmput301t4.gameswap.Managers;

import java.util.ArrayList;

import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.Item;

public class InventoryManager {

    private static Inventory inventory = null;

    //Singleton Code
    static public Inventory getInstance(){
        if (inventory == null){
            inventory = new Inventory();
        }
        //TODO: pull the cached items and add them to this list
        return inventory;
    }//end getInstance

    //=====Basic Function=====//

    static public void addItem(String Name, String ReleaseDate, boolean isPrivate, Integer Quaility, Integer Platform, String Description){
        getInstance().add(new Item(Name, ReleaseDate, isPrivate,Quaility,Platform,Description));
    }//end addItem

    public void delItem(int position){
        getInstance().del(position);
    }//end delItem

    public Item getItem(int position){
        return getInstance().getItem(position);
    }//end getItem

    static public ArrayList<Item> getItems() { return getInstance().getItems(); }

    public void replaceItem(Item item, int position){
        getInstance().replace(item, position);
    }//end replaceItem

    public boolean hasItem(Item item){
        return getInstance().hasItem(item);
    }//end hasItem

    //Wrote this in for testing, not sure if we really need
    public void clearInventory(){
        getInstance().clearInventory();
    }

    //==More Advance Function==//

    public void bulkDel(ArrayList<Integer> delList){
        getInstance().bulkdel(delList);
    }//end bulkDel

    public void editItem(String Name, String ReleaseDate, boolean isPrivate, Integer Quaility, Integer Platform, String Description, int index){
        getInstance().editItem(Name,ReleaseDate,isPrivate, Quaility, Platform, Description, index);
    }//end editItem

    //edit Item with the addition of an Image (Not yet implmeneted)
    /*
    public void editItem(String Name, String ReleaseDate, boolean isPrivate, Integer Quaility, Integer Platform, String Description, int index){
        getInstance().editItem(Name,ReleaseDate,isPrivate, Quaility, Platform, Description, index);
    }//end editItem
    */

}//end Inventory Manager
