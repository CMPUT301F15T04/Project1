package cmput301t4.gameswap.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

import cmput301t4.gameswap.R;

public class myInventoryActivity extends Activity{

    private ListView myInventoryListView;
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    protected int myInventoryListViewPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_inventory);

        myInventoryListView = (ListView) findViewById(R.id.myInventoryListView);
        items.add("Call of Duty");
        items.add("Halo");

        adapter = new ArrayAdapter<String>(this,R.layout.myinventorylistviewtext,items);
        myInventoryListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        myInventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View childView, int position, long id) {
                PopupMenu popupMenu = new PopupMenu(myInventoryActivity.this,childView);
                popupMenu.getMenuInflater().inflate(R.menu.myinventoryitempopup,popupMenu.getMenu());

                myInventoryListViewPosition = position;
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    public boolean onMenuItemClick(MenuItem item){

                        switch (item.getItemId()){

                            case R.id.editItemMenuId:
                                Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.deleteItemMenuId:
                                Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                                return true;
                        }


                        return false;
                    }
                });


                popupMenu.show();

            }
        });






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_inventory, menu);
        return super.onCreateOptionsMenu(menu);
        // return true;
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
