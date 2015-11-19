package cmput301t4.gameswap.Managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cmput301t4.gameswap.Models.User;

/**
 * Created by dren on 11/2/15.
 */


public class ServerManager {
    /*
    Servers will not be able to run on the main UI thread. Trying to call these functions from any views will
    probably create errors, working on fixing that.
    */



    /**
     * Get the user from with the username given
     * and set the User to it.
     * @param username
     */
    public static void getUserOnline(String username){     //Access Server function
        String url = "http://cmput301.softwareprocess.es:8080/cmput301f15t04/users/" + username + "/_source";
        System.out.println(url);
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = null;

        try {                           //run URL
            response = httpClient.execute(httpGet);
        } catch (ClientProtocolException e1) {
            throw new RuntimeException(e1);
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
        BufferedReader rd = null;
        User sr = null;
        Gson gson = new Gson();

        try {
            rd = new BufferedReader((new InputStreamReader((response.getEntity().getContent()))));
            //String line = rd.readLine();
            //System.out.println(line);
            sr = gson.fromJson(rd, User.class);
            System.out.println(sr.getUserName() + " username from servermanager");
        } catch (JsonIOException e) {
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UserManager.setTrader(sr);
    }

    /**
     * Loads user into server
     * @param user
     */
    public static void saveUserOnline(User user){//code obtained from elastic search and ESDemo
        String url = "http://cmput301.softwareprocess.es:8080/cmput301f15t04/users/" + user.getUserName();
        HttpClient httpclient = new DefaultHttpClient();
        Gson gson = new Gson();
        HttpPost httpPost = new HttpPost(url);

        StringEntity stringentity = null;

        try {
            stringentity = new StringEntity(gson.toJson(user));
            System.out.println(gson.toJson(user));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        httpPost.setHeader("Accept","application/json");

        httpPost.setEntity(stringentity);
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String status = response.getStatusLine().toString();
        System.out.println(status);
        HttpEntity entity = response.getEntity();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
            String output;
            System.err.println("Output from Server -> ");
            while ((output = br.readLine()) != null) {
                System.err.println(output);
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
