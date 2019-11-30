package com.medialink.databindimage;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.medialink.databindimage.models.Movie;
import com.medialink.databindimage.models.MovieRespon;
import com.medialink.databindimage.networks.ApiClient;
import com.medialink.databindimage.networks.ApiMovie;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static final String TAG = "MovieRepository";
    private ArrayList<Movie> listMovie = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutaListMovie = new MutableLiveData<>();
    private MutableLiveData<String> mutaError = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public MovieRepository() {
    }

    public MutableLiveData<List<Movie>> getMutableMovie() {
        final ApiMovie apiMovie = ApiClient.getClient().create(ApiMovie.class);

        apiMovie.getMoviePopular(Consts.API_KEY, 1)
                .enqueue(new Callback<MovieRespon>() {
                    @Override
                    public void onResponse(Call<MovieRespon> call, Response<MovieRespon> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null && response.body().getResults() != null) {
                                mutaListMovie.postValue(response.body().getResults());
                                Log.d(TAG, "getMutableMovie totalResult: " + response.body().getTotalResults());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieRespon> call, Throwable t) {
                        mutaError.postValue(t.getMessage());
                        Log.d(TAG, "onFailure getMutableMovie: " + t.getMessage());
                    }
                });
        return mutaListMovie;
    }
}
