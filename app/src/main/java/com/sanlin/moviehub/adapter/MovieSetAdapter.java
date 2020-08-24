package com.sanlin.moviehub.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sanlin.moviehub.R;
import com.sanlin.moviehub.models.MovieSetModel;
import com.sanlin.moviehub.view.movieListVertical;

import java.util.ArrayList;
import java.util.List;

public class MovieSetAdapter extends RecyclerView.Adapter<MovieSetAdapter.viewHolder> {

    private static final String TAG = "set";
    private Context context;
    private List<MovieSetModel> movieModelArrayList = new ArrayList<>();

    public MovieSetAdapter(Context context, List<MovieSetModel> movieModelArrayList) {
        this.context = context;
        this.movieModelArrayList = movieModelArrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_set,parent,false);
        return new MovieSetAdapter.viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {

         LinearLayoutManager layoutManager;
         MovieArticleAdapter adapter;
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        holder.my_recycler_view.setLayoutManager(layoutManager);
        holder.my_recycler_view.setHasFixedSize(true);
        adapter = new MovieArticleAdapter(context, movieModelArrayList.get(position).getMovieModelArrayList(),movieModelArrayList.get(position).getCastModelList(),movieModelArrayList.get(position).getType());
        holder.my_recycler_view.setAdapter(adapter);
        holder.title.setText(movieModelArrayList.get(position).getTitle());
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, movieListVertical.class);
                intent.putExtra("type",movieModelArrayList.get(position).getType_title());
                intent.putExtra("title",movieModelArrayList.get(position).getTitle());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieModelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private RecyclerView my_recycler_view;
        private TextView more;

        public viewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.set_title);
            my_recycler_view = (RecyclerView) itemView.findViewById(R.id.recycler);
            more = (TextView) itemView.findViewById(R.id.moreText);
        }
    }


}
