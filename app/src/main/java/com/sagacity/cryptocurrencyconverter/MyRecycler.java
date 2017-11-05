package com.sagacity.cryptocurrencyconverter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alo on 10/25/17.
 */

public class MyRecycler extends RecyclerView.Adapter<MyRecycler.ViewHolder> {
    private List<CurrencyList> currencyList;
    private Context context;

    public MyRecycler(List<CurrencyList> currencyList, Context context) {
        this.currencyList = currencyList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        CurrencyList items = currencyList.get(position);

        holder.countryText.setText(items.getCountry());
        holder.currencyText.setText(items.getCurrency());
        holder.countryCodeText.setText(items.getCountryCode());



    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView currencyText;
        public TextView countryText,countryCodeText;
        public ViewHolder(View itemView) {
            super(itemView);

            currencyText = (TextView) itemView.findViewById(R.id.currency);
            countryText = (TextView) itemView.findViewById(R.id.country);
            countryCodeText = (TextView) itemView.findViewById(R.id.countrycode);

        }
    }
}
