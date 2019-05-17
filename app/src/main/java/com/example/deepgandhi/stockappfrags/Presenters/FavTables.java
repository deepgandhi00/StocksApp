package com.example.deepgandhi.stockappfrags.Presenters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepgandhi.stockappfrags.Models.StocksDetails;
import com.example.deepgandhi.stockappfrags.R;
import com.example.deepgandhi.stockappfrags.Resource;
import com.example.deepgandhi.stockappfrags.Status;
import com.example.deepgandhi.stockappfrags.ViewModels.CommonViewModel;
import com.example.deepgandhi.stockappfrags.adapters.TableAdapter;
import com.example.deepgandhi.stockappfrags.di.Injectable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import retrofit2.Retrofit;

@SuppressLint("ValidFragment")
public class FavTables extends Fragment implements TableDataClickListener,Injectable {
    private static final String TAG = "FavTables";
    ArrayList<Integer> selected;
    TableView tableView;
    CommonViewModel viewModel;
    List<StocksDetails> stocksDetailsList;
    TableAdapter adapter;
    int i;


    Observer<Resource<StocksDetails>> observer;

    private static final String[] headers = {"name","symbol","currency","lastprice","pricingdate","highprice","lowprice"};

    @Inject
    ViewModelProvider.Factory factory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favtableslayout,container,false);

        Bundle bundle = getArguments();
        selected = bundle.getIntegerArrayList("list");

        stocksDetailsList = new ArrayList<>();

        tableView = view.findViewById(R.id.tableView);
        tableView.addDataClickListener(this);

        setAdapter();

        setViewModel();

        viewModel = ViewModelProviders.of(this,factory).get(CommonViewModel.class);
        for(i=0;i<selected.size();i++){
            viewModel.getStockDetails(selected.get(i)).observe(this,observer);
        }




        return view;
    }

    private void setViewModel() {
        observer = new Observer<Resource<StocksDetails>>() {
            @Override
            public void onChanged(Resource<StocksDetails> stocksDetailsResource) {
                if(stocksDetailsResource.status == Status.SUCCESS){
                    stocksDetailsList.add(stocksDetailsResource.data);
                    adapter.notifyDataSetChanged();
                }
                else if(stocksDetailsResource.status == Status.LOADING){

                }
                else if(stocksDetailsResource.status == Status.ERROR){

                    AlertDialog.Builder builder = new AlertDialog.Builder(FavTables.this.getActivity())
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).setCancelable(true)
                            .setTitle("ERROR")
                            .setMessage(stocksDetailsResource.errorMessage);

                    builder.create().show();
                }
            }
        };


    }

    private void setAdapter(){
        Log.i("listsizestocksdetails",stocksDetailsList.size()+"");
        adapter = new TableAdapter(getActivity(),stocksDetailsList);
        tableView.setDataAdapter(adapter);
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getActivity(),headers));
    }

    @Override
    public void onDataClicked(int rowIndex, Object clickedData) {
        StocksDetails clicked = (StocksDetails)clickedData;
        GraphFrag graphFrag = new GraphFrag();
        Bundle bundle = new Bundle();
        bundle.putInt("id",selected.get(rowIndex));
        graphFrag.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction().replace(R.id.container,graphFrag)
                .addToBackStack("graph")
                .commit();
    }

    @Override
    public void onDestroyView() {
        stocksDetailsList.clear();
        adapter.notifyDataSetChanged();
        for(int b = 0; b < selected.size();b++){
            viewModel.getStockDetails(selected.get(b)).removeObserver(observer);
        }

        Log.d(TAG, "onDestroyView: ondestroyview");
        super.onDestroyView();
    }
}
