package com.cordebora.projects.schedules.clone;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class NHLdata {
    private String jsonResponse;
    public String getJsonResponse() {
        try {
            URL url = new URL("https://api-web.nhle.com/v1/club-schedule/PHI/month/now");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
            }

            StringBuilder response = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;

            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            jsonResponse = response.toString();

            br.close();
            conn.disconnect();

            return jsonResponse;

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
            return null;
        }
    }
}
