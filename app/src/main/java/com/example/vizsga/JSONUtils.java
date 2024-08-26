package com.example.vizsga;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JSONUtils {

    private static final String JSON_URL = "https://people.vts.su.ac.rs/~probi/Mobile/vizsga/json2.txt";

    public static void loadProblemsFromUrl(OnDataLoadedListener listener) {
        new FetchDataTask(listener).execute(JSON_URL);
    }

    private static class FetchDataTask extends AsyncTask<String, Void, List<Problem>> {

        private final OnDataLoadedListener listener;

        FetchDataTask(OnDataLoadedListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<Problem> doInBackground(String... urls) {
            List<Problem> problems = new ArrayList<>();
            String json = fetchJsonFromUrl(urls[0]);

            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("json2");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    int a = obj.getInt("a");
                    int b = obj.getInt("b");
                    String op = obj.getString("op");

                    Problem problem = new Problem(a, b, op);
                    problems.add(problem);
                }
            } catch (JSONException e) {
                Log.e("JSONUtils", "JSON parsing error", e);
            }

            return problems;
        }

        @Override
        protected void onPostExecute(List<Problem> problems) {
            if (listener != null) {
                listener.onDataLoaded(problems);
            }
        }

        private String fetchJsonFromUrl(String urlString) {
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                reader.close();
            } catch (Exception e) {
                Log.e("JSONUtils", "Error fetching JSON", e);
            }

            return result.toString();
        }
    }

    public interface OnDataLoadedListener {
        void onDataLoaded(List<Problem> problems);
    }
}