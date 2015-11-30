package cmput301t4.gameswap.Managers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;

import cmput301t4.gameswap.Exceptions.ServerDownException;
import cmput301t4.gameswap.Models.ImageModel;
import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.serverTools.ElasticSearchSearchResponse;

/**
 * Created by dren on 11/2/15.
 */

//MIGHT WANT TO SET TIMEOUT PARAMETERS FOR HTTP OPERATIONS
public class ServerManager {
    /*
    Servers will not be able to run on the main UI thread. Trying to call these functions from any views will
    probably create errors, working on fixing that.
    */

    private static boolean foundResult = Boolean.FALSE;
    private static boolean serverDown;

    /**
     * Get the user from with the username given
     * and set the User to it.
     * @param username
     */
    public static void getUserOnline(final String username){     //Access Server function

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //taken from http://stackoverflow.com/questions/693997/how-to-set-httpresponse-timeout-for-android-in-java
                HttpParams httpParameters = new BasicHttpParams();
                // Set the timeout in milliseconds until a connection is established.
                // The default value is zero, that means the timeout is not used.
                int timeoutConnection = 3000;
                HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
                // Set the default socket timeout (SO_TIMEOUT)
                // in milliseconds which is the timeout for waiting for data.
                int timeoutSocket = 5000;
                HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

                String url = "http://cmput301.softwareprocess.es:8080/cmput301f15t04/users/" + username + "/_source";
                System.out.println(url);
                HttpClient httpClient = new DefaultHttpClient(httpParameters);
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
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }

    }//end getUserOnline

    /**
     * Adapted from https://github.com/rayzhangcl/ESDemo on November 20, 2015
     *
     * @param username
     * @return
     */
    public static void searchForUser(final String username) {
        try {
            Thread serverThread = new Thread(new Runnable() {

                    @Override
                    public void run () {

                    //taken from http://stackoverflow.com/questions/693997/how-to-set-httpresponse-timeout-for-android-in-java
                    HttpParams httpParameters = new BasicHttpParams();
                    // Set the timeout in milliseconds until a connection is established.
                    // The default value is zero, that means the timeout is not used.
                    int timeoutConnection = 3000;
                    HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
                    // Set the default socket timeout (SO_TIMEOUT)
                    // in milliseconds which is the timeout for waiting for data.
                    int timeoutSocket = 5000;
                    HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

                    HttpClient httpclient = new DefaultHttpClient(httpParameters);
                    HttpGet searchRequest = new HttpGet("http://cmput301.softwareprocess.es:8080/cmput301f15t04/_search?pretty=1&q=" + username);
                    searchRequest.setHeader("Accept", "application/json");
                    HttpResponse response = null;

                    try {
                        response = httpclient.execute(searchRequest);
                    } catch (IOException e) {
                        serverIsDown();
                        throw new ServerDownException();
                    }
                    Gson gson = new Gson();


                    String json = null;


                    try {
                        json = getEntityContent(response);
                    } catch (IOException e) {
                        throw new RuntimeException();
                    }

                    Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<User>>() {
                    }.getType();
                    ElasticSearchSearchResponse<User> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);

                    try {
                        System.out.println(esResponse.getHits().size());
                        if (esResponse.getHits().size() != 0) {
                            ServerManager.resultFound();
                        } else {
                            ServerManager.resultNotFound();
                        }
                    } catch (RuntimeException e) {
                        serverIsDown();
                    }

                }//end run

            }//end thread


            );
            try {
                if(serverDown == Boolean.TRUE){
                    serverNotDown();
                    throw new ServerDownException();
                } else {
                    serverNotDown();
                    serverThread.start();
                    serverThread.join();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }

        } catch (ServerDownException e){
            serverIsDown();
            throw new ServerDownException();
        }

    }

    private static void resultFound() {foundResult = Boolean.TRUE;}

    private static void resultNotFound() {foundResult = Boolean.FALSE;}

    public static boolean checkResult() {return foundResult;}

    public static void serverNotDown(){serverDown = Boolean.FALSE;}

    public static void serverIsDown(){serverDown = Boolean.TRUE;}

    public static boolean checkServerStatus(){return serverDown;}

    /**
     * Loads user into server
     * @param user
     */
    public static void saveUserOnline(final User user){//code obtained from elastic search and ESDemo

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    //taken from http://stackoverflow.com/questions/693997/how-to-set-httpresponse-timeout-for-android-in-java
                    HttpParams httpParameters = new BasicHttpParams();
                    // Set the timeout in milliseconds until a connection is established.
                    // The default value is zero, that means the timeout is not used.
                    int timeoutConnection = 3000;
                    HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
                    // Set the default socket timeout (SO_TIMEOUT)
                    // in milliseconds which is the timeout for waiting for data.
                    int timeoutSocket = 5000;
                    HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

                    String url = "http://cmput301.softwareprocess.es:8080/cmput301f15t04/users/" + user.getUserName();
                    HttpClient httpclient = new DefaultHttpClient(httpParameters);
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
                    httpPost.setHeader("Accept", "application/json");

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
                    try {
                        String status = response.getStatusLine().toString();
                        System.out.println(status);
                    } catch (NullPointerException e) {
                        throw new RuntimeException();
                    }
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
                //taken from http://stackoverflow.com/questions/693997/how-to-set-httpresponse-timeout-for-android-in-java
                HttpParams httpParameters = new BasicHttpParams();
                // Set the timeout in milliseconds until a connection is established.
                // The default value is zero, that means the timeout is not used.
                int timeoutConnection = 3000;
                HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
                // Set the default socket timeout (SO_TIMEOUT)
                // in milliseconds which is the timeout for waiting for data.
                int timeoutSocket = 5000;
                HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

                String url = "http://cmput301.softwareprocess.es:8080/cmput301f15t04/users/" + username + "/_source";
                System.out.println(url);
                HttpClient httpClient = new DefaultHttpClient(httpParameters);
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
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }

    }//end getUserOnline

    public static void deleteUserOnline(final String username){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                HttpParams httpParameters = new BasicHttpParams();
                // Set the timeout in milliseconds until a connection is established.
                // The default value is zero, that means the timeout is not used.
                int timeoutConnection = 3000;
                HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
                // Set the default socket timeout (SO_TIMEOUT)
                // in milliseconds which is the timeout for waiting for data.
                int timeoutSocket = 5000;
                HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

                String url = "http://cmput301.softwareprocess.es:8080/cmput301f15t04/users/" + username;
                System.out.println(url);
                HttpClient httpClient = new DefaultHttpClient(httpParameters);
                HttpDelete httpDel = new HttpDelete(url);
                HttpResponse response = null;

                try {                           //run URL
                    response = httpClient.execute(httpDel);
                } catch (ClientProtocolException e1) {
                    throw new RuntimeException(e1);
                } catch (IOException e1) {
                    throw new RuntimeException();
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
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }

    }//end Delete User online

    public static void blakeLoaItemdImage(final int itemid) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //taken from http://stackoverflow.com/questions/693997/how-to-set-httpresponse-timeout-for-android-in-java
                HttpParams httpParameters = new BasicHttpParams();
                // Set the timeout in milliseconds until a connection is established.
                // The default value is zero, that means the timeout is not used.
                int timeoutConnection = 3000;
                HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
                // Set the default socket timeout (SO_TIMEOUT)
                // in milliseconds which is the timeout for waiting for data.
                int timeoutSocket = 5000;
                HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

                String url = "http://cmput301.softwareprocess.es:8080/cmput301f15t04/images/" + UserManager.getTrader().getUserName() + itemid + "/_source";

                HttpClient httpClient = new DefaultHttpClient(httpParameters);
                HttpGet httpGet = new HttpGet(url);
                HttpResponse response = null;
                Gson gson = new Gson();

                try {                           //run URL
                    response = httpClient.execute(httpGet);
                } catch (ClientProtocolException e1) {
                    throw new RuntimeException(e1);
                } catch (IOException e1) {
                    throw new RuntimeException(e1);
                }

                Bitmap image = null;

                try {
                    BufferedReader rd = new BufferedReader((new InputStreamReader((response.getEntity().getContent()))));
                    Byte[] bytes = gson.fromJson(rd, Byte[].class);
                    image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                } catch (JsonIOException e) {
                    throw new RuntimeException(e);
                } catch (JsonSyntaxException e) {
                    throw new RuntimeException(e);
                } catch (IllegalStateException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                //TODO: Store image in the item
            }
        };

        Thread serverThread = new Thread(runnable);
        serverThread.start();

        try {
            serverThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public static void blakeSaveItemImage(final int itemid, final Bitmap image) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //taken from http://stackoverflow.com/questions/693997/how-to-set-httpresponse-timeout-for-android-in-java
                HttpParams httpParameters = new BasicHttpParams();
                // Set the timeout in milliseconds until a connection is established.
                // The default value is zero, that means the timeout is not used.
                int timeoutConnection = 3000;
                HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
                // Set the default socket timeout (SO_TIMEOUT)
                // in milliseconds which is the timeout for waiting for data.
                int timeoutSocket = 5000;
                HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

                String url = "http://cmput301.softwareprocess.es:8080/cmput301f15t04/images/" + image.getImageuserName() + itemid;
                HttpClient httpClient = new DefaultHttpClient(httpParameters);
                HttpPost httpPost = new HttpPost(url);
                HttpResponse response = null;

                Gson gson = new Gson();
                StringEntity stringentity = null;

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                try {
                    stringentity = new StringEntity(gson.toJson(bytes);
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                httpPost.setHeader("Accept","application/json");
                httpPost.setEntity(stringentity);

                try {                           //run URL
                    response = httpClient.execute(httpPost);//BAD HTTP REQUEST HERE
                } catch (ClientProtocolException e1) {
                    throw new RuntimeException(e1);
                } catch (IOException e1) {
                    throw new RuntimeException(e1);
                }
            }
        };

        Thread serverThread = new Thread(runnable);
        serverThread.start();

        try {
            serverThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public static void saveImage(final ImageModel image){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //taken from http://stackoverflow.com/questions/693997/how-to-set-httpresponse-timeout-for-android-in-java
                HttpParams httpParameters = new BasicHttpParams();
                // Set the timeout in milliseconds until a connection is established.
                // The default value is zero, that means the timeout is not used.
                int timeoutConnection = 3000;
                HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
                // Set the default socket timeout (SO_TIMEOUT)
                // in milliseconds which is the timeout for waiting for data.
                int timeoutSocket = 5000;
                HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

                String url = "http://cmput301.softwareprocess.es:8080/cmput301f15t04/images/" + image.getImageuserName() + image.getImageItemId();
                System.out.println(url);
                HttpClient httpClient = new DefaultHttpClient(httpParameters);
                HttpPost httpPost = new HttpPost(url);
                HttpResponse response = null;
                GsonBuilder builder = new GsonBuilder();
                builder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);
                Gson gson = builder.create();
                StringEntity stringentity = null;

                try {
                    stringentity = new StringEntity(gson.toJson(image));
                    System.out.println(gson.toJson(image));
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                httpPost.setHeader("Accept","application/json");

                httpPost.setEntity(stringentity);

                try {                           //run URL
                    response = httpClient.execute(httpPost);//BAD HTTP REQUEST HERE
                } catch (ClientProtocolException e1) {
                    throw new RuntimeException(e1);
                } catch (IOException e1) {
                    throw new RuntimeException(e1);
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

        try {
            serverThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }//end save image

    public static void loadImage(final int item){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //taken from http://stackoverflow.com/questions/693997/how-to-set-httpresponse-timeout-for-android-in-java
                HttpParams httpParameters = new BasicHttpParams();
                // Set the timeout in milliseconds until a connection is established.
                // The default value is zero, that means the timeout is not used.
                int timeoutConnection = 3000;
                HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
                // Set the default socket timeout (SO_TIMEOUT)
                // in milliseconds which is the timeout for waiting for data.
                int timeoutSocket = 5000;
                HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

                String url = "http://cmput301.softwareprocess.es:8080/cmput301f15t04/images/" + UserManager.getTrader().getUserName() + item + "/_source";
                GsonBuilder builder = new GsonBuilder();
                builder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);

                HttpClient httpClient = new DefaultHttpClient(httpParameters);
                HttpGet httpGet = new HttpGet(url);
                HttpResponse response = null;
                System.out.println(url);
                Gson gson = builder.create();

                try {                           //run URL
                    response = httpClient.execute(httpGet);
                } catch (ClientProtocolException e1) {
                    throw new RuntimeException(e1);
                } catch (IOException e1) {
                    throw new RuntimeException(e1);
                }
                BufferedReader rd = null;
                ImageModel image = null;
                //String jsonData = "";

                try {
                    //String line;
                    rd = new BufferedReader((new InputStreamReader((response.getEntity().getContent()))));
                    /*while((line = rd.readLine()) != null){
                        jsonData += line;
                    }*/
                    image = gson.fromJson(rd, ImageModel.class);
                    System.out.println(image.getImageuserName() + " username for picture");
                    UserManager.imageRdy = 1;
                } catch (JsonIOException e) {
                    throw new RuntimeException(e);
                } catch (JsonSyntaxException e) {
                    throw new RuntimeException(e);
                } catch (IllegalStateException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch(NullPointerException e){
                    UserManager.imageRdy = 0;
                }
                if(UserManager.imageRdy == 1){
                System.out.println("This is the name of the image taken" + UserManager.getTrader().getUserName() + item);
                UserManager.setImage(image);}
            }
        };

        Thread serverThread = new Thread(runnable);
        serverThread.start();
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }//end load image

}//end Server Manager
