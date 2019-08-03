package com.esragungor.gyk3011;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Aramayap extends AppCompatActivity {
EditText et_telefon;
Button btn_arama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aramayap);
        et_telefon=findViewById(R.id.et_aramabaslat_telefonno);
        btn_arama=findViewById(R.id.btn_arama_yap);
        btn_arama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telefonNumarasi=et_telefon.getText().toString();
                AramaYap(telefonNumarasi);
            }
        });
    }

     void AramaYap(String telefonNo) {
        //uri semayı belirler
         Intent i=new Intent(Intent.ACTION_DIAL);
         i.setData(Uri.parse("tel:"+telefonNo) );
         if(i.resolveActivity(getPackageManager())!=null){
             startActivity(i);
         }else{
             Toast.makeText(Aramayap.this,
                     "Bu islemi gerceklestirecek uygulama yok",Toast.LENGTH_LONG).show();

         }
    }
}
