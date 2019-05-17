package com.example.deepgandhi.stockappfrags.Presenters;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.deepgandhi.stockappfrags.Models.GraphDetails;
import com.example.deepgandhi.stockappfrags.R;
import com.example.deepgandhi.stockappfrags.Resource;
import com.example.deepgandhi.stockappfrags.Status;
import com.example.deepgandhi.stockappfrags.ViewModels.CommonViewModel;
import com.example.deepgandhi.stockappfrags.di.Injectable;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Retrofit;

@SuppressLint("ValidFragment")
public class GraphFrag extends Fragment implements OnChartValueSelectedListener,Injectable {

    LineChart barChart;
    CommonViewModel viewModel;
    int assetid;
    Fragment fragment;
    Retrofit retrofit;

    float[][] arr;
    ArrayList<Entry> values;

    Observer<Resource<GraphDetails>> observer;

    @Inject
    ViewModelProvider.Factory factory;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.graphlayout,container,false);

        Bundle bundle = getArguments();
        assetid = bundle.getInt("id",0);
        Toast.makeText(getActivity(), assetid+"", Toast.LENGTH_SHORT).show();

        values = new ArrayList<>();

        barChart = view.findViewById(R.id.chart1);
        barChart.setOnChartValueSelectedListener(this);

        barChart.setBackgroundColor(Color.WHITE);
        barChart.getDescription().setEnabled(false);

        barChart.setDrawGridBackground(false);

        barChart.setDragEnabled(true);

        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = barChart.getXAxis();

            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f);
        }

        barChart.animateX(1500);

        fragment = this;

        setViewModel();
        viewModel = ViewModelProviders.of(this,factory).get(CommonViewModel.class);


        viewModel.getGraphDetails(assetid).observe(this, observer);
        return view;
    }

    private void setViewModel() {

        observer = new Observer<Resource<GraphDetails>>() {
            @Override
            public void onChanged(Resource<GraphDetails> graphDetailsResource) {
                if(graphDetailsResource.status == Status.SUCCESS){

                    arr = graphDetailsResource.data.getArr();

                    setData();
                }

                else if(graphDetailsResource.status == Status.LOADING){

                }

                else if(graphDetailsResource.status == Status.ERROR){
                    AlertDialog.Builder builder = new AlertDialog.Builder(GraphFrag.this.getActivity())
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).setCancelable(true)
                            .setTitle("ERROR")
                            .setMessage(graphDetailsResource.errorMessage);

                    builder.create().show();
                }
            }
        };
    }

    private void setData() {

        float maxVal = arr[0][1];

        for (int i = 0; i < arr.length; i++) {
            values.add(new Entry(i, arr[i][1]));
            if (arr[i][1] > maxVal) {
                maxVal = arr[i][1];
            }
        }


        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = barChart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            barChart.getAxisRight().setEnabled(false);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
            yAxis.setAxisMaximum(maxVal + 10);
            yAxis.setAxisMinimum(0);
        }

        LineDataSet set1;

        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values, "DataSet 1");
            set1.enableDashedLine(10f, 5f, 0f);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return barChart.getAxisLeft().getAxisMinimum();
                }
            });


            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            barChart.setData(data);
        }
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }



}
