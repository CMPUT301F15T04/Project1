package cmput301t4.gameswap.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.R;

public class myInventoryActivity extends Activity{

    private ListView myInventoryListView;
    private ArrayAdapter<Item> adapter;
    private ArrayList<Item> inventory;
    protected int myInventoryListViewPosition;
    private InventoryManager im;

    private myInventoryActivity activity = this;

    private String Name;
    /** A description of the Item */
    private String Description;
    /** The date when the game was released for purchase */
    private Date ReleaseDate;
    /** Quality of the game*/
    private String Quality;
    /*IsPRivate */
    private String IsPrivate;
    /*Platform of the game*/
    private String Platform;

    private static final String FILENAME = "file.sav"; // model

    //=====Varibles used in testcase=====//

    private PopupMenu popupMenu;
    private AlertDialog.Builder alert;
    private AlertDialog alertDialog;
    //=====End Varibles used in testcases=====//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_inventory);

        myInventoryListView = (ListView) findViewById(R.id.myInventoryListView);

        myInventoryListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                popupMenu = new PopupMenu(myInventoryActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.myinventoryitempopup, popupMenu.getMenu());

                myInventoryListViewPosition = position;

                inventory = InventoryManager.getInstance().getItems();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.editItemMenuId:
                                //Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();

                                Name = inventory.get(myInventoryListViewPosition).getName().toString();
                                Description = inventory.get(myInventoryListViewPosition).getDescription().toString();
                                ReleaseDate = inventory.get(myInventoryListViewPosition).getReleaseDate();

                                final Intent intent = new Intent(myInventoryActivity.this, EditItemActivity.class);
                                intent.putExtra("name", Name);
                                intent.putExtra("description", Description);
                                intent.putExtra("releaseDate", ReleaseDate.toString());
                                intent.putExtra("index", myInventoryListViewPosition);
                                activity.finish();
                                startActivity(intent);

                                return true;
                            case R.id.deleteItemMenuId:

                                //final AlertDialog.Builder alert = new AlertDialog.Builder(myInventoryActivity.this);
                                alert = new AlertDialog.Builder(myInventoryActivity.this);
                                alert.setMessage("Are you sure, you want to delete this item");

                                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

                                        inventory.remove(myInventoryListViewPosition);
                                        resetAdapter();
                                    }

                                });
                                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // finish();
                                        dialog.dismiss();
                                    }
                                });

                                alertDialog = alert.create();
                                alertDialog.show();

                                return true;

                            default:
                                ;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                return false;
            }
        });

        myInventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Name = inventory.get(position).getName().toString();
                Description = inventory.get(position).getDescription().toString();
                ReleaseDate = inventory.get(position).getReleaseDate();
                Platform = inventory.get(position).getPlatform().toString();
                IsPrivate = inventory.get(position).getIsPrivate().toString();
                Quality = inventory.get(position).getQuality().toString();

                final Intent intent = new Intent(myInventoryActivity.this, ViewItemActivity.class);
                intent.putExtra("name", Name);
                intent.putExtra("description", Description);
                intent.putExtra("releaseDate", ReleaseDate);
                intent.putExtra("index", myInventoryListViewPosition);
                intent.putExtra("quality",Quality);
                intent.putExtra("private",IsPrivate);
                intent.putExtra("platform",Platform);

                startActivity(intent);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_my_inventory, menu);
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

    @Override
      protected void onStart(){

        super.onStart();
        loadFromFile();
        myInventoryListView = (ListView) findViewById(R.id.myInventoryListView);
        inventory = InventoryManager.getInstance().getItems();
        adapter = new ArrayAdapter<Item>(this,R.layout.myinventorylistviewtext, inventory);

        myInventoryListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void addNewItem(View view){

        Intent intent = new Intent(myInventoryActivity.this,AddItemActivity.class);
        startActivity(intent);
        this.finish();

    }

    private void resetAdapter(){

        adapter = new ArrayAdapter<Item>(this,R.layout.myinventorylistviewtext, inventory);
        myInventoryListView.setAdapter(adapter);
        saveToFile();

    }

    private void loadFromFile(){

        try {

            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //code referenced from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type arraylistType = new TypeToken<ArrayList<Item>>() {}.getType();
            ArrayList<Item> items = gson.fromJson(in, arraylistType);
            InventoryManager.getInstance().setItems(items);
        } catch (FileNotFoundException e) {
            ArrayList<Item> items = InventoryManager.getInstance().getItems();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void saveToFile() {

        try {
            ArrayList<Item> items = InventoryManager.getInstance().getItems();
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(items, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }

    }

    //=====Function needed for Testcases=====//

    public Button getAddItemButton(){
        Button button  = (Button) findViewById(R.id.button9);
        return button;
    }//end getAdditem

    public ListView getInventoryList(){
        ListView list = (ListView) findViewById(R.id.myInventoryListView);
        return list;
    }//end getInventoryList

    public Menu getPopupMenu(){
        Menu menu = popupMenu.getMenu();
        return menu;
    }//end PopupMenu

    public AlertDialog getAlertDialog(){
        return alertDialog;
    }//end getAlertDialog

    //=====End Function needed for Testcases=====//

}
