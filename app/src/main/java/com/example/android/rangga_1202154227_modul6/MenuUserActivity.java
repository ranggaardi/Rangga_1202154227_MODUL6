package com.example.android.rangga_1202154227_modul6;

import android.support.v4.app.Fragment;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuUserActivity extends Fragment{

    DatabaseReference ref;
    AdapterP adapter; ArrayList<Database> list;
    RecyclerView rc;

    public MenuUserActivity() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.menu_user, container, false);
        ref = FirebaseDatabase.getInstance().getReference().child("post");
        list = new ArrayList<>();
        adapter = new AdapterP(list, this.getContext());
        rc = v.findViewById(R.id.view);


        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rc.setAdapter(adapter);


        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    Database cur = data.getValue(Database.class);
                    if(cur.getUser().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        cur.setKey(data.getKey());
                        list.add(cur);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;
    }
}
