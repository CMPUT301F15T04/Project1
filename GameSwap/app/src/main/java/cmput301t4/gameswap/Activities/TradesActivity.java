package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.TradeManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.Trade;
import cmput301t4.gameswap.Models.TradeList;
import cmput301t4.gameswap.R;


/**
 * Class that displays user's current and past trades
 *
 * @author Preyanshu Kumar, Kynan Ly, Daniel Ren, Rupehra Chouhan, Blake Sakaluk
 * @version Part 4
 */
public class TradesActivity extends Activity {


    /** Current trade borrowers */
    private ArrayList<String> currentTradeBorrowers;
    /** Past trade borrowers */
    private ArrayList<String> pastTradeBorrowers;
    /** Current trades list view */
    private ListView currentListView;
    /** Past trades list view */
    private ListView pastListView;
    /** Adapter for current trades */
    private ArrayAdapter<String> currentAdapter;
    /** Adapter for past trades */
    private ArrayAdapter<String> pastAdapter;
    /** object trade */
    private Trade trade;


    /**
     * Called when the activity is created
     * Collects current trades and past trades and displays them on this activity page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trades);

        //reload user before viewing tradelist
        ServerManager.getUserOnline(UserManager.getTrader().getUserName());

        currentTradeBorrowers = TradeManager.getCurrentNames(true);
        pastTradeBorrowers = TradeManager.getCurrentNames(false);
        currentListView = (ListView) findViewById(R.id.pendingtradeListView);
        pastListView = (ListView) findViewById(R.id.pasttradeListView);
        currentAdapter = new ArrayAdapter<String>(this, R.layout.currentofferalisttextview, currentTradeBorrowers);
        pastAdapter = new ArrayAdapter<String>(this, R.layout.pastofferlisttextview, pastTradeBorrowers);
        currentListView.setAdapter(currentAdapter);
        pastListView.setAdapter(pastAdapter);

        currentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                trade = TradeManager.getCurrent().getTrade(position);
                if(UserManager.getTrader().getUserName().equals(trade.getOwnername())){
                    Intent intent = new Intent(TradesActivity.this, CancelCreateTradeActivity.class);
                    intent.putExtra("index",position);
                    startActivity(intent);

                }
                else {
                    Intent intent1 = new Intent(TradesActivity.this, DecideTradeActivity.class);
                    intent1.putExtra("index",position);
                    startActivity(intent1);}

            }
        });

        pastListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                trade = TradeManager.getPast().getTrade(position);
            }
        });

    }

    /**
     * Called when this activity is resumed to update data that needs to be displayed
     * on this page
     */
    public void resetAdapter(){

        currentTradeBorrowers = TradeManager.getCurrentNames(true);
        pastTradeBorrowers = TradeManager.getCurrentNames(false);
        currentAdapter = new ArrayAdapter<String>(this, R.layout.currentofferalisttextview, currentTradeBorrowers);
        pastAdapter = new ArrayAdapter<String>(this, R.layout.pastofferlisttextview, pastTradeBorrowers);
        currentListView.setAdapter(currentAdapter);
        pastListView.setAdapter(pastAdapter);
    }


    @Override
    public void onResume(){
        super.onResume();
        resetAdapter();
    }

    //===================Function needed for TestCases===================//

    public ListView getCurrentTrade(){
        return currentListView;
    }

    //===================Function needed for TestCases===================//
}
