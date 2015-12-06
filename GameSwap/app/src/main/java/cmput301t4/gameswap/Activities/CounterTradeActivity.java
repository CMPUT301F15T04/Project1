/*

    WE DON'T NEED THIS ACTIVITY ANYMORE. REFACTORED OFFERTRADE ACTIVITY TO HANDLE THINGS PROPERLY

package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.CreateTradeManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.Trade;
import cmput301t4.gameswap.R;

public class CounterTradeActivity extends Activity {

    private ListView myInventoryItemsListView;
    private ListView friendInventoryItemsListView;
    private ArrayAdapter<String> myadapter;
    private ArrayAdapter<String> friendAdapter;
    private ArrayList<String> myItems;
    private ArrayList<String> friendItems;
    private Trade trade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_trade);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null) {
            int index = b.getInt("index");
            //String borrower;
            trade = UserManager.getPendingList().getTrade(index);
            CreateTradeManager.setFriendSide(trade.getBorrowerItems());
            CreateTradeManager.setOwnerSide(trade.getOwnerItems());

            Intent intent1 = new Intent(CounterTradeActivity.this,OfferTradeActivity.class);
            startActivity(intent1);
            myItems = CreateTradeManager.getOwnerSideName();
            friendItems = CreateTradeManager.getFriendSideName();
            //friendItems = UserManager.getPendingList().getTrade(index).getOwnerItems().getItemsNames();
        }

        myInventoryItemsListView = (ListView) findViewById(R.id.counteritemsMyInventory);
        friendInventoryItemsListView = (ListView) findViewById(R.id.counteritemsFromFriendInventory);
        myadapter = new ArrayAdapter<String>(this, R.layout.countermylist, myItems);
        friendAdapter = new ArrayAdapter<String>(this, R.layout.counterfriendlist, friendItems);
        myInventoryItemsListView.setAdapter(myadapter);
        friendInventoryItemsListView.setAdapter(friendAdapter);

    }





}
*/