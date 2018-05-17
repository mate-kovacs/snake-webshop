package com.lordofstrings.netconnector;

import com.codecool.snake.Globals;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpRequester {
    private HttpURLConnection connection = null;
    private URL URL_ADDRESS = null;

    public HttpRequester(String url) {
        try {
            URL_ADDRESS = new URL(url);
        } catch (MalformedURLException e) {
            System.out.println("Wrong URL string");
            e.printStackTrace();
        }
    }

    private void connectServer() {
        try {
            connection = (HttpURLConnection) URL_ADDRESS.openConnection();
        } catch (IOException e) {
            System.out.println("Cannot connect URL");
            e.printStackTrace();
        }
    }

    public String sendPostRequest(String parameters) {
        connectServer();
        try {
            composePostRequestHeader(parameters);

            DataOutputStream connectionDataOutputStream = new DataOutputStream(connection.getOutputStream());
            connectionDataOutputStream.writeBytes(parameters);
            connectionDataOutputStream.flush();

            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + connection.getURL());
            System.out.println("Post content type : " + connection.getRequestProperty("Content-Type"));
            System.out.println("Post parameters : " + parameters);
            System.out.println("Response Code : " + responseCode);
        } catch (IOException e) {
            System.out.println("Error sending data");
        }

        StringBuilder serverResponse = new StringBuilder();

        try (BufferedReader connectionDataInputStream = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));){
            String inputLine;
            while ((inputLine = connectionDataInputStream.readLine()) != null) {
                serverResponse.append(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Error receiving data");
        }

        connection.disconnect();
        return serverResponse.toString();

    }

    private void composePostRequestHeader(String urlParameters) {
        try {
            connection.setRequestMethod("POST");
            //connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Language", "en-US");
            //connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
            connection.setDoOutput(true);
            connection.setDoInput(true);
        } catch (ProtocolException e) {
            System.out.println("Protocol exception");
            e.printStackTrace();
        }
    }

    //For testing
    public static void main(String[] args) {
        HttpRequester productAdder = new HttpRequester(Globals.SHOP_ADDRESS);
        productAdder.sendPostRequest("id=30");
    }
}
