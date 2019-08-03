package com.esragungor.biletalma.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.esragungor.biletalma.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText et_kullaniciAdi;
    private EditText et_sifre;
    private Button btn_girisYap;
    private Button btn_kayitOl;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_kullaniciAdi = findViewById(R.id.et_main_kullaniciadi);
        et_sifre = findViewById(R.id.et_main_sifre);
        btn_girisYap = findViewById(R.id.btn_main_giris);
        btn_kayitOl=findViewById(R.id.btn_main_kayitol);

        btn_girisYap.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String username = et_kullaniciAdi.getText().toString().trim();

        String password = et_sifre.getText().toString();
        System.out.println(username + " " + password);
    mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
           Intent i=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(i);
        finish();
        }
        else{

            System.out.println("Failed");
        }
    }
});

        }
    });
        mAuth=FirebaseAuth.getInstance();


        btn_kayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
