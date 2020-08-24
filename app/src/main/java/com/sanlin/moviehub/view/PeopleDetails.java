package com.sanlin.moviehub.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanlin.moviehub.R;
import com.sanlin.moviehub.models.PeopleModel;
import com.sanlin.moviehub.viewmodel.MovieViewModel;
import com.squareup.picasso.Picasso;

public class PeopleDetails extends AppCompatActivity {

    private ImageView imageView;
    private TextView name,address,popularity,dob,biography;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_details);

        init();
        progressDialog.show();
        long id = getIntent().getLongExtra("id",287);
        final MovieViewModel movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMoviePeople(id).observe(this, new Observer<PeopleModel>() {
            @Override
            public void onChanged(PeopleModel peopleModel) {
                String poster_base_url = "https://image.tmdb.org/t/p/w185";
                Picasso.get().load(poster_base_url+peopleModel.getProfile_path()).into(imageView);
                name.setText(peopleModel.getName());
                address.setText(peopleModel.getPlace_of_birth());
                popularity.setText(String.valueOf(peopleModel.getPopularity()));
                dob.setText(peopleModel.getBirthday());
                biography.setText(peopleModel.getBiography());
                progressDialog.cancel();
            }
        });


    }

    private void init() {

        Toolbar myToolbar = findViewById(R.id.searchToolbar);
        setSupportActionBar(myToolbar);
        ImageView back = myToolbar.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imageView = (ImageView) findViewById(R.id.peopleImage);
        name = (TextView) findViewById(R.id.peopleName);
        address = (TextView) findViewById(R.id.peopleAddress);
        popularity = (TextView) findViewById(R.id.peopleBiography);
        dob = (TextView) findViewById(R.id.peopleDob);
        biography = (TextView) findViewById(R.id.peopleBiography);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCancelable(false);
    }
}
