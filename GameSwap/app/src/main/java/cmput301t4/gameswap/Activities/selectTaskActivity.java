package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cmput301t4.gameswap.R;

public class selectTaskActivity extends Activity {

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

        else if(id ==R.id.editProfile){
            Intent intent = new Intent(selectTaskActivity.this,EditProfileActivity.class);
            startActivity(intent);
        }else if(id == R.id.my_profile){
            //put stuff here when you want to view your profile
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
}
