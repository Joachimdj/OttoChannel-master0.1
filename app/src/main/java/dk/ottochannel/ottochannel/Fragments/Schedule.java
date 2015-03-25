package dk.ottochannel.ottochannel.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dk.ottochannel.ottochannel.Model.DownloadJSON;
import dk.ottochannel.ottochannel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Schedule extends Fragment {


    public Schedule() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        new DownloadJSON(getActivity(), view, "https://rollespil.dk/app/ottoChannel/OttoDates.php").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        return view;
    }


}
