package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.R;

public class ViewItemActivity extends Activity {

    /*Declaring all the listViews here*/
    private TextView name;
    private TextView quality;
    private TextView descrition;
    private TextView platform;
    private TextView date;
    private Boolean status;
    private String statusDisplay;
    private TextView statusView;
    private ImageView imageView;
    private TextView location;
    private ArrayList<String> platformList;
    private String platformString;
    private Integer platformIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        name  = (TextView) findViewById(R.id.viewItemName);
        quality = (TextView) findViewById(R.id.viewItemQuality);
        descrition = (TextView) findViewById(R.id.viewItemDesciption);
        platform = (TextView) findViewById(R.id.viewItemPlatform);
        date = (TextView) findViewById(R.id.viewItemDate);
        name = (TextView) findViewById(R.id.viewItemName);
        statusView = (TextView) findViewById(R.id.viewStatus);
        location =(TextView) findViewById(R.id.locationDescription);
        imageView = (ImageView) findViewById(R.id.gameImageView);


        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null){
            descrition.setText(b.getString("description"));

            status = b.getBoolean("private");
            if (status == Boolean.TRUE){
                statusDisplay = "Private";
            }
            else {
                statusDisplay = "Public";
            }

            name.setText(b.getString("name"));
            date.setText(b.getString("releaseDate"));
            quality.setText(b.getString("quality"));
            platform.setText(b.getString("platform"));
            statusView.setText(statusDisplay.toUpperCase());
            location.setText("Latitude: " + b.getDouble("Latitude") + ", Longitude: " + b.getDouble("Longitude"));

            ServerManager.loadImage(b.getInt("itemId"));

            //ServerManager.loadImage(8);
            System.out.println("reached load image");
            //byte[] decodeImage = Base64.decode(UserManager.getImage().getImage(), Base64.DEFAULT);

            if(UserManager.imageRdy == 1) {
                byte[] byteArray = UserManager.getImage().getImage();
                Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                imageView.setImageBitmap(image);
            }


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_item, menu);
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


    //=====Function needed for Testcases=====//

    public TextView getNameText() {
        return name;
    }

    public TextView getQualityText() {
        return quality;
    }

    public TextView getDescritionText() {
        return descrition;
    }

    public TextView getPlatformText() {
        return platform;
    }

    public TextView getDateText() {
        return date;
    }

    public String getStatusText() {
        return statusDisplay;
    }

    //=====End function needed for Testcases=====//


}
