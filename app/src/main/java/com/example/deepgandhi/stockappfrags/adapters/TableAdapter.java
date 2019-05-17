package com.example.deepgandhi.stockappfrags.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepgandhi.stockappfrags.Models.StocksDetails;
import com.example.deepgandhi.stockappfrags.R;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

public class TableAdapter extends TableDataAdapter<StocksDetails> {
    public TableAdapter(Context context, List<StocksDetails> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        StocksDetails details = getRowData(rowIndex);

        View renderView = null;

        switch (columnIndex){
            case 0:
                renderView = getLayoutInflater().inflate(R.layout.tablecellview,parentView,false);
                TextView tvname = renderView.findViewById(R.id.cellText);
                tvname.setText(details.getName());
                break;


            case 1:
                renderView = getLayoutInflater().inflate(R.layout.tablecellview,parentView,false);
                TextView tvname1 = renderView.findViewById(R.id.cellText);
                tvname1.setText(details.getSymbol());
                break;

            case 2:
                renderView = getLayoutInflater().inflate(R.layout.tablecellview,parentView,false);
                TextView tvname2 = renderView.findViewById(R.id.cellText);
                tvname2.setText(details.getCurrency());
                break;

            case 3:
                renderView = getLayoutInflater().inflate(R.layout.tablecellview,parentView,false);
                TextView tvname3 = renderView.findViewById(R.id.cellText);
                tvname3.setText(String.valueOf(details.getLastprice()));
                break;

            case 4:
                renderView = getLayoutInflater().inflate(R.layout.tablecellview,parentView,false);
                TextView tvname4 = renderView.findViewById(R.id.cellText);
                tvname4.setText(details.getPricingDate());
                break;

            case 5:
                renderView = getLayoutInflater().inflate(R.layout.tablecellview,parentView,false);
                TextView tvname5 = renderView.findViewById(R.id.cellText);
                tvname5.setText(String.valueOf(details.getHighPrice()));
                break;

            case 6:
                renderView = getLayoutInflater().inflate(R.layout.tablecellview,parentView,false);
                TextView tvname6 = renderView.findViewById(R.id.cellText);
                tvname6.setText(String.valueOf(details.getLowPrice()));
                break;
        }
        return renderView;
    }
}
