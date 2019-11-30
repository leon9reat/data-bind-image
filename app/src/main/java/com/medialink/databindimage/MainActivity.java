package com.medialink.databindimage;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.medialink.databindimage.databinding.ActivityMainBinding;
import com.medialink.databindimage.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView rvMain;
    private MovieViewModel modelMovie;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );
        modelMovie = ViewModelProviders.of(this).get(MovieViewModel.class);
        mAdapter = new MovieAdapter();

        setupRecyler();


        setupObserverMovie();
    }

    private void setupObserverMovie() {
        modelMovie.getListMovie().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                mAdapter.setData((ArrayList<Movie>) movies);
                Log.d(TAG, "setupObserverMovie: ada perubahan");
            }
        });
    }

    private void setupRecyler() {
        rvMain = findViewById(R.id.rv_movie);
        rvMain.setHasFixedSize(true);
        rvMain.setAdapter(mAdapter);
    }
}
