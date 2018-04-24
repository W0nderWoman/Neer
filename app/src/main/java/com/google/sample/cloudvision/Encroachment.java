package com.google.sample.cloudvision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Encroachment extends Fragment {

    public Encroachment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.encroachment_fragment, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view1);
        rv.setHasFixedSize(true);
        MyAdapter adapter = new MyAdapter(new String[]{"Example 1", "Example 2", "Example Three", "Example Four", "Example Five" , "Example Six" , "Example Seven","Example eight" });
        rv.setAdapter(adapter);
        //MyAdapter adapter2 = new MyAdapter(new String[]{"Example", "Example Two", "Example Three", "Example Four", "Example Five" , "Example Six" , "Example Seven","Example eight" });

        adapter.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(int itemId) {
                Intent intent=new Intent(getActivity().getApplication(),HowMuchWorkDone.class);
                startActivity(intent);
            }
        });



        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }

}