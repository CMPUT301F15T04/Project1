package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import cmput301t4.gameswap.Managers.TradeManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.Models.Trade;
import cmput301t4.gameswap.Models.TradeList;
import cmput301t4.gameswap.R;

public class TradesActivity extends Activity {

    private ArrayAdapter<String> adapter;
    private ListView pendingtradeListView;
    private ArrayList<String> trades;
    private TradeList tradeList;
    private ArrayList<String> currentTradeBorrowers;
    private ArrayList<String> pastTradeBorrowers;
    private TradeList currentTrades;
    private TradeList pastTrades;
    private TradeManager TM;
    private ListView currentListView;
    private ListView pastListView;
    private ArrayAdapter<String> currentAdapter;
    private ArrayAdapter<String> pastAdapter;
    private Trade trade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trades);
        TM = new TradeManager();
        tradeList = new TradeList();
        currentTrades = TM.getCurrent();
        pastTrades = TM.getPast();
        currentTradeBorrowers = currentTrades.getBorrowerNames();
        pastTradeBorrowers = pastTrades.getBorrowerNames();
        currentListView = (ListView) findViewById(R.id.pendingtradeListView);
        pastListView = (ListView) findViewById(R.id.pasttradeListView);
        currentAdapter = new ArrayAdapter<String>(this, R.layout.currentofferalisttextview, currentTradeBorrowers);
        pastAdapter = new ArrayAdapter<String>(this, R.layout.pastofferlisttextview, pastTradeBorrowers);
        currentListView.setAdapter(currentAdapter);
        pastListView.setAdapter(pastAdapter);

        currentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                trade = currentTrades.getTrade(position);;
                if(UserManager.getTrader().getUserName().equals(trade.getOwnername())){
                    Intent intent = new Intent(TradesActivity.this, CancelCreateTradeActivity.class);
                    intent.putStringArrayListExtra("oitems",trade.getOwnerItems().getItemsNames());
                    intent.putStringArrayListExtra("bitems", trade.getBorrowerItems().getItemsNames());
                    startActivity(intent);

                }
                else {
                    Intent intent1 = new Intent(TradesActivity.this, DecideTradeActivity.class);
                    intent1.putStringArrayListExtra("oitems",trade.getOwnerItems().getItemsNames());
                    intent1.putStringArrayListExtra("bitems", trade.getBorrowerItems().getItemsNames());
                    startActivity(intent1);}

            }
        });

        pastListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                trade = pastTrades.getTrade(position);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_trades, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
