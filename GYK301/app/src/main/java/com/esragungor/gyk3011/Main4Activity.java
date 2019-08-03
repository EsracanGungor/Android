package com.esragungor.gyk3011;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {
Button btn_fotografvideoekle,btn_seskayit,btn_harita,btn_websayfasi,
        btn_sms,btn_arama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        btn_fotografvideoekle=findViewById(R.id.btn_main_fotografvideoekle);
        btn_seskayit=findViewById(R.id.btn_main_seskayit);
        btn_harita=findViewById(R.id.btn_main_harita);
        btn_websayfasi=findViewById(R.id.btn_main_websayfasi);
        btn_sms=findViewById(R.id.btn_main_sms);
        btn_arama=findViewById(R.id.btn_main_arama);
        btn_websayfasi=findViewById(R.id.btn_main_websayfasi);

    }

    @Override
    public void onClick(View v) {
        if (v==btn_fotografvideoekle){
            Intent i=new Intent(Main4Activity.this,Main3Activity.class);
            startActivity(i);
        }
    else if (v==btn_harita){
            Intent i=new Intent(Main4Activity.this,Main2Activity.class);
            startActivity(i);
        }
        else if(v==btn_seskayit){
            Intent i=new Intent(Main4Activity.this,MainActivity.class);
            startActivity(i);
        }
        else if(v==btn_sms){
            Intent i=new Intent(Main4Activity.this,Smsgonderme.class);
            startActivity(i);
        }
        else if(v==btn_arama){
            Intent i=new Intent(Main4Activity.this,Aramayap.class);
            startActivity(i);
        }
        else if(v==btn_websayfasi) {
            Intent i=new Intent(Main4Activity.this,WebActivity.class);
            i.putExtra("URL","https://gelecegiyazanlar.turkcell.com.tr/");
            startActivity(i);
        }
    }
    }

