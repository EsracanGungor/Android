package com.esragungor.biletalma.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.esragungor.biletalma.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends Activity {

    private EditText et_kullaniciAdi;
    private EditText et_sifre;
    private EditText et_email;
    private Button btn_kayit;
    private CheckBox cb_sözles;
    String message;

private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        et_kullaniciAdi = (EditText) findViewById(R.id.et_register_ad);
        et_sifre = (EditText) findViewById(R.id.et_register_sifre);
        et_email = (EditText) findViewById(R.id.et_register_email);
        cb_sözles = (CheckBox) findViewById(R.id.cb_sözlesme);
        btn_kayit = (Button) findViewById(R.id.btn_register_kayitol);

        mAuth=FirebaseAuth.getInstance();


        btn_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = et_kullaniciAdi.getText().toString();
                final String password = et_sifre.getText().toString();
                final String mail = et_email.getText().toString();
                if (checkForm(name, password, mail)) {

                    //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                mAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"Kayıt işlemi başarılı",Toast.LENGTH_LONG).show();
                            FirebaseUser user=mAuth.getCurrentUser();

                        }
                        else{
                            System.out.println("başarısız kayıt:"+task.getException());
                            Toast.makeText(RegisterActivity.this,"Kayıt işlemi başarısız",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                } else {
                    //todo:toast mesajı çıkar
                    Toast.makeText(getApplicationContext(),
                            "Bütün alanları doldurunuz", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    public Boolean checkForm(String... data) {
        for (String str : data) {
            if (str == null || str.trim().equals("")) {
                return false;
            }
        }
        return true;
    }

}
