package com.sanlin.moviehub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanlin.moviehub.R;
import com.sanlin.moviehub.models.ProductionCompanies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductionCompanyAdapter extends BaseAdapter {

    private Context context;
    private List<ProductionCompanies> productionCompanies = new ArrayList<>();

    public ProductionCompanyAdapter(Context context, List<ProductionCompanies> productionCompanies) {
        this.context = context;
        this.productionCompanies = productionCompanies;
    }

    @Override
    public int getCount() {
        return productionCompanies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String poster_base_url = "https://image.tmdb.org/t/p/w185";

        final LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.production_company_item, null);

        ImageView production_company_logo = (ImageView) convertView.findViewById(R.id.production_company_logo);
        TextView production_company_name = (TextView)  convertView.findViewById(R.id.production_company_name);

        if(productionCompanies.get(position).getLogo_path()!=null)
        Picasso.get().load(poster_base_url+productionCompanies.get(position).getLogo_path()).into(production_company_logo);
        else
            Picasso.get().load(R.drawable.facebook_profile_image).into(production_company_logo);
        production_company_name.setText(productionCompanies.get(position).getName());
        return convertView;
    }
}
