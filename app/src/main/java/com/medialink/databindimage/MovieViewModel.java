package com.medialink.databindimage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.medialink.databindimage.models.Movie;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        this.movieRepository = new MovieRepository();
    }

    public LiveData<List<Movie>> getListMovie() {
        return movieRepository.getMutableMovie();
    }
}
