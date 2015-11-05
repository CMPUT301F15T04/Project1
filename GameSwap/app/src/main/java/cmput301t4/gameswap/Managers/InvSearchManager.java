package cmput301t4.gameswap.Managers;

import java.util.ArrayList;
import java.util.Date;

import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.Models.Trade;
import cmput301t4.gameswap.Models.TradeList;

/**
 * Created by dren on 11/4/15.
 */
public class InvSearchManager {

    public boolean searchFriendTrade(TradeList tradelistFriend, Trade trade){//returns true if trade exists in friend tradelist
        return tradelistFriend.hasTrade(trade);
    }

    public Inventory searchItembyName(Inventory inv, String name){//finds all items by name
        Inventory matching = new Inventory();
        for(int i = 0; i < inv.size(); i++){
            if(inv.getItem(i).getName().equals(name)){
                matching.add(inv.getItem(i));
            }
        }
        return matching;//returns an inventory
    }

    public Inventory searchItembyPlatform(Inventory inv, int platform){//finds all items by platform
        Inventory matching = new Inventory();
        for(int i = 0; i < inv.size(); i++){
            if(inv.getItem(i).getPlatform() == platform){
                matching.add(inv.getItem(i));
            }
        }
        return matching;//returns an inventory
    }

    public Inventory searchItembyQuality(Inventory inv, int quality){//finds all items by platform
        Inventory matching = new Inventory();
        for(int i = 0; i < inv.size(); i++){
            if(inv.getItem(i).getQuality() == quality){
                matching.add(inv.getItem(i));
            }
        }
        return matching;//returns an inventory
    }

    public Inventory searchItembyDate(Inventory inv, Date date){//finds all items by date
        Inventory matching = new Inventory();
        for(int i = 0; i < inv.size(); i++){
            if(inv.getItem(i).getReleaseDate().equals(date) ){
                matching.add(inv.getItem(i));
            }
        }
        return matching;//returns an inventory
    }

    public Inventory searchItembyPrivate(Inventory inv, boolean priv){//finds all items by privacy information
        Inventory matching = new Inventory();
        for(int i = 0; i < inv.size(); i++){
            if(inv.getItem(i).getIsPrivate()== priv){
                matching.add(inv.getItem(i));
            }
        }
        return matching;//returns an inventory
    }

}
