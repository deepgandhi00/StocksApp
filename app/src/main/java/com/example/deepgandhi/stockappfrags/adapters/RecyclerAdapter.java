package com.example.deepgandhi.stockappfrags.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepgandhi.stockappfrags.Models.StocksDetails;
import com.example.deepgandhi.stockappfrags.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    List<StocksDetails> llist;
    Context context;

    public RecyclerAdapter(List<StocksDetails> llist,Context context) {
        this.llist = llist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StocksDetails stocksDetails = llist.get(position);

        holder.textViewName.setText(stocksDetails.getName());
        holder.textViewSymbol.setText(stocksDetails.getSymbol());
        holder.textViewCurrency.setText(stocksDetails.getCurrency());
        holder.textViewlastPrice.setText(String.valueOf(stocksDetails.getLastprice()));
        holder.textViewPricingDate.setText(stocksDetails.getPricingDate());
        holder.textViewHighPrice.setText(String.valueOf(stocksDetails.getHighPrice()));
        holder.textViewLowPrice.setText(String.valueOf(stocksDetails.getLowPrice()));
    }

    @Override
    public int getItemCount() {
        return llist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName,
                textViewSymbol,textViewCurrency,
                textViewlastPrice,textViewPricingDate,
                textViewHighPrice,textViewLowPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.card_name);
            textViewCurrency = itemView.findViewById(R.id.card_currency);
            textViewSymbol = itemView.findViewById(R.id.card_symbol);
            textViewlastPrice = itemView.findViewById(R.id.card_lastPrice);
            textViewPricingDate = itemView.findViewById(R.id.card_pricingDate);
            textViewHighPrice = itemView.findViewById(R.id.card_highPrice);
            textViewLowPrice = itemView.findViewById(R.id.card_lowPrice);
        }
    }
}
