package com.kamak.katalogfilmuiux.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieModel {

    public static final String KEY_ID="id";
    public static final String KEY_VOTE="vote_average";
    public static final String KEY_POSTER_PATH="poster_path";
    public static final String KEY_RELEASE_DATE="release_date";
    public static final String KEY_TITLE="title";
    public static final String KEY_OVERVIEW="overview";
    public MovieModel(JSONObject jsonObject) {
        try {
          int movieid=jsonObject.getInt("id");
            String  movietitle=jsonObject.getString("title");
            Double movievote=jsonObject.getDouble("vote_average");
            String movieposter_path=jsonObject.getString("poster_path");
            String movie_overview=jsonObject.getString("overview");
            String movie_release_date=jsonObject.getString("release_date");
            this.id = movieid;
            this.vote = movievote;
            this.poster_path = movieposter_path;
            this.release_date = movie_release_date;
            this.title = movietitle;
            this.overview = movie_overview;



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    int id;

    double vote;

     String poster_path;
    String release_date;
    String title;
    String overview;

    public int getId() {
        return id;
    }

    public double getVote() {
        return vote;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }




}


