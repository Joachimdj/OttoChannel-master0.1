package dk.ottochannel.ottochannel.Model;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import android.app.Activity;
import android.net.Uri;
import android.content.Intent;
import dk.ottochannel.ottochannel.Adapters.EpisodesAdapter;
import dk.ottochannel.ottochannel.Adapters.QuotesAdapter;
import dk.ottochannel.ottochannel.Adapters.ScheduleAdapter;
import dk.ottochannel.ottochannel.R;

public class DownloadJSON extends AsyncTask<String, String, JSONArray> {

    String url;
    View view;
    Activity activity;
    ArrayList<String> links = null;
    ArrayList<String> videoIds = null;

    ArrayList<String> sounds = new ArrayList<>();
    public DownloadJSON(Activity activity, View view, String url) {
        this.url = url;
        this.view = view;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONArray doInBackground(String... params) {
        JSONParser jParser = new JSONParser();
        return jParser.getJSONFromUrl(url);

    }

    @Override
    protected void onPostExecute(JSONArray json) {

        GridView gridView = null;
        ListView listView = null;



        // Gridview for episodes
        if (url.contains("videos")) {
            links = new ArrayList<>();
            videoIds = new ArrayList<>();

            try {
                for (int i = 0; i < json.length(); i++) {
                    JSONObject o = (JSONObject) json.get(i);
                    String link = o.getString("thumbnail_large");
                    String videoId = o.getString("id");
                    links.add(link);
                    videoIds.add(videoId);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            gridView = (GridView) view.findViewById(R.id.episodes_gridview);
            gridView.setAdapter(new EpisodesAdapter(activity, links));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Toast.makeText(activity, "A episode was clicked", Toast.LENGTH_SHORT).show();
                    String url = "http://rollespil.dk";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://rollespil.dk/app/ottoChannel/video.php?id="+videoIds.get(position)));
                    activity.startActivity(i);
                }
            });

        } else if (url.contains("OttoSounds")) {
            //Gridview for Quotes
            links = new ArrayList<>();

            try {
                for (int i = 0; i < json.length(); i++) {
                    JSONObject o = (JSONObject) json.get(i);
                    String link = o.getString("thumbnail_large");
                    String sound = o.getString("audioFile");
                    links.add(link);
                    sounds.add(sound);
                    System.out.println(sound);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            gridView = (GridView) view.findViewById(R.id.quotes_gridview);
            gridView.setAdapter(new QuotesAdapter(activity, links));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    System.out.println(position);
                    System.out.println(sounds.get(position));

                }
            });

        } else if (url.contains("OttoDates")) {
            listView = (ListView) view.findViewById(R.id.schedule_listview);
            listView.setAdapter(new ScheduleAdapter(activity, json));
        } else {
            // If url contains none of the above
        }

    }


}

