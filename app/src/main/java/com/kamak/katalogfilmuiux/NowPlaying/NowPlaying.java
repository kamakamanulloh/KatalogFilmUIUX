package com.kamak.katalogfilmuiux.NowPlaying;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kamak.katalogfilmuiux.Model.MovieModel;
import com.kamak.katalogfilmuiux.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlaying extends Fragment {
    private RecyclerView.Adapter adapter;
    private View view;
    private List<MovieModel>movieModels;
    public static final String URL=


    public NowPlaying() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing2, container, false);
    }

    static class ViewHolder {
        @BindView(R.id.rv_category)
        RecyclerView rvCategory;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
