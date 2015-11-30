package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.Models.Trade;
import cmput301t4.gameswap.Models.TradeList;
import cmput301t4.gameswap.R;

public class TradesActivity extends Activity {

    private ArrayAdapter<String> adapter;
    private ListView pendingtradeListView;
    private ArrayList<String> trades;
    private TradeList tradeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trades);

        tradeList = new TradeList();
        ArrayList<Item> o_tradeitem = new ArrayList<Item>();
        ArrayList<Item> b_tradeitem = new ArrayList<Item>();
        Item item_1 = new Item("Call of Duty", "01-01-2000", false, 5, 5, "It's Okay");
        Item item_2 = new Item("Call of Doom", "02-02-1000", true, 2, 5, "It's better than Okay");
        o_tradeitem.add(item_1);
        b_tradeitem.add(item_2);

        //Trade trade = new Trade("Owner", "Borrower", o_tradeitem, b_tradeitem);
        //tradeList.add(trade);

        //trades.add(tradeList.getTrade(0).getOwnername().toString()+ "     "+tradeList.getTrade(0).getBorrowerName().toString());
       // pendingtradeListView = (ListView) findViewById(R.id.pendingtradeListView);
        //adapter = new ArrayAdapter<String>(this,R.layout.tradelistviewtext,trades);
        //pendingtradeListView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

        /*
        pendingtradeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View childView, int position, long id) {
                PopupMenu popupMenu = new PopupMenu(TradesActivity.this, childView);
                popupMenu.getMenuInflater().inflate(R.menu.myinventoryitempopup, popupMenu.getMenu());

                //myInventoryListViewPosition = position;
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.acceptTrade:
                                //Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();

                                AlertDialog.Builder alert = new AlertDialog.Builder(TradesActivity.this);
                                alert.setMessage("Are you sure, you want to accept trade?");

                                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        //Toast.makeText(myInventoryActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                                    }
                                });

                                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });

                                AlertDialog alertDialog = alert.create();
                                alertDialog.show();

                                return true;
                            case R.id.rejectTrade:

                                AlertDialog.Builder alert1 = new AlertDialog.Builder(TradesActivity.this);
                                alert1.setMessage("Are you sure, you want to reject trade?");

                                alert1.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        //Toast.makeText(myInventoryActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                                    }
                                });

                                alert1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });

                                AlertDialog alertDialog1 = alert1.create();
                                alertDialog1.show();

                                return true;

                            default:
                                ;

                        }

                        return false;
                    }
                });

                popupMenu.show();

            }
        });
        */

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
