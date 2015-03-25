package dk.ottochannel.ottochannel.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dk.ottochannel.ottochannel.R;

/**
 * Created by Mathias on 24-03-2015.
 */
public class ScheduleAdapter extends BaseAdapter {
    private Context mContext;
    private JSONArray data;

    public ScheduleAdapter(Context c, JSONArray data) {
        mContext = c;
        this.data = data;
    }

    public int getCount() {
        return data.length();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        String when = "Title";
        String where = "Desc";

        try {
            JSONObject schedule = data.getJSONObject(position);
            when = schedule.getString("dateLabel");
            where = schedule.getString("place");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.schedule_item, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.schedule_title);
        TextView desc = (TextView) convertView.findViewById(R.id.schedule_desc);

        title.setText(when);
        desc.setText(where);

        return convertView;
    }

}
