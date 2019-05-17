package com.example.deepgandhi.stockappfrags.Presenters;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.deepgandhi.stockappfrags.AppViewModelFactory;
import com.example.deepgandhi.stockappfrags.Models.Stocks;
import com.example.deepgandhi.stockappfrags.R;
import com.example.deepgandhi.stockappfrags.Resource;
import com.example.deepgandhi.stockappfrags.Status;
import com.example.deepgandhi.stockappfrags.ViewModels.CommonViewModel;
import com.example.deepgandhi.stockappfrags.adapters.AutoCompleteAdapter;
import com.example.deepgandhi.stockappfrags.application.MyApplication;
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
import retrofit2.Retrofit;

@SuppressLint("ValidFragment")
public class AllStocksFrag extends Fragment implements Injectable {
    private CommonViewModel viewModel;
    AutoCompleteTextView autoCompleteTextView;
    AutoCompleteAdapter adapter;
    List<Stocks> selectedStocks = new ArrayList<>();
    ArrayList<Integer> selectedIds = new ArrayList<>();
    Button btnFav;
    ProgressBar progressBar;

    @Inject
    ViewModelProvider.Factory factory;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        MyApplication.get(getActivity()).getApplicationComponent().inject((MyApplication) getActivity().getApplication());
        View view = inflater.inflate(R.layout.allstockslayout, container, false);
        autoCompleteTextView = view.findViewById(R.id.AutoCompleteTextView);
        btnFav = view.findViewById(R.id.main_button_fav);
        progressBar = view.findViewById(R.id.allProgressBar);

        setViewModel();
        return view;
    }

    private void setViewModel() {
        viewModel = ViewModelProviders.of(this,this.factory).get(CommonViewModel.class);

        viewModel.getStocks().observe(this, new Observer<Resource<List<Stocks>>>() {
            @Override
            public void onChanged(Resource<List<Stocks>> listResource) {

                if(listResource.status == Status.SUCCESS) {
                    progressBar.setVisibility(View.GONE);
                    Log.i("statuscode",listResource.statusCode+"");
                    adapter = new AutoCompleteAdapter(AllStocksFrag.this.getActivity(), R.layout.autocompletetextview, listResource.data);
                    autoCompleteTextView.setAdapter(adapter);
                    viewModel.getStocks().removeObservers(AllStocksFrag.this);
                }
                else if(listResource.status == Status.LOADING){
                    Log.i("statuscode",listResource.statusCode+"");
                    Log.i("allstocks","loading");
                }
                else if(listResource.status == Status.ERROR){
                    Log.i("statuscode",listResource.statusCode+"");
                    AlertDialog.Builder builder = new AlertDialog.Builder(AllStocksFrag.this.getActivity())
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).setCancelable(true)
                            .setTitle("ERROR")
                            .setMessage(listResource.errorMessage);

                    builder.create().show();
                }
            }
        });


        setListeners();
    }

    private void setListeners() {

        autoCompleteTextView.setOnItemClickListener((AdapterView<?> adapterView, View view, int i, long l) -> {
            Stocks stocks2 = (Stocks) adapterView.getItemAtPosition(i);
            selectedStocks.add(stocks2);
            selectedIds.add(stocks2.getId());
            autoCompleteTextView.setText("");
            Toast.makeText(getActivity(), selectedStocks.size()+"", Toast.LENGTH_SHORT).show();
        });

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavTables favTables = new FavTables();
                FavRecycler favRecycler = new FavRecycler();
                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("list",selectedIds);
                favTables.setArguments(bundle);

                getActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.container,favTables)
                        .addToBackStack("tables")
                        .commit();
            }
        });

    }


}
