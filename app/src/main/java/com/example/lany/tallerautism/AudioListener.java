package com.example.lany.tallerautism;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import java.io.File;
import java.io.IOException;

/**
 * Created by ju on 12/04/2018.
 */

public class AudioListener implements View.OnTouchListener {
    static final String LOG_TAG = "Grabadora";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    int contador=0;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            grabar();
            contador ++;
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

            try{
                detenerGrabacion();

            }catch(RuntimeException stopException) {

            }

        }
        return false;
    }


   String fichero = Environment.getExternalStorageDirectory().getAbsolutePath()
           + "/" + contador + ".3gp";

    public void grabar(){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setOutputFile(fichero);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();
    }

    public void detenerGrabacion() {
        mediaRecorder.stop();
        mediaRecorder.release();

    }

    public void reproducir(View view) {

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(String.valueOf(fichero));
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {

            Log.e(LOG_TAG, "Fallo en reproducción");

        }

    }
}
