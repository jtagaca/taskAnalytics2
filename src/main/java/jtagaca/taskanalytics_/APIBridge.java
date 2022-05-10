package jtagaca.taskanalytics_;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class APIBridge
{
    private String apiURL ="http://localhost:9003";
//    private WeatherModel weatherModel = new WeatherModel();
//    private UIBind uiBind;


    //    limiting the functions that uiBind can call,  we then use API Bridge create the model then se using UIBind
    public APIBridge() {
//         what does this do? still do not understand the calls of uiBind
//        this.uiBind = uiBind;


//        we are trying to see and read from the json file
//        try (Scanner scanner = new Scanner(new File("api.json"))) {
////            much better way of concatenation since strings are immutable
//            StringBuilder apiJSONString = new StringBuilder();
//
//            while (scanner.hasNextLine()) {
//                apiJSONString.append(scanner.nextLine());
//
//            }

//            JSONObject jsonObject = new JSONObject(apiJSONString.toString());
////            this.apiKey = jsonObject.getString("apikey");
////             setting will need this?
//            this.geocodingURL = jsonObject.getString("geocodingURL");
//            this.weatherURL = jsonObject.getString("weatherURL");
//            this.weatherIconURL = jsonObject.getString("weatherIconURL");
//
////            System.out.println(geocodingURL);
//
////            System.out.println(weatherURL);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }




    }

    public static void LoginUser(String username, String password) {

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://www.a-domain.com/foo/");

// Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("param-1", "12345"));
        params.add(new BasicNameValuePair("param-2", "Hello!"));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

//Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            try (InputStream instream = entity.getContent()) {
                // do something useful
            }
        }


    }

    private void getUserData(String lat, String lon) {
        HttpClient client = HttpClient.newBuilder().build();
        String URL = String.format(weatherURL,
                lat, lon, apiKey);
//        System.out.println(URL);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL)).header("Content-Type", "application/json").build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    JSONObject tmp = new JSONObject(response.body());
                    JSONObject weather = tmp.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = tmp.getJSONObject("main");

                    JSONObject wind = tmp.getJSONObject("wind");
                    weatherModel.setWindSpeed(wind.getDouble("speed"));
                    weatherModel.setWindDirection(wind.getInt("deg"));

                    weatherModel.setWeatherIcon(String.format(weatherIconURL, weather.getString("icon")));
                    weatherModel.setWeatherDescription(weather.getString("description"));


                    weatherModel.setTemp(main.getDouble("temp"));
                    weatherModel.setTempMin(main.getDouble("temp_min"));
                    weatherModel.setHumidity(main.getInt("humidity"));
                    weatherModel.setPressure(main.getInt("pressure"));
                    weatherModel.setFeelsLike(main.getDouble("feels_like"));
                    weatherModel.setTempMax(main.getDouble("temp_max"));

                    uiBind.mapUI(weatherModel);
                    return response.body();
                });
    }

    public WeatherModel getWeatherModel() {
        return weatherModel;
    }

    public void setWeatherModel(WeatherModel weatherModel) {
        this.weatherModel = weatherModel;
    }

    //    creating a client to make url gets
    public void GenerateWeatherModel(String loc) {
        HttpClient client = HttpClient.newBuilder().build();
//        we are just using a format since the we created variables for the loc in the api.json
//         why was there no this in the geocodingURL
//        "this" will tell that the variable will be in this class
        String URL = String.format(geocodingURL,
                URLEncoder.encode(loc, StandardCharsets.UTF_8), apiKey);
//        System.out.println(URL);

//        creating a way to request using the URL

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL)).header("Content-Type", "application/json").build();
//        using an async will only do the after once it recieves an information from the requested url
//        the traditional way is having the application wait or lock up while it is feetching the request

//        QUESTION what does the httpResponse.bodyhandlers.ofstring do again does it store the response?
//        this is a way to store the response, there are different ways to
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    JSONArray tmp = new JSONArray(response.body());
                    weatherModel.setLat(tmp.getJSONObject(0).getDouble("lat"));
                    weatherModel.setLon(tmp.getJSONObject(0).getDouble("lon"));
                    getWeather(String.valueOf(weatherModel.getLat()), String.valueOf(weatherModel.getLon()));
//                    QUESTION why does this not work
//                    System.out.print(String.valueOf(weatherModel.getLat()) );
//                    weatherModel.getLon();
//                    uiBind.mapUI(weatherModel);
                    return response.body();
                });


    }
}
