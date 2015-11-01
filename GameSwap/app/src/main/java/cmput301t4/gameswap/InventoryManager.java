package cmput301t4.gameswap;

import java.util.ArrayList;

public class InventoryManager {

    private static Inventory inventory = null;

    //Singleton Code
    static public Inventory getInstance(){
        if (inventory == null){
            inventory = new Inventory();
        }
        return inventory;
    }//end getInstance

    //=====Basic Function=====//

    public void addItem(Item item){
        getInstance().add(item);
    }//end addItem

    public void delItem(int position){
        getInstance().del(position);
    }//end delItem

    public Item getItem(int position){
        return getInstance().getItem(position);
    }//end getItem

    public void replaceItem(Item item, int position){
        getInstance().replace(item, position);
    }//end replaceItem

    public Item findItem(int position){
        return getInstance().getItem(position);
    }//end findItem

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
