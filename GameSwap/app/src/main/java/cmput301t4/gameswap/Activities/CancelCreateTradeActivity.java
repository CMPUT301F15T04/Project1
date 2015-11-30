package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.CreateTradeManager;
import cmput301t4.gameswap.Managers.TradeManager;
import cmput301t4.gameswap.Models.Trade;
import cmput301t4.gameswap.Models.TradeList;
import cmput301t4.gameswap.R;

public class CancelCreateTradeActivity extends Activity {

    private ListView myInventoryItemsListView;
    private ListView friendInventoryItemsListView;
    private ArrayAdapter<String> myadapter;
    private ArrayAdapter<String> friendAdapter;
    private ArrayList<String> myItems;
    private ArrayList<String> friendItems;
    //private CreateTradeManager CTM;
    private Trade trade;
    private TradeManager TM;
    private TradeList tradeList;
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_create_trade);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        TM = new TradeManager();

        if(b!=null) {
            myItems = b.getStringArrayList("oitems");
            friendItems = b.getStringArrayList("bitems");
            index = b.getInt("index");


        }
        Toast.makeText(getBaseContext(), "Here", Toast.LENGTH_SHORT).show();
        myInventoryItemsListView = (ListView) findViewById(R.id.cancelitemsFromMyInventory);
        friendInventoryItemsListView = (ListView) findViewById(R.id.cancelitemsFromFriendInventory);
        myadapter = new ArrayAdapter<String>(this, R.layout.cancelmyinventorytextview, myItems);
        friendAdapter = new ArrayAdapter<String>(this, R.layout.cancelfriendinventorytextview, friendItems);
        myInventoryItemsListView.setAdapter(myadapter);
        friendInventoryItemsListView.setAdapter(friendAdapter);
        myadapter.notifyDataSetChanged();
        friendAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_decide_trade, menu);
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



    public void cancelTradeClicked(View v){
        CreateTradeManager.clearOwnerSide();
        CreateTradeManager.clearFriendSide();
        tradeList  = TM.getCurrent();
        tradeList.del(index);
        finish();
    }
}
