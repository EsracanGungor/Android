package com.esragungor.gyk3011;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
Button btn_haritaac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn_haritaac=findViewById(R.id.btn_harita);
        btn_haritaac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri istLoc=Uri.parse("geo:41.0138400,28.9496600");
                HaritayiAc(istLoc);
            }
        });
    }

    private void HaritayiAc(Uri uriLocation) {
        Intent i=new Intent(Intent.ACTION_VIEW);
        i.setData(uriLocation);
        if(i.resolveActivity(getPackageManager())!=null){
            startActivity(i);
        }
    }
}
