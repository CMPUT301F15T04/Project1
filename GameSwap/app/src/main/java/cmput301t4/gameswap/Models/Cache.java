package cmput301t4.gameswap.Models;

import java.util.ArrayList;

/**
 * Created by sakaluk on 11/1/15.
 */
public class Cache {
    final static String ITEMS_SAVE_FILE = "gameswap_local_items.sav";
    final static String TRADES_SAVE_FILE = "gameswap_local_trades.sav";
    ArrayList<Item> itemsToBePushed = new ArrayList<Item>();
    ArrayList<Trade> tradesToBePushed = new ArrayList<Trade>();
    int accountID;

    /* TODO: Move to CacheManager
    public void saveToFile() {
        try {
			FileOutputStream fos = openFileOutput(ITEMS_SAVE_FILE, 0);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
			Gson gson = new Gson();
			gson.toJson(itemsToBePushed, out);
			out.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

        try {
			FileOutputStream fos = openFileOutput(TRADES_SAVE_FILE, 0);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
			Gson gson = new Gson();
			gson.toJson(tradesToBePushed, out);
			out.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

        //TODO: store saved friend to file
    }

    private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(ITEMS_SAVE_FILE);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			Gson gson = new Gson();
			// https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
			Type arraylistType = new TypeToken<ArrayList<Item>>() {}.getType();
			itemsToBePushed = gson.fromJson(in, arraylistType);
		} catch (FileNotFoundException e) {
			itemsToBePushed = new ArrayList<Tweet>();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try {
			FileInputStream fis = openFileInput(TRADES_SAVE_FILE);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			Gson gson = new Gson();
			// https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
			Type arraylistType = new TypeToken<ArrayList<Item>>() {}.getType();
			tradesToBePushed = gson.fromJson(in, arraylistType);
		} catch (FileNotFoundException e) {
			itemsToBePushed = new ArrayList<Tweet>();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

        //TODO: load friend from save file
	}
	*/

    public void addItemsToCache(ArrayList<Item> items) {
        itemsToBePushed.addAll(items);
    }

    public void addItemToCache(Item item) {
        itemsToBePushed.add(item);
    }

    public void addTradesToCache(ArrayList<Trade> trades) {
        tradesToBePushed.addAll(trades);
    }

    public void addTradeToCache(Trade trade) {
        tradesToBePushed.add(trade);
    }

    public void pullFriend(User friend) {

    }
}
