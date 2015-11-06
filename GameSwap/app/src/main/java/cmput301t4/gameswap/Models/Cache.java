package cmput301t4.gameswap.Models;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sakaluk on 11/1/15.
 */
public class Cache {
    ArrayList<Item> itemsToBePushed = new ArrayList<Item>();
    ArrayList<Trade> tradesToBePushed = new ArrayList<Trade>();
    int accountID; //Not yet impkemented (Server stuff)



    public void addItemsToCache(Collection<? extends Item> items) {
        itemsToBePushed.addAll(items);
    }//end addItemsToCache

    public void addItemToCache(Item item) {
        itemsToBePushed.add(item);
    }//end addItemToCache

	public Collection<? extends Item> getPendingItems() {
		return itemsToBePushed;
	}//end getPendingItem

    public void addTradesToCache(Collection<? extends Trade> trades) {
        tradesToBePushed.addAll(trades);
    }//end addTradeToCache

    public void addTradeToCache(Trade trade) {
        tradesToBePushed.add(trade);
    }//end addTradeToCache

	public Collection<? extends Trade> getPendingTrades() {
		return tradesToBePushed;
	}//end getPendingTrade

    public void pullFriend(User friend) {
		//TODO: Pull friend from server
		//TODO: Move this method to CacheManager
    }//end getpullFriend
}//end Cache
