package com.esragungor.instagramclonefirebase;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class UploadActivity extends AppCompatActivity {
    ImageView postImage;
    EditText postComment;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Uri selectedImage;
    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        postImage=findViewById(R.id.iv_post_image);
        postComment=findViewById(R.id.et_post_comment);
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference();
        mAuth=FirebaseAuth.getInstance();
        mStorageRef= FirebaseStorage.getInstance().getReference();


    }

    public void upload(View view){
        UUID uuıd=UUID.randomUUID();
        final String imageName="images/"+uuıd+".jpg";

    StorageReference storageReference=mStorageRef.child(imageName);
    storageReference.putFile(selectedImage).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
           StorageReference newReference=FirebaseStorage.getInstance().getReference(imageName);
           newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
               @Override
               public void onSuccess(Uri uri) {
                   String downloadUrL=uri.toString();
                   FirebaseUser user=mAuth.getCurrentUser();
                   String userEmail=user.getEmail();
                   String userComment=postComment.getText().toString();

                   UUID uuıd1=UUID.randomUUID();
                   final String uuidString=uuıd1.toString();
                   myRef.child("Posts").child(uuidString).child("useremail").setValue(userEmail);
                   myRef.child("Posts").child(uuidString).child("usercomment").setValue(userComment);
                   myRef.child("Posts").child(uuidString).child("downloadurl").setValue(downloadUrL);

                   Toast.makeText(UploadActivity.this, "Post Shared!", Toast.LENGTH_SHORT).show();
                   Intent i = new Intent(getApplicationContext(),FeedActivity.class);
                   startActivity(i);



               }
           });




        }
    }).addOnFailureListener(this, new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(UploadActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

        }
    });
    }
    public void selectImage(View view){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

       }else{
            Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,2);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       if (requestCode==1&&grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
           Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
           startActivityForResult(intent,2);
       }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      try{
        if (requestCode==2&&resultCode==RESULT_OK&&data!=null){
          selectedImage=data.getData();
          Bitmap bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
          postImage.setImageBitmap(bitmap);

      }}catch (Exception e){
          e.printStackTrace();
      }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
