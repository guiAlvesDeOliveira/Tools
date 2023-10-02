package com.jtools.jtools.cepvalidation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

public class CepValidator {

    public boolean isCepValid(String cep) throws IOException {
        if (isValidResponse(convertJsonString(viaCepApiCall(cep)))){
            return true;
        }
        return false;
    }

    private BufferedReader viaCepApiCall(String cep) {
        try {
            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            return new BufferedReader(new InputStreamReader(connection.getInputStream()));

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String convertJsonString(BufferedReader bufferedReader) throws IOException {
        String response;
        String jsonString = "";

        while ((response = bufferedReader.readLine()) != null) {
            jsonString += response;
        }
        return jsonString;
    }

    private static boolean isValidResponse(String string) {
        try {
            JSONObject json = new JSONObject(string);
            return json.has("cep");
        } catch (Exception e) {
            return false;
        }
    }
}
