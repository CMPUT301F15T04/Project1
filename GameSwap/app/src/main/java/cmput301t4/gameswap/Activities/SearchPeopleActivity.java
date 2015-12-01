package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import org.apache.commons.logging.Log;

import cmput301t4.gameswap.Managers.FriendManager;
import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.UserListManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.FriendList;
import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.Models.UsersList;
import cmput301t4.gameswap.R;

public class SearchPeopleActivity extends Activity {

    Button sButton;
    EditText traderEditText;
    String traderName;
    private SearchView search;
    private FriendList friendList;

    private SearchPeopleActivity activity = this;


    private User user_1;
    private User user_2;
    private User user_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_people);

        friendList = UserManager.getTrader().getFriendList();
        /**
         * Code from - http://sampleprogramz.com/android/searchview.php
         */
        search = (SearchView) findViewById(R.id.searchView);
        search.setQueryHint("Search Trader");
        search.setIconifiedByDefault(false);

        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(getBaseContext(), query, Toast.LENGTH_SHORT).show();

                findTrader(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void findTrader(final String trader){
        //traderName = trader;
        //traderName = search.getQuery().toString();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ServerManager.searchForUser(trader);

            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        //search.setQuery("",false);

        String user  = UserManager.getTrader().getUserName();
        //Toast.makeText(getBaseContext(), name, Toast.LENGTH_SHORT).show();
        int size = UserManager.getTrader().getFriendList().getFriendlistSize();
        String sizeString  = Integer.toString(size);
        android.util.Log.e("size",sizeString);

        android.util.Log.e("name",user);
        int i =0;
        //UserManager.getTrader().getFriendList().hasFriend(trader)
        if(UserManager.getTrader().getFriendList().hasFriend(trader)== Boolean.TRUE){
            Intent intent = new Intent(SearchPeopleActivity.this, FriendProfileActivity.class);
            intent.putExtra("isfriend", Boolean.TRUE);
            ServerManager.getFriendOnline(trader);
            //intent.putExtra("name", traderName.toLowerCase());
            activity.finish();
            startActivity(intent);

        } else if(ServerManager.checkResult()) {
            Intent intent = new Intent(SearchPeopleActivity.this, FriendProfileActivity.class);
            intent.putExtra("isfriend", Boolean.FALSE);
            ServerManager.getFriendOnline(trader);
            //intent.putExtra("name", traderName.toLowerCase());
            activity.finish();
            startActivity(intent);

        } else {
            Toast.makeText(getBaseContext(), "No user exist", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onPause(){
        super.onPause();
    }

}
