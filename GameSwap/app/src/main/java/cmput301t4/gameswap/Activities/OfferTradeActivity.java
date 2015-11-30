package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cmput301t4.gameswap.R;

public class OfferTradeActivity extends Activity {

    private ListView myInventoryItemsListView;
    private ListView friendInventoryItemsListView;
    private ArrayAdapter<String> myadapter;
    private ArrayAdapter<String> friendAdapter;
    private ArrayList<String> myItems;
    private ArrayList<String> friendItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_trade);
        myItems = new ArrayList<String>();
        friendItems = new ArrayList<String>();
        myInventoryItemsListView = (ListView) findViewById(R.id.itemsFromMyInventory);
        friendInventoryItemsListView = (ListView) findViewById(R.id.itemsFromFriendInventory);
        myadapter = new ArrayAdapter<String>(this, R.layout.myselecteditemstext, myItems);
        friendAdapter = new ArrayAdapter<String>(this, R.layout.friendselecteditemstext, friendItems);
        Intent intent = getIntent();
        myItems = intent.getStringArrayListExtra("myitems");
        friendItems = intent.getStringArrayListExtra("frienditems");
        if(myItems != null) {addItemsToMyList(myItems);}
        if(friendItems != null){addItemsToFriendList(friendItems);}


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_offer_trade, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void friendInventoryButtonClicked(View v) {
        Intent intent = new Intent(OfferTradeActivity.this, FriendInventoryActivity.class);
        startActivity(intent);
        finish();
    }

    public void myInventoryButtonClicked(View v) {
        Intent intent = new Intent(OfferTradeActivity.this, MineInventoryActivity.class);
        startActivity(intent);
        finish();
    }


    public void addItemsToMyList(ArrayList<String> myItems) {

        myInventoryItemsListView.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();

    }

    public void addItemsToFriendList(ArrayList<String> friendItems) {

        friendInventoryItemsListView.setAdapter(friendAdapter);
        friendAdapter.notifyDataSetChanged();


    }
}
