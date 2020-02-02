package com.esragungor.simpsonbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ImageView imageView = findViewById(R.id.iv_details);
        TextView nameText = findViewById(R.id.tv_detail_name);
        TextView jobText = findViewById(R.id.tv_detail_job);

        Intent intent=getIntent();
        Simpson selectedSimpson=(Simpson) intent.getSerializableExtra("selectedSimpson");

        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),selectedSimpson.getPictureInteger());
        imageView.setImageBitmap(bitmap);

        nameText.setText(selectedSimpson.getName());
        jobText.setText(selectedSimpson.getJob());
    }
}
