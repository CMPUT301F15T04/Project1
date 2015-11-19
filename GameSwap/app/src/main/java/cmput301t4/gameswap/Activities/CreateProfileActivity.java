package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.R;

/**
 * Created by Blake on 2015-11-05.
 */
public class CreateProfileActivity extends Activity {
    private User myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
    }

    public void createProfileButton(View view) {
        EditText usernameEditText = (EditText) findViewById(R.id.username);
        EditText emailEditText = (EditText) findViewById(R.id.email_user);
        EditText cityEditText = (EditText) findViewById(R.id.user_city);
        EditText phoneNumberEditText = (EditText) findViewById(R.id.user_phone_num);
        UserManager.createUser(usernameEditText.getText().toString(), emailEditText.getText().toString(), cityEditText.getText().toString(), phoneNumberEditText.getText().toString(), this);
        ServerManager.saveUserOnline(UserManager.getTrader());
        this.finish();
    }

    public void cancelButton(View view) {
        this.finish();
    }

    public User getUser() {
        return myUser;
    }
}
