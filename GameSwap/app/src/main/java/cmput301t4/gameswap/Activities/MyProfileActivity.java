package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.R;

public class MyProfileActivity extends Activity {

    private Button editProfileButton;
    private TextView nameTextView;
    private TextView locationTextView;
    private TextView contactTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        editProfileButton = (Button) findViewById(R.id.editProfile);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);
        contactTextView = (TextView) findViewById(R.id.contactTextView);

        nameTextView.setText(UserManager.getTrader().getUserName().toString());
        locationTextView.setText(UserManager.getTrader().getUserCity().toString());
        contactTextView.setText(UserManager.getTrader().getUserEmail()+ "  "+UserManager
                .getTrader().getUserPhoneNumber());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_profile, menu);
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


    public void editProfileButtonClicked(View view){
        Intent intent = new Intent(MyProfileActivity.this,EditProfileActivity.class);
        startActivity(intent);
        finish();
    }


}
