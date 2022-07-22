package com.example.physicsshortnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class pdfview extends AppCompatActivity {
    PDFView pdfView;
    static ProgressBar progressBar;
    String[] pdfUrl=new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        progressBar=findViewById(R.id.progressBar);
        int pos = getIntent().getIntExtra("pos", 0);
        pdfView = findViewById(R.id.pdfView);
        pdfUrl[0]="https://firebasestorage.googleapis.com/v0/b/ebook-6de74.appspot.com/o/twain_ghost_story.pdf?alt=media&token=cca8489b-e2dd-441c-9f99-d607bbda25c1";
        switch (pos) {
            case 0:
                new RetrivePdf().execute(pdfUrl[pos]);
//                progressBar.setVisibility(View.GONE);
                break;
            default:
                new RetrivePdf().execute(pdfUrl[0]);
//                progressBar.setVisibility(View.GONE);
        }
        progressBar.setVisibility(View.GONE);
    }

    class RetrivePdf extends AsyncTask<String, Void, InputStream> {
  @Override
        protected InputStream doInBackground(String... strings)
        {
            InputStream inputStream=null;
            try{
                URL url =new URL (strings[0]);
                HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                if(urlConnection.getResponseCode()==200){
                    inputStream =new BufferedInputStream(urlConnection.getInputStream());

                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
                Toast.makeText(pdfview.this,"Not Uploded Yet",Toast.LENGTH_SHORT).show();
                return null;
            }
//            progressBar.setVisibility(View.GONE);
            pdfView.fromStream(inputStream).load();
            return inputStream;
        }
    }

}