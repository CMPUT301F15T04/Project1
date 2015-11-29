package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.zip.CheckedInputStream;

import Adapter.ListViewAdapter;
import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.R;

public class MineInventoryActivity extends Activity  {

    private ListView listView;



    //Adapter.ItemHolder.ListViewAdapter adapter;
    //private ArrayList<Item> inventory;
    ArrayList<Item> inventory;
    LayoutInflater inflater;
    ListViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_inventory);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listView = (ListView) findViewById(R.id.mineInventoryListView);
        inventory = InventoryManager.getInstance().getItems();

        adapter = new ListViewAdapter(this,R.layout.mineinventorylistviewtext,inventory);
       // final ListViewAdapter adapter = new ListViewAdapter(this,R.layout.mineinventorylistviewtext,inventory);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Inventory inventory = (Inventory) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + inventory.getItem(position).getName(),
                        Toast.LENGTH_LONG).show();
            }
        });


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


}

