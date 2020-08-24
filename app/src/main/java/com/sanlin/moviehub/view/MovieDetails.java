package com.sanlin.moviehub.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanlin.moviehub.R;
import com.sanlin.moviehub.adapter.MovieSetAdapter;
import com.sanlin.moviehub.adapter.ProductionCompanyAdapter;
import com.sanlin.moviehub.models.CreditsModel;
import com.sanlin.moviehub.models.MovieSetModel;
import com.sanlin.moviehub.models.ProductionCompanies;
import com.sanlin.moviehub.models.ReviewModelList;
import com.sanlin.moviehub.models.movieList;
import com.sanlin.moviehub.viewmodel.MovieViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import at.blogc.android.views.ExpandableTextView;

public class MovieDetails extends AppCompatActivity {

     ExpandableTextView expandableTextView;
    ImageButton buttonToggle;
    private static final String TAG = "details";
    Button more_reviews;
    private TextView movieTitle;
    private ProgressDialog progressDialog;
    private ImageView banner;
    private Context context;
    private TextView title,genre,date,popularity,rating,description,revenue,home,duration,author,review,more;
    private List<MovieSetModel> castList = new ArrayList<>();
    private List<MovieSetModel> relatedList = new ArrayList<>();
    private MovieSetAdapter adapter;
    private MovieSetAdapter adapterRelated;
    private List<ProductionCompanies> productionCompanies = new ArrayList<>();
    private ProductionCompanyAdapter productionCompanyAdapter;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);
        context = getApplicationContext();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please wait...");
        progressDialog.show();

        init();

        id = getIntent().getLongExtra("id",1111);

        final MovieViewModel movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovieDetails(id).observe(this, new Observer<com.sanlin.moviehub.models.MovieDetails>() {
            @Override
            public void onChanged(com.sanlin.moviehub.models.MovieDetails movieDetails) {
                if(movieDetails!=null) {
                    String poster_base_url = "https://image.tmdb.org/t/p/w500";
                    Picasso.get().load(poster_base_url + movieDetails.getBackdrop_path()).into(banner);
                    title.setText(movieDetails.getOriginal_title());
                    date.setText(movieDetails.getRelease_date());
                    rating.setText(String.valueOf(movieDetails.getVote_average()));
                    popularity.setText(String.valueOf(movieDetails.getPopularity()));
                    description.setText(movieDetails.getOverview());
                    //revenue.setText(String.valueOf(movieDetails.getRevenue()));
                    duration.setText(String.valueOf(movieDetails.getRuntime()));
                    //home.setText(movieDetails.getHomepage());
                    Log.i(TAG, "onChanged: " + movieDetails.getOriginal_title());
                    progressDialog.dismiss();
                }else{
                    progressDialog.dismiss();
                }
            }
        });

        movieViewModel.getMovieReviews(id,1).observe(this, new Observer<ReviewModelList>() {
            @Override
            public void onChanged(ReviewModelList reviewModelList) {
                if(reviewModelList!=null && reviewModelList.getResults().size()!=0) {
                    author.setText("Author: " + reviewModelList.getResults().get(0).getAuthor());
                    expandableTextView.setText(reviewModelList.getResults().get(0).getContent());
                    //review.setText(reviewModelList.getResults().get(0).getContent());
                }else{
                    //review.setText("No reviews found");
                    expandableTextView.setText("No reviews found");
                    buttonToggle.setVisibility(View.GONE);
                    author.setVisibility(View.GONE);
                    more_reviews.setVisibility(View.GONE);
                }
            }
        });

        movieViewModel.getMovieSimilar(id).observe(this, new Observer<movieList>() {
            @Override
            public void onChanged(movieList movieList) {
                MovieSetModel movieSetModel = new MovieSetModel();
                movieSetModel.setTitle("Related");
                movieSetModel.setType(1);
                movieSetModel.setMovieModelArrayList(movieList.getMovieList());
                relatedList.add(movieSetModel);
                adapterRelated.notifyDataSetChanged();
            }
        });

        movieViewModel.getMovieCredits(id).observe(this, new Observer<CreditsModel>() {
            @Override
            public void onChanged(CreditsModel creditsModel) {
                if(creditsModel!=null) {
                    MovieSetModel movieSetModel = new MovieSetModel();
                    movieSetModel.setTitle("Cast");
                    movieSetModel.setType(3);
                    movieSetModel.setCastModelList(creditsModel.getCast());
                    castList.add(movieSetModel);
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void init() {
        more_reviews = findViewById(R.id.mvie_info_more_reviews);
        title = (TextView) findViewById(R.id.movie_info_title);
        more = (TextView) findViewById(R.id.moreText);
        banner = (ImageView) findViewById(R.id.movie_info_banner);
        //genre = (TextView) findViewById(R.id.detailsGenre);
        date = (TextView) findViewById(R.id.movie_info_date);
        rating = (TextView) findViewById(R.id.movie_info_rating);
        popularity = (TextView) findViewById(R.id.movie_info_popularity);
        description = (TextView) findViewById(R.id.movie_info_overview);
        //revenue = (TextView) findViewById(R.id.detailsRevenue);
        //review = (TextView) findViewById(R.id.movie_info_review);
        author = (TextView) findViewById(R.id.movie_info_review_author);
        duration = (TextView) findViewById(R.id.movie_info_duration);
        //home = (TextView) findViewById(R.id.detailsHome);
        //GridView gridView = (GridView)findViewById(R.id.production_company_gridView);

//        Toolbar myToolbar = findViewById(R.id.searchToolbar);
//        setSupportActionBar(myToolbar);
//
//        movieTitle = myToolbar.findViewById(R.id.movieDetailsTitle);
//        ImageView back = myToolbar.findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        RecyclerView recyclerView =(RecyclerView) findViewById(R.id.moview_info_cast_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MovieDetails.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        RecyclerView recyclerViewRelated =(RecyclerView) findViewById(R.id.movie_info_releated);
        RecyclerView.LayoutManager relatedLayoutManager = new LinearLayoutManager(MovieDetails.this);
        recyclerViewRelated.setLayoutManager(relatedLayoutManager);
        recyclerViewRelated.setHasFixedSize(true);

        // adapter
        adapter = new MovieSetAdapter(MovieDetails.this,castList);
        recyclerView.setAdapter(adapter);

        adapterRelated = new MovieSetAdapter(MovieDetails.this,relatedList);
        recyclerViewRelated.setAdapter(adapterRelated);

//        productionCompanyAdapter = new ProductionCompanyAdapter(context,productionCompanies);
//        gridView.setAdapter(productionCompanyAdapter);

        expandableTextView = (ExpandableTextView) this.findViewById(R.id.movie_info_review);
        buttonToggle = (ImageButton) this.findViewById(R.id.button_toggle);

// set animation duration via code, but preferable in your layout files by using the animation_duration attribute
        expandableTextView.setAnimationDuration(750L);

        // set interpolators for both expanding and collapsing animations
        expandableTextView.setInterpolator(new OvershootInterpolator());

// or set them separately
        expandableTextView.setExpandInterpolator(new OvershootInterpolator());
        expandableTextView.setCollapseInterpolator(new OvershootInterpolator());

// but, you can also do the checks yourself
        buttonToggle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                if (expandableTextView.isExpanded())
                {
                    expandableTextView.collapse();
                    buttonToggle.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                }
                else
                {
                    expandableTextView.expand();
                    buttonToggle.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                }
            }
        });

        more_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetails.this,MovieReview.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

    }
}
