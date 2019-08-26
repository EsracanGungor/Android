package com.esragungor.instagramclonefirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signinActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText et_email;
    EditText et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);

        FirebaseUser user=mAuth.getCurrentUser();
        if (user!=null){
            Intent i = new Intent(getApplicationContext(),FeedActivity.class);
            startActivity(i);
        }
    }


    public void signIn(View view){
        mAuth.signInWithEmailAndPassword(et_email.getText().toString(),et_password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent i = new Intent(getApplicationContext(),FeedActivity.class);
                    startActivity(i);
                }
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(signinActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });


    }
    public void signUp(View view){
        mAuth.createUserWithEmailAndPassword(et_email.getText().toString(),et_password.getText().toString())
.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
            Toast.makeText(signinActivity.this, "User Created!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(),FeedActivity.class);
            startActivity(i);
        }
    }
}).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(signinActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
