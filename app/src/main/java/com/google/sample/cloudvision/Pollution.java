package com.google.sample.cloudvision;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

;import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class Pollution extends Fragment {

    private DatabaseReference dbref;
//    ListView lv;
//    private ArrayList<String> al = new ArrayList<>();
//    private ArrayList<String> keys = new ArrayList<>();
//    HashMap<String, Object> map;
//    String val1, val2, val3, val4;

    ListView lv;
    //    private ArrayList<String> al = new ArrayList<String>();
    private ArrayList<String> keys = new ArrayList<>();
    HashMap<String, String > map;
    String val1, val2, val3, val4,val5;

    //COPIED
//    private ProgressDialog m_ProgressDialog = null;
    private ArrayList<User> al = new ArrayList<User>();
    //    private OrderAdapter m_adapter;
    private Runnable viewUsers;
    private ArrayAdapter<User> adpt;
    //TextView t1,t2,t3,t4;
    private CheckBox cb;

    //NEW
// Creating RecyclerView.
    RecyclerView recyclerView;

    // Creating RecyclerView.Adapter.
    RecyclerView.Adapter adapter ;

    // Creating Progress dialog
    ProgressDialog progressDialog;

    // Creating List of ImageUploadInfo class.
    List<User> list = new ArrayList<>();
    User o1;

    public Pollution() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbref = FirebaseDatabase.getInstance().getReference();
//        lv = (ListView) findViewById(R.id.lv);


        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                map = (HashMap<String, String>) dataSnapshot.getValue();

                o1 = new User();
                val1 = (String) map.get("Date");
                val2 = (String) map.get("Location");
                val3 = (String) map.get("Prob");
                val4 = (String) map.get("Status");
                val5=(String)map.get("Image");

                o1.setDate(val1);
                o1.setLocation(val2);
                o1.setProb(val3);
                o1.setStatus(val4);
                o1.setImages(val5);
                list.add(o1);
                adapter.notifyDataSetChanged();
                String key = dataSnapshot.getKey();
                keys.add(key);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                map = (HashMap<String, String>) dataSnapshot.getValue();

                val4 = (String) map.get("Status");
                String key = dataSnapshot.getKey();
                int index = keys.indexOf(key);
                User o=list.get(index);
                o.setStatus(val4);
                list.set(index,o);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.polution_fragment, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(Pollution.this));
        //adapter = new Adapter(getApplicationContext(), list);
        recyclerView.setAdapter(adapter);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        Adapter adapter = new Adapter(new User[o1]);
        rv.setAdapter(adapter);
        //MyAdapter adapter2 = new MyAdapter(new String[]{"Example", "Example Two", "Example Three", "Example Four", "Example Five" , "Example Six" , "Example Seven","Example eight" });

//        adapter.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(int itemId) {
//                Intent intent=new Intent(getActivity().getApplication(),HowMuchWorkDone.class);
//                startActivity(intent);
//            }
//        });


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }

}