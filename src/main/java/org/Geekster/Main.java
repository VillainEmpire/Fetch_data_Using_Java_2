package org.Geekster;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        String urlString = "https://api.zippopotam.us/us/33162";

        URL url = null;
        HttpURLConnection connection = null;
        int responseCode = 0;

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            System.out.println("URL was incorrect.");
        }
        try {
            connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();

        } catch (IOException e) {
            System.out.println("Unable to setup connection.");
        }

        if(responseCode==200){
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder apiData = new StringBuilder();
            String readLine = null;
            while((readLine=in.readLine())!=null){
                apiData.append(readLine);
            }
            in.close();

            JSONObject apiResponseData = new JSONObject(apiData.toString());
            System.out.println(apiResponseData.get("places"));
        }
        else{
            System.out.println("Api call could not be made.");
        }

    }
}