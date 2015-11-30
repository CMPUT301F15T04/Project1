package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.CreateTradeManager;
import cmput301t4.gameswap.Managers.TradeManager;
import cmput301t4.gameswap.R;

public class DecideTradeActivity extends Activity {

    private ListView myInventoryItemsListView;
    private ListView friendInventoryItemsListView;
    private ArrayAdapter<String> myadapter;
    private ArrayAdapter<String> friendAdapter;
    private ArrayList<String> myItems;
    private ArrayList<String> friendItems;
    private CreateTradeManager CTM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decide_trade);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        CTM = new CreateTradeManager();
        CTM.setOwnerSide(TradeManager.getCurrent().getTrade(b.getInt("Position")).getOwnerItems());
        CTM.setFriendSide(TradeManager.getCurrent().getTrade(b.getInt("Position")).getBorrowerItems());
        myItems = CTM.getOwnerSide().getItemsNames();
        friendItems = CTM.getFriendSide().getItemsNames();
        myInventoryItemsListView = (ListView) findViewById(R.id.decideitemsFromMyInventory);
        friendInventoryItemsListView = (ListView) findViewById(R.id.decideitemsFromFriendInventory);
        myadapter = new ArrayAdapter<String>(this, R.layout.decidemyitemstextlistview, myItems);
        friendAdapter = new ArrayAdapter<String>(this, R.layout.decidefrienditemlisttextview, friendItems);
        myInventoryItemsListView.setAdapter(myadapter);
        friendInventoryItemsListView.setAdapter(friendAdapter);
    }

}
