package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.R;

public class EditProfileActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        EditText nameEditText = (EditText) findViewById(R.id.changeName);
        EditText cityEditText = (EditText) findViewById(R.id.changeCity);
        EditText phoneEditText = (EditText) findViewById(R.id.changePhone);



        nameEditText.setText(UserManager.getTrader().getUserName());
        cityEditText.setText(UserManager.getTrader().getUserCity());
        phoneEditText.setText(UserManager.getTrader().getUserPhoneNumber());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
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

    public void clickSaveButton(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.changeName);
        EditText cityEditText = (EditText) findViewById(R.id.changeCity);
        EditText phoneEditText = (EditText) findViewById(R.id.changePhone);

        UserManager.editUserName(nameEditText.getText().toString());
        UserManager.editCity(cityEditText.getText().toString());
        UserManager.editPhoneNumber(phoneEditText.getText().toString());



        this.finish();
    }

    public void clickCancelButton(View view) {
        this.finish();
    }
}
