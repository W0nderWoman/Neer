package com.google.sample.cloudvision;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserTimeline extends AppCompatActivity {

    private DatabaseReference dbref;
    ListView lv;
    private ArrayList<String> keys = new ArrayList<>();
    HashMap<String, Object> map;
    String val1, val2, val3, val4,val5;
    private ArrayList<User> al = new ArrayList<User>();
    private Runnable viewUsers;
    private ArrayAdapter<User> adpt;
    private CheckBox cb;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter ;
    List<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_timeline);
        dbref = FirebaseDatabase.getInstance().getReference();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(UserTimeline.this));
        adapter = new Adapter(getApplicationContext(), list);
        recyclerView.setAdapter(adapter);

        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                map = (HashMap<String, Object>) dataSnapshot.getValue();
                User o1 = new User();
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
                map = (HashMap<String, Object>) dataSnapshot.getValue();

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
    };
}
