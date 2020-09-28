package com.saxakiil.api;

import com.google.gson.Gson;
import com.saxakiil.EnvResources;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ConnectionCatApi {

    private static String getResponse() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpGet request = new HttpGet(EnvResources.urlApi);
            request.addHeader("User-Agent", "Mozilla/5.0 Firefox/26.0");
            request.addHeader("x-api-key", EnvResources.tokenApi);
            request.addHeader("Accept", "application/json");
            request.addHeader("Content-Type", "application/json");
            request.addHeader("Accept-Charset", "utf-8");
            CloseableHttpResponse response = httpClient.execute(request);

            try {
                if(response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String result = EntityUtils.toString(entity);
                        return result;
                    }
                }

            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
        return null;
    }

    private static String responseFormatter(String res) {
        int lastIndex = res.length() - 1;
        return res.substring(1, lastIndex);
    }

    private static String responseValidator(String res) throws IOException {
        if(res.contains(".jpg")) {
            return res;
        } else {
           return responseValidator(getResponse());
        }
    }

    public static CatObject getNewCatObject() throws IOException {
        String stringJson = responseValidator(getResponse());
        Gson gson = new Gson();
        CatObject catObject = gson.fromJson(responseFormatter(stringJson), CatObject.class);
        if(catObject != null) {
            return catObject;
        } else {
            throw new NullPointerException("Response in invalid");
        }
    }
}
