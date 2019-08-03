package com.esragungor.gyk3011;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Smsgonderme extends AppCompatActivity {
EditText et_mesaj,et_telefonNo;
Button btn_sms_gonder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsgonderme);
        et_mesaj=findViewById(R.id.et_sms_mesaj);
        et_telefonNo=findViewById(R.id.et_sms_telefon);
        btn_sms_gonder=findViewById(R.id.btn_sms_gonder);
        btn_sms_gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mesaj=et_mesaj.getText().toString();
                String telefonNo=et_telefonNo.getText().toString();
                Smsgonder(mesaj,telefonNo);
            }
        });
    }

     void Smsgonder(String mesaj, String telefonNo) {
         Uri uri=Uri.parse("smsto:"+telefonNo);//uri semayı belirler
         Intent intent=new Intent(Intent.ACTION_SENDTO,uri);
         intent.putExtra("sms_body",mesaj);
         startActivity(intent);


    }

}
