package com.esragungor.gyk3011;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebActivity extends AppCompatActivity {
WebView webView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView1=findViewById(R.id.webview_web);

String gelenURL=getIntent().getStringExtra("URL");

WebSayfasiniAc(gelenURL);

    }

    private void WebSayfasiniAc(String url) {
    webView1.getSettings().setJavaScriptEnabled(true);//site ici javascriptler duzgun islenmez
        webView1.loadUrl(url);
        final ProgressDialog progressDialog=ProgressDialog.show(this,"Lütfen bekleyin","Sayfa Yükleniyor",false);

webView1.setWebViewClient(new WebViewClient(){
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        Toast.makeText(getApplicationContext(),"Sayfa yüklendi",Toast.LENGTH_LONG).show();
    progressDialog.dismiss();
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        Toast.makeText(getApplicationContext(),"Sayfa yüklenemedi",Toast.LENGTH_LONG).show();
    progressDialog.dismiss();}
});

    }
}
