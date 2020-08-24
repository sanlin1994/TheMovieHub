package com.sanlin.moviehub.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sanlin.moviehub.R;
import com.sanlin.moviehub.adapter.adapterVertical;
import com.sanlin.moviehub.models.MovieModel;
import com.sanlin.moviehub.models.movieList;
import com.sanlin.moviehub.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class SearchActivity extends AppCompatActivity {

    private ImageView back;
    private EditText searchText;
    private RecyclerView recyclerView;
    private List<MovieModel> MovieList = new ArrayList<>();
    private ProgressDialog progressDialog;
    private adapterVertical adapter;
    private LinearLayoutManager linearLayoutManager;
    private int page = 1;
    private com.sanlin.moviehub.models.movieList movieList;
    private Boolean isLoading;
    private MovieViewModel movieViewModel;
    private String queryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.searchToolbar);
        setSupportActionBar(toolbar);

        ImageView search = toolbar.findViewById(R.id.enterSearch);
        back = toolbar.findViewById(R.id.back);
        searchText =  toolbar.findViewById(R.id.searchString);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                queryString = searchText.getText().toString();
                if(queryString.equals("")){
                    progressDialog.cancel();
                    Toast.makeText(SearchActivity.this,"Please enter a valid keyword...",Toast.LENGTH_LONG).show();
                }else {
                    MovieList.clear();
                    getData(page);

                }

            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("please wait...");

        adapter = new adapterVertical(MovieList,getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.searchRecycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                movieList = new movieList();
                if (!isLoading && page!=movieList.getTotal_pages()) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {

                        if(!queryString.equals(""))
                        getData(page++);
                        else
                            Toast.makeText(SearchActivity.this,"Please enter a valid keyword...",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });



    }

    private void getData(int page) {

        isLoading = true;
        movieViewModel.getMovieSearch(queryString,page).observe(this, new Observer<com.sanlin.moviehub.models.movieList>() {
            @Override
            public void onChanged(com.sanlin.moviehub.models.movieList movieList) {
                progressDialog.dismiss();
                MovieList.addAll(movieList.getMovieList());
                adapter.notifyDataSetChanged();
                isLoading = false;
            }
        });

    }
}
