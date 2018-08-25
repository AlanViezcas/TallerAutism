package com.example.lany.tallerautism;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.File;
import java.io.IOException;

public class GrabaAudio extends AppCompatActivity  {

    static final String LOG_TAG = "Grabadora";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    ImageButton bGrabar;
    int contador=0;

     static String fichero = Environment.getExternalStorageDirectory().getAbsolutePath()+"/audio.3gp";

    //String root = Environment.getExternalStorageDirectory().toString();
    //File carpeta = new File(root + "/TallerAutism");

   // String foto = contador+".3gp";
    //final File file = new File(carpeta, foto);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graba_audio);
       bGrabar = (ImageButton) findViewById(R.id.imgbGrabar);
       bGrabar.setOnTouchListener(new AudioListener());
    }




}
