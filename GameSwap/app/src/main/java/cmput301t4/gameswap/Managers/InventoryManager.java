package cmput301t4.gameswap.Managers;

import java.util.ArrayList;

import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.Item;

public class InventoryManager {

    //Singleton Code
    static public Inventory getInstance(){
        return UserManager.getInventory();
    }

    //=====Basic Function=====//

    static public void addItem(String Name, String ReleaseDate, boolean isPrivate, Integer Quaility, Integer Platform, String Description){
        getInstance().add(new Item(Name, ReleaseDate, isPrivate,Quaility,Platform,Description));
    }//end addItem

    static public void delItem(int position){
        getInstance().del(position);
    }//end delItem

    static public Item getItem(int position){
        return getInstance().getItem(position);
    }//end getItem

    static public ArrayList<Item> getItems() { return getInstance().getItems(); }

    static public ArrayList<String> getItemsNames(){
        return getInstance().getItemsNames();
    }

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


    //adding this function - Rupehra also need to write a test case for this
    public void setItems(int itemsPosition) {
        getInstance().setItemArrayPosition(itemsPosition);
    }


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
