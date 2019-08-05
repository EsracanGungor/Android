package com.esragungor.landmarkbook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView tv_landmarkname;
    ImageView iv_landmark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

    iv_landmark=findViewById(R.id.iv_landmark);
    tv_landmarkname=findViewById(R.id.tv_name);

        Intent i=getIntent();
        String name=i.getStringExtra("name");

        tv_landmarkname.setText(name);

        Globals globals=Globals.getInstance();
        Bitmap bitmap= globals.getData();

        iv_landmark.setImageBitmap(bitmap);
    }
}
