package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cmput301t4.gameswap.Managers.FriendManager;
import cmput301t4.gameswap.R;

public class AddFriendActivity extends Activity {

    private Button addTrader;
    private String traderName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        addTrader = (Button) findViewById(R.id.addtrader);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b != null) {
          traderName = (b.getString("name"));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_friend, menu);
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

    public void addTraderClicked(View v){
        FriendManager.addFriend(traderName);
        Toast.makeText(getBaseContext(), "Added", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AddFriendActivity.this,FriendProfileActivity.class);
        intent.putExtra("name", traderName);
        startActivity(intent);
        finish();

    }


}
