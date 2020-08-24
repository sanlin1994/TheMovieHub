package com.sanlin.moviehub.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sanlin.moviehub.R;
import com.sanlin.moviehub.adapter.MovieSetAdapter;
import com.sanlin.moviehub.models.MovieSetModel;
import com.sanlin.moviehub.models.movieList;
import com.sanlin.moviehub.viewmodel.MovieViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MovieSetModel> movieTrendingList = new ArrayList<>();
    private ProgressDialog progressDialog;
    private static final String TAG = "main";
    private MovieSetAdapter adapter;
    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        movieViewModel.getMovie("upcoming",1).observe(this, new Observer<movieList>() {
            @Override
            public void onChanged(movieList movieList) {
                MovieSetModel movieSetModel = new MovieSetModel();
                movieSetModel.setTitle("Upcomming");
                movieSetModel.setType(2);
                movieSetModel.setMovieModelArrayList(movieList.getMovieList());
                movieSetModel.setType_title("upcoming");
                movieTrendingList.add(movieSetModel);
                adapter.notifyDataSetChanged();
                loadAfterBanner();
            }
        });

        init();


    }

    private void loadAfterBanner(){
        movieViewModel.getMovieTrending("all","week",1).observe(this, new Observer<movieList>() {
            @Override
            public void onChanged(movieList movieModels) {
                MovieSetModel movieSetModel = new MovieSetModel();
                movieSetModel.setTitle("Trending");
                movieSetModel.setType(1);
                movieSetModel.setMovieModelArrayList(movieModels.getMovieList());
                movieSetModel.setType_title("all");
                movieTrendingList.add(movieSetModel);
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }
        });

        movieViewModel.getMovie("popular",1).observe(this, new Observer<movieList>() {
            @Override
            public void onChanged(movieList movieModels) {
                MovieSetModel movieSetModel = new MovieSetModel();
                movieSetModel.setTitle("Popular");
                movieSetModel.setType(1);
                movieSetModel.setMovieModelArrayList(movieModels.getMovieList());
                movieSetModel.setType_title("popular");
                movieTrendingList.add(movieSetModel);
                adapter.notifyDataSetChanged();
            }
        });

        movieViewModel.getMovie("top_rated",1).observe(this, new Observer<movieList>() {
            @Override
            public void onChanged(movieList movieModels) {
                MovieSetModel movieSetModel = new MovieSetModel();
                movieSetModel.setTitle("Top rated");
                movieSetModel.setType(1);
                movieSetModel.setMovieModelArrayList(movieModels.getMovieList());
                movieTrendingList.add(movieSetModel);
                movieSetModel.setType_title("top_rated");
                adapter.notifyDataSetChanged();
            }
        });

        movieViewModel.getMovie("now_playing",1).observe(this, new Observer<movieList>() {
            @Override
            public void onChanged(movieList movieModels) {
                MovieSetModel movieSetModel = new MovieSetModel();
                movieSetModel.setTitle("Now playing");
                movieSetModel.setType(1);
                movieSetModel.setMovieModelArrayList(movieModels.getMovieList());
                movieTrendingList.add(movieSetModel);
                movieSetModel.setType_title("now_playing");
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void init() {

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        ImageView search =(ImageView) myToolbar.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);

            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("please wait....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        RecyclerView recyclerView =(RecyclerView) findViewById(R.id.recyclerset);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // adapter
        adapter = new MovieSetAdapter(MainActivity.this,movieTrendingList);
        recyclerView.setAdapter(adapter);

    }
}
