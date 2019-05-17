package com.example.deepgandhi.stockappfrags.Presenters;

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
import com.example.deepgandhi.stockappfrags.adapters.RecyclerAdapter;
import com.example.deepgandhi.stockappfrags.adapters.TableAdapter;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class FavRecycler extends Fragment {
    ArrayList<Integer> selected;
    CommonViewModel viewModel;
    RecyclerView recyclerView;
    List<StocksDetails> stocksDetailsList;
    RecyclerAdapter adapter;
    int i;

    Observer<Resource<StocksDetails>> observer;

    private static final String[] headers = {"name","symbol","currency","lastprice","pricingdate","highprice","lowprice"};

    @Inject
    ViewModelProvider.Factory factory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragemnt_recycler,container,false);

        Bundle bundle = getArguments();
        selected = bundle.getIntegerArrayList("list");

        stocksDetailsList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.fragment_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                GraphFrag graphFrag = new GraphFrag();
                Bundle bundle = new Bundle();
                bundle.putInt("id",selected.get(position));
                graphFrag.setArguments(bundle);

                getActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.container,graphFrag)
                        .addToBackStack("graph")
                        .commit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

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

                    AlertDialog.Builder builder = new AlertDialog.Builder(FavRecycler.this.getActivity())
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
        adapter = new RecyclerAdapter(stocksDetailsList,FavRecycler.this.getActivity());
        recyclerView.setAdapter(adapter);

    }
}
