package com.esragungor.instagramclonefirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FeedActivity extends AppCompatActivity {
    ListView lv_feed;
    PostClass adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    ArrayList<String> useremailFromFB;
    ArrayList<String> usercommentFromFB;
    ArrayList<String> userimageFromFB;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_post,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.addPost){
            Intent i = new Intent(getApplicationContext(),UploadActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        lv_feed=findViewById(R.id.lv_feed);
        useremailFromFB=new ArrayList<String>();
        usercommentFromFB=new ArrayList<String>();
        userimageFromFB=new ArrayList<String>();

        firebaseDatabase =FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference();

        adapter=new PostClass(useremailFromFB,usercommentFromFB,userimageFromFB,this);
        lv_feed.setAdapter(adapter);

        getDataFromFirebase();
    }

      public  void getDataFromFirebase(){
        DatabaseReference newReference=firebaseDatabase.getReference("Posts");
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

             /*   System.out.println("FBV children: " + dataSnapshot.getChildren() );
                System.out.println("FBV key: " + dataSnapshot.getKey() );
                System.out.println("FBV value: " + dataSnapshot.getValue() );
                System.out.println("FBV priority: " + dataSnapshot.getPriority() );*/

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    //System.out.println("FBV ds value: " + ds.getValue());

                    HashMap<String, String> hashMap = (HashMap<String, String>) ds.getValue();

                    //System.out.println("FBV useremail:" + hashMap.get("useremail"));

                    useremailFromFB.add(hashMap.get("useremail"));
                    usercommentFromFB.add(hashMap.get("usercomment"));
                    userimageFromFB.add(hashMap.get("downloadurl"));
                    adapter.notifyDataSetChanged();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
      }







}
