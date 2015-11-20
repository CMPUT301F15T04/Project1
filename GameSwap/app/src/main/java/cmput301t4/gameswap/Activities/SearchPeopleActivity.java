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

import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.R;

public class SearchPeopleActivity extends Activity {

    Button sButton;
    EditText traderEditText;
    String traderName;
    private SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_people);


        //creating some dummy users
        User user_1 = new User("Kynan", "kynan@ualberta.ca", "Edmonton", "780-999-8888");
        User user_2 = new User("Daneil", "dren@ualberta.ca", "Edmonton", "780-999-8887");
        User user_3 = new User("Preyanshu", "pre@ualberta.ca", "Edmonton", "780-999-8886");

        /**
         * Code from - http://sampleprogramz.com/android/searchview.php
         */

        search = (SearchView) findViewById(R.id.searchView);
        search.setQueryHint("Search Trader");
        search.setIconifiedByDefault(false);

        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //Toast.makeText(getBaseContext(), "searching...", Toast.LENGTH_SHORT).show();
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(getBaseContext(), query,
                //Toast.LENGTH_SHORT).show();
                findTrader(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void findTrader(String trader){
        traderName = search.getQuery().toString();
        //Toast.makeText(getBaseContext(), traderName, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SearchPeopleActivity.this,FriendProfileActivity.class);
        startActivity(intent);
        this.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_search_people, menu);
        return true;
    }

    @Override
    public void onPause(){
        super.onPause();
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
