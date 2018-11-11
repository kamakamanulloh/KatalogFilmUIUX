package com.kamak.katalogfilmuiux.AsyncTask;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.kamak.katalogfilmuiux.Model.MovieModel;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class UpcomingAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieModel>> {

    private ArrayList<MovieModel> movieModels;
    private boolean mHasResult = false;
    public static final String API_KEY="5afa93632eb4b47ccefcc296ff3f202c";

    private String cari_judul;

    public UpcomingAsyncTaskLoader(final Context context, String titleMovie) {
        super(context);

        onContentChanged();
        this.cari_judul = titleMovie;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(movieModels);
    }

    //show result data
    public void deliverResult(ArrayList<MovieModel> data) {
        movieModels = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            onReleaseResources(movieModels);
            movieModels = null;
            mHasResult = false;
        }
    }



    private void onReleaseResources(ArrayList<MovieModel> data) {
    }

    @Override
    //get data synchronously
    public ArrayList<MovieModel> loadInBackground() {
        SyncHttpClient syncHttpClient=new SyncHttpClient();
        final ArrayList<MovieModel>mModel=new ArrayList<>();
        String url="https://api.themoviedb.org/3/movie/upcoming?api_key="+API_KEY+"&language=en-US";
        syncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {

                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String hasil=new String(responseBody);
                try {
                    JSONObject response=new JSONObject(hasil);
                    JSONArray daftar=response.getJSONArray("results");
                    for (int x=0;x<daftar.length();x++){
                        JSONObject movie=daftar.getJSONObject(x);
                        MovieModel movieModel=new MovieModel(movie);
                        mModel.add(movieModel);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return mModel;
    }
}