package cmput301t4.gameswap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cmput301t4.gameswap.Exceptions.DateFormatException;

/**
 * Created by kynan on 10/30/15.
 */
public class Inventory {
    private ArrayList<Item> inventory = new ArrayList<Item>();

    //======Basic Function=====//
    public void add(Item item) {
        inventory.add(item);
    }//end add to Inventory

    //This del the item if you have item object already, most likely will
    //be changed later to take in position
    public void del(int position) {
        inventory.remove(inventory.get(position));
    }//end del to Inventory

    //Replace item at position X with new item
    public void replace(Item item, int position){
        inventory.set(position, item);
    }//end replace

    //Retrieve item at index
    public Item getItem(int index){
        return inventory.get(index);
    }//End getItem

    //See if inventory contain item
    public boolean hasItem(Item item) {
        return inventory.contains(item);
    }//end hasItem

    //Wrote this in for testing, Not sure if we really need
    public void clearInventory(){
        inventory.clear();
    }//end clearInventory

    //=====Advance Functions=====//

    //Bulk delete item from inventory based on given inventory indexs
    public void bulkdel(ArrayList<Integer> delList){
        /*count is used to take into account all items in arraylist
        move down 1 slot each time an item is deleted from arraylist
        */
        int count = 0;
        for (int i = 0; i < delList.size(); i++){
            del(delList.get(i)-count);
            count++;
        }
    }//end bulkdel

    //EditItem without a picture
    public void editItem(String Name, String ReleaseDate, boolean isPrivate, Integer Quaility, Integer Platform, String Description, int index){
        Item edited_item = new Item(Name, ReleaseDate,isPrivate,Quaility,Platform,Description);
        replace(edited_item, index);
    }//end EditItem

    //EditItem with a picture (Not Yet Implemented)
    /*
    public void editItem(String Name, String ReleaseDate, boolean isPrivate, Integer Quaility, Integer Platform, String Description, int index){
        Item edited_item = new Item(Name, ReleaseDate,isPrivate,Quaility,Platform,Description);
        replace(edited_item, index);
    }//end EditItem
    */

    public String dateToString(Date date) throws DateFormatException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String ConvertedDate = formatter.format(date);
        return ConvertedDate;
    }//End dateToString

}//end Inventory