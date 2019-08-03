package com.esragungor.gyk3011;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class Main3Activity extends AppCompatActivity {
    private static final int FOTOGRAF_ISTEKKOD=99;
    private static final int VIDEO_ISTEKKOD=123;
Button btn_fotografcek,btn_videocek;
ImageView iv_fotograf;
VideoView iv_video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btn_fotografcek=findViewById(R.id.btn_main_fotograf);
        btn_videocek=findViewById(R.id.btn_main_video);
        iv_fotograf=findViewById(R.id.iv_camera_fotograf);
        iv_video=findViewById(R.id.videoview_camera_video);
        btn_fotografcek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FotografCek();
            }
        });
        btn_videocek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              VideoCek();
            }
        });
    }
     void FotografCek(){
        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,99);//99 istege bagli herhangi bir sayi olabilir
    }
    void VideoCek(){
Intent i=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
startActivityForResult(i,123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode!=RESULT_OK)return;
        if(requestCode==FOTOGRAF_ISTEKKOD){
            if(data!=null) {
                Bundle bundle = data.getExtras();
                iv_fotograf.setImageBitmap((Bitmap) bundle.get("data"));
            }
        }
        else if(requestCode==VIDEO_ISTEKKOD){
if(data!=null){

    iv_video.setVideoURI(data.getData());
    iv_video.setMediaController(new MediaController(Main3Activity.this));
    iv_video.requestFocus();
    iv_video.start();

}
            }
        }
        }

