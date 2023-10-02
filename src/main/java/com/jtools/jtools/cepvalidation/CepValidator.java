package com.jtools.jtools.cepvalidation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CepValidator {

    public boolean isCepValid(String cep) {
        try {
            URL url = new URL("https://viacep.com.br/ws/01001000/json/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader response = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonstring = convertJsonString(response);

            System.out.println(jsonstring);
            System.out.println(response);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    private static String convertJsonString(BufferedReader bufferedReader) throws IOException{
        String response, jsonString = "";

        while ((response= bufferedReader.readLine()) != null){
            jsonString += response;
        }
        return jsonString;
    }
}
