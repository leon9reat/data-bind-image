package com.medialink.databindimage;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.medialink.databindimage.databinding.MovieItemBinding;
import com.medialink.databindimage.models.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private ArrayList<Movie> listMovie;

    public void setData(ArrayList<Movie> inputData) {
        this.listMovie = inputData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding movieItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.movie_item, parent, false
        );
        return new MyViewHolder(movieItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie currentMovie = listMovie.get(position);
        holder.movieItemBinding.setMovie(currentMovie);
    }

    @Override
    public int getItemCount() {
        return listMovie == null ? 0 : listMovie.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private MovieItemBinding movieItemBinding;

        public MyViewHolder(@NonNull MovieItemBinding itemView) {
            super(itemView.getRoot());
            this.movieItemBinding = itemView;
        }
    }
}
