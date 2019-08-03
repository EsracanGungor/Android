package com.esragungor.turkcellornek2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

private TextView tv_toplanan,tv_toplayan,tv_sonuc;
private SeekBar sbar_toplayan,sbar_toplanan;
private int toplanan,toplayan,sonuc;//otomatikman 0 atanır
private Button btn_hesapla;
ArrayList<String> gecmisListesi= new ArrayList<>();
ArrayAdapter gecmisListeAdapter;
private ListView lv_gecmis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R .layout.activity_main);
        tv_toplanan=findViewById(R.id.tv_main_toplanan);
        tv_toplayan=findViewById(R.id.tv_main_toplayan);
        tv_sonuc=findViewById(R.id.tv_main_sonuc);

        sbar_toplanan=findViewById(R.id.sbar_main_toplanan);
        sbar_toplayan=findViewById(R.id.sbar_main_toplayan);

        btn_hesapla=findViewById(R.id.btn_main_hesapla);
        lv_gecmis=findViewById(R.id.lv_main_gecmishesaplama);

        sbar_toplanan.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                toplanan=progress;
                tv_toplanan.setText(String.valueOf(progress));//progress 0-500 arası
               sonuc=toplanan+toplayan; //direk toplamayı yapar
                tv_sonuc.setText(String.valueOf(sonuc));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
        sbar_toplayan.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              toplayan=progress;
                tv_toplayan.setText(String.valueOf(progress));//progress 0-500 arası
                sonuc=toplanan+toplayan; //direk oynattıkca toplama yapıyor
                tv_sonuc.setText(String.valueOf(sonuc));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btn_hesapla.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        sonuc=toplanan+toplayan;
        tv_sonuc.setText(String.valueOf(sonuc));

        AdapteraItemEkle(String.valueOf(toplanan),String.valueOf(toplayan),String.valueOf(sonuc));
    }
});
AdapteriYukle();
lv_gecmis.setAdapter(gecmisListeAdapter);
}
    private void AdapteraItemEkle(String toplanan,String toplayan,String sonuc) {
        gecmisListesi.add(toplanan+"+"+toplayan+"sonucu:"+sonuc);
        gecmisListeAdapter.notifyDataSetChanged();

    }

    private void AdapteriYukle(){
    gecmisListeAdapter=new ArrayAdapter<String>(MainActivity.this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            gecmisListesi );

    }

}
