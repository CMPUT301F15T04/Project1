package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.R;

public class EditProfileActivity extends Activity {


    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        TextView nameEditText = (TextView) findViewById(R.id.changeName);
        EditText emailEditText = (EditText) findViewById(R.id.changeEmail);
        EditText cityEditText = (EditText) findViewById(R.id.changeCity);
        EditText phoneEditText = (EditText) findViewById(R.id.changePhone);

        nameEditText.setText(UserManager.getTrader().getUserName());
        emailEditText.setText(UserManager.getTrader().getUserEmail());
        cityEditText.setText(UserManager.getTrader().getUserCity());
        phoneEditText.setText(UserManager.getTrader().getUserPhoneNumber());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    public void addImageOption(View view) {
        final ImageButton takeProfPic = (ImageButton) findViewById(R.id.newProfilePicture);
        takeProfPic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView gameImageView = (ImageView) findViewById(R.id.newProfPicView);
            gameImageView.setImageBitmap(imageBitmap);
        }
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

    public void editProfileButton (View view) {
        TextView nameEditText = (TextView) findViewById(R.id.changeName);
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
