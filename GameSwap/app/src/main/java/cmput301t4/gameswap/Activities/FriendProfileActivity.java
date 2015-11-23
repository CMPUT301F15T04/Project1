package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cmput301t4.gameswap.Managers.FriendManager;
import cmput301t4.gameswap.R;

public class FriendProfileActivity extends Activity {

    private Button removeTraderButton;
    private TextView traderNameTextView;
    private String traderName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        removeTraderButton = (Button)findViewById(R.id.removeTraderButton);
        traderNameTextView = (TextView) findViewById(R.id.traderNameTextView);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b != null) {
            traderNameTextView.setText(b.getString("name"));
        }
        traderName = traderNameTextView.getText().toString();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_friend_profile, menu);
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
    protected void onPause() {
        super.onPause();

        this.finish();
    }

    public void removeTraderButtonClicked(View view){
        //int index;
        //index = FriendManager.getFriendIndex(traderName);
       // FriendManager.delFriend(index);
    }
}
