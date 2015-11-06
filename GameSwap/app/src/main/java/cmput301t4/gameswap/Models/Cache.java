package cmput301t4.gameswap.Models;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sakaluk on 11/1/15.
 */
public class Cache {
    ArrayList<Item> itemsToBePushed = new ArrayList<Item>();
    ArrayList<Trade> tradesToBePushed = new ArrayList<Trade>();
    int accountID;



    public void addItemsToCache(Collection<? extends Item> items) {
        itemsToBePushed.addAll(items);
    }

    public void addItemToCache(Item item) {
        itemsToBePushed.add(item);
    }

	public Collection<? extends Item> getPendingItems() {
		return itemsToBePushed;
	}

    public void addTradesToCache(Collection<? extends Trade> trades) {
        tradesToBePushed.addAll(trades);
    }

    public void addTradeToCache(Trade trade) {
        tradesToBePushed.add(trade);
    }

	public Collection<? extends Trade> getPendingTrades() {
		return tradesToBePushed;
	}

    public void pullFriend(User friend) {
		//TODO: Pull friend from server
		//TODO: Move this method to CacheManager
    }
}
