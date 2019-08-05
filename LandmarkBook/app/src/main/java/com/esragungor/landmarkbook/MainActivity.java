package com.esragungor.landmarkbook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv_landmark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_landmark=findViewById(R.id.lv_land);

       final ArrayList<String> LandmarkNames=new ArrayList<String>();
        LandmarkNames.add("Pisa Kulesi");
        LandmarkNames.add("Eyfel Kulesi");
        LandmarkNames.add("Dolmabahçe Sarayı");
        LandmarkNames.add("Tac Mahal");
        LandmarkNames.add("Haliskarnas Mozolesi");

        Bitmap pisa=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.pisa);
        Bitmap eyfel=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.eyfel);
        Bitmap dolmabahce=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.dolmabahce);
        Bitmap tacmahal=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.tacmahal);
        Bitmap halikarnas=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.halikarnas);

       final ArrayList<Bitmap> LandmarkImages=new ArrayList<Bitmap>();
        LandmarkImages.add(pisa);
        LandmarkImages.add(eyfel);
        LandmarkImages.add(dolmabahce);
        LandmarkImages.add(tacmahal);
        LandmarkImages.add(halikarnas);

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,LandmarkNames);

        lv_landmark.setAdapter(arrayAdapter);
        lv_landmark.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent=new Intent(getApplicationContext(),DetailActivity.class);
        intent.putExtra("name",LandmarkNames.get(position));

        Bitmap bitmap=LandmarkImages.get(position);

        Globals globals=Globals.getInstance();
        globals.setData(bitmap);


        startActivity(intent);
    }
});

    }
}
