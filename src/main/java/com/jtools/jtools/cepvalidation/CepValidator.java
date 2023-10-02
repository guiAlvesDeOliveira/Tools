package com.jtools.jtools.cepvalidation;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CepValidator {

    public boolean isCepValid(String cep) throws IOException {
        return isValidResponse(convertJsonString(viaCepApiCall(cep)));
    }

    private BufferedReader viaCepApiCall(String cep) {
        try {
            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            return new BufferedReader(new InputStreamReader(connection.getInputStream()));

        } catch (IOException e) {
            throw new ApiRequestException("Error making API request for CEP: " + cep, e);
        }
    }

    private static String convertJsonString(BufferedReader bufferedReader) throws IOException {
        String response;
        StringBuilder jsonString = new StringBuilder();

        while ((response = bufferedReader.readLine()) != null) {
            jsonString.append(response);
        }
        return jsonString.toString();
    }

    private static boolean isValidResponse(String string) {
        JSONObject json = new JSONObject(string);
        return json.has("cep");
    }


    public static class ApiRequestException extends RuntimeException {
        public ApiRequestException(String cep, Throwable cause) {
            super("Error making API request for CEP: " + cep, cause);
        }
    }

}
