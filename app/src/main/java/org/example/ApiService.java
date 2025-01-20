package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiService {
    private static final String API_ENDPOINT = System.getenv("API_ENDPOINT");

    public String getDataFromApi() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url(API_ENDPOINT)
            .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}