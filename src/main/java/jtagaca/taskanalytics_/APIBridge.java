package jtagaca.taskanalytics_;


import com.google.gson.JsonObject;
import okhttp3.*;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;

public class APIBridge {
    public static String url = "http://localhost:9004/index.php";
    private int UserID;
    public static int user_id;
//    static OkHttpClient httpClient = new OkHttpClient();
//    httpClient.setConnectTimeout(15, TimeUnit.SECONDS);

    static OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS).build();
    public static boolean Register(String username, String password) throws IOException {


//    aysnc issue
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .add("Register", "true")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "OkHttp Bot")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            String res = response.body().string();
            if (res.contains("success")) {
                System.out.println("Registration Successful");
                msgbox("Registration Successful");
                return true;
            } else {
                System.out.println("Registration Failed");
                msgbox("Registration Failed");
                return false;
            }

        }
    }
//        HttpPost post = new HttpPost(url);


    private static void msgbox(String s){
        JOptionPane.showMessageDialog(null, s);
    }

    public static boolean Login(String username, String password) {
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .add("Login", "true")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "OkHttp Bot")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            String res = response.body().string();
            if (res.contains("success")) {
                System.out.println("Login Successful");
//                JSONParser parser = new JSONParser();
                JsonObject jobj = new Gson().fromJson(res, JsonObject.class);
                user_id = jobj.get("user_id").getAsInt();

//                JSONObject json = (JSONObject) parser.parse(stringToParse);

                msgbox("Login Successful");
                response.body().close();
                return true;
            } else {
                System.out.println("Login Failed");
                msgbox("Login Failed");
                response.body().close();

                return false;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static JSONArray getUser (String username) {
        RequestBody formBody = new FormBody.Builder()
                .add("GetAllTodo", "true")
                .add("user_id", String.valueOf(user_id))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "OkHttp Bot")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            String res = response.body().string();
            if (res.contains("todo")) {
                JSONArray jsonArray = new JSONArray(res);;
//                new json object
                response.body().close();
                return jsonArray;

            } else if ( res.contains("no todos")      ) {
                msgbox("No Todos");
                return null;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    public static boolean updateTodo(String todoName, float elapsedTime) {
        RequestBody formBody = new FormBody.Builder()
                .add("UpdateTodo", "true")
                .add("user_id", String.valueOf(user_id))
                .add("todo", todoName)
                .add("timespent", String.valueOf(elapsedTime))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "OkHttp Bot")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            String res = response.body().string();
            if (res.contains("success")) {
                System.out.println("Update Successful");
                msgbox("Update Successful");
                response.body().close();
                return true;
            } else {
                System.out.println("Update Failed");
                msgbox("Update Failed");
                response.body().close();

                return false;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private void sendGet() throws Exception {

        Request request = new Request.Builder()
                .url("https://www.google.com/search?q=mkyong")
                .addHeader("custom-key", "mkyong")  // add request headers
                .addHeader("User-Agent", "OkHttp Bot")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            System.out.println(response.body().string());
        }

    }


//
//        // add request parameter, form parameters
//        List<NameValuePair> urlParameters = new ArrayList<>();
//        urlParameters.add(new BasicNameValuePair("username", username));
//        urlParameters.add(new BasicNameValuePair("password", password));
//        urlParameters.add(new BasicNameValuePair("Register", "true"));
//
//        post.setEntity(new UrlEncodedFormEntity(urlParameters));
//
//        try (CloseableHttpClient httpClient = HttpClients.createDefault();
//             CloseableHttpResponse response = httpClient.execute(post)) {
//
//            System.out.println(EntityUtils.toString(response.getEntity()));
//        } catch (ClientProtocolException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }







}
