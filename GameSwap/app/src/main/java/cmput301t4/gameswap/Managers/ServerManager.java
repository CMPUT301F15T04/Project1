package cmput301t4.gameswap.Managers;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.serverTools.ElasticSearchSearchResponse;

/**
 * Created by dren on 11/2/15.
 */


public class ServerManager {
    /*
    Servers will not be able to run on the main UI thread. Trying to call these functions from any views will
    probably create errors, working on fixing that.
    */

    private static boolean foundResult = Boolean.FALSE;

    /**
     * Get the user from with the username given
     * and set the User to it.
     * @param username
     */
    public static void getUserOnline(final String username){     //Access Server function

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
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
        };
        Thread serverThread = new Thread(runnable);
        serverThread.start();

    }//end getUserOnline

    /**
     * Adapted from https://github.com/rayzhangcl/ESDemo on November 20, 2015
     *
     * @param username
     * @return
     */
    public static void searchForUser(final String username) {
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpclient = new DefaultHttpClient();
                HttpGet searchRequest = new HttpGet("http://cmput301.softwareprocess.es:8080/cmput301f15t04/_search?pretty=1&q=" + username);
                searchRequest.setHeader("Accept", "application/json");
                HttpResponse response = null;

                try {
                    response = httpclient.execute(searchRequest);
                } catch (IOException e) {
                    throw new RuntimeException();
                }
                Gson gson = new Gson();

                String json = null;

                try {
                    json = getEntityContent(response);
                } catch (IOException e) {
                    throw new RuntimeException();
                }

                Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<User>>(){}.getType();
                ElasticSearchSearchResponse<User> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);

                System.out.println(esResponse.getHits().size());

                if(esResponse.getHits().size() != 0) { ServerManager.resultFound(); } else {ServerManager.resultNotFound();}
            }
        });

        serverThread.start();
        
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    private static void resultFound() {foundResult = Boolean.TRUE;}

    private static void resultNotFound() {foundResult = Boolean.FALSE;}

    public static boolean checkResult() {return foundResult;}

    /**
     * Loads user into server
     * @param user
     */
    public static void saveUserOnline(final User user){//code obtained from elastic search and ESDemo
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
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
        };

        Thread serverThread = new Thread(runnable);
        serverThread.start();

    }//end saveUserOnline

    /**
     * get the http response and return json string
     * Taken from https://github.com/rayzhangcl/ESDemo on November 20, 2015
     */
    private static String getEntityContent(HttpResponse response) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        String output;
        System.err.println("Output from Server -> ");
        String json = "";
        while ((output = br.readLine()) != null) {
            System.err.println(output);
            json += output;
        }
        System.err.println("JSON:" + json);
        return json;
    }

    public static void getFriendOnline(final String username){     //Access Server function

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
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
                UserManager.setFriend(sr);
            }
        };
        Thread serverThread = new Thread(runnable);
        serverThread.start();

    }//end getUserOnline

}//end Server Manager
