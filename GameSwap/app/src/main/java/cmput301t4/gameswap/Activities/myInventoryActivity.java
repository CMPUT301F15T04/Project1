package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.R;

public class myInventoryActivity extends Activity{

    private ListView myInventoryListView;
    private ArrayAdapter<String> adapter;
    //private ArrayList<Item> inventory;
    private ArrayList<String> nameOfItemsList;
   // private ArrayList<String> statusOfItemsList;
    protected int myInventoryListViewPosition;

    private myInventoryActivity activity = this;

    private int itemID;
    private String Name;
    /** A description of the Item */
    private String Description;
    /** The date when the game was released for purchase */
    private String ReleaseDate;
    /** Quality of the game*/
    private String Quality;
    /*IsPRivate */
    private Boolean IsPrivate;
    /*Platform of the game*/
    private String Platform;

    private static final String FILENAME = "file.sav"; // model

    //=====Varibles used in testcase=====//

    private PopupMenu popupMenu;
    private AlertDialog.Builder alert;
    private AlertDialog alertDialog;
    private ArrayList<String> items;
    private ArrayList<Item> inventory;
    //=====End Varibles used in testcases=====//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_inventory);

        myInventoryListView = (ListView) findViewById(R.id.myInventoryListView);

        myInventoryListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                myInventoryListViewPosition = position;
                popupMenu = new PopupMenu(myInventoryActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.myinventoryitempopup, popupMenu.getMenu());


                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.editItemMenuId:
                                //Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();

                                Name = InventoryManager.getItem(myInventoryListViewPosition).getName();
                                Description = InventoryManager.getItem(myInventoryListViewPosition).getDescription();
                                ReleaseDate = InventoryManager.getItem(myInventoryListViewPosition).getReleaseDate();
                                itemID = InventoryManager.getItem(myInventoryListViewPosition).getItemid();

                                final Intent intent = new Intent(myInventoryActivity.this, EditItemActivity.class);
                                intent.putExtra("name", Name);
                                intent.putExtra("description", Description);
                                intent.putExtra("releaseDate", ReleaseDate);
                                intent.putExtra("index", myInventoryListViewPosition);
                                intent.putExtra("itemId", itemID);
                                startActivity(intent);

                                return true;
                            case R.id.deleteItemMenuId:
                                alert = new AlertDialog.Builder(myInventoryActivity.this);
                                alert.setMessage("Are you sure, you want to delete this item");

                                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

                                        InventoryManager.delItem(myInventoryListViewPosition);
                                        ServerManager.saveUserOnline(UserManager.getTrader());
                                        resetAdapter();


                                    }

                                });
                                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
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
                Name = InventoryManager.getItem(position).getName();
                Description = InventoryManager.getItem(position).getDescription();
                ReleaseDate = InventoryManager.getItem(position).getReleaseDate();
                Platform = InventoryManager.getItem(position).getPlatform().toString();
                IsPrivate = InventoryManager.getItem(position).getIsPrivate();
                Quality = InventoryManager.getItem(position).getQuality().toString();

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
    public void onResume(){
        super.onResume();
        resetAdapter();
    }


    @Override
      protected void onStart(){

        super.onStart();

        myInventoryListView = (ListView) findViewById(R.id.myInventoryListView);
        inventory = InventoryManager.getItems();
        nameOfItemsList = InventoryManager.getItemsNames();
        adapter = new ArrayAdapter<String>(this,R.layout.myinventorylistviewtext, nameOfItemsList);
        myInventoryListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void addNewItem(View view){
        Intent intent = new Intent(myInventoryActivity.this,AddItemActivity.class);
        startActivity(intent);
        this.finish();

    }

    private void resetAdapter(){
        nameOfItemsList = UserManager.getTrader().getInventory().getItemsNames();
        adapter = new ArrayAdapter<String>(this,R.layout.myinventorylistviewtext, nameOfItemsList);
        myInventoryListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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
