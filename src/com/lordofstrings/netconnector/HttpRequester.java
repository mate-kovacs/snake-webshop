package com.lordofstrings.netconnector;

import com.codecool.snake.utils.JsonParserProEdition;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

public class HttpRequester {
    private HttpURLConnection connection = null;

    public HttpRequester(String url) {
        try {
            URL UrlObj = new URL(url);
            connection = (HttpURLConnection) UrlObj.openConnection();
        } catch (MalformedURLException e) {
            System.out.println("Wrong URL string");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Cannot connect URL");
            e.printStackTrace();
        }
    }

    public String sendPostRequest(String parameters) {
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
        HttpRequester productAdder = new HttpRequester("http://localhost:8080/");
        productAdder.sendPostRequest("id=30");
    }
}
