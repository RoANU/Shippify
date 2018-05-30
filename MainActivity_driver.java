package com.example.currentplacedetailsonmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import static android.R.attr.x;

public class MainActivity_driver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_driver);
        final RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("activeDriver");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int Dcount= (int) dataSnapshot.getChildrenCount();
                Iterable<DataSnapshot> iterable=dataSnapshot.getChildren();
                Log.e("recycler1", String.valueOf(Dcount));

                String Fname[]=new String[ Dcount];
                String Fphone[]= new String[Dcount];
               String Frating[]=new String[Dcount];
                int i=0;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Fname[i]= (String) postSnapshot.child("Name").getValue();
                    Frating[i]= (String) postSnapshot.child("Rating").getValue();
                   Fphone[i]= (String) postSnapshot.child("Phone").getValue();
                    Log.e("NAme_show",Fname[i]);
                   Log.e("PHONE_show", Frating[i]);
                  Log.e("Rating", Fphone[i]);
                    i++;
                }

             Log.e("recycler","msg");
             recyclerView.setAdapter(new driver_adapter(Fname,Fphone,Frating,Dcount,MainActivity_driver.this));

                /*     final String[] s = new String[1];
                for(Iterator<String> i = mylist.iterator();i.hasNext();)
                {
                    child=database.getReference(i.next());
                    child.addValueEventListener(new ValueEventListener() {
                        HashMap<String ,String > Name=new HashMap<String, String>();

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Name= (HashMap<String, String>) dataSnapshot.getValue(Map.class);
                           s[0] =Name.get("Name");
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });x++;
                }*/

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
