package com.lordofstrings.netconnector;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpRequester {
    private HttpURLConnection connection = null;

    private void connectServer(String urlString) {
        URL urlObj = null;
        try {
            urlObj = new URL(urlString);
        } catch (MalformedURLException e) {
            System.out.println("Wrong URL string");
            e.printStackTrace();
        }

        try {
            connection = (HttpURLConnection) urlObj.openConnection();
        } catch (IOException e) {
            System.out.println("Cannot connect URL");
            e.printStackTrace();
        }
    }

    public String sendPostRequest(String url, String parameters) {
        connectServer(url);
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
                new InputStreamReader(connection.getInputStream()));) {
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

    public String sendPostRequest(String url) {
        connectServer(url);

        StringBuilder serverResponse = new StringBuilder();

        try (BufferedReader connectionDataInputStream = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));) {
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
}
