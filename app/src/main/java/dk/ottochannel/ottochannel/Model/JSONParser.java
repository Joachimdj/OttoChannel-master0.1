package dk.ottochannel.ottochannel.Model;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Mathias on 19-03-2015.
 */
public class JSONParser {


    public JSONArray getJSONFromUrl(String url) {

        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-type", "application/json");

        InputStream inputStream = null;
        String result = null;

        try {
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            inputStream = entity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            result = sb.toString();

        } catch (Exception e) {
            // Oops
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception squish) {

            }
        }

        JSONArray json = null;
        try {
            json = new JSONArray(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

}
