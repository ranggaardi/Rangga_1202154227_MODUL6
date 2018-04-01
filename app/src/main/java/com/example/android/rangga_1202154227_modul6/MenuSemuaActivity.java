package com.example.android.rangga_1202154227_modul6;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuSemuaActivity extends Fragment {
    RecyclerView rc;
    DatabaseReference dataref;
    ArrayList<Database> list;
    AdapterP adapterPost;

    public MenuSemuaActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.menu_semua, container, false);
        rc = v.findViewById(R.id.vw);
        list = new ArrayList<>();
        dataref = FirebaseDatabase.getInstance().getReference().child("post");
        adapterPost = new AdapterP(list, this.getContext());


        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rc.setAdapter(adapterPost);


        dataref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Database post = data.getValue(Database.class);
                    post.key = data.getKey();
                    list.add(post);
                    adapterPost.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;
    }

}

