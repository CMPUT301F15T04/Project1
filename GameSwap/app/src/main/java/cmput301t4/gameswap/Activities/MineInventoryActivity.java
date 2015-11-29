package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.CheckedInputStream;

import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.R;

public class MineInventoryActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<Item> inventory;
    private ArrayList<String> items;
    protected int myInventoryListViewPosition;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_inventory);

        listView = (ListView) findViewById(R.id.mineInventoryListView);
        items = new ArrayList<String>();
        button = (Button) findViewById(R.id.checkbutton);
        //http://www.androidinterview.com/android-custom-listview-with-checkbox-example/
        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);
        items.add("Call of Duty");
        items.add("Halo");
        //nameOfItemsList = InventoryManager.getInstance().getItemsNames();
        //inventory = InventoryManager.getItems();
        adapter = new ArrayAdapter<String>(this,R.layout.mineinventorylistviewtext, items);

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(this);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
        public void onClick(View v){
                Toast.makeText(getBaseContext(), "Clicked", Toast.LENGTH_SHORT).show();

            }
        });


       // listView.

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mine_inventory, menu);
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


    @Override
    public void onItemClick(AdapterView arg0, View v, int position, long arg3){
        CheckBox cb = (CheckBox) v.findViewById(R.id.myInventoryCheckbox);
        cb.performClick();

    }
}

