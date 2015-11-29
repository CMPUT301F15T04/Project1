package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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
import java.util.List;

import cmput301t4.gameswap.Managers.FriendManager;
import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Managers.UserListManager;
import cmput301t4.gameswap.Models.FriendList;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.Models.UsersList;
import cmput301t4.gameswap.R;

public class selectTaskActivity extends Activity {

    private MenuItem Actionmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_task);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_select_task, menu);
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
        else if(id == R.id.my_profile){
            Intent intent = new Intent(selectTaskActivity.this, MyProfileActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void openInventoryButton(View view){
        Intent intent = new Intent(selectTaskActivity.this,myInventoryActivity.class);
        startActivity(intent);
    }
    public void openFriendsButton(View view){
        Intent intent = new Intent(selectTaskActivity.this,FriendsNTradingActivity.class);
        startActivity(intent);
    }
    public void tradeOffersButton(View view){
        Intent intent = new Intent(selectTaskActivity.this,TradesActivity.class);
        startActivity(intent);
    }

    //=====Function needed for Testcases=====//

    public Button getInventoryButton(){
        Button button = (Button) findViewById(R.id.inventory);
        return button;
    }//end getInventoryButton


    //=====End Function needed for Testcases
}
