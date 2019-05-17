package com.example.deepgandhi.stockappfrags.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.deepgandhi.stockappfrags.Models.Stocks;
import com.example.deepgandhi.stockappfrags.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AutoCompleteAdapter extends ArrayAdapter<Stocks> {

    Context context;
    List<Stocks> stocks = new ArrayList<>();
    List<Stocks> allstocks = new ArrayList<>();
    int resourceId;

    public AutoCompleteAdapter(Context context, int resource, List<Stocks> objects) {
        super(context, resource, objects);
        this.context = context;
        stocks.addAll(objects);
        allstocks.addAll(objects);
        resourceId = resource;
    }

    @Override
    public int getCount() {
        return stocks.size();
    }

    @Nullable
    @Override
    public Stocks getItem(int position) {
        return stocks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.autocompletetextview,parent,false);
        }
        Stocks stocks = getItem(position);
        TextView name = convertView.findViewById(R.id.autocompleteTv);
        name.setText(stocks.getName());

        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                List<Stocks> suggestions = new ArrayList<>();
                if(charSequence != null) {
                    Log.i("allstockssize",allstocks.size()+"");
                    Log.i("stockssize",stocks.size()+"");
                    for (Stocks stocks1 : allstocks) {
                        if (stocks1.getName().toLowerCase().contains(charSequence)) {
                            suggestions.add(stocks1);
                        }
                    }
                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                stocks.clear();
                if (filterResults != null && filterResults.count > 0) {
                    // avoids unchecked cast warning when using mDepartments.addAll((ArrayList<Department>) results.values);
                    for (Object object : (List<?>) filterResults.values) {
                        if (object instanceof Stocks) {
                            stocks.add((Stocks) object);
                        }
                    }
                    notifyDataSetChanged();
                } else if (charSequence == null) {
                    // no filter, add entire original list back in
                    stocks.addAll(allstocks);
                    notifyDataSetChanged();
                }
            }
        };
    }
}

