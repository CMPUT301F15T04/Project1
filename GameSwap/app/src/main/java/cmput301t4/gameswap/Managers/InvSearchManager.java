package cmput301t4.gameswap.Managers;

import java.util.Date;

import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.Trade;
import cmput301t4.gameswap.Models.TradeList;

/**
 * InventoryManager handles all the searching through the inventory
 * @author  Daniel Ren, Kynan Ly, Preyanshu Kumar, Rupehra Chouhan , Blake Sakaluk,
 * @version Part 4
 */
public class InvSearchManager {

    /**
     * Returns true if trade exists in friend tradelist
     * @param tradelistFriend friend's trade list
     * @param trade trade that was made
     * @return true if trade exists in friend tradelist, and returns false otherwise
     */
    public boolean searchFriendTrade(TradeList tradelistFriend, Trade trade){//
        return tradelistFriend.hasTrade(trade);
    }

    /**
     * Searches item from inventory by name
     * @param inv inventory from which we are searching
     * @param name name of the item we are looking for
     * @return items searched by name
     */
    public Inventory searchItembyName(Inventory inv, String name){
        Inventory matching = new Inventory();
        for(int i = 0; i < inv.size(); i++){
            if(inv.getItem(i).getName().equals(name)){
                matching.add(inv.getItem(i));
            }//end If
        }//end For loop
        return matching;//returns an inventory
    }

    /**
     * finds all items by platform
     * @param inv: inventotyr in which we are looking up the items
     * @param platform platform that we are using to search up items
     * @return all the items by platform
     */
    public Inventory searchItembyPlatform(Inventory inv, int platform){
        Inventory matching = new Inventory();
        for(int i = 0; i < inv.size(); i++){
            if(inv.getItem(i).getPlatform() == platform){
                matching.add(inv.getItem(i));
            }//end If
        }//end For Loop
        return matching;//returns an inventory
    }

    /**
     * finds all items by quality
     * @param inv inventory in which we are looking up the items
     * @param quality quality by which we are searching the items
     * @return all the items of the given quality
     */
    public Inventory searchItembyQuality(Inventory inv, int quality){
        Inventory matching = new Inventory();
        for(int i = 0; i < inv.size(); i++){
            if(inv.getItem(i).getQuality() == quality){
                matching.add(inv.getItem(i));
            }//end If
        }//end For
        return matching;//returns an inventory
    }

    /**
     * finds all items by date
     * @param inv inventory in which we are looking up the items
     * @param date date by which we are searching the items
     * @return all the items by date
     */
    public Inventory searchItembyDate(Inventory inv, Date date){
        Inventory matching = new Inventory();
        for(int i = 0; i < inv.size(); i++){
            if(inv.getItem(i).getReleaseDate().equals(date) ){
                matching.add(inv.getItem(i));
            }//end If
        }//end For
        return matching;//returns an inventory
    }

    /**
     * finds all items by privacy information
     * @param inv inventory in which we are looking up the items
     * @param priv status by which we are searching the items
     * @return all the items by the given status
     */
    public Inventory searchItembyPrivate(Inventory inv, boolean priv){
        Inventory matching = new Inventory();
        for(int i = 0; i < inv.size(); i++){
            if(inv.getItem(i).getIsPrivate()== priv){
                matching.add(inv.getItem(i));
            }//end If
        }//end For loop
        return matching;//returns an inventory
    }

    /**
     * Shows friend inventory
     * @param friendInv friend inventory
     * @return all the items in the friend inventory
     */
    public static Inventory showFriendInventory (Inventory friendInv){
        Inventory invPublic = new Inventory();
        for(int i = 0; i<friendInv.size(); i++){
            if (friendInv.getItem(i).getIsPrivate()==Boolean.FALSE){
                invPublic.add(friendInv.getItem(i));
            }
        }
        return invPublic;
    }

}
