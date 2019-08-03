package com.esragungor.biletalma.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.esragungor.biletalma.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {
    Button btn_foto_degistir;
    TextView tv_mail;
    private final static int PICK_PHOTO_CODE = 1;
    ImageView iv_profile;
    private static final String FIREBASE_DIRECTORY = "/UserProfilePhotos/";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btn_foto_degistir = view.findViewById(R.id.btn_profil_degis);
        tv_mail = view.findViewById(R.id.tv_profile_email);
        tv_mail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        iv_profile = view.findViewById(R.id.profile_image);

        btn_foto_degistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, PICK_PHOTO_CODE);

            }
        });
        DowloadImage(FIREBASE_DIRECTORY + FirebaseAuth.getInstance().getCurrentUser().getUid()+"-pp.jpg");
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_CODE)
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                System.out.println(selectedImage.getPath() + ".jpg");
                UploadImage(selectedImage,"/UserProfilePhotos/"+
                        FirebaseAuth.getInstance().getCurrentUser().getUid() + "-pp.jpg");
            }
    }

    private void UploadImage(final Uri filepath, String photoName) {
        if (filepath != null) {
            final ProgressDialog p = new ProgressDialog(getActivity());
            p.setTitle("Yükleniyor...");
            p.show();
            StorageReference ref = FirebaseStorage.getInstance().getReference(photoName);

            ref.putFile(filepath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        ChangeUserImage(filepath);
                    } else {
                        System.out.println("BITTI:" + task.getException());
                    }
                    p.dismiss();
                }


            });

        }
    }

    private void ChangeUserImage(Uri imageUrl) {
        Glide.with(this).load(imageUrl).into(iv_profile);
    }

    public void DowloadImage(String filepath) {
        if (filepath != null) {
            final ProgressDialog p = new ProgressDialog(getActivity());
            p.setTitle("Yükleniyor...");
            p.show();
            System.out.println(filepath);
            StorageReference ref = FirebaseStorage.getInstance().getReference(filepath);
            Task<Uri> url = ref.getDownloadUrl();
            url.addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        ChangeUserImage(task.getResult());
                        System.out.println("url:" + task.getResult());
                    }
                    p.dismiss();
                }
            });
        }
    }


}