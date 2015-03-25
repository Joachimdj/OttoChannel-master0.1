package dk.ottochannel.ottochannel.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dk.ottochannel.ottochannel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NextShow extends Fragment {


    public NextShow() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_show, container, false);

    }


}
