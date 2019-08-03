package com.esragungor.turkcelllogin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {
private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView=(ListView)findViewById(R.id.listView);
        final ArrayList arrayList=new ArrayList();

       ArrayList arrayList1=new ArrayList();
      /* arrayList.add("Türkiye");
       arrayList.add("Almanya");
       arrayList.add("Fransa");
        arrayList.add("Japonya");
        arrayList.add("Belçika");*/
        Locale trLocale=Locale.forLanguageTag("tr-TR");
        String [] locales=trLocale.getISOCountries();
        for(String countryCode:locales){
            Locale obj=new Locale("tr-TR",countryCode);
            arrayList.add(obj.getDisplayCountry(trLocale));
        }

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
           String ulkeAdi=arrayList.get(position).toString();
                builder.setMessage(ulkeAdi+"sectiniz.");

                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }
}
