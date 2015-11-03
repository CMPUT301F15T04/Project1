package cmput301t4.gameswap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dren on 11/2/15.
 */


public class AccessServer {

    public static void getServer(){
        String url = "http://cmput301.softwareprocess.es:8080/testing/CMPUT301F15T04/name/_source";

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);
        } catch (ClientProtocolException e1) {
            throw new RuntimeException(e1);
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
        BufferedReader rd = null;
        try {
            rd = new BufferedReader((new InputStreamReader((response.getEntity().getContent()))));
            String line = rd.readLine();
            System.out.println(line);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        /*String sr = "";
        Gson gson = new Gson();
        TypeToken<List<Item>> list = new TypeToken<List<Item>>() {};

        try {
            sr = gson.fromJson(new InputStreamReader((response.getEntity().getContent())), list);
        } catch (JsonIOException e) {
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

    }

}
