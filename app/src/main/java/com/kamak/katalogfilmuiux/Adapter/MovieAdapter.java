package com.kamak.katalogfilmuiux.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kamak.katalogfilmuiux.Model.MovieModel;
import com.kamak.katalogfilmuiux.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    @NonNull
    public ArrayList<MovieModel> getMoviemodel() {
        return moviemodel;
    }

    public MovieAdapter(@NonNull ArrayList<MovieModel> moviemodel) {
        this.moviemodel = moviemodel;
    }

    public void setMoviemodel(@NonNull ArrayList<MovieModel> moviemodel) {
        this.moviemodel = moviemodel;
    }

    @NonNull
    private ArrayList<MovieModel> moviemodel;
    private Context context;

    public MovieAdapter(Context context) {

        this.context = context;
    }
    public void setData(ArrayList<MovieModel> items) {
        moviemodel.clear();
        moviemodel.addAll(items);
        notifyDataSetChanged();
    }


    @Override


    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_movie, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        MovieModel movieModel=getMoviemodel().get(position);
        Picasso.get()
                .load("http://image.tmdb.org/t/p/w154/" + moviemodel.get(position).getPoster_path())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imgItemPhoto);
        viewHolder.tvItemName.setText(movieModel.getTitle());
        try {

            viewHolder.tvDeskripsi.setText(movieModel.getOverview().substring(0,30)+"........");
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.print("Invalid indexes or empty string");
        }
        catch (NullPointerException e) { // mTextView or response can be null
            e.printStackTrace();
            System.out.println("Something went wrong ,missed initialization");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something unexpected happened , move on or can see stacktrace ");
        }
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(movieModel.getRelease_date());

            SimpleDateFormat new_date_format = new SimpleDateFormat("E, MMM dd, yyyy");
            String date_of_release = new_date_format.format(date);
            viewHolder.txtrelease.setText(movieModel.getRelease_date());

        } catch (ParseException e) {
            e.printStackTrace();
        }









    }

    @Override
    public int getItemCount() {
        return getMoviemodel().size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_item_photo)
        ImageView imgItemPhoto;
        @BindView(R.id.tv_item_name)
        TextView tvItemName;
        @BindView(R.id.tv_item_remarks)
        TextView tvDeskripsi;
        @BindView(R.id.txtrelease)
        TextView txtrelease;
        @BindView(R.id.btn_set_favorite)
        Button btnSetFavorite;
        @BindView(R.id.btn_set_share)
        Button btnSetShare;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }



}
