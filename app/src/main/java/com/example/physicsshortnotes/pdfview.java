package com.example.physicsshortnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
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
    String[] pdfUrl=new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        int pos = getIntent().getIntExtra("pos", 0);
        pdfView = findViewById(R.id.pdfView);
        pdfUrl[0]="https://unec.edu.az/application/uploads/2014/12/pdf-sample.pdf";
        switch (pos) {
            case 0:
                new RetrivePdf().execute(pdfUrl[pos]);
                break;
            default:
                new RetrivePdf().execute(pdfUrl[0]);
        }

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
            pdfView.fromStream(inputStream).load();
            return inputStream;
        }





    }
}