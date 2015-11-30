package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


import cmput301t4.gameswap.Models.Trade;

import cmput301t4.gameswap.R;

public class DecideTradeActivity extends Activity {

    private ListView myInventoryItemsListView;
    private ListView friendInventoryItemsListView;
    private ArrayAdapter<String> myadapter;
    private ArrayAdapter<String> friendAdapter;
    private ArrayList<String> myItems;
    private ArrayList<String> friendItems;
    //private CreateTradeManager CTM;
    private Trade trade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decide_trade);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null) {
            myItems = b.getStringArrayList("oitems");
            friendItems = b.getStringArrayList("bitems");

        }
<<<<<<< HEAD
=======
        Toast.makeText(getBaseContext(), "Here", Toast.LENGTH_SHORT).show();

>>>>>>> 8952477b0696004a2a5dbc2fe93c4f7baf1cd855
        myInventoryItemsListView = (ListView) findViewById(R.id.decideitemsFromMyInventory);
        friendInventoryItemsListView = (ListView) findViewById(R.id.decideitemsFromFriendInventory);
        myadapter = new ArrayAdapter<String>(this, R.layout.decidemyitemstextlistview, myItems);
        friendAdapter = new ArrayAdapter<String>(this, R.layout.decidefrienditemlisttextview, friendItems);
        myInventoryItemsListView.setAdapter(myadapter);
        friendInventoryItemsListView.setAdapter(friendAdapter);


    }


}
