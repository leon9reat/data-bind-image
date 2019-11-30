package com.medialink.databindimage.networks;

import com.medialink.databindimage.models.MovieRespon;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMovie {
    @GET("movie/popular")
    Call<MovieRespon> getMoviePopular(@Query("api_key") String apiKey,
                                      @Query("page") int page);
}
